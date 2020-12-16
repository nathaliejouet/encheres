<%@ include file="layouts/head.jsp"%>
<body>
	<div class="container-fluid">
		<%@ include file="layouts/header.jsp"%>

		<form action="MonProfile" method="get">
			<div class="col-md-3 mx-auto" style="margin-top: 100px">
				<div class="tab-content profile-tab" id="myTabContent">
					<div class="tab-pane fade show active" id="home" role="tabpanel"
						aria-labelledby="home-tab">
						<div class="row">
							<div class="col-md-5">
								<label>Pseudo :</label>
							</div>
							<div class="col-md-7">
								<p>${user.pseudo }</p>
							</div>
						</div>
						<div class="row">
							<div class="col-md-5">
								<label>Nom :</label>
							</div>
							<div class="col-md-7">
								<p>${user.nom }</p>
							</div>
						</div>
						<div class="row">
							<div class="col-md-5">
								<label>Prénom :</label>
							</div>
							<div class="col-md-7">
								<p>${user.prenom }</p>
							</div>
						</div>
						<div class="row">
							<div class="col-md-5">
								<label>Email :</label>
							</div>
							<div class="col-md-7">
								<p>${user.email }</p>
							</div>
						</div>
						<div class="row">
							<div class="col-md-5">
								<label>Teléphone :</label>
							</div>
							<div class="col-md-7">
								<p>${user.telephone }</p>
							</div>
						</div>
						<div class="row">
							<div class="col-md-5">
								<label>Rue :</label>
							</div>
							<div class="col-md-7">
								<p>${user.rue }</p>
							</div>
						</div>
						<div class="row">
							<div class="col-md-5">
								<label>Code Postal :</label>
							</div>
							<div class="col-md-7">
								<p>${user.code_postal }</p>
							</div>
						</div>
						<div class="row">
							<div class="col-md-5">
								<label>Ville :</label>
							</div>
							<div class="col-md-7">
								<p>${user.ville }</p>
							</div>
						</div>
						<c:if test="${user.pseudo == sessionScope.utilisateur.pseudo }">

							<button type="submit" class="btn btn-lg btn-primary btn-block">Modifier</button>

						</c:if>
					</div>

				</div>
			</div>
		</form>

	</div>
</body>
<%@ include file="layouts/footers.jsp"%>
</html>