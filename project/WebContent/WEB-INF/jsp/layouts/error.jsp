	<%@page import="fr.eni.ENIEnchere.messages.LecteurMessage"%>
	<c:if test="${not empty listeCodesErreur }">
		<!--erreur-->
		<div class="d-flex alert-danger">
			<div class="col-3 p-2">
				<img class="small-icon" src="${pageContext.request.contextPath}/images/error.svg">
			</div>
			<ul class="col-9 list-unstyled p-2">
				<c:forEach items="${listeCodesErreur }" var="l">
					<li>${LecteurMessage.getMessageErreur(l)}</li>
				</c:forEach>
			</ul>
		</div>
	</c:if>