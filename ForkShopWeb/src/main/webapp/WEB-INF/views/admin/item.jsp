<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>

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
					<li><a href="/admin/fs">Feature String</a></li>
					<li><a href="/admin/item">Item</a></li>
				</ul>
			</div>
		</div>
	</nav>
</div>
<div class="row">
	<div class="col-md-3 col-xs-12">
		<form:form class="form-horizontal" action="/admin/item" method="GET" modelAttribute="filter">
			<custom:hiddenInputs excludeParams="min, max, brand, _brandId"/>
			<div class="form-group">
				<div class="col-sm-6">
					<form:input path="min" class="form-control" placeholder="Min"/>
				</div>
				<div class="col-sm-6">
					<form:input path="max" class="form-control" placeholder="Max"/>
				</div>
			</div>
			<div class="form-group">
				<label class="contol-label col-sm-12">Brand</label>
				<div class="col-sm-12">
					<form:checkboxes element="div" items="${brands}" itemValue="id" itemLabel="name" path="brandIds"/>
				</div>
			</div>
			<button type="submit" class="btn btn-primary">Ok</button>
			<a href  = "/admin/item/cancel" class="btn btn-primary" >Cancel</a>
		</form:form>
	</div>
	<div class="col-md-7 col-xs-12">
		<div class="row">
			<div class="col-md-12 col-xs-12">
				<form:form class="form-horizontal" action="/admin/item" method="POST" modelAttribute="item" enctype="multipart/form-data">
					<label for="item" style="color:red;text-align:left;" class="col-sm-10 col-sm-offset-2 control-label"><form:errors path="*"/></label>
					<div class="form-group">
    					<label for="item" class="col-sm-2 control-label">Item</label>
    					<div class="col-sm-10">
      						<form:input class="form-control" path="name" id="item"/>
    					</div>
  					</div>
  					<div class="form-group">
    					<label for="item" class="col-sm-2 control-label">Price</label>
    					<div class="col-sm-10">
      						<form:input class="form-control" path="price" id="item"/>
    					</div>
  					</div>
  					<div class="form-group">
    					<label for="category" class="col-sm-2 control-label">Category</label>
    					<div class="col-sm-10">
      						<form:select class="form-control" path="category" id="category"  items="${categories}" itemValue="id" itemLabel="name"/>
    					</div>
  					</div>
  					<div class="form-group">
    					<label for="brand" class="col-sm-2 control-label">Brand</label>
    					<div class="col-sm-10">
      						<form:select class="form-control" path="brand" id="brand"  items="${brands}" itemValue="id" itemLabel="name"/>
    					</div>
  					</div>
   					<div class="form-group"> 
    					<label for="featureStrings" class="col-sm-2 control-label">Feature String</label>
     					<div class="col-sm-10">
 						<form:select class="form-control" path="featureStrings" id="featureStrings"  items="${featureStrings}" itemValue="id" itemLabel="name"/> 
     					</div> 
  					</div> 
  					<div class="form-group">
    					<label for="digitalUnits" class="col-sm-2 control-label">Digital units</label>
    					<div class="col-sm-10">
      						<form:select class="form-control" path="digitalUnits" id="digitalUnits" items="${digitalUnits}" itemValue="id" itemLabel="name"/>
    					</div>
  					</div>
  					<div class="form-group">
    					<label for="file" class="col-sm-2 control-label">Image</label>
    					<div class="col-sm-10">
      						<input name="file" id="file" type="file">
    					</div>
					</div>
  					<div class="form-group">
    					<div class="col-sm-offset-2 col-sm-10">
      						<button type="submit" class="btn btn-primary">Create</button>
      						<a href  = "/admin/item/cancel" class="btn btn-primary" >Cancel</a>
    					</div>
  					</div>
				</form:form>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4 col-xs-4"><h3>Fork</h3></div>
<!-- 			<div class="col-md-2 col-xs-2"><h3>ForceShaft</h3></div> -->
<!-- 			<div class="col-md-2 col-xs-2"><h3>Size</h3></div> -->
<!-- 			<div class="col-md-2 col-xs-2"><h3>Update</h3></div> -->
<!-- 			<div class="col-md-2 col-xs-2"><h3>Delete</h3></div> -->
		</div>
			<c:forEach items="${page.content}" var="item">
				<div class="row">
					<div class="col-md-4 col-xs-4">${item.name}</div>
					<div class="col-md-2 col-xs-2">${item.price}</div>
<%-- 					<div class="col-md-2 col-xs-2">${modelServo.size.size}</div> --%>
					<div class="col-md-2 col-xs-2"><a class="btn btn-success" href="/admin/item/update/${item.id}">update</a></div>
					<div class="col-md-2 col-xs-2"><a class="btn btn-danger" href="/admin/item/delete/${item.id}">delete</a></div>
				</div>
			</c:forEach>
	</div>
	<div class="col-md-2 col-xs-12">
		<div class="row">
					<div class="col-md-6 col-xs-6 text-center">
						<div class="dropdown">
							<button class="btn btn-primary dropdown-toggle" type="button"
								data-toggle="dropdown">
								Sort <span class="caret"></span>
							</button>
							<ul class="dropdown-menu">
								<custom:sort innerHtml="Name asc" paramValue="name" />
								<custom:sort innerHtml="Name desc" paramValue="name,desc" />
							</ul>
						</div>
					</div>
					<div class="col-md-6 col-xs-6 text-center">
						<custom:size posibleSizes="1,2,5,10" size="${page.size}" />
					</div>
				</div>
			</div>
	</div>
<script>
	$('label').each(function() {
		if(!$(this).html()) $(this).parent('div').hide();
	});
</script>