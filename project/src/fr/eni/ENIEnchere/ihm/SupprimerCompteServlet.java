package fr.eni.ENIEnchere.ihm;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ENIEnchere.BusinessException;
import fr.eni.ENIEnchere.bll.ArticleManager;
import fr.eni.ENIEnchere.bll.EnchereManager;
import fr.eni.ENIEnchere.bll.UtilisateurManager;
import fr.eni.ENIEnchere.bo.Article;
import fr.eni.ENIEnchere.bo.Enchere;
import fr.eni.ENIEnchere.bo.Utilisateur;

/**
 * Servlet implementation class SupprimerCompteServlet
 */
@WebServlet("/supprimer")
public class SupprimerCompteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UtilisateurManager um = new UtilisateurManager();
	ArticleManager am = new ArticleManager();
	EnchereManager em = new EnchereManager();
	List<Enchere> listeEnchere = null;
	List<Article> listeArticle = null;
	int montant_enchere;
	int montant_avant_supression;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)																		
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur userCurrent = (Utilisateur) session.getAttribute("utilisateur");
		Utilisateur usertoDelete = null;
		
		
		try {
			if (userCurrent.isAdministrateur() && request.getRequestURI().equals(request.getContextPath()+"/supprimer")) {
				usertoDelete = um.trouverUtilisateurParPseudo(request.getParameter("pseudo"));
			} else {
				usertoDelete = (Utilisateur) session.getAttribute("utilisateur");
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		try {
			listeArticle = am.recupererParNoUtilisateur(usertoDelete);
			if (listeArticle != null) {
				for (int i = 0; i < listeArticle.size(); i++) {
					listeEnchere = em.recupereEncheres(listeArticle.get(i));

					if (listeEnchere.size() > 0) {
						montant_enchere = listeEnchere.get(0).getMontant_enchere();
						montant_avant_supression = listeEnchere.get(0).getUtilisateur().getCredit();
						listeEnchere.get(0).getUtilisateur().setCredit(montant_avant_supression + montant_enchere);
						try {
							em.deleteAll(listeEnchere.get(0));
							am.deleteById(listeArticle.get(i));
							um.modifierUtilisateur(listeEnchere.get(0).getUtilisateur());
						} catch (BusinessException e) {
							e.printStackTrace();
						}
					} else {
						try {
							am.deleteById(listeArticle.get(i));
						} catch (BusinessException e) {
							e.printStackTrace();
						}
					}
				}
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		try {
			if (userCurrent.isAdministrateur() && request.getRequestURI().equals(request.getContextPath()+"/supprimer")) {
				um.supprimerUtilisateur(usertoDelete.getNo_utilisateur());
				response.sendRedirect("admin");
			} else {
				int no_utilisateur = userCurrent.getNo_utilisateur();
				response.sendRedirect("deconnexion");
				um.supprimerUtilisateur(no_utilisateur);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}

	}

}