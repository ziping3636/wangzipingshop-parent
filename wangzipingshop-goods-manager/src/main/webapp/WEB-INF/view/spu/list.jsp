<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<button type="button" class="btn btn-danger">删除</button>
	<button type="button" class="btn btn-warning" onclick="addSpu()">添加</button>
</div>
<table class="table" style="margin-top: 10px">
	<tr>
		<th>ID</th>
		<th>商品名称</th>
		<th>是否上线</th>
		<th>品牌</th>
		<th>标题</th>
		<th>分类</th>
		<th>小图</th>
		<th>操作</th>
	</tr>
	<c:forEach items="${info.list }" var="spu">
		<tr>
			<td><input type="checkbox" name="ids" value="${spu.id }"
				onchange="selectOne()">&nbsp;&nbsp;&nbsp;${spu.id }</td>
			<td>${spu.goodsName }</td>
			<td>${spu.isMarketable == 1 ? '上线' : '未上线' }</td>
			<td>${spu.brand.name }</td>
			<td>${spu.caption }</td>
			<td>${spu.category.name }</td>
			<td><img width="60px" height="60px" src="/pic/${spu.smallPic }"></td>
			<td>
				<%-- <button type="button" class="btn btn-danger"
					onclick="delSpu(${spu.id })">删除</button>
				<button type="button" class="btn btn-warning"
					onclick="openUpdateSpu(${spu.id })">修改</button> --%>
				<button type="button" class="btn btn-success">详情</button>
				<button type="button" class="btn btn-danger">删除</button>
				<button type="button" class="btn btn-warning">修改</button>
				<button type="button" class="btn btn-warning" onclick="addSku(${spu.id})">添加sku</button>
				<a target="_blank" href="/spu/download?fileName=${spu.smallPic }">下载小图</a>
			</td>
		</tr>
	</c:forEach>
</table>

<jsp:include page="../common/page.jsp"></jsp:include>

<script type="text/javascript">
	function addSpu() {
		$("#main").load("/spu/toAdd")
	}
	
	function addSku(id) {
		$("#main").load("/spu/sku/toAddSku?spuId="+id)
	} 

	function goPage(pageNum) {
		var url = "/spu/list?pageNum=" + pageNum;
		$("#main").load(url);
	}
</script>