<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<head>
	<meta charset="UTF-8">
	<title>Historique - ${entreprise}" for ${poste}</title>
    <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container-fluid">
	<h2>Historique</h2>
	<h3>Entreprise : offre</h3>
	
	<table class="table table-striped">
		<caption>Your History</caption>
		<thead class="thead-dark">
			<tr>
				<th scope="col">id</th>
				<th scope="col">id recherche</th>
				<th scope="col">creationDate</th>
				<th scope="col">modification</th>
			</tr>
		</thead>
		<tbody>
  			<c:forEach items="${historique}" var="item">
    		<tr>
    	  		<th scope="row">${item.id}"</th>
    	  		<td>${item.rechercheId}"</td>
    	  		<td>${item.date}"</td>
    	  		<td>${item.modification}"</td>
    		</tr>
  			</c:forEach>
  		</tbody>
	</table>

	<p><a class="btn btn-primary" href="/home" role="button">Back</a></p>

</div>
</body>
</html>