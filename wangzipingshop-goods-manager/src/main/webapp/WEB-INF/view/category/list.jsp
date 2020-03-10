<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Bootstrap core CSS -->
<link href="/resource/css/bootstrap.css" rel="stylesheet">
<!-- <link
	href="http://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet"> -->
<link href="/resource/bootstrap-treeview/css/bootstrap-treeview.css"
	rel="stylesheet">
<script type="text/javascript"
	src="/resource/bootstrap-treeview/js/bootstrap-treeview.js"></script>
<div class="container-fluid" style="margin-top: 10px">
	<div class="row">
		<div class="col-sm-6" id="catTree"></div>
		<div class="col-sm-6" id="edit">
			<!-- 开始 用于添加 -->
			<form id="addChildInfo">
				<div class="form-group">
					<label for="parentId">上一级id</label> <input type="text"
						class="form-control" name="parentId" disabled="disabled"
						id="parentId" placeholder="上一级id"> <label for="parentName">上一级名称</label>
					<input type="text" class="form-control" disabled="disabled"
						name="parentName" id="parentName" placeholder="上一级名称"> <label
						for="name">名称</label> <input type="text" class="form-control"
						name="name" id="name" placeholder="节点名称" autocomplete="off">
				</div>
				<button type="button" class="btn btn-dark" onclick="addChild()">添加</button>
			</form>
			<!-- 结束 用于添加 -->


			<!-- 开始 用于修改 -->
			<form id="updateChildInfo">
				<div class="form-group">
					<input type="hidden" id="currentChildLength"> <label
						for="currentId">当前id</label> <input type="text"
						class="form-control" name="currentId" disabled="disabled"
						id="currentId" placeholder="当前id"> <label
						for="currentName">当前名称</label> <input type="text"
						class="form-control" name="currentName" id="currentName"
						placeholder="节点名称" autocomplete="off">
				</div>
				<button type="button" class="btn btn-danger" onclick="delNode()">删除</button>
				<button type="button" class="btn btn-primary"
					onclick="updateChild()">修改</button>
			</form>
			<!-- 结束 用于修改 -->
		</div>
	</div>
</div>
<script type="text/javascript">
	function addChild() {
		if ($("#name").val() == null || $("#name").val() == '') {
			alert("未输入节点名称!!")
		} else {

			$.post("/category/addChild", {
				parentId : $("#parentId").val(),
				name : $("#name").val()
			}, function(obj) {
				if (obj) {
					alert("数据提交成功✔")
					refresh();
				} else {
					alert("数据提交失败✘")
				}
			})
		}
	}

	function delNode() {
		if ($("#currentChildLength").val() > 0) {

			alert("该节点还有子节点, 您不能删除!")
			return;
		}
		if (confirm("确定删除该节点吗?")) {
			$.post("/category/delCategory", {
				id : $("#currentId").val()
			}, function(obj) {
				if (obj) {
					alert("节点删除成功✔")
					refresh();
				} else {
					alert("节点删除失败✘")
				}
			})
		}
	}

	function updateChild() {
		$.post("/category/updateCategory", {
			id : $("#currentId").val(),
			name : $("#currentName").val()
		}, function(obj) {
			if (obj) {
				alert("数据修改成功✔")
				refresh();
			} else {
				alert("数据修改失败✘")
			}
		})
	}

	/**
	 * 刷新
	 */
	function refresh() {
		var url = "/category/list";
		$("#main").load(url)
	}

	function initTree() {
		//发送ajax获取树需要的数据
		$.post("/category/treeData", {}, function(treeData) {
			//初始化添加的时候分类的树
			$("#catTree").treeview({
				data : treeData,
				levels : 2,
				onNodeSelected : function(event, node) {
					/* if (node.nodes.length == 0) {
						alert("最末级")
						$("#add_spu_category").val(node.text);
						$("#add_spu_category_id").val(node.id);
						$("#addCategoryTree").hide();
					} else { */
					$("#parentId").val(node.id);
					$("#parentName").val(node.text);
					$("#name").val("");
					$("#currentId").val(node.id);
					$("#currentName").val(node.text);
					$("#currentChildLength").val(node.nodes.length);
					/* } */
				}
			});

		}, "json");
	}

	initTree();
</script>