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
 * Servlet implementation class InscriptionServlet
 */
@WebServlet("/inscription")
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public InscriptionServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/inscription.jsp");
		rd.forward(request, response);
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UtilisateurManager um = new UtilisateurManager();
		Utilisateur utilisateur;
		List<Integer> listeCodesErreur=new ArrayList<>();
				
		String pseudo = request.getParameter("pseudo");
		if(pseudo.contains("@")) {
			listeCodesErreur.add(CodesResultatServlets.FORMAT_UTILISATEUR_EMAIL_ERREUR_CHAR);
		}
		try {
			for(String pseud : um.listePseudo()) {
				if(pseud.equals(pseudo)) {
					listeCodesErreur.add(CodesResultatServlets.FORMAT_UTILISATEUR_PSEUDO_ERREUR);	
				}
			}
		}catch (BusinessException e1) {
			e1.printStackTrace();
		}
			
		
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		
		try {
			for(String mail : um.listeEmail()) {
				if(mail.equals(email)) {
					listeCodesErreur.add(CodesResultatServlets.FORMAT_UTILISATEUR_EMAIL_ERREUR);
				}
			}
		} catch (BusinessException e1) {
			e1.printStackTrace();
		}
		
		String phone = request.getParameter("phone");
		String rue = request.getParameter("rue");
		String code_postal = request.getParameter("code_postal");
		String ville = request.getParameter("ville");
		String password = request.getParameter("password");
		
		utilisateur = new Utilisateur(pseudo, nom, prenom, email, phone, rue, code_postal, ville, password, false);
		if(listeCodesErreur.size()>0) {
			request.setAttribute("listeCodesErreur",listeCodesErreur);
			request.setAttribute("nom", nom);
			request.setAttribute("prenom", prenom);
			request.setAttribute("email", email);
			request.setAttribute("phone", phone);
			request.setAttribute("rue", rue);
			request.setAttribute("code_postal", code_postal);
			request.setAttribute("ville", ville);
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/inscription.jsp");
			rd.forward(request, response);
		}else {
			
			try {
				um.ajouterUtilisateur(utilisateur);
				HttpSession session = request.getSession();
				session.setAttribute("utilisateur", utilisateur);
				response.sendRedirect(request.getContextPath());
			} catch (BusinessException e) {
				e.printStackTrace();
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/inscription.jsp");
				rd.forward(request, response);
			}
			
		}
		
	}

}
