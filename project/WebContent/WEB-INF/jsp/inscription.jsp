<%@ include file="layouts/head.jsp" %>
<body>
	<div class="container-fluid">
		<!--emptyHeader-->
        <header>
            <nav class="pr-5 navbar navbar-expand-sm bg-dark navbar-dark align-top justify-content-between">
                <!-- Brand/logo -->
                <a class="navbar-brand" href="${pageContext.request.contextPath}">
                    <img class="small-icon" src="${pageContext.request.contextPath}/images/trocenchere.svg" alt="Accueil ENI-Encheres">
                    <strong>ENI-Encheres</strong>
                </a>
            </nav>
        </header>
		<!--main bloc-->
		<main>
			<!--title-->
			<div class="mx-auto text-center">
				<h1>Inscription</h1>
				<img class="mb-4 large-icon rounded-circle" src="${pageContext.request.contextPath}/images/user.svg"
					alt="">
			</div>
			<!-- Erreur -->
				<%@ include file="layouts/error.jsp"%>
		
			<!--formulaire-->
			<form class="form-register needs-validation" action="inscription"
				method="post" novalidate>
				<div class="row">
					<div class="col-md-6 mb-3">
						<label for="pseudo">Pseudo</label> <input type="text"
							class="form-control" id="pseudo" name="pseudo" placeholder=""
							maxlength="30" required value="${not empty pseudo ? pseudo : ''}">
						<div class="invalid-feedback">Ce champ est invalide !</div>
					</div>

					<div class="col-md-6 mb-3">
						<label for="lastname">Nom</label> <input type="text"
							class="form-control" id="nom" name="nom" placeholder=""
							value="${not empty nom ? nom : ''}" maxlength="30" required>
						<div class="invalid-feedback">Ce champ est invalide !</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-6 mb-3">
						<label for="firstname">Prénom</label> <input type="text"
							class="form-control" id="prenom" name="prenom" placeholder=""
							value="${not empty prenom ? prenom : ''}" maxlength="30" required>
						<div class="invalid-feedback">Ce champ est invalide !</div>
					</div>

					<div class="col-md-6 mb-3">
						<label for="email">Email</label> <input type="email"
							class="form-control" id="email" name="email"
							placeholder="you@example.com"
							value="${not empty email ? email : ''}" maxlength="50" required>
						<div class="invalid-feedback">Ce champ est invalide !</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-4 mb-3">
						<label for="phone">Téléphone <span class="text-muted">(Optional)</span></label>
						<input type="text" class="form-control" id="phone" name="phone"
							placeholder="" value="${not empty phone ? phone : ''}"
							maxlength="15">
					</div>
					<div class="col-md-8 mb-3">
						<label for="street">Rue</label> <input type="text"
							class="form-control" id="rue" name="rue" placeholder=""
							value="${not empty rue ? rue : ''}" maxlength="30" required>
						<div class="invalid-feedback">Ce champ est invalide !</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-4 mb-3">
						<label for="zipcode">Code postal</label> <input type="number"
							class="form-control" id="code_postal" name="code_postal"
							placeholder="" min="01000" max="99999"
							value="${not empty code_postal ? code_postal : ''}" required>
						<div class="invalid-feedback">Ce champ est invalide !</div>
					</div>
					<div class="col-md-8 mb-3">
						<label for="city">Ville</label> <input type="text"
							class="form-control" id="ville" name="ville" placeholder=""
							maxlength="30" value="${not empty ville ? ville : ''}" required>
						<div class="invalid-feedback">Ce champ est invalide !</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-6 mb-3">
						<label for="password">Mot de passe</label> <input type="password"
							class="form-control" id="password" name="password" placeholder=""
							minlength="6" maxlength="30" value="" required>
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
				<button class="btn btn-primary btn-lg btn-block" type="submit">Créer
					mon compte</button>
					<hr class="mb-4">
			<a href="connexion"><buton class="btn btn-sm btn-secondary btn-block">J'ai déjà compte.</buton></a>
			</form>
		</main>
		<!--footer-->

	<%@ include file="layouts/footers.jsp" %>
	</div>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
	<script>
		// Example starter JavaScript for disabling form submissions if there are invalid fields
		(function() {
			'use strict';

			window
					.addEventListener(
							'load',
							function() {
								// Fetch all the forms we want to apply custom Bootstrap validation styles to
								var forms = document
										.getElementsByClassName('needs-validation');

								// Loop over them and prevent submission
								var validation = Array.prototype.filter
										.call(
												forms,
												function(form) {
													form
															.addEventListener(
																	'submit',
																	function(
																			event) {
																		//validation du mot de passe
																		var password = document
																				.getElementById("password"), confirm_password = document
																				.getElementById("confirm_password");
																		if (password.value != confirm_password.value) {
																			confirm_password
																					.setCustomValidity("Les mots de passe sont différents");
																			event
																					.preventDefault();
																			event
																					.stopPropagation();
																		} else {
																			confirm_password
																					.setCustomValidity('');
																		}
																		//validations des saisies obligatoires
																		if (form
																				.checkValidity() === false) {
																			event
																					.preventDefault();
																			event
																					.stopPropagation();
																		}
																		form.classList
																				.add('was-validated');
																	}, false);
												});
							}, false);
		})();
	</script>
</body>

</html>