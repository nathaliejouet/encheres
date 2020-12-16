package fr.eni.ENIEnchere.ihm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ENIEnchere.BusinessException;
import fr.eni.ENIEnchere.bll.UtilisateurManager;
import fr.eni.ENIEnchere.bo.Utilisateur;

/**
 * Servlet implementation class MonProfil
 */
@WebServlet("/MonProfile")
public class ModificationProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurManager um = new UtilisateurManager();
    private Utilisateur sessionUser;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificationProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/MonProfile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		HttpSession session = request.getSession();
		sessionUser = (Utilisateur) session.getAttribute("utilisateur");
		modification(request, response);
	}

	
	
	private void modification(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Utilisateur utilisateur;
		List<Integer> listeCodesErreur=new ArrayList<>();
				
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String rue = request.getParameter("rue");
		String code_postal = request.getParameter("code_postal");
		String ville = request.getParameter("ville");
		String password = request.getParameter("password");
		
		
		if (!sessionUser.getPseudo().equals(pseudo)||!sessionUser.getEmail().equals(email)) {
			verif(pseudo, email, listeCodesErreur);
		}
		
		
		
		utilisateur = new Utilisateur(sessionUser.getNo_utilisateur(),pseudo, nom, prenom, email, phone, rue, code_postal, ville, password,sessionUser.getCredit(),sessionUser.isAdministrateur());
		
		
		if(listeCodesErreur.size()>0) {
			request.setAttribute("listeCodesErreur",listeCodesErreur);
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/MonProfile.jsp");
			rd.forward(request, response);
		}
		else if (!utilisateur.equals(sessionUser)) {
			try {
				um.modifierUtilisateur(utilisateur);
				HttpSession session = request.getSession();
				session.setAttribute("utilisateur", utilisateur);
				response.sendRedirect(request.getContextPath());
			} catch (BusinessException e) {
				e.printStackTrace();
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/MonProfile.jsp");
				rd.forward(request, response);
			}
		}else {
			response.sendRedirect(request.getContextPath());
		}
	}
	
	private void verif(String pseudo,String email,List<Integer> listeCodesErreur) {
		if(pseudo.contains("@")) {
			listeCodesErreur.add(CodesResultatServlets.FORMAT_UTILISATEUR_EMAIL_ERREUR_CHAR);
		}
		try {
			if(!pseudo.equals(sessionUser.getPseudo())) {
				for(String pseud : um.listePseudo()) {
					if(pseud.equals(pseudo)) {
						listeCodesErreur.add(CodesResultatServlets.FORMAT_UTILISATEUR_PSEUDO_ERREUR);	
					}
				}
			}
			if (!email.equals(sessionUser.getEmail()) ) {
				for(String mail : um.listeEmail()) {
					if(mail.equals(email)) {
						listeCodesErreur.add(CodesResultatServlets.FORMAT_UTILISATEUR_EMAIL_ERREUR);
					}
				}
			}
		}catch (BusinessException e1) {
			e1.printStackTrace();
		}
	}
}
