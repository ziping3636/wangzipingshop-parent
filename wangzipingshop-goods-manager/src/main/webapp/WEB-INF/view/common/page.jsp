<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav aria-label="..." style="text-align: center;">
	<ul class="pagination">
		<li class="page-item disabled"><button onclick="goPage(1)"
				class="btn btn-light">First</button></li>
		<li class="page-item disabled"><button
				onclick="goPage(${info.pageNum == 1 ?  1 : info.prePage})"
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
				onclick="goPage(${info.pages})">Last</button></li>
	</ul>
</nav>