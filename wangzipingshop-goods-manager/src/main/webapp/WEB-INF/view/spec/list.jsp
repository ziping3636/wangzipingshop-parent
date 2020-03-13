<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="form-inline" style="margin-top: 20px">
	<input type="text" class="form-control" name="name" id="queryName"
		value="${name }" autocomplete="off">
	<!-- Button trigger modal -->
	<button type="button" class="btn btn-primary" onclick="query()">查询</button>
	&nbsp;&nbsp;&nbsp;&nbsp;
	<!-- Button trigger modal -->
	<button type="button" class="btn btn-primary" data-toggle="modal"
		data-target="#exampleModalScrollable">添加</button>
	&nbsp;&nbsp;&nbsp;&nbsp;
	<!-- Button trigger modal -->
	<button type="button" class="btn btn-primary" onclick="delBatch()">批量删除</button>
</div>
<table class="table" style="margin-top: 10px">
	<tr>
		<th><input type="checkbox" onchange="selectAll()" id="allSel">ID
			<button type="button" class="btn btn-sm btn-info" onclick="selAll(1)">全选</button>
			<button type="button" class="btn btn-sm btn-info" onclick="selAll(2)">全不选</button>
			<button type="button" class="btn btn-sm btn-info" onclick="selAll(3)">反选</button>
		</th>
		<th>名称</th>
		<th>详情</th>
		<th>操作</th>
	</tr>
	<c:forEach items="${info.list }" var="spec">
		<tr>
			<td><input type="checkbox" name="ids" value="${spec.id }"
				onchange="selectOne()">&nbsp;&nbsp;&nbsp;${spec.id }</td>
			<td>${spec.specName }</td>
			<td width="300PX" height="50px"><c:forEach items="${spec.options }" var="options"
					varStatus="index">
						&nbsp;&nbsp;&nbsp;&nbsp;${options.optionName }
					</c:forEach></td>
			<td>
				<button type="button" class="btn btn-danger"
					onclick="delSpec(${spec.id })">删除</button>
				<button type="button" class="btn btn-warning"
					onclick="openUpdateSec(${spec.id })">修改</button>
			</td>
		</tr>
	</c:forEach>
</table>

<jsp:include page="../common/page.jsp"></jsp:include>


<!-- Modal -->
<div class="modal fade" id="exampleModalScrollable" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalScrollableTitle"
	aria-hidden="true">
	<div class="modal-dialog modal-dialog-scrollable" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalScrollableTitle">添加规格</h5>
				<button type="button" onclick="addProp(addSpec)"
					class="btn btn-info">添加属性</button>
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
						<button type="button" onclick="$(this).parent().remove()"
							class="btn btn-info">移除</button>
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


<!-- Modal -->
<div class="modal fade" id="exampleModalScrollableUpdate" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalScrollableTitle"
	aria-hidden="true">
	<div class="modal-dialog modal-dialog-scrollable" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalScrollableTitle">修改规格</h5>
				<button type="button" onclick="addProp(updateSpec)"
					class="btn btn-info">添加属性</button>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form action="" id="updateSpec" method="post">
					<input type="hidden" name="id" id="upId">
					<div class="form-group">
						<label for="specName">规格名称</label> <input type="text"
							class="form-control" id="upSpecName" name="specName"
							aria-describedby="specName"> <small id="emailHelp"
							class="form-text text-muted">请输入规格名称</small>
					</div>
					<div class="form-group form-group-proper">
						<label for="inputAddress">属性值</label> <input type="text"
							class="form-control" name="options[0].optionName"
							id="inputAddress" placeholder="1234 Main St">
						<button type="button" onclick="$(this).parent().remove()"
							class="btn btn-info">移除</button>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button type="button" onclick="commitUpdateSpec()" class="btn btn-primary">提交</button>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var addIndex = 1;
	
	function commitUpdateSpec() {
		var formData = new FormData($("#updateSpec")[0]);
		$.ajax({
			url : "/spec/updateSpec",
			dataType : "json",
			data : formData,
			// 让 jQuery 不要在提交数据前进行处理
			processData : false,
			// 提交的数据不能加消息头
			contentType : false,
			// 提交方式
			type : "post",
			success : function(obj) {
				if (obj) {
					alert("数据提交成功✔")
					$('#exampleModalScrollableUpdate').modal('hide')
				} else {
					alert("数据提交失败✘")
				}
			}
		})
	}
	
	
	function openUpdateSec(id) {
		
		$(".form-group-proper").each(function() {
			$(this).remove();
		})
		addIndex = 0;
		$("upSpecName").val("")
		
		$.post("/spec/getSpecById", {id:id}, function(data) {
			$("#upId").val(data.id)
			$("#upSpecName").val(data.specName)
			addIndex = 0;
			for (var i = 0; i < data.options.length; i++) {
				var option = data.options[i];
				$("#updateSpec")
				.append(
						'<div class="form-group form-group-proper">'
								+ '<label for="inputAddress">属性值</label><input type="hidden" name="options['+addIndex+'].id" value="'+option.id+'"> <input type="text"'+
					'class="form-control" name="options['+addIndex+'].optionName"'+
					'id="inputAddress" value="'+option.optionName+'" placeholder="1234 Main St">'
								+ '<button type="button" onclick="$(this).parent().remove()" class="btn btn-info">移除</button>'
								+ '</div>');
				addIndex++;
			}
		})
		$("#exampleModalScrollableUpdate").modal("show");
	}
	
	function selectOne() {
		var allSelected = $("[name=ids]").length==$("[name=ids]:checked").length;
		$("#allSel").prop("checked", allSelected);
	}
	
	function selectAll() {
		var checked = $("#allSel").prop("checked")
		$("[name=ids]").each(function(){
				$(this).prop("checked", checked)
			})
	}
	
	function selAll(flag) {
		if (flag == 1) {
			$("[name=ids]").each(function(){
				$(this).prop("checked", true)
			})
		} else if(flag == 2) {
			$("[name=ids]").each(function(){
				$(this).prop("checked", false)
			})
		} else if(flag == 3) {
			$("[name=ids]").each(function(){
				$(this).prop("checked",  !$(this).prop("checked"))
			})
		}
		var allSelected = $("[name=ids]").length==$("[name=ids]:checked").length
		$("#allSel").prop("checked", allSelected);
	}
	
	/**
	*	删除规格
	*/
	function delSpec(id) {
		if (confirm("确定删除该条数据吗?")) {
			$.post("/spec/delSpec", {id:id}, function(obj) {
				if (obj) {
					alert("删除成功✔")
					refresh();
				} else {
					alert("删除失败✘")
				}
			})
		}
	}
	
	function delBatch() {
		// 组织删除的数据
		if ($("[name=ids]:checked").length <= 0) {
			alert("没有数据被选中, 不能删除!")
			return;
		}
		var ids = new Array();
		$("[name=ids]:checked").each(function(){
			ids.push($(this).val());
		})
		
		
		if (confirm("确定删除这些数据吗?")) {
			$.post("/spec/delBatchSpec", {ids:ids}, function(obj) {
				if (obj) {
					alert("删除成功✔")
					refresh();
				} else {
					alert("删除失败✘")
				}
			})
		}
	}
	
	/**
	*	修改规格
	*/
	function update(id) {
		
	}

	/**
	 * 查询
	 */
	function query() {
		var url = "/spec/list?name=" + $("#queryName").val();
		$("#main").load(url);
	}

	/**
	 * 分页
	 */
	function goPage(pageNum) {
		var url = "/spec/list?name="+$("#queryName").val()+"&pageNum=" + pageNum;
		$("#main").load(url);
	}

	function commitSpec() {
		var formData = new FormData($("#addSpec")[0]);
		$.ajax({
			url : "/spec/addSpec",
			dataType : "json",
			data : formData,
			// 让 jQuery 不要在提交数据前进行处理
			processData : false,
			// 提交的数据不能加消息头
			contentType : false,
			// 提交方式
			type : "post",
			success : function(obj) {
				if (obj) {
					alert("数据提交成功✔")
					$('#exampleModalScrollable').modal('hide')
				} else {
					alert("数据提交失败✘")
				}
			}
		})
	}

	/**
	* 隐藏模态框时刷新页面
	*/
	$('#exampleModalScrollableUpdate').on('hidden.bs.modal', function(e) {
		// do something...
		refresh();
	})
	
	/**
	* 隐藏模态框时刷新页面
	*/
	$('#exampleModalScrollable').on('hidden.bs.modal', function(e) {
		// do something...
		refresh();
	})
	
	/**
	* 给模态框增加显示成成功后的事件
	*/
	$('#exampleModalScrollable').on('shown.bs.modal', function(e) {
		// do something...
		resetAddForm()
	})

	function addProp(flag) {
			$(flag)
				.append(
						'<div class="form-group form-group-proper">'
								+ '<label for="inputAddress">属性值</label> <input type="text"'+
					'class="form-control" name="options['+addIndex+'].optionName"'+
					'id="inputAddress" placeholder="1234 Main St">'
								+ '<button type="button" onclick="$(this).parent().remove()" class="btn btn-info">移除</button>'
								+ '</div>');
		addIndex++;
	}
	
	/**
	*	添加窗口复位
	*/
	function resetAddForm() {
		$(".form-group-proper").each(function() {
			$(this).remove();
		})
		addIndex = 1;
		$("#specName").val("");
	}
	
	
	/**
	* 刷新
	*/
	function refresh() {
		var url = "/spec/list?name=${name}&pageNum=${info.pageNum}";
		$("#main").load(url)
	}
</script>
