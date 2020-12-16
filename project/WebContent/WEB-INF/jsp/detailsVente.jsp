<%@ include file="layouts/head.jsp"%>
<body>
	<div class="container-fluid">
		<%@ include file="layouts/header.jsp"%>

		<!-- Erreur -->
		<%@ include file="layouts/error.jsp"%>

		<!--main bloc-->
		<main class="row" style="margin-bottom: 20px;">
			<!--title-->
			<div class="col-sm-4">
				<div class="mx-auto text-center" style="margin-top: 50px">
					<img width="200px" height="200px"
						src="${pageContext.request.contextPath}/images/article.svg"
						alt="imgArticle">
				</div>
			</div>
			<div class="col-sm-8">
			<c:choose>
				<c:when test="${estTermine eq false and estRetire eq false}">
					<h1 class="mx-auto" style="margin-top: 50px">Détail vente</h1>
				</c:when>

				<c:otherwise>
					<c:choose>
						<c:when test="${not empty enchere}">
							<h1 class="mx-auto" style="margin-top: 50px">
								<span>${sessionScope.utilisateur.no_utilisateur == enchere.utilisateur.no_utilisateur ? 'Vous avez' : enchere.utilisateur.pseudo }</span>
								<span>${sessionScope.utilisateur.no_utilisateur == enchere.utilisateur.no_utilisateur ? '' : ' a'}</span>
								<span> remporté la vente !</span>
							</h1>
						</c:when>
						<c:otherwise>
							<h1 class="mx-auto" style="margin-top: 50px">Il n'y a pas eu
								d'enchères rélisés sur cette article</h1>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
				</c:choose>
				<div class="mx-auto">

					<form class="form form-horizontal" action="afficherVente"
						method="post">
						<input id="no_article" name="no_article" type="hidden"
							value="${article.no_article }">
						<div class="form-group row justify-content-center">
							<label for="nomArticle" class="col-sm-4 col-form-label">Nom
								:</label>
							<div class="col-sm-8" id="nomArticle">
								${article.nom_article}</div>
						</div>
						<div class="form-group row">
							<label for="descriptionArticle" class="col-sm-4 col-form-label">Description
								:</label>
							<div class="col-sm-8" id="descriptionArticle">
								${article.description}</div>
						</div>
						<c:if test="${estTermine != true }">
							<div class="form-group row">
								<label for="categorieArticle" class="col-sm-4 col-form-label">Catégorie
									:</label>
								<div class="col-sm-8" id="categorieArticle">
									${article.categorieArticle.libelle}</div>
							</div>
						</c:if>
						<c:if test="${not empty enchere }">
							<div class="form-group row">
								<label for="meilleureOffreArticle"
									class="col-sm-4 col-form-label">Meilleure offre :</label>
								<div class="col-sm-8">
									<span id="meilleureOffreArticle">${enchere.montant_enchere}</span>
									pts
									<c:if test="${estTermine != true }">
									 par <span id="nomUtilisateur">${utilisateur.pseudo }</span>
										<input type="hidden" readonly class="" id="pseudo"
											name="pseudo" value="${utilisateur.pseudo}" />
									</c:if>
								</div>
							</div>
						</c:if>
						<div class="form-group row">
							<label for="prixArticle" class="col-sm-4 col-form-label">Mise
								à  prix :</label>
							<div class="col-sm-8">${article.prix_initial}
								<span> pts</span>
							</div>
						</div>
						<div class="form-group row">
							<label for="finEnchere" class="col-sm-4 col-form-label">Fin
								de l'enchère :</label>
							<div class="col-sm-8" id="finEnchere">${date_fin_enchere}</div>
						</div>
						<div class="form-group row">
							<label for="retrait" class="col-sm-4 col-form-label">Retrait
								:</label>
							<div class="col-sm-8" id="retrait">${not empty adresseLieuRetrait ? adresseLieuRetrait : adresse}</div>
						</div>

						<div class="form-group row">
							<label for="vendeur" class="col-sm-4 col-form-label">Vendeur
								:</label>
							<div class="col-sm-8" id="vendeur">
								<a
									href="afficherUtilisateur?pseudo=${article.utilisateur.pseudo}">${article.utilisateur.pseudo}</a>
							</div>
						</div>
						<c:if test="${estTermine == true }">
							<div class="form-group row">
								<label for="vendeur" class="col-sm-4 col-form-label">Téléphone
									:</label>
								<div class="col-sm-8" id="vendeur">${not empty article.utilisateur.telephone ? article.utilisateur.telephone : 'N/A'}</div>
							</div>
						</c:if>
						<c:if
							test="${enCours eq true and (sessionScope.utilisateur.no_utilisateur != article.utilisateur.no_utilisateur) }">
							<div class="form-group row">
								<label for="encherir" class="col-sm-4 col-form-label">
									Ma proposition : </label> <input class="col-sm-2" type="number"
									id="encherir" name="encherir"
									min="${not empty enchere.montant_enchere ? enchere.montant_enchere +1 : article.prix_initial + 1 }"
									required max="${sessionScope.utilisateur.credit }" required
									value="${not empty enchere.montant_enchere ? enchere.montant_enchere +1 : article.prix_initial + 1 }">
								<button type="submit"
									class="btn btn-lg btn-primary btn-block col-sm-2 offset-sm-2">Enchérir</button>
							</div>
						</c:if>
						<hr class="mb-4">
					</form>

					<div class="col-sm-6">
						<c:if
							test="${sessionScope.utilisateur.no_utilisateur == article.utilisateur.no_utilisateur and (estTermine eq true) }">
							<form action="miseAJourRetrait" method="post">
								<input id="no_article" name="no_article" type="hidden"
									value="${article.no_article }">
								<button type="submit"
									class="btn btn-lg btn-primary btn-block">Retrait
									effectué</button>
							</form>
						</c:if>
					</div>
					<div class="col-sm-6">
						<a href="accueil">
							<button type="button" class="btn btn-lg btn-danger btn-block">
								Retour</button>
						</a>
					</div>
					<br> <br> <br> <br>
					<hr class="mb-4">
					<c:if test="${empty article.listeEnchere }">
						<h2 class="text-primary">Il n'y a pas encore d'enchère</h2>
					</c:if>
					<c:if test="${not empty article.listeEnchere }">
						<div class="form-group row col-lg-8">
							<table
								class="table table-dark table-striped mx-auto col-form-label">
								<thead>
									<tr>
										<th scope="col">#</th>
										<th scope="col">Date & Heure</th>
										<th scope="col">Enchérisseur</th>
										<th scope="col">Montant</th>
									</tr>
								</thead>
								<tbody>
									<c:set var="Compteur">1</c:set>
									<c:forEach items="${article.listeEnchere }" var="enchere">
										
											<tr>
												<th scope="row">${Compteur }</th>
												<td>${enchere.dateEnchereString() }</td>
												<td><a	href="afficherUtilisateur?pseudo=${enchere.utilisateur.pseudo }">${enchere.utilisateur.pseudo }</a></td>
												<td class="table-active">${enchere.montant_enchere }
													Points</td>
											</tr>
										<c:set var="Compteur" value="${Compteur+1 }"></c:set>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</c:if>
				</div>
			</div>
		</main>
		<!--footer-->
		<%@ include file="layouts/footers.jsp"%>
	</div>


</body>

</html>