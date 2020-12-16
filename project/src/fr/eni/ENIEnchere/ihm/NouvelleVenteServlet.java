package fr.eni.ENIEnchere.ihm;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ENIEnchere.ArticleEtat;
import fr.eni.ENIEnchere.BusinessException;
import fr.eni.ENIEnchere.bll.ArticleManager;
import fr.eni.ENIEnchere.bo.Article;
import fr.eni.ENIEnchere.bo.Categorie;
import fr.eni.ENIEnchere.bo.Retrait;
import fr.eni.ENIEnchere.bo.Utilisateur;
import fr.eni.ENIEnchere.dal.DaoFactory;

/**
 * Servlet implementation class NouvelleVenteServlet
 */
@WebServlet("/vente")
public class NouvelleVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NouvelleVenteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Categorie> listeCategorie = new ArrayList<>();
		try {
			listeCategorie = DaoFactory.getCategorieDao().selectAll();
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		request.setAttribute("Listecateg", listeCategorie);
		
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/nouvelleVente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();
		Utilisateur utilisateur = new Utilisateur();
		utilisateur = (Utilisateur) session.getAttribute("utilisateur");

		String nomArticle = request.getParameter("article");
		String description = request.getParameter("description");
		System.out.println(nomArticle);

		// Gestion des catégories

		// 1 = informatique, 2 = ameublement, 3 = vêtements, 4 = sports & loisirs

		int cat = Integer.parseInt(request.getParameter("categorie"));
		Categorie categorie = new Categorie(cat, null);

		System.out.println(cat);

		// String categorie = request.getParameter("categorie");
		// String image = request.getParameter("image");
		int prix_initial = 0;

		try {
			prix_initial = Integer.parseInt(request.getParameter("debutPrix"));

		} catch (Exception e) {

			prix_initial = 0;
		}

		// gestion de la date

		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime dateTimes = LocalDate.parse(request.getParameter("debutEnchere"), format).atStartOfDay();

		LocalDateTime dateTimess = LocalDate.parse(request.getParameter("finEnchere"), format).atStartOfDay();

		// gestion du retrait

		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");

		Retrait retrait = new Retrait(rue, codePostal, ville);

		// Article

		Article article = new Article(nomArticle, description, dateTimes, dateTimess, prix_initial, ArticleEtat.CR, utilisateur
				,categorie);
	
		System.out.println(article);

		ArticleManager am = new ArticleManager();
		try {
			am.ajouterArticle(article);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		response.sendRedirect(request.getContextPath()+"/accueil");

	}

}
