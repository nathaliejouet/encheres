package fr.eni.ENIEnchere.ihm;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ENIEnchere.BusinessException;
import fr.eni.ENIEnchere.bll.ArticleManager;
import fr.eni.ENIEnchere.bo.Article;

/**
 * Servlet implementation class RetraitEffectueServlet
 */
@WebServlet("/miseAJourRetrait")
public class RetraitEffectueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArticleManager am = new ArticleManager();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int no_article = 0;
		Article article = null;
		try {
			no_article = Integer.parseInt(request.getParameter("no_article"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		
		if(no_article != 0) {
			try {
				article = am.recupereArticle(no_article);
				article.setEtat_vente("RT");
				am.modifierArticle(article);
				
				
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		}
		
		request.setAttribute("no_article", no_article);
		
		response.sendRedirect("afficherVente?no_article=" + no_article);
	}

}
