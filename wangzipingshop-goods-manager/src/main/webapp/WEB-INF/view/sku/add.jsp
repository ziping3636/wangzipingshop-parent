<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div>
	<form id="addSkuFrm">
		<div class="row">
			<div class="col">
				<label for="goodsName">商品名称</label>
				<input type="hidden" name="spuId" value="${spu.id }" >
				<input type="text" disabled="disabled" name="goodsName" value="${spu.goodsName }" autocomplete="off" id="goodsName" class="form-control" placeholder="Goods name">
			</div>
		</div>
		<div class="row">
			<div class="col">
				<label for="title">商品标题</label>
				<input type="text" name="title" id="title" class="form-control" placeholder="标题">
			</div>
		</div>
		<div class="row">
			<div class="col">
				<label for="sellPoint">卖点</label>
				<input type="text" name="sellPoint" id="sellPoint" class="form-control" placeholder="卖点">
			</div>
		</div>
		<div class="row">
			<div class="col">
				<label for="price">价格</label>
				<input type="text" name="price" id="price" class="form-control" placeholder="价格">
			</div>
		</div>
			
		<div id="oneSpec">
			<div class="row">
				<div class="col">
					<label for="brand">属性</label>
					<select name="specIds" class="form-control form-control-lg" onchange="specChange($(this))">
						<option>Large select</option>
						<c:forEach items="${specs }" var="spec">
							<option value="${spec.id }">${spec.specName }</option>
						</c:forEach>
					</select>
				</div>
				<div class="col">
					<label for="brand">属性选项（数值）</label>
					<select name="specOptionIds" class="form-control form-control-lg">
						<option>Large select</option>
						<c:forEach items="${specs }" var="spec">
							<option value="${brand.id }">${brand.name }</option>
						</c:forEach>
					</select>
				</div>
				<div class="col" style="margin-top: 32px">
					<label for="brand"></label>
					<button type="button" class="btn btn-danger" onclick="delOption($(this))">删除</button>
				</div>
			</div>
		</div>
		
		<div id="specList">
			
		</div>
		
		<div style="margin-top: 30px">
			<button type="button" class="btn btn-warning" onclick="addSpec()">添加新属性</button>
		</div>
		<div class="row" style="margin-top: 30px; text-align: center">
			<div class="col">
				<button type="button" style="width: 250px; height: 50px" class="btn btn-primary" onclick="commitData()">添加</button>
			</div>
		</div>
	</form>
</div>

<script type="text/javascript">
	function addSpec() {
		$("#specList").append($("#oneSpec").html())
	}
	
	function delOption(delBtn) {
		delBtn.parent().parent().remove();
	}
	
	function commitData() {
		var formData = new FormData($("#addSkuFrm")[0])
		$.ajax({
			url:"/spu/addSku",
			data:formData,
			processData:false,
			contentType:false,
			type:"post",
			success:function (data) {
				if (data) {
					alert("sku添加成功✔")
					var url = "/spu/sku/list";
					$("#main").load(url);
				} else {
					alert("sku添加失败✘")
				}
			}
		})
	}
	
	function back() {
		window.history.back(-1); 
	}
	
	function specChange(specSel) {
		$.post("/spec/getSpecById", {id:specSel.val()}, function(specData) {
			var specOption = specSel.parent().next().children().filter("select").first();
			specOption.empty();
			for ( var i in specData.options) {
				specOption.append("<option value='"+specData.options[i].id+"'>"+specData.options[i].optionName+"</option>")
			}
		})
	}
</script>