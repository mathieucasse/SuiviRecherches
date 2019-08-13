<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>
<script>
    $(document).ready(function(){
      var date_input=$('input[name="recherche.creationDate"]'); //our date input has the name "date"
      var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
      var options={
        format: 'dd/mm/yyyy',
        container: container,
        todayHighlight: true,
        autoclose: true,
      };
      date_input.datepicker(options);
    })
</script>

<head>
	<meta charset="UTF-8">
	<title>Create Recherche</title>
    <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
</head>
    
<body>
<div class="container-fluid">
	<h2>Creer une Recherche</h2>
	<form action="addRecherche" method="post" enctype="multipart/form-data">
		<h3>Recherche</h3>
		<div class="form-row">
			<div class="form-group col-md-2">
	    		<label class="control-label" for="recherche.creationDate">Date de contact</label>
        		<input class="form-control" id="recherche.contactDate" name="recherche.creationDate" placeholder="DD/MM/YYYY" type="text" required/>
			</div>
	    	<div class="form-group col-md-5">
	    		<label for="recherche.poste">Poste</label>
	    		<input type="text" class="form-control" id="recherche.poste" name="recherche.poste" placeholder="Example input" required/>
			</div>
			<div class="form-group col-md-3">
	    		<label class="control-label" for="recherche.client">Client</label>
        		<input class="form-control" id="recherche.client" name="recherche.client" placeholder="Client" type="text"/>
			</div>
			<div class="form-group col-md-2">
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
	  	<div class="form-row">
	  		<div class="form-group col-md-1">
				<button type="submit" class="btn btn-success">Submit</button>
			</div>
			<div class="form-group col-md-1">
				<p><a class="btn btn-primary" href="home" role="button">Back</a></p>
			</div>
		</div>
	</form>
</div>	
</body>
</html>