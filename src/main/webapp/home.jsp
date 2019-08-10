<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<script>
// Example starter JavaScript for disabling form submissions if there are invalid fields
(function() {
  'use strict';
  window.addEventListener('load', function() {
    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    var forms = document.getElementsByClassName('needs-validation');
    // Loop over them and prevent submission
    var validation = Array.prototype.filter.call(forms, function(form) {
      form.addEventListener('submit', function(event) {
        if (form.checkValidity() === false) {
          event.preventDefault();
          event.stopPropagation();
        }
        form.classList.add('was-validated');
      }, false);
    });
  }, false);
})();
</script>
<head>
	<meta charset="UTF-8">
	<title>Recherches here</title>
    <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container-fluid">
	<h2>Recherches</h2>
	<table class="table table-striped">
		<caption>Your Recherches are</caption>
		<thead class="thead-dark">
			<tr>
				<th  scope="col">id</th>
				<th  scope="col">creationDate</th>
				<th  scope="col">poste</th>
				<th  scope="col">statut</th>
				<th scope="col">entreprise</th>
				<th scope="col">contact</th>
				<th scope="col">tel</th>
				<th scope="col">email</th>
				<th scope="col">history</th>
				<th scope="col">edit</th>
				<th scope="col">delete</th>
			</tr>
		</thead>
		<tbody>
  			<c:forEach items="${recherches}" var="item">
    		<tr>
    	  		<td scope="row">${item.id}</td>
    	  		<td>${item.creationDate}</td>
				<td>${item.poste}</td>
				<td>${item.statut}</td>
    	  		<td>${item.entreprise.nom}</td>
    	  		<td>${item.personne.prenom} ${item.personne.nom}</td>
    	  		<td>${item.personne.telephone}"</td>
    	  		<td>${item.personne.email}"</td>
    	  		<td><a class="btn btn-warning" href="/showHistory?id=${item.id}&poste=${item.poste}&entreprise=${item.entreprise.nom}">History</a></td>
    	  		<td><a class="btn btn-warning" href="/editRecherche?id=${item.id}">Edit</a></td>
    	  		<td><a class="btn btn-warning" href="/delRecherche?id=${item.id}">Delete</a></td>
    		</tr>
  			</c:forEach>
  		</tbody>
	</table>

	<p><a class="btn btn-primary" href="rechercheForm" role="button">Creer Recherche</a></p>

</div>
<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>