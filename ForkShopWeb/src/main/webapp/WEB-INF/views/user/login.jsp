<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row">
<c:if test="${param.fail}">
	<div class="col-sm-12 col-xs-12 text-center">
		<h2>Fail to autorize</h2>
	</div>
</c:if>
	<div class="col-sm-12 col-xs-12">
		<form:form class="form-horizontal" action="/login" method="POST">
			<div class="form-group">
    			<label for="login" class="col-sm-4 control-label">Em@il</label>
    			<div class="col-sm-4">
      				<input class="form-control" name="login" id="login">
    			</div>
  			</div>
			<div class="form-group">
    			<label for="password" class="col-sm-4 control-label">Password</label>
    			<div class="col-sm-4">
      				<input type="password" class="form-control" name="password" id="user">
    			</div>
  			</div>
  			<div class="form-group">
  				<div class="col-sm-offset-4 col-sm-4">
  					<div class="checkbox">
  						<label>
  							<input name="remember-me" type="checkbox"> Remember me
  						</label>
  					</div>
  				</div>
  			</div>
  			<div class="form-group">
    			<div class="col-sm-offset-4 col-sm-4">
      				<button type="submit" class="btn btn-default">Sign in</button>
    			</div>
  			</div>
		</form:form>
	</div>
</div>