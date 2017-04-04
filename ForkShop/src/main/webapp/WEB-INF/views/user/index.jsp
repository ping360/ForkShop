<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<h3>Зайчик долбится об стену, и попасть не может в Вену</h3>
<sec:authorize access="isAuthenticated()">
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<a href="/admin">admin</a>
	</sec:authorize>
	<form:form action="/logout" method="POST">
		<button type="submit" class="btn btn-danger">Logout</button>
	</form:form>
</sec:authorize>
<sec:authorize access="!isAuthenticated()">
	<a href="/login">Login</a>
	<a href="/registration">Registration</a>
</sec:authorize>
<div class="row">
	<div class="col-sm-12 col-xs-12 parent">
		<c:forEach items="${items}" var="item">
			<div>
				<img src="/images/item/${item.id}.jpg?version=${item.version}" width="100%">
				<p>${item.name}</p>
				<p>${item.price} грн</p>
				<div>
					<a href="/buy/${item.id}" class="btn btn-primary">В корзину</a>
				</div>
			</div>
		</c:forEach>
	</div>
</div>
<script type="text/javascript">
$("#myCarousel").carousel();
</script>