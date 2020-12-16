package fr.eni.ENIEnchere.ihm;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
 * Servlet implementation class AfficherVenteServlet
 */
@WebServlet("/afficherVente")
public class AfficherVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ArticleManager am = new ArticleManager();
	EnchereManager em = new EnchereManager();
	UtilisateurManager um = new UtilisateurManager();
	boolean aDejaUneEnchere = false;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		aDejaUneEnchere = false;
		try {

			boolean enCours = false;
			boolean estTermine = false;
			boolean estRetire = false;
			String adresseLieuRetrait = null;
			Enchere enchere = null;
			Utilisateur utilisateur = null;
			int no_article = Integer.parseInt(request.getParameter("no_article"));
			Article article = am.recupereArticle(no_article);
			if(em.recupereEncheres(article).size() > 0) {
				enchere = em.recupereEncheres(article).get(0);
				utilisateur = um.trouverUtilisateurParId(enchere.getUtilisateur().getNo_utilisateur());
			}
			
			switch (article.getEtat_vente()) {
			case "EC":
				enCours = true;
				break;
			case "VD":
				estTermine = true;
				enCours = false;
				break;
			case "RT":
				estRetire = true;
				break;
			default:
				enCours = false;
				estTermine = false;
				break;
			}
			
			request.setAttribute("enCours", enCours);
			request.setAttribute("estTermine", estTermine);
			request.setAttribute("estRetire", estRetire);
			
			request.setAttribute("article", article);
			request.setAttribute("enchere", enchere);
			request.setAttribute("utilisateur", utilisateur);
			
			String adresse = article.getUtilisateur().getRue() + ", " + article.getUtilisateur().getCode_postal() + " " + article.getUtilisateur().getVille();			
			String date_fin_enchere = localDateTimeToDate(article.getDate_fin_enchere());
			request.setAttribute("date_fin_enchere", date_fin_enchere);
			
			
			try {
				adresseLieuRetrait = article.getLieuRetrait().getRue() + ", " + article.getLieuRetrait().getCode_postal() + " " + article.getLieuRetrait().getVille();
			} catch (Exception e) {
				System.out.println("pas d'adresse de retrait");
			}
		
			
			
			request.setAttribute("adresse", adresse);
			request.getRequestDispatcher("/WEB-INF/jsp/detailsVente.jsp").forward(request, response);
		} catch (BusinessException e) {
			System.out.println("Erreur lors de la recuperation de l'article");
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String pseudoMeilleurEncherisseur = request.getParameter("pseudo");
		Utilisateur u = (Utilisateur) session.getAttribute("utilisateur");
		List<Enchere> listeEnchere = null;
		int propEnchere = 0;
		int id_article = 0;
		int montantEnchereAvtModif = 0;

		try {
			propEnchere = Integer.parseInt(request.getParameter("encherir"));
			id_article = Integer.parseInt(request.getParameter("no_article"));
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		}
		Article article = null;
		try {
			article = am.recupereArticle(id_article);
		} catch (BusinessException e1) {
			e1.printStackTrace();
		}
		Enchere enchere = new Enchere(LocalDateTime.now(), propEnchere, u, article);
		Utilisateur meilleurEncherisseur = null;
		EnchereManager em = new EnchereManager();
		List<Integer> listeCodesErreur = new ArrayList<>();
		try {
			listeEnchere = em.recupereEncheres(article);
			if (listeEnchere.size() > 0) {

				for (int i = 0; i < listeEnchere.size(); i++) {
					if (listeEnchere.get(i).getUtilisateur().getNo_utilisateur() == u.getNo_utilisateur()) {
						aDejaUneEnchere = true;
					}
				}

				listeEnchere.get(0).getUtilisateur().getNo_utilisateur();
				Enchere enchereEnCours = listeEnchere.get(0);
				montantEnchereAvtModif = enchereEnCours.getMontant_enchere();
				meilleurEncherisseur = um.trouverUtilisateurParPseudo(pseudoMeilleurEncherisseur); // On récupère
																									// l'utilisateur de
																									// l'ancienne
																									// meilleure enchère

				// si le montant de la proposition est supérieur à l'enchere actuelle et si
				// l'utilisateur de la session dispose d'assez de crédits
				if (propEnchere > enchereEnCours.getMontant_enchere() && propEnchere <= u.getCredit()) {
					ajouterEnchere(u, propEnchere, enchere, meilleurEncherisseur, montantEnchereAvtModif);
					if (!meilleurEncherisseur.equals(u)) {
						modifierAncienEncherisseur(meilleurEncherisseur, montantEnchereAvtModif);
					}
				} else {
					if (propEnchere <= enchereEnCours.getMontant_enchere()) {
						listeCodesErreur.add(CodesResultatServlets.FORMAT_MONTANT_ENCHERE);
					} else {
						listeCodesErreur.add(CodesResultatServlets.FORMAT_MONTANT_CREDIT_ENCHERE);
					}
					request.setAttribute("listeCodesErreur", listeCodesErreur);
				}
			} else if (propEnchere > article.getPrix_initial() && propEnchere <= u.getCredit()) {
				ajouterEnchere(u, propEnchere, enchere, meilleurEncherisseur, montantEnchereAvtModif);
			} else {
				if (propEnchere < article.getPrix_initial()) {
					listeCodesErreur.add(CodesResultatServlets.FORMAT_MONTANT_ENCHERE);
				} else {
					listeCodesErreur.add(CodesResultatServlets.FORMAT_MONTANT_CREDIT_ENCHERE);
				}
				request.setAttribute("listeCodesErreur", listeCodesErreur);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
		}

		doGet(request, response);

	}

	private void modifierAncienEncherisseur(Utilisateur meilleurEncherisseur, int montantEnchereAvtModif)
			throws BusinessException {

		meilleurEncherisseur.setCredit(meilleurEncherisseur.getCredit() + montantEnchereAvtModif); // Ajuster le montant de crédits de l'ancien enchérisseur
		um.modifierUtilisateur(meilleurEncherisseur); // Recréditer le montant de l'enchere à l'utilisateur précédent
	}

	private void ajouterEnchere(Utilisateur u, int propEnchere, Enchere enchere, Utilisateur meilleurEncherisseur,
			int montantAvtEnchere) throws BusinessException {
		if (aDejaUneEnchere) {
			em.modifierEnchere(enchere);
		} else {
			em.ajouterEnchere(enchere);
		}
		// Créer une nouvelle enchère

		if (meilleurEncherisseur != null && meilleurEncherisseur.equals(u)) {
			u.setCredit(u.getCredit() - (propEnchere - montantAvtEnchere));
		} else {
			u.setCredit(u.getCredit() - propEnchere);// Ajuster le montant de crédits du nouvel enchérisseur
		}

		um.modifierUtilisateur(u); // Débiter le montant proposé à l'utilisateur de la session
	}

	protected String localDateTimeToDate(LocalDateTime localDateTime) {
		return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(localDateTime);
	}

}
