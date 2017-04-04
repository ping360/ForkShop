<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<div class="row">
	<div class="col-sm-12 col-xs-12">
		<form:form class="form-horizontal" action="/registration" method="POST" modelAttribute="user">
  			<div class="form-group"><h2 class="col-sm-offset-5 col-sm-2">Registration</h2></div>
  			<div class="form-group">
				<label for="email" class="col-sm-offset-4 col-sm-4"><form:errors path="email"/></label>
			</div>
			<div class="form-group">
    			<label for="email" class="col-sm-4 control-label">Name</label>
    			<div class="col-sm-4">
      				<form:input class="form-control" path="email" id="name"/>
    			</div>
  			</div>
  			<div class="form-group">
				<label for="username" style="color:red;text-align:left;" class="col-sm-10 col-sm-offset-2 control-label"><form:errors path="username"/></label>
			</div>
  			<div class="form-group">
    			<label for="username" class="col-sm-4 control-label">Email</label>
    			<div class="col-sm-4">
      				<form:input class="form-control" path="username" id="user"/>
    			</div>
  			</div>
  			<div class="form-group">
				<label for="password" style="color:red;text-align:left;" class="col-sm-10 col-sm-offset-2 control-label"><form:errors path="password"/></label>
			</div>
			<div class="form-group">
    			<label for="password" class="col-sm-4 control-label">Password</label>
    			<div class="col-sm-4">
      				<form:password class="form-control" path="password" id="name"/>
    			</div>
  			</div>
  			<div class="form-group">
    			<div class="col-sm-offset-4 col-sm-4">
      				<button type="submit" class="btn btn-primary">Register</button>
      				<a href  = "/cancel" class="btn btn-primary" >Cancel</a>
    			</div>
  			</div>
		</form:form>
	</div>
</div>
<script>
	$('label').each(function() {
		if(!$(this).html()) $(this).parent('div').hide();
	});
</script>