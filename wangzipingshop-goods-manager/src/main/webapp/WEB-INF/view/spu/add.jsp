<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/resource/css/bootstrap.css" rel="stylesheet">
<link href="/resource/bootstrap-treeview/css/bootstrap-treeview.css"
	rel="stylesheet">
<script type="text/javascript"
	src="/resource/bootstrap-treeview/js/bootstrap-treeview.js"></script>
<div>
	<form id="addSpu">
		<div class="row">
			<div class="col">
				<label for="goodsName">商品名称</label>
				<input type="text" name="goodsName" autocomplete="off" id="goodsName" class="form-control" placeholder="Goods name">
			</div>
		</div>
		<div class="row">
			<div class="col">
				<label for="caption">商品标题</label>
				<input type="text" name="caption" id="caption" class="form-control" placeholder="Caption">
			</div>
		</div>
		<div class="row">
			<div class="col">
				<label for="category">商品分类</label>
				<input type="hidden" name="categoryId" id="categoryId" >
				<input type="text" name="categoryName" id="category" onclick="showTree()" id="category" class="form-control" placeholder="Category name">
			</div>
				<div id="addCategoryTree" style="display: none; margin-left: 15px; position: absolute; z-index: 1010; background-color: white; width: 96%">
				</div>
		</div>
		<div class="row">
			<div class="col">
				<label for="brand">商品品牌</label>
				<select id="brandId" name="brandId" class="form-control form-control-lg">
					<option>Large select</option>
					<c:forEach items="${brand }" var="brand">
						<option value="${brand.id }">${brand.name }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<!-- <div class="row">
			<div class="col">
				<label for="pic">小图</label>
				<div class="custom-file" id="pic">
		  			<input type="file" class="custom-file-input" id="smallPic" name="file">
		  			<label class="custom-file-label" for="smallPic">Choose file</label>
				</div>
			</div>
		</div> -->
		<div class="form-row">
		    <div class="col">
		      <label>小图</label>	
		      <input type="file" name="file" class="form-control-file" id="exampleFormControlFile1">
		    </div>
		</div>
		<div class="row">
			<div class="col">
				<button type="button" class="btn btn-primary" onclick="addGoods()">添加</button>
			</div>
		</div>
	</form>
</div>

<script type="text/javascript">
	
	function addGoods() {
		var formData = new FormData($("#addSpu")[0]);
		$.ajax({
			url : "/spu/addSpu",
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
					var url = "/spu/list";
					$("#main").load(url);
				} else {
					alert("数据提交失败✘")
				}
			}
		})
	}
	
	function initTree() {
		//发送ajax获取树需要的数据
		$.post("/category/treeData", {}, function(treeData) {
			//初始化添加的时候分类的树
			$("#addCategoryTree").treeview({
				data : treeData,
				levels : 2,
				onNodeSelected : function(event, node) {
					if (node.nodes.length == 0) {
						$("#categoryId").val(node.id);
						$("#category").val(node.text);
						$("#addCategoryTree").hide();
					}
				}
			});
	
		}, "json");
	}
	initTree();
	
	function showTree() {
		$("#addCategoryTree").show();
	}
</script>