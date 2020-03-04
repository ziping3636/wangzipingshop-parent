<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<!-- Button trigger modal -->
	<button type="button" class="btn btn-primary" data-toggle="modal"
		data-target="#exampleModalScrollable">添加</button>
</div>
<table class="table">
	<tr>
		<th>ID</th>
		<th>名称</th>
		<th>详情</th>
	</tr>
	<c:forEach items="${info.list }" var="spec">
		<tr>
			<td>${spec.id }</td>
			<td>${spec.specName }</td>
			<td><c:forEach items="${spec.options }" var="options"
					varStatus="index">
						&nbsp;&nbsp;&nbsp;&nbsp;${options.optionName }
					</c:forEach></td>
		</tr>
	</c:forEach>
</table>
<nav aria-label="...">
	<ul class="pagination">
		<li class="page-item disabled"><button onclick="goPage(1)"
				class="btn btn-light">首页</button></li>
		<li class="page-item disabled"><button
				onclick="goPage(${info.prePage == 0 ? pageNum : info.prePage})"
				class="btn btn-light">Previous</button></li>

		<c:forEach begin="${info.pageNum - 2 > 1 ? info.prePage - 2 : 1}"
			end="${info.pageNum + 2 > info.pages ? info.pages : info.pageNum + 2}"
			varStatus="index">
			<c:if test="${info.pageNum != index.index}">
				<li class="page-item"><button type="button"
						class="btn btn-light" onclick="goPage(${index.index})">${index.index}</button></li>
			</c:if>
			<c:if test="${info.pageNum == index.index}">
				<li class="page-item"><button type="button"
						class="btn btn-primary" onclick="goPage(${index.index})">${index.index}</button></li>
			</c:if>
		</c:forEach>

		<li class="page-item"><button type="button" class="btn btn-light"
				onclick="goPage(${info.pageNum == info.pages ? info.pages : info.pageNum +1})">Next</button></li>
		<li class="page-item"><button type="button" class="btn btn-light"
				onclick="goPage(${info.pages})">尾页</button></li>
	</ul>
</nav>



<!-- Modal -->
<div class="modal fade" id="exampleModalScrollable" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalScrollableTitle"
	aria-hidden="true">
	<div class="modal-dialog modal-dialog-scrollable" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalScrollableTitle">添加规格</h5>
				<button type="button" onclick="addProp()" class="btn btn-info">添加属性</button>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form action="" id="addSpec" method="post">
					<div class="form-group">
						<label for="specName">规格名称</label> <input type="text"
							class="form-control" id="specName" name="specName"
							aria-describedby="specName"> <small id="emailHelp"
							class="form-text text-muted">请输入规格名称</small>
					</div>
					<div class="form-group">
						<label for="inputAddress">属性值</label> <input type="text"
							class="form-control" name="options[0].optionName"
							id="inputAddress" placeholder="1234 Main St">
						<button type="button" onclick="$(this).parent().remove()" class="btn btn-info">移除</button>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button type="button" onclick="commitSpec()" class="btn btn-primary">提交</button>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var addIndex = 1;
		
	function goPage(pageNum) {
		var url = "/spec/list?pageNum="+pageNum;
		$("#main").load(url);
	}
	
	function commitSpec() {
		var formData = new FormData($("#addSpec")[0]);
		$.ajax({
			url:"/spec/addSpec",
			dataType:"json",
			data:formData,
			// 让 jQuery 不要在提交数据前进行处理
			processData : false,
			// 提交的数据不能加消息头
			contentType : false,
			// 提交方式
			type:"post",
			success:function(obj) {
				if (obj) {
					$('#exampleModalScrollable').modal('hide')
					return;
				}
			}
		})
	}
	
	//隐藏模态框时刷新页面
	 $('#exampleModalScrollable').on('hidden.bs.modal', function(e) {
		// do something...
		$('input').val("");
		refreshPage();
	})
	
	function addProp() {
		$("#addSpec").append('<div class="form-group">'+
				'<label for="inputAddress">属性值</label> <input type="text"'+
					'class="form-control" name="options['+addIndex+'].optionName"'+
					'id="inputAddress" placeholder="1234 Main St">'+
				'<button type="button" onclick="$(this).parent().remove()" class="btn btn-info">移除</button>'+
			'</div>');
			addIndex++;
	}
</script>
