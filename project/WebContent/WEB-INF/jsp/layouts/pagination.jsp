<br><br>
<nav aria-label="Pages" class="d-flex justify-content-center">
  <ul class="pagination">
    <li class="page-item"><a class="page-link" href="">Précédent</a></li>
  	<c:forEach var="p" begin="1" end="${Pages}" step="1">
	    <li class="page-item"><a class="page-link" href="accueil?${pageContext.request.queryString }&page=${p}">${p}</a></li>
    </c:forEach>
    
    <li class="page-item"><a class="page-link" href="">Suivant</a></li>
  </ul>
</nav>