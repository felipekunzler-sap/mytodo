<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.sap.Todo"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Todo List</title>
<style type="text/css">
table {
	border-collapse: collapse;
	border: 1px solid black;
}

table td {
	padding: 5px;
	border: 1px solid black;
}
</style>
</head>
<body>
	<h1>My Todo list</h1>
	<p />
	<table>
		<tr>
			<td><b>Description</b></td>
			<td><b>Done</b></td>
		</tr>
		<c:forEach items="${todoList}" var="todo">
			<tr>
				<td>${todo.description}</td>
				<td>${todo.done}</td>
			</tr>
		</c:forEach>
	</table>
	<p />
	<form action="addTodo" method="get" >
		<input type="submit" value="Add new Todo" />
	</form>

</body>
</html>