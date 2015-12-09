<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.sap.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<style type="text/css">
    <%@include file="../css/bootstrap.min.css" %>
    <%@include file="../css/bootstrap.css" %>
</style>
</head>
<body>
	<div class="container" style="width: 450px">
	  	<br/>
	  	<div class="jumbotron">
			<h1 align="center">Login</h1>
	  	</div>
	  	<br/>
		<form action="login" method="POST">
			<div class="form-group">
				<label for="username">Username:</label>
				<input class="form-control" type="text" name="username" />
			</div>
			<div class="form-group">
				<label for="password">Password:</label>
				<input class="form-control" type="password" name="password"/>
			</div>
			<input class="btn btn-success btn-block btn-md" type="submit" value="Login" />
		</form>
		<c:if test="${param.error != null}">
			<font color="red" size="2">Login failed. Check your username and
				password.</font>
		</c:if>
		<br>
		<div class="form-group" align="right">
			<a href="signUp">Sign Up</a>
		</div>
	</div>
</body>
</html>