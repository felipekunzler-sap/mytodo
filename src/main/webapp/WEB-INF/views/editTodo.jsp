<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Todo</title>
<style type="text/css">

    <%@include file="../css/bootstrap.min.css" %>
    <%@include file="../css/bootstrap.css" %>
</style>
</head>
<body>
	<div class="container" style="width: 450px">
	  	<br/>
	  	<div class="jumbotron">
			<h1 align="center">Edit Todo</h1>
	  	</div>
	  	<br/>
			<form:form action="editTodo" method="post" modelAttribute="todo">
				<div class="form-group">
					<label for="description">Description:</label>
					<form:input class="form-control" type="text" name="description" path="description" />
					<font color="red" size="2"><form:errors path ="description"/></font>
				</div>
				<div class="form-group">
					<label for="description" style="display:inline-block; vertical-align: middle;">Done:</label>
					<form:checkbox class="form-control" name="done" path="done" style=" width:34px;" />
				</div>
				<form:input type="hidden" name="id" path="id" />
				<form:input type="hidden" name="userId" path="userId" />
				<input type="hidden" name="createForm" value="false" />
				<input class="btn btn-success btn-block btn-md" type="submit" value="Save" />
			</form:form>
		</div>
</body>
</html>