<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<!-- saved from url=(0047)https://v4.bootcss.com/docs/examples/dashboard/ -->
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Jekyll v3.8.6">
<title>商城</title>

<link rel="canonical"
	href="https://v4.bootcss.com/docs/examples/dashboard/">

<!-- Bootstrap core CSS -->
<link href="/resource/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="/resource/jquery/jquery-3.4.1.js"></script>
<script type="text/javascript"
	src="/resource/bootstrap4/js/bootstrap.js"></script>
	<link href="/resource/css/bootstrap.css" rel="stylesheet">
<link href="/resource/bootstrap-treeview/css/bootstrap-treeview.css"
	rel="stylesheet">
<script type="text/javascript"
	src="/resource/bootstrap-treeview/js/bootstrap-treeview.js"></script>
<!-- Favicons -->
<link rel="apple-touch-icon"
	href="https://v4.bootcss.com/docs/assets/img/favicons/apple-touch-icon.png"
	sizes="180x180">
<link rel="icon"
	href="https://v4.bootcss.com/docs/assets/img/favicons/favicon-32x32.png"
	sizes="32x32" type="image/png">
<link rel="icon"
	href="https://v4.bootcss.com/docs/assets/img/favicons/favicon-16x16.png"
	sizes="16x16" type="image/png">
<!-- <link rel="manifest"
	href="https://v4.bootcss.com/docs/assets/img/favicons/manifest.json"> -->
<link rel="mask-icon"
	href="https://v4.bootcss.com/docs/assets/img/favicons/safari-pinned-tab.svg"
	color="#563d7c">
<link rel="icon"
	href="https://v4.bootcss.com/docs/assets/img/favicons/favicon.ico">
<meta name="msapplication-config"
	content="/docs/assets/img/favicons/browserconfig.xml">
<meta name="theme-color" content="#563d7c">


<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>
<!-- Custom styles for this template -->
<link href="/resource/css/dashboard.css" rel="stylesheet">
<style type="text/css">/* Chart.js */
@
-webkit-keyframes chartjs-render-animation {
	from {opacity: 0.99
}

to {
	opacity: 1
}

}
@
keyframes chartjs-render-animation {
	from {opacity: 0.99
}

to {
	opacity: 1
}

}
.chartjs-render-monitor {
	-webkit-animation: chartjs-render-animation 0.001s;
	animation: chartjs-render-animation 0.001s;
}

.p-name {
	text-align: center;
	margin-top: 8px;
	overflow: hidden;
	line-height: 20px;
	-webkit-line-clamp: 2;
}
</style>
</head>
<body>
	<nav
		class="navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow">
		<a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">请输入商品名称</a>
		<div class="form-inline">
			<form action="/search" method="post">
				<input class="form-control" name="key" type="text"
					placeholder="商品关键字" autocomplete="off" aria-label="Search">
				<input type="submit" class="btn btn-outline-light" value="搜索">
			</form>
		</div>
		<ul class="navbar-nav px-3">
			<li class="nav-item text-nowrap"><a class="nav-link"
				href="https://v4.bootcss.com/docs/examples/dashboard/#">Sign out</a>
			</li>
		</ul>
	</nav>

	<div class="container-fluid" style="margin-top: 80px">
		<div class="row">
			<div class="col-md-3" id="addCategoryTree"></div>
			<div class="col-md-9">
				<div class="row">
					<c:forEach items="${info.list }" var="spu">

						<div class="col-md-3" style="border: 1px solid red; margin: 10px;">
							<a href="/detail?spuId=${spu.id }" target="_blank">
								<div style="text-align: center;">
									<img alt="暂无" src="/pic/${spu.smallPic}" width="200px"
										height="200px">
								</div> <a href="/detail?spuId=${spu.id }" target="_blank">
									<div style="" class="p-name">${spu.caption}</div>
							</a> <%-- <a href="/detail?spuId=${spu.id }" target="_blank"><div>${spu.goodsName}</div></a> --%>
						</div>

					</c:forEach>
				</div>
			</div>
		</div>
	</div>

</body>
<script type="text/javascript">
	function initTree() {
		//发送ajax获取树需要的数据
		$.post("/treeData", {}, function(treeData) {
			//初始化添加的时候分类的树
			$("#addCategoryTree").treeview({
				data : treeData,
				levels : 2,
				onNodeSelected : function(event, node) {
					if (node.nodes.length == 0) {
						$("#categoryId").val(node.id);
						$("#category").val(node.text);
						//$("#addCategoryTree").hide();
					}
				}
			});

		}, "json");
	}
	initTree();
</script>
</html>