<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row">
	<div class="col-sm-12 col-xs-12 parent">
		<c:forEach items="${items}" var="item">
			<div>
				<img src="/images/item/${item.id}.jpg?version=${item.version}" width="30%">
				<p>${item.name}</p>
				<p>${item.price} грн</p>
				<div>
					<a href="/del/${item.id}" class="btn btn-primary">Kick fro here!</a>
				</div>
			</div>
		</c:forEach>
	</div>
</div>
<div class="row">
	<div>
		<a href="/iNeedIt/" class="btn btn-primary">Buy</a>
		<p>Total price <c:forEach items="${items}" var="item"> <p>${item.price} грн</p></c:forEach>
<!-- 		<div class="btn-modal-cart total-cost">Total price -->
<%-- 					${cartItemPrice} грн.</div> --%>
	</div>
</div>