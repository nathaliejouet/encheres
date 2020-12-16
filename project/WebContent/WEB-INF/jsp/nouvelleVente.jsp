<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<title>Nouvelle Vente</title>


<%@ include file="layouts/head.jsp"%>
<body>


	<%@ include file="layouts/header.jsp"%>


	<div class="container-fluid"></div>
	<div class="col-md-3 mb-3"></div>
	<!--title-->
	<div class="mx-auto text-center">
		<h3>Nouvelle vente</h3>
		<p>
		<p>
	</div>
	<!-- Erreur -->


	<form class="form-register needs-validation" action="vente"
		method="post" novalidate>

		<!--formulaire-->

		<label>Article :</label> <input type="text" id="nom_article"
			name="article">
		<!--   class="form-control" <class="invalid-feedback">
     Donnez un nom à l'article !-->


		<div>
			<label>Description :</label>
			<TEXTAREA name="description" rows=4 cols=40></TEXTAREA>
		</div>

		<!--catégorie-->

		<div class="form-group">
			<label for="categories-select">Catégorie :</label> <select
				class="form-control" id="categories-select" name="categorie">
				<c:forEach items="${Listecateg}" var="c">
					<option value="${c.no_categorie}">${c.libelle}</option>


				</c:forEach>


			</select>
		</div>


		<!-- Photo -->

		<!--  <FORM ACTION=".jpg"> 
		<INPUT TYPE="SUBMIT" VALUE="Charger image"> 
		</FORM>-->

		<p>
			<!-- Mise à prix -->
		<div>
			<label for="prix"> Mise à prix :</label> <input type="text"
				id="debut_prix" name="debutPrix" min="1">
		</div>

		<!--dates -->

		<div>
			<label for="debutEnchere">Début de l'enchère :</label> <input
				type="date" id="debut_nchere" name="debutEnchere">
		</div>


		<div>
			<label for="finEnchere">Fin de l'enchère :</label> <input type="date"
				id="fin_enchere" name="finEnchere">
		</div>




		<!--  Retrait -->

		<div>
			<label for="rue">Rue :</label> <input type="text" id="rue" name="rue">
		</div>

		<div>
			<label for="codePostal">Code postal :</label> <input type="text"
				id="codePostal" name="codePostal">
		</div>

		<div>
			<label for="ville">Ville :</label> <input type="text" id="ville"
				name="ville">
		</div>


		<!-- Boutons -->
		<button class="btn btn-primary btn-lg btn-block" type="submit">Enregistrer</button>
		<hr class="mb-4">
		
		<a href="${pageContext.request.contextPath }/accueil"><button class="btn btn-primary btn-lg btn-block" type="button">Annuler</button></a>
	</form>
</body>
<%@ include file="layouts/footers.jsp"%>
</html>