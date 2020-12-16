<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="layouts/head.jsp"%>
<body>
	<div class="container-fluid">
		<%@ include file="layouts/header.jsp"%>
		<c:if test="${ users.size() > 1}">
			<div class="row" style="padding: 2rem 0rem">
				<div class="col-12">
					<table class="table table-bordered" style="vertical-align: middle">
						<thead>
							<tr>
								<th scope="col">Utilisateurs</th>
								<th scope="col">Actions</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${ users }" var="u">
								<c:if test="${u.administrateur != true }">

									<tr>
										<td>${u.pseudo }</td>
										<td>
										<a href="supprimer?pseudo=${u.pseudo }">
													<button onclick="return confirm('Êtes-vous sûr de vouloir supprimer cet utilisateur ?');" type="submit" class="btn btn-danger"
														name="btnsupprimer">
														<i class="far fa-trash-alt"></i>
													</button>
										</a>
										</td>
									</tr>
								</c:if>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</c:if>
	</div>
	<c:if test="${ users.size() == 1}">
		<div class="mx-auto text-center" style="margin-top: 100px">
			<p>Aucun utilisateur à afficher</p>
		</div>
	</c:if>

</body>
</html>