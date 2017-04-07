<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>Fork: ${item.name}</h2>
<%-- <H3>Services: <c:forEach items = "${brand}" var = "brand"> --%>
<%-- <h2>Fork: ${item.brand.name}</h2> --%>
<%-- </c:forEach> --%>
<%-- <H3>Services: <c:forEach items = "${category}" var = "category"> --%>
<%-- <h2>Fork: ${item.category.name}</h2> --%>
<%-- </c:forEach> --%>
<div class="col-md-6 col-xs-12 parent">
		<div>
			<img src="/images/item/${item.id}.jpg?version=${item.version}" width="100%">
		</div>
<%-- 		<c:forEach items="${digitalUnits}" var="digitalUnit"> --%>
<!-- 			<div> -->
<%-- 				${digitalUnits.name} --%>
<!-- 			</div> -->
<%-- 		</c:forEach> --%>
</div>