<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.sap.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="myTodo"/></title>
<style type="text/css">

    <%@include file="../css/bootstrap.min.css" %>
    <%@include file="../css/bootstrap.css" %>
</style>
</head>
<body>
	<br>
	<div class="container" style="width: 500px">
	  	<div class="jumbotron">
			<h1 align="center"><spring:message code="myTodo"/></h1>
	  	</div>
	  	<br>
	  	<div class="form-group" align="right">
	  		Hi <%=  ((User) request.getSession().getAttribute("currentUser")).getUsername() %>! &nbsp;
	  		<a href="logout" >Logout</a>
	  	</div>
		<table class="table">
			<tr>
				<td><b>Description</b></td>
				<td><b>Done</b></td>
			</tr>
			<c:forEach items="${todoList}" var="todo">
				<tr>
					<td>${todo.description}</td>
					<td width="20%">${todo.done}</td>
					<td width="10%">
						<form action="editTodo" method="post">
							<input type="hidden" name="description" value="${todo.description}" />
							<input type="hidden" name="id" value="${todo.id}" />
							<input type="hidden" name="done" value="${todo.done}" />
							<input type="hidden" name="userId" value="${todo.userId}" />
							<input type="hidden" name="createForm" value="true" />
							<input class="btn btn-default btn-xs" type="submit" value="Edit" />
						</form>
					</td>
					<td width="10%">
						<form action="" method="post">
							<input type="hidden" name="todoId" value="${todo.id}" />
							<input class="btn btn-danger btn-xs" type="submit" value="X" />
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
		<p />
		<form action="addTodo" method="get" >
			<input class="btn btn-success btn-block btn-md" type="submit" value="Add new Todo" />
		</form>
	</div>
</body>
</html>