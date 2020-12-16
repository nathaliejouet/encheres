<%@ include file="layouts/head.jsp" %>
<body>
	<div class="container-fluid">
	<%@ include file="layouts/header.jsp" %>
		<!--main bloc-->
		<main>
			<!--title-->
			<div class="mx-auto text-center">
				<h1>Mon Profile</h1>
				<img class="mb-4 large-icon rounded-circle"
					src="${pageContext.request.contextPath}/images/user.svg" alt="">
			</div>
			<!-- Erreur -->
			<%@ include file="layouts/error.jsp"%>

			<!--formulaire-->
			<form class="form-register needs-validation" action="MonProfile"
				method="post" novalidate>
				<div class="row">
					<div class="col-md-6 mb-3">
						<label for="pseudo">Pseudo</label> <input type="text"
							class="form-control" id="pseudo" name="pseudo" placeholder=""
							maxlength="30" required
							value="${sessionScope.utilisateur.pseudo}">
						<div class="invalid-feedback">Ce champ est invalide !</div>
					</div>

					<div class="col-md-6 mb-3">
						<label for="lastname">Nom</label> <input type="text"
							class="form-control" id="nom" name="nom" placeholder=""
							value="${sessionScope.utilisateur.nom}" maxlength="30" required>
						<div class="invalid-feedback">Ce champ est invalide !</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-6 mb-3">
						<label for="firstname">Prénom</label> <input type="text"
							class="form-control" id="prenom" name="prenom" placeholder=""
							value="${sessionScope.utilisateur.prenom}" maxlength="30"
							required>
						<div class="invalid-feedback">Ce champ est invalide !</div>
					</div>

					<div class="col-md-6 mb-3">
						<label for="email">Email</label> <input type="email"
							class="form-control" id="email" name="email"
							placeholder="you@example.com"
							value="${sessionScope.utilisateur.email}" maxlength="50" required>
						<div class="invalid-feedback">Ce champ est invalide !</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-4 mb-3">
						<label for="phone">Téléphone <span class="text-muted">(Optional)</span></label>
						<input type="text" class="form-control" id="phone" name="phone"
							placeholder="" value="${sessionScope.utilisateur.telephone}"
							maxlength="15">
					</div>
					<div class="col-md-8 mb-3">
						<label for="street">Rue</label> <input type="text"
							class="form-control" id="rue" name="rue" placeholder=""
							value="${sessionScope.utilisateur.rue}" maxlength="30" required>
						<div class="invalid-feedback">Ce champ est invalide !</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-4 mb-3">
						<label for="zipcode">Code postal</label> <input type="number"
							class="form-control" id="code_postal" name="code_postal"
							placeholder="" min="01000" max="99999"
							value="${sessionScope.utilisateur.code_postal}" required>
						<div class="invalid-feedback">Ce champ est invalide !</div>
					</div>
					<div class="col-md-8 mb-3">
						<label for="city">Ville</label> <input type="text"
							class="form-control" id="ville" name="ville" placeholder=""
							maxlength="30" value="${sessionScope.utilisateur.ville}" required>
						<div class="invalid-feedback">Ce champ est invalide !</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-6 mb-3">
						<label for="password">Mot de passe</label> <input type="password"
							class="form-control" id="password" name="password" placeholder=""
							minlength="6" maxlength="30"
							value="${sessionScope.utilisateur.mot_de_passe}" required>
						<div class="invalid-feedback">Ce champ est invalide !</div>
					</div>

					<div class="col-md-6 mb-3">
						<label for="confirm_password">Confirmation</label> <input
							type="password" class="form-control" id="confirm_password"
							name="confirm_password" placeholder="" required>
						<div class="invalid-feedback">Ce champ est invalide ou les
							mots de passe sont différents !</div>
					</div>
				</div>
				<hr class="mb-4">
				<button class="btn btn-primary btn-lg btn-block" type="submit">Modifier
					mes informations</button>
				<button class="btn btn-danger btn-lg btn-block" data-toggle="modal"
					data-target="#validation-supression">Supression du compte</button>

			</form>

			<!-- Supression -->
			<div class="modal fade" id="validation-supression" tabindex="-1"
				aria-labelledby="validation-supression" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title">Attention !</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<form action="supprimer" method="get">
							<div class="modal-body">
								<p>
									<strong>Vos informations seront définitivement perdues
										!</strong><br>Êtes vous sûr de vouloir supprimer votre compte ?
								</p>
								<div class="col-md-6 mb-3">
									<label for="confirm_password">Mot de Passe</label> <input
										type="password" class="form-control" id="confirm_password"
										name="confirm_password" placeholder="" required>
									<div class="invalid-feedback">Ce champ est invalide ou
										les mots de passe sont différents !</div>
								</div>
							</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Annuler</button>
							<button type="submit" class="btn btn-danger">Suprimer</button>
						</div>
						</form>
					</div>
				</div>
			</div>
		</main>
		<!--footer-->

	<%@ include file="layouts/footers.jsp" %>
	</div>

	
</body>

</html>