<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>indexPersonnes</title>
</head>
<body>
	<h2>Personnes</h2>
	<table>
		<th><td>id</td><td>nom</td><td>prenom</td></th>
  		<c:forEach items="${personnes}" var="item">
    		<tr>
    	  		<td> <c:out value="${item.id}" /></td>
    	  		<td> <c:out value="${item.nom}" /></td>
    	  		<td><c:out value="${item.prenom}" /></td>
    		</tr>
  		</c:forEach>
	</table>
    <h2>Submitted Employee Information</h2>
    <table>
        <tr>
            <td>Name :</td>
            <td>${name}</td>
        </tr>
        <tr>
            <td>ID :</td>
            <td>${id}</td>
        </tr>
        <tr>
            <td>Contact Number :</td>
            <td>${contactNumber}</td>
        </tr>
    </table>
	<form action="addAlien">
		<input type="text" name="aid"><br>
		<input type="text" name="aname"><br>
		<input type="text" name="tech"><br>
		<input type="submit">
	</form>
	
	<form action="getAlien">
		<input type="text" name="aid"><br>
		<input type="submit">
	</form>

</body>
</html>