<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<style>
	#filter>.form-group>.col-sm-12>span{
		display: block;
	}
</style>
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
					<li><a href="/admin/nofd">Name of feature digital</a></li>
					<li><a href="/admin/nofs">Name of feature string</a></li>
					<li><a href="/admin/fs">Feature String</a></li>
					<li><a href="/admin/item">Item</a></li>
				</ul>
			</div>
		</div>
	</nav>
</div>
<div class="row">
	<div class="col-md-3 col-xs-12">
		<c:if test="${category eq null}">
			<form:form class="form-horizontal" action="/admin/item" method="GET" modelAttribute="filter" id="filter">
				<custom:hiddenInputs excludeParams="search, minPrice, maxPrice, brandIds, fsIds"/>
<%-- 				<custom:hiddenInputs excludeParams="search, minPrice, maxPrice, brandIds, fsIds" excludeParamsPrefix="futureDigitFilters"/> --%>
				<div class="form-group">
					<div class="col-sm-12">
	      				<form:input type="text" class="form-control" path="search" placeholder="Search"/>
	    			</div>
				</div>
				<div class="form-group">
					<div class="col-sm-6 col-xs-6">
	      				<form:input type="text" class="form-control" path="minPrice" placeholder="Min price"/>
	    			</div>
	    			<div class="col-sm-6 col-xs-6">
	      				<form:input type="text" class="form-control" path="maxPrice" placeholder="Max price"/>
	    			</div>
				</div>
				<div class="form-group">
					<div class="col-sm-12">
						<form:checkboxes items="${brands}" path="brandIds" itemLabel="name" itemValue="id"/>
					</div>
				</div>
				<c:forEach items="${nofss}" var="nofs">
					<div class="form-group">
						<label class="col-sm-12">${nofs.name}</label>
					</div>
					<div class="form-group">
						<div class="col-sm-12">
							<form:checkboxes items="${nofs.featureStrings}" itemLabel="name" itemValue="id" path="fsIds"/>
						</div>
					</div>
				</c:forEach>
				<c:forEach items="${nosds}" var="nosd" varStatus="vs">
					<form:hidden path="featureDigitFilters[${vs.index}].nameId" value="${nofd.id}"/>
					<div class="form-group">
						<label class="col-sm-12">${nofd.name}</label>
					</div>
					<div class="form-group">
						<div class="col-sm-12">
							<form:radiobuttons path="specDigitFilters[${vs.index}].msId" itemLabel="name" itemValue="id" items="${nofd.du}"/>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-6 col-xs-6">
							<form:input type="text" class="form-control" path="featureDigitFilters[${vs.index}].min" placeholder="Min value"/>
						</div>
						<div class="col-sm-6 col-xs-6">
							<form:input type="text" class="form-control" path="featureDigitFilters[${vs.index}].max" placeholder="Max value"/>
						</div>
					</div>
				</c:forEach>
				<div class="form-group">
    				<div class="col-sm-12">
      					<button type="submit" class="btn btn-primary">Search</button>
    				</div>
  				</div>
			</form:form>
		</c:if>
	</div>
	<div class="col-md-7 col-xs-12">
	<c:if test="${category ne null}">
		<div class="row">
			<div class="col-md-12 col-xs-12">
				<form:form class="form-horizontal" action="/admin/item" method="POST" modelAttribute="item" enctype="multipart/form-data">
					<custom:hiddenInputs excludeParams="category, name, brand, featureStrings, price, digitalUnit"/>
<%-- 					<custom:hiddenInputs excludeParams="category, name, producer, featureStrings, price, digitalUnit" excludeParamsPrefix="featureDigitals"/> --%>
					<form:hidden value="${category.id}" path="category"/>
					<div class="form-group">
						<label for="name" class="col-sm-offset-2 col-sm-10"><form:errors path="name"/></label>
					</div>
					<div class="form-group">
    					<label for="name" class="col-sm-2 control-label">Name</label>
    					<div class="col-sm-10">
      						<form:input type="text" class="form-control" path="name" id="name"/>
    					</div>
  					</div>
  					<div class="form-group">
    					<label class="col-sm-2 control-label">Brand</label>
    					<div class="col-sm-10">
      						<form:select class="form-control" path="brand" itemLabel="name" itemValue="id" items="${brands}"/>
    					</div>
  					</div>
  					<c:forEach items="${nofss}" var="nofs">
	  					<div class="form-group">
	    					<label class="col-sm-2 control-label">${nofs.name}</label>
	    					<div class="col-sm-10">
	      						<form:select path="featureStrings" items="${nofs.name}" itemLabel="name" itemValue="id" class="form-control" multiple="false"/>
	    					</div>
	  					</div>
  					</c:forEach>
  					<c:forEach items="${nofds}" var="nofd" varStatus="vs">
  					<form:hidden path="featureDigitals[${vs.index}].nameOFFeatureDigitals" value="${nofd.id}"/>
  						<div class="form-group">
							<label for="value${vs.index}" class="col-sm-offset-2 col-sm-10"><form:errors path="featureDigitals[${vs.index}].value"/></label>
						</div>
  						<div class="form-group">
  							<label class="col-sm-2 control-label">${nofd.name}</label>
  							<div class="col-sm-7">
  								<form:input id="value${vs.index}" path="featureDigitals[${vs.index}].value" class="form-control"/>
  							</div>
  							<div class="col-sm-3">
  								<form:select itemLabel="name" itemValue="id" items="${digitalUnits}" path="featureDigitals[${vs.index}].digitalUnits" class="form-control" multiple="false"/>
  							</div>
  						</div>
  					</c:forEach>
  					<div class="form-group">
						<label for="price" class="col-sm-offset-2 col-sm-10"><form:errors path="price"/></label>
					</div>
  					<div class="form-group">
  						<label class="col-sm-2 control-label">Price</label>
  						<div class="col-sm-7">
  							<form:input id="price" path="price" class="form-control"/>
  						</div>
  						<div class="col-sm-3">
  							<form:select itemLabel="name" itemValue="id" items="${digitalUnits}" path="digitalUnit" class="form-control" multiple="false"/>
  						</div>
  					</div>
  					<div class="form-group">
  						<div class="col-sm-offset-2 col-sm-10">
		  					<label class="btn btn-default btn-file">
		        				Browse <input type="file" name="file" style="display: none;">
		    				</label>
	    				</div>
    				</div>
  					<div class="form-group">
    					<div class="col-sm-offset-2 col-sm-10">
      						<button type="submit" class="btn btn-default">Create</button>
      						<a href  = "/admin/item/cancel" class="btn btn-primary" >Cancel</a>
    					</div>
  					</div>
				</form:form>
			</div>
		</div>
	</c:if>
		<div class="row">
			<div class="col-md-2 col-xs-2"><h3>Image</h3></div>
			<div class="col-md-2 col-xs-2"><h3>Item name</h3></div>
			<div class="col-md-2 col-xs-2"><h3>Item price</h3></div>
			<div class="col-md-2 col-xs-2"><h3>Category</h3></div>
			<div class="col-md-2 col-xs-2"><h3>Producer</h3></div>
			<div class="col-md-2 col-xs-2"><h3>Update</h3>
			<h3>Delete</h3></div>
		</div>
			<c:forEach items="${page.content}" var="item">
				<div class="row">
					<div class="col-md-2 col-xs-2"><img class="img-rounded" width="100%" src="/images/item/${item.id}.jpg?version=${item.version}"></div>
					<div class="col-md-2 col-xs-2">${item.name}</div>
					<div class="col-md-2 col-xs-2">${item.price}</div>
					<div class="col-md-2 col-xs-2">${item.category.name}</div>
					<div class="col-md-2 col-xs-2">${item.brand.name}</div>
					<div class="col-md-2 col-xs-2"><a class="btn btn-warning" href="/admin/item/update/${item.id}<custom:allParams/>">update</a>
					<a class="btn btn-danger" href="/admin/item/delete/${item.id}<custom:allParams/>">delete</a></div>
				</div>
			</c:forEach>
	</div>
	<div class="col-md-2 col-xs-12">
		<div class="row">
			<div class="col-md-6 col-xs-6 text-center">
				<div class="dropdown">
					<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Sort <span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<custom:sort innerHtml="Name asc" paramValue="name"/>
						<custom:sort innerHtml="Name desc" paramValue="name,desc"/>
						<custom:sort innerHtml="Price asc" paramValue="price"/>
						<custom:sort innerHtml="Price asc" paramValue="price,desc"/>
					</ul>
				</div>
			</div>
			<div class="col-md-6 col-xs-6 text-center">
				<custom:size posibleSizes="1,2,5,10" size="${page.size}" />
			</div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-md-12 col-xs-12 text-center">
		<custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>" />
	</div>
</div>