<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="js/client-home.js"></script>
<title>Create a TODO</title>
</head>
<body style="text-align: center">
	<h3>TODOS</h3>
	<form method="POST" action="/ejb_jpa/todos" id="createForm">
		<label>Title: </label> <br> <input type="text" name="title">
		<br>
		<br>
		<label>Category: </label> <br>
		<select name="category" id="category">
			<c:forEach items="${categories}" var="category">
				<option value="${category}">${category}</option>
			</c:forEach>
		</select>
		<br>
		<br>
		<label>Content: </label> 
		<br>
		<textarea form="createForm" name="content" rows="4" cols="50"></textarea>
		<br>
		<br>
		<input type="submit" value="Create">
	</form>
</body>
</html>
