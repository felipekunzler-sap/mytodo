<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign Up</title>
<style type="text/css">

    <%@include file="../css/bootstrap.min.css" %>
    <%@include file="../css/bootstrap.css" %>
</style>
</head>
<body>
	<div class="container" style="width: 450px">
	  	<br/>
	  	<div class="jumbotron">
			<h1 align="center">Sign Up</h1>
	  	</div>
	  	<br/>
		<form:form action="signUp" method="post" modelAttribute="user">
			<div class="form-group">
				<label for="username">Username:</label>
				<form:input class="form-control" type="text" name="username" path="username" />
				<font color="red" size="2"><form:errors path ="username"/></font>
			</div>
			<div class="form-group">
				<label for="password">Password:</label>
				<form:input class="form-control" type="password" name="password" path="password"/>
				<font color="red" size="2"><form:errors path ="password"/></font>
			</div>
			<div class="form-group">
				<label for="password">Confirm password:</label>
				<form:input class="form-control" type="password" name="confirmPassword" path="confirmPassword" />
				<font color="red" size="2"><form:errors path ="confirmPassword"/></font>
			</div>
			<input class="btn btn-success btn-block btn-md" type="submit" value="Register" />
		</form:form>
	</div>
</body>
</html>