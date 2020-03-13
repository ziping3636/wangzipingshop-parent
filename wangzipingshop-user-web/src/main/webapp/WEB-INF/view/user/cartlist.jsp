<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="container" style="margin-top: 30px">
	<button type="button" class="btn btn-outline-danger" onclick="createOrder()">下订单</button>
	<table class="table" style="margin-top: 20px">
		<tr>
			<th>id</th>
			<th>商品名称</th>
			<th>购买数量</th>
			<th>总价格</th>
			<th>加入时间</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${info.list }" var="cart">
			<tr>
				<td><input type="checkbox" name="cartId" value="${cart.id }">${cart.id }</td>
				<td>${cart.title }</td>
				<td>${cart.pnum }</td>
				<td>${cart.sumTotal }</td>
				<td><fmt:formatDate value="${cart.createtime }"
						pattern="yyyy-MM-dd" /></td>
				<td>
					<button type="button" class="btn btn-danger">删除</button>
				</td>
			</tr>
		</c:forEach>


	</table>
</div>

<script type="text/javascript">
	function createOrder() {
		var cartIds =  new Array();
		$("[name=cartId]:checked").each(function() {
			cartIds.push($(this).val());
		})

		if (cartIds.length <= 0) {
			alert("请选择数据")
			return;
		}
		var address = prompt("请输入邮寄地址", "北京")
		if (!address) {
			return;
		}
		
		$.post("/user/addOrder", { cartIds:cartIds, address:address }, function(message) {

			if (message) {
				alert("下单成功!")
				refrensh();
			} else {
				alert(message)
			}
		})
		
		function refrensh() {
			
		}
	}
</script>