<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row">
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li><a href="/admin/category">Category</a></li>
					<li><a href="/admin/du">Digital Unit</a></li>
					<li><a href="/admin/brand">Brand</a></li>
					<li class="active"><a href="/admin/nofd">Name of feature digital</a></li>
					<li><a href="/admin/nofs">Name of feature string</a></li>
					<li><a href="/admin/fs">Feature string</a></li>
					<li><a href="/admin/item">Item</a></li>
				</ul>
			</div>
		</div>
	</nav>
</div>
<div class="row">
	<div class="col-md-3 col-xs-12"></div>
	<div class="col-md-7 col-xs-12">
		<div class="row">
			<div class="col-md-12 col-xs-12">
				<form:form class="form-horizontal" action="/admin/nofd" method="POST" modelAttribute="nofd">
					<div class="form-group">
    					<label for="name" class="col-sm-2 control-label">Name</label>
    					<div class="col-sm-10">
      						<form:input type="text" class="form-control" path="name" id="name"/>
    					</div>
  					</div>
  					<div class="form-group">
    					<label for="categories" class="col-sm-2 control-label">Categories</label>
    					<div class="col-sm-10">
      						<form:select class="form-control" path="categories" id="categories" items="${categoriess}" itemValue="id" itemLabel="name"/>
    					</div>
					</div>
  					<div class="form-group">
    					<div class="col-sm-offset-2 col-sm-10">
      						<button type="submit" class="btn btn-primary">Create</button>
      						<a href  = "/admin/nofd/cancel" class="btn btn-primary" >Cancel</a>
    					</div>
  					</div>
				</form:form>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4 col-xs-4"><h3>NameOfFeatureDigital</h3></div>
			<div class="col-md-4 col-xs-4"><h3>Update</h3></div>
			<div class="col-md-4 col-xs-4"><h3>Delete</h3></div>
		</div>
			<c:forEach items="${page.content}" var="nofd">
				<div class="row">
					<div class="col-md-4 col-xs-4">${nofd.name}</div>
					<div class="col-md-4 col-xs-4"><a class="btn btn-success" href="/admin/nofd/update/${nofd.id}">update</a></div>
					<div class="col-md-4 col-xs-4"><a class="btn btn-danger" href="/admin/nofd/delete/${nofd.id}">delete</a></div>
				</div>
			</c:forEach>
	</div>
	<div class="col-md-2 col-xs-12"></div>
</div>