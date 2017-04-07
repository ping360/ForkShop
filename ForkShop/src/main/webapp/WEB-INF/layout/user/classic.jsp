<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="/resources/js/jquery-3.1.1.min.js"></script>
<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
<script src="/resources/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="/resources/css/chosen.min.css">
<link rel="stylesheet" href="/resources/css/calendar.css">
<script src="/resources/js/chosen.jquery.min.js"></script>
<script src="/resources/js/calendar.js"></script>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="/resources/css/style.css">
<script>
	$(function() {
		$('select').chosen();
	})
</script>
<style type="text/css">
body {
	padding-bottom: 70px; 
	padding-top: 70px;
}
@media (min-width: 1000px) {
    .navbar .navbar-nav {
        display: inline-block;
        float: none;
        vertical-align: top;
    }
    .navbar .navbar-collapse {
        text-align: center;
    }
}
@media(max-width:1000px)  {
	.nav > li{
	 	float: none;
		position: relative;
		display: block;
	}
    .navbar-collapse.collapse {
        display: none !important;
    }
    .navbar-collapse {
        overflow-x: visible !important;
    }
    .navbar-collapse.in {
      overflow-y: auto !important;
    }
    .collapse.in {
      display: block !important;
    }
    .navbar-toggle {
      display: block;
  }
}
</style>
<!-- сюди буде підставлено атрибут з ім'ям title -->
<title><tiles:getAsString name="title" /></title>
</head>
<body>
<!-- 	а сюди jsp файл з ім'ям header -->
    <tiles:insertAttribute name="header" />
	<div class="container-fluid">
<!-- 	сюди jsp файл з ім'ям body -->
		<tiles:insertAttribute name="body" />
	</div>
<!-- 	сюди jsp файл з ім'ям footer -->
		<tiles:insertAttribute name="footer" />
<!-- 	зверніть увагу на те що в темплейт файлі (тут) -->
<!-- 	вже описано основний HTML код, в інших файлах -->
<!-- 	його писати не потрібно(я про <head><body><html>) -->
</body>
</html>