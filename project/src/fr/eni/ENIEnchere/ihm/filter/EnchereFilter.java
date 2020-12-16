package fr.eni.ENIEnchere.ihm.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import fr.eni.ENIEnchere.ArticleEtat;
import fr.eni.ENIEnchere.BusinessException;
import fr.eni.ENIEnchere.bll.ArticleManager;
import fr.eni.ENIEnchere.bo.Article;
import fr.eni.ENIEnchere.bo.Utilisateur;

/**
 * Servlet Filter implementation class EnchereFilter
 */
@WebFilter(
		dispatcherTypes = {
				DispatcherType.REQUEST, 
				DispatcherType.FORWARD, 
				DispatcherType.INCLUDE, 
				DispatcherType.ERROR
		}
		, 
		urlPatterns = { 
				"/",
				"/EnchereFilter", 
				"/accueil"
		}, 
		servletNames = { "AccueilServlet" })
public class EnchereFilter implements Filter {

	private List<Article> listNonFiltrer;
	private List<Article> listFiltrer;
	private Utilisateur user;
	private ArticleManager aMng;
	private Integer nb_pages;
	private int page;
	private HttpServletRequest request;
	private String no_user;
	private String etat,categorie,nom;
	private int min = 0,max = 0;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		aMng = new ArticleManager();
	}



	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		this.request = (HttpServletRequest) request;
		listNonFiltrer = new ArrayList<Article>();
		no_user="";
		categorie = request.getParameter("categorie");
		nom = request.getParameter("Nom");
		user = (Utilisateur) this.request.getSession().getAttribute("utilisateur");
		System.out.println(user);
		if(request.getParameter("type-encheres")==null || request.getParameter("type-encheres").equals("achats")) {
			generateListFiltrerArticle();
		}else {
			generateListFiltrerVente();
		}
		generatePagination();
		
		request.setAttribute("ListeArticle", listFiltrer.subList(min, max));
		request.setAttribute("Pages", nb_pages);
		chain.doFilter(request, response);

	}

	private void generateListFiltrerArticle(){
		try {
			if (request.getParameter("type-encheres") == null || (
					request.getParameter("type-encheres").equals("achats")
					&& request.getParameter("encours")==null && request.getParameter("remportees")==null 
					&& request.getParameter("ouvertes")==null)) {
				System.out.println("ALL");
				listNonFiltrer.addAll(aMng.recupererAchatParEtat(ArticleEtat.ALL, categorie, nom, no_user, false));
			}
			if (request.getParameter("encours")!=null && request.getParameter("encours").equals("EC")) {
				System.out.println("EC");
				no_user=user.getNo_utilisateur().toString();
				listNonFiltrer.addAll(aMng.recupererAchatParEtat(ArticleEtat.EC, categorie, nom, no_user, false));
			}
			if (request.getParameter("remportees")!=null && request.getParameter("remportees").equals("ER")) {
				System.out.println("ER");
				no_user=user.getNo_utilisateur().toString();
				listNonFiltrer.addAll(aMng.recupererAchatParEtat(ArticleEtat.VD, categorie, nom, no_user, false ));
			}
			if (request.getParameter("ouvertes")!=null && request.getParameter("ouvertes").equals("EO")) {
				System.out.println("EO");
				listNonFiltrer.addAll(aMng.recupererAchatParEtat(ArticleEtat.Ouvert, categorie, nom, no_user, false ));
			}
			supressionDesDoublon();
		}catch(BusinessException be) {
		}
	}
	private void generateListFiltrerVente(){
		try {
			no_user=user.getNo_utilisateur().toString();
			if (request.getParameter("type-encheres").equals("ventes") && request.getParameter("venteencours")==null && request.getParameter("ventescreer")==null 
					&& request.getParameter("ventesterminees")==null) {
				listNonFiltrer.addAll(aMng.recupererVenteParEtat(ArticleEtat.ALL, categorie, nom, no_user));
			}
			if (request.getParameter("venteencours")!=null) {
				listNonFiltrer.addAll(aMng.recupererVenteParEtat(ArticleEtat.EC, categorie, nom, no_user));
			}
			if (request.getParameter("ventescreer")!=null) {
				listNonFiltrer.addAll(aMng.recupererVenteParEtat(ArticleEtat.NonOuver, categorie, nom, no_user));
			}
			if (request.getParameter("ventesterminees")!=null) {
				listNonFiltrer.addAll(aMng.recupererVenteParEtat(ArticleEtat.VD, categorie, nom, no_user));
			}
			supressionDesDoublon();
		}catch(BusinessException be) {
			be.printStackTrace();
		}
		
	}
	
	private void supressionDesDoublon() {
		
		listFiltrer = new ArrayList<Article>();
		
		for (Article a : listNonFiltrer) {
			if (!listFiltrer.contains(a)) {
				listFiltrer.add(a);
			}
		}
				
	}
	
	private void generatePagination() {
		
		nb_pages = listFiltrer.size()/10;
		if (nb_pages==0) {nb_pages=1;}
		try {
			page = Integer.parseInt(request.getParameter("page"))-1;
			if (page>nb_pages) {
				page=0;
			}else if (page<nb_pages && page !=0) {
				page=nb_pages;
			}
		} catch (Exception e) {
			page=0;
		}

		min = page;
		max = min+9;
		if (max>listFiltrer.size()) {
			max=listFiltrer.size();
		}
	}

}