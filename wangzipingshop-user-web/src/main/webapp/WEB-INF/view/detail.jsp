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
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript"
	src="/resource/bootstrap4/js/bootstrap.js"></script>
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
</style>
</head>
<body>
	<nav
		class="navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow">
		<a class="navbar-brand col-sm-3 col-md-2 mr-0"
			href="https://v4.bootcss.com/docs/examples/dashboard/#">Company
			name</a> <input class="form-control form-control-dark w-100" type="text"
			placeholder="Search" aria-label="Search">
		<ul class="navbar-nav px-3">
			<li class="nav-item text-nowrap"></li>
		</ul>
	</nav>

	<div class="container" style="margin-top: 80px">
		<div class="row">
			<div class="col-md-6">
				<img alt="异常" width="400px" height="400px"
					src="/pic/${spu.smallPic }">
			</div>
			<div class="col-md-6">
				<div>${spu.goodsName }</div>

				<div  style="margin: 5px">
					<font color="red" size="5px">${spu.caption }</font>
				</div>

				<div>
					<div style="margin: 20px">
						<c:forEach items="${skus }" var="sku">
							<div style="border: 1px red solid; height: 30px;"
								onclick="selSku(${sku.id}, ${sku.price })">
								<c:forEach items="${sku.specs }" var="spec">
								&nbsp;${spec.specName }:${spec.optionName }
							</c:forEach>
							</div>
						</c:forEach>
					</div>
				</div>
				<div style="margin: 20px; width font-size: 10px;font-size-adjust: 10px" id="skuDetail">
				</div>

				<div>
					购买数量：<input type="number" min="1" class="form-control" id="buyNum" >
					<button style="margin: 20px" type="button" class="btn btn-danger btn-lg" onclick="addCart()">加入购物车</button>
				</div>
			</div>
		</div>
	</div>

</body>

<script type="text/javascript">

	var buySkuId = 0;
	
	function addCart() {
		$.post("/user/addCart", {skuId:buySkuId, buyNum:$("#buyNum").val()}, function(message) {
			if (message=="success") {
				alert("已加入购物车")
			} else { 
				alert(message)
			}
		})
	}
	
	function selSku(id, price) {
		$("#skuDetail").html("价格：" + price + "元");
		buySkuId = id;
	}
</script>
</html>