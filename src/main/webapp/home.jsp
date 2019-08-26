<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>Recherches here</title>
</head>

<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<style>

        .CellWithComment{position:relative;}

        .CellComment
        {
            visibility: hidden;
            width: auto;
            position:absolute; 
            z-index:100;
            text-align: Left;
            opacity: 0.4;
            transition: opacity 2s;
            border-radius: 6px;
            border-style: groove;
            background-color: white;
            padding:3px;
            top:30px;  
/* 			bottom:0px; */
            left:0px;
        }   
        .CellWithComment:hover span.CellComment {visibility: visible;opacity: 1;}
</style>
<body>
<div class="container-fluid">
	<h2>Recherches</h2>
<!-- 	<table class="table table-striped"> -->
	<table class="table table-hover table-sm">
	
		<caption>Your Recherches are</caption>
		<thead class="thead-dark">
			<tr>
				<th scope="col">id</th>
				<th scope="col">creationDate</th>
				<th scope="col">poste</th>
				<th scope="col">client</th>
				<th scope="col">statut</th>
				<th scope="col">entreprise</th>
				<th scope="col">contact</th>
				<th scope="col">history</th>
				<th scope="col">edit</th>
				<th scope="col">delete</th>
			</tr>
		</thead>
		
<!-- 		<button type="button" class="btn btn-lg btn-danger" data-toggle="popover" title="Popover title" data-content="And here's some amazing content. It's very engaging. Right?">Click to toggle popover</button> -->
		
		<tbody>
  			<c:forEach items="${recherches}" var="item">
    		<tr>
    	  		<td scope="row">${item.id}</td>
    	  		<td><fmt:formatDate pattern = "dd/MM/yyyy" value = "${item.contactDate}"/></td>
				<td>${item.poste}</td>
				<td>${item.client}</td>
				<td>${item.statut}</td>
    	  		<td class="CellWithComment">${item.entreprise.nom}<span class="CellComment">Tel : ${item.entreprise.telephone}</span></td>
    	  		<td class="CellWithComment">${item.personne.prenom} ${item.personne.nom}<span class="CellComment">Tel : ${item.personne.telephone} <br> Mail : ${item.personne.email}</span></td>
    	  		<td><a class="btn btn-success btn-sm" href="showHistory?id=${item.id}&poste=${item.poste}&entreprise=${item.entreprise.nom}">History</a></td>
    	  		<td><a class="btn btn-warning btn-sm" href="editRecherche?id=${item.id}">Edit</a></td>
    	  		<td><a class="btn btn-danger btn-sm" href="delRecherche?id=${item.id}">Delete</a></td>
    		</tr>
  			</c:forEach>
  		</tbody>
	</table>

	<p><a class="btn btn-primary" href="rechercheForm" role="button">Creer Recherche</a></p>
	<p><a class="btn btn-primary" href="test" role="button">Test</a></p>

</div>

</body>
</html>