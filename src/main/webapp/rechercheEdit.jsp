<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

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
	<title>Recherche Edit</title>
    <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container-fluid">
	<h2>Editer votre Recherche</h2>
	<form action="updateRecherche" method="post" enctype="multipart/form-data">
		<h3>Recherche</h3>
		<input type="hidden" name="id" value="${recherche.id}" name="recherche.id">
		<div class="form-row">
			<div class="form-group col-md-2">
	    		<label class="control-label" for="recherche.creationDate">Date de contact</label>
        		<input class="form-control" value="<fmt:formatDate pattern = "dd/MM-yyyy" value = "${recherche.contactDate}"/>" 
        						id="recherche.creationDate" name="recherche.creationDate" placeholder="DD/MM/YYYY" type="text">
			</div>
	    	<div class="form-group col-md-5">
	    		<label class="control-label" for="recherche.poste">Poste</label>
	    		<input type="text" class="form-control" value="${recherche.poste}" id="recherche.poste" name="recherche.poste" placeholder="Poste" required>
<%-- 				<label>Poste : <input type="text" class="form-control" value="${recherche.poste}" id="recherche.poste" name="recherche.poste" placeholder="Poste" required></label> --%>
			</div>
			<div class="form-group col-md-3">
	    		<label class="control-label" for="recherche.Client">Client</label>
	    		<input type="text" class="form-control" value="${recherche.client}" id="recherche.client" name="recherche.client" placeholder="Client">
			</div>
			<div class="form-group col-md-2">
				<label class="control-label" for="recherche.statut">Statut</label>
	   			<select class="form-control" name="recherche.statut" id="recherche.statut" required>
	   				
	   				<c:forEach items="${statutRecherche}" var="item">
						<option <c:if test="${item eq recherche.statut}">selected="selected"</c:if> >${item}</option>
        			</c:forEach>
	    		</select>
			</div>
		</div>
		<h3>Contact</h3>
	  	<div class="form-row">
	   		<div class="form-group col-md-6">
	   			<label class="control-label" for="personne.nom">Nom</label>
				<input type="text" class="form-control" value="${recherche.personne.nom}" name="personne.nom" placeholder="Nom Personne" required>
	  		</div>
	  		<div class="form-group col-md-6">
				<label class="control-label" for="personne.prenom">Prenom</label>
				<input type="text" class="form-control" value="${recherche.personne.prenom}" name="personne.prenom" placeholder="Prenom Personne" required>
	  		</div>
	  	</div>
	  	<div class="form-row">
	   		<div class="form-group col-md-4">	
	  			<label class="control-label" for="personne.tel">Telephone</label>
				<input type="text" class="form-control" value="${recherche.personne.telephone}" name="personne.tel" placeholder="0000 0 00 00 00">
	  		</div>
	  		<div class="form-group col-md-8">
				<label class="control-label" for="personne.email">Email</label>
				<input type="text" class="form-control" value="${recherche.personne.email}" name="personne.email" placeholder="0000 0 00 00 00">
			</div>
		</div>
	  	<h3>Entreprise</h3>
	  	<div class="form-row">
	   		<div class="form-group col-md-8">
	   			<label class="control-label" for="entreprise.nom">Nom</label>
				<input type="text" class="form-control" value="${recherche.entreprise.nom}" name="entreprise.nom" placeholder="Entreprise" required>
	  		</div>
	  		<div class="form-group col-md-4">	
	  			<label class="control-label" for="entreprise.tel">Telephone</label>
				<input type="text" class="form-control" value="${recherche.entreprise.telephone}" name="entreprise.tel" placeholder="0000 0 00 00 00">
	  		</div>
	  	</div>
		<div class="form-row">
	  		<div class="form-group col-md-1">
				<button type="submit" class="btn btn-warning">Submit</button>
			</div>
			<div class="form-group col-md-1">
				<p><a class="btn btn-primary" href="home" role="button">Back</a></p>
			</div>
		</div>
	</form>
</div>
</body>
</html>