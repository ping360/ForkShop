<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>Fork: ${item.name}</h2>
<div class="col-md-6 col-xs-12 parent">
		<div>
			<img src="/images/item/${item.id}.jpg?version=${item.version}" width="100%">
		</div>
</div>