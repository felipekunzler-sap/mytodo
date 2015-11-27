<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add new Todo</title>
</head>
<body>
	<h1>Add new Todo:</h1>
	<form:form action="addTodo" method="post" modelAttribute="todo">
	
		<p>Description: <form:input type="text" name="description" value="" path="description" /><br/>
		<font color="red" size="2"><form:errors path ="description"/></font></p>
		
		Done: <form:checkbox name="done" path="done"/><br/><br/>
		<input type="hidden" name="createForm" value="false" />
		<input type="submit" value="Add new Todo"/>
		
	</form:form>
</body>
</html>