	<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ include file="layouts/head.jsp"%>
<body>
	<div class="container-fluid">
		<%@ include file="layouts/header.jsp"%>


		<!--filtre-->
		<form class="form-filter border mb-3" action="accueil" method="get">
			<div class="row">
				<!--Partie gauche-->
				<div class="col-md-6 mb-3">
					<div class="form-group">
						<label for="filter-input">Filtre</label> <input type="text"
							class="form-control" id="filter-input" name="Nom"
							value="${param['Nom'] }" placeholder="articles contenant...">
					</div>
					<div class="form-group">
						<label for="categories-select">Catégories</label> <select
							class="form-control" id="categories-select" name="categorie">
							<c:if test="${empty param['categorie']}">
								<option selected value="" selected>Toutes</option>
							</c:if>
							<c:if test="${not empty param['categorie']}">
								<option value="" selected>Toutes</option>
							</c:if>
							<c:forEach items="${LesCategories }" var="categorie">
								<c:if test="${categorie.no_categorie == param['categorie']}">
									<option selected value="${categorie.no_categorie }">${categorie.libelle }</option>
								</c:if>
								<c:if test="${categorie.no_categorie != param['categorie']}">
									<option value="${categorie.no_categorie }">${categorie.libelle }</option>
								</c:if>
							</c:forEach>
						</select>
					</div>
				</div>
				<!--Partie droite-->
				<div class="col-md-6 mb-3">
					<div class="form-check">
							<label class="form-check-label"> 
							<c:if test="${empty param['type-encheres'] || param['type-encheres'] eq 'achats' }">
								<input type="radio" class="form-check-input" checked name="type-encheres" value="achats" id="achats">Achats
							</c:if>
							<c:if test="${not empty param['type-encheres'] && param['type-encheres'] ne 'achats' }">
								<input type="radio" class="form-check-input" name="type-encheres" value="achats" id="achats">Achats
							</c:if>
							</label>
					</div>
					<div class="form-group">
						<div class="form-check">
							<c:if test="${param['ouvertes'] eq 'EO'  && param['type-encheres'] eq 'achats'}">
								<label class="form-check-label"> <input type="checkbox"
									class="form-check-input" checked name="ouvertes" value="EO"
									id="ouvertes">Enchères ouvertes
								</label>
							</c:if>
							<c:if test="${param['ouvertes'] ne 'EO'  || param['type-encheres'] ne 'achats'}">
								<label class="form-check-label"> <input type="checkbox"
									class="form-check-input" name="ouvertes" value="EO"
									id="ouvertes">Enchères ouvertes
								</label>
							</c:if>
						</div>


						<c:if test="${empty sessionScope.utilisateur }">
					</div>
					</c:if>


					<c:if test="${not empty sessionScope.utilisateur }">
						<div class="form-check">
							<c:if test="${param['encours'] eq 'EC'  && param['type-encheres'] eq 'achats'}">
								<label class="form-check-label"> <input type="checkbox" checked
									class="form-check-input" name="encours" value="EC" id="encours">Mes Enchères en cours </label>
							</c:if>
							<c:if test="${param['encours'] ne'EC' || param['type-encheres'] ne 'achats'  }">
								<label class="form-check-label"> <input type="checkbox"
									class="form-check-input" name="encours" value="EC" id="encours">Mes
									Enchères en cours
								</label>
							</c:if>
						</div>
						<div class="form-check">
							<label class="form-check-label"> 
							<c:if test="${param['remportees']== 'ER' && param['type-encheres'] eq 'achats'}">
								<input type="checkbox" checked class="form-check-input" name="remportees" value="ER" id="remportees">Mes Enchères remportées 
							</c:if>
							<c:if test="${param['remportees']!= 'ER' || param['type-encheres'] ne 'achats' }">
							<input type="checkbox" class="form-check-input" name="remportees" value="ER" id="remportees">Mes Enchères remportées 
							</c:if>
							</label>
						</div>
				</div>
				<div class="form-check">
					<c:if test="${param['type-encheres'] eq 'ventes' }">
						<label class="form-check-label"> <input type="radio"
							class="form-check-input" checked name="type-encheres"
							value="ventes" id="ventes">Ventes
						</label>
					</c:if>
					<c:if test="${param['type-encheres'] ne 'ventes' }">
						<label class="form-check-label"> <input type="radio"
							class="form-check-input" name="type-encheres" value="ventes"
							id="ventes">Ventes
						</label>
					</c:if>
				</div>
				<div class="form-group">
					<div class="form-check">
						<label class="form-check-label"> 
							<c:if test="${param['venteencours'] eq 'EC'  &&  param['type-encheres'] eq 'ventes' }">
								<input type="checkbox" class="form-check-input" checked name="venteencours" value="EC" id="venteencours">Mes ventes en cours
							</c:if>
							<c:if test="${param['venteencours'] ne 'EC'}">
								<input type="checkbox" class="form-check-input" name="venteencours" value="EC" id="venteencours">Mes ventes en cours
							</c:if>
						</label>
					</div>
					<div class="form-check">
						<c:if test="${param['ventescreer'] eq 'CR'}">
							<label class="form-check-label"> <input type="checkbox"
								class="form-check-input" checked name="ventescreer" value="CR"
								id="nondebutees">Mes ventes non débutées
							</label>
						</c:if>
						<c:if test="${param['ventescreer'] ne 'CR'}">
							<label class="form-check-label"> <input type="checkbox"
								class="form-check-input" name="ventescreer" value="CR"
								id="nondebutees">Mes ventes non débutées
							</label>
						</c:if>
					</div>
					<div class="form-check">
						<c:if test="${param['ventesterminees'] eq 'VD'}">
							<label class="form-check-label"> <input type="checkbox"
								class="form-check-input"  checked name="ventesterminees" value="VD"
								id="terminees">Mes ventes terminées
							</label>
						</c:if>
						<c:if test="${param['ventesterminees'] ne 'VD'}">
							<label class="form-check-label"> <input type="checkbox"
								class="form-check-input" name="ventesterminees" value="VD"
								id="terminees">Mes ventes terminées
							</label>
						</c:if>
					</div>
				</div>
				</c:if>
			</div>
	</div>
	<button class="btn btn-primary btn-lg btn-block" type="submit">
		<img class="small-icon" src="${pageContext.request.contextPath}/images/search.svg" alt="Eni Ecole">
	</button>
	</form>
	<%@ include file="layouts/pagination.jsp"%>


	<!--Enchères-->
	<div class="row justify-content-center border-top card-deck">
		<c:forEach items="${ListeArticle }" var="article">
			<div class="col-12 col-sm-6 p-2">
				<div class="card">
					<div class="card-header text-center">
						<h4 class="my-0 font-weight-normal">${article.nom_article }</h4>
					</div>
					<div class="d-flex">
						<div class="col-3 p-2">
							<c:if test="${empty article.image}">
								<img class="img-fluid img-thumbnail" src="${pageContext.request.contextPath}/images/photo.svg"
									alt="Photo non renseignée" />
							</c:if>
							<c:if test="${not empty article.image}">
								<img class="img-fluid img-thumbnail"
									src="${article.image }" alt="Photo non renseignée" />
							</c:if>
						</div>
						<ul class="col-9 list-unstyled p-2">
							<li>Prix : ${article.prix_initial} point(s)</li>
							<li>Meilleure enchère :
								${article.meilleurEncher()} points</li>
							<li>Fin de l'enchère : ${article.dateFinEnchere()}</li>
							<c:if test="${article.utilisateur.pseudo == sessionScope.utilisateur.pseudo}">
								<li>Vendeur : Vous</li>
							</c:if>
							<c:if test="${article.utilisateur.pseudo != sessionScope.utilisateur.pseudo}">
								<li>Vendeur : ${article.utilisateur.pseudo}</li>
							</c:if>
						</ul>
					</div>
					<a class="mt-3 btn btn-lg btn-block btn-primary"
						href="afficherVente?no_article=${article.no_article }"
						title="faire une enchère"> <img class="small-icon"
						src="${pageContext.request.contextPath}/images/bid.svg">
					</a>
				</div>
			</div>
		</c:forEach>
	</div>
	<%@ include file="layouts/pagination.jsp"%>
	</div>
	<%@ include file="layouts/footers.jsp"%>