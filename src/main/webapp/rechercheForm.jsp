<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
	<meta charset="UTF-8">
	<title>Recherches here</title>
    
    <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    
    <link href="webjars/bootstrap-datepicker/1.8.0/css/bootstrap-datepicker3.min.css" rel="stylesheet">
    <script src="webjars/bootstrap-datepicker/1.8.0/js/bootstrap-datepicker.min.js"></script>
    
<!--     <link href="webjars/bootstrap-datepicker/2.4.4/css/datetimepicker.min.css" rel="stylesheet"> -->
<!--     <script src="webjars/bootstrap-datetimepicker/2.4.4/js/bootstrap-datetimepicker.min.js"></script> -->
    
    <script src="webjars/jquery/1.9.1/jquery.min.js"></script>
	
</head>
<body>
<div class="container-fluid">
	<h2>Creer une Recherche</h2>
	<form action="addRecherche" method="post" enctype="multipart/form-data">
			<h3>Recherche</h3>
		<div class="form-row">
	    	<div class="form-group col-md-8">
	    		<label for="recherche.poste">Poste</label>
	    		<input type="text" class="form-control" id="recherche.poste" name="recherche.poste" placeholder="Example input" required>
			</div>
			<div class="form-group col-md-4">
				<label for="recherche.statut">Statut</label>
	   			<select class="form-control" name="recherche.statut" required>
	   				<c:forEach items="${statutRecherche}" var="item">
						<option>${item}</option>
	   				</c:forEach>
	    		</select>
			</div>
		</div>
		<h3>Contact</h3>
	  	<div class="form-row">
	   		<div class="form-group col-md-6">
	   			<label for="personne.nom">Nom</label>
				<input type="text" class="form-control" name="personne.nom" placeholder="Nom Personne" required>
	  		</div>
	  		<div class="form-group col-md-6">
				<label for="personne.prenom">Prenom</label>
				<input type="text" class="form-control" name="personne.prenom" placeholder="Prenom Personne" required>
	  		</div>
	  	</div>
	  	<div class="form-row">
	   		<div class="form-group col-md-4">	
	  			<label for="personne.tel">Telephone</label>
				<input type="text" class="form-control" name="personne.tel" placeholder="0000 0 00 00 00">
	  		</div>
	  		<div class="form-group col-md-8">
				<label for="personne.email">Email</label>
				<input type="text" class="form-control" name="personne.email" placeholder="0000 0 00 00 00">
			</div>
		</div>
	  	<h3>Entreprise</h3>
	  	<div class="form-row">
	   		<div class="form-group col-md-8">
	   			<label for="entreprise.nom">Nom</label>
				<input type="text" class="form-control" name="entreprise.nom" placeholder="Nom Entreprise" required>
	  		</div>
	  		<div class="form-group col-md-4">	
	  			<label for="entreprise.tel">Telephone</label>
				<input type="text" class="form-control" name="entreprise.tel" placeholder="0000 0 00 00 00">
	  		</div>
	  	</div>
		<button type="submit" class="btn btn-primary">Submit</button>
	</form>
	
</div>
</body>
</html>