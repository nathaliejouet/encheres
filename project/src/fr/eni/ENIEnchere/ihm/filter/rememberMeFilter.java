package fr.eni.ENIEnchere.ihm.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ENIEnchere.BusinessException;
import fr.eni.ENIEnchere.bll.UtilisateurManager;
import fr.eni.ENIEnchere.bo.Utilisateur;

/**
 * Servlet Filter implementation class rememberMeFilter
 */
@WebFilter(

		dispatcherTypes = { 
				DispatcherType.REQUEST, 
				DispatcherType.FORWARD, 
				DispatcherType.INCLUDE,
				DispatcherType.ERROR 
				}, 
		urlPatterns = { 
				"/*",
				}
		)

public class rememberMeFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public rememberMeFilter() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		
		UtilisateurManager um = new UtilisateurManager();
		HttpSession session = req.getSession();
		Cookie[] cookies = req.getCookies();
		if (cookies != null ) {
			for (Cookie c : cookies) {
				if (c.getName().equals("login")) {
					try {
						session.setAttribute("utilisateur", um.trouverUtilisateurParPseudo(c.getValue()));
					} catch (BusinessException e) {
						e.printStackTrace();
					}
				} 
			}
			
		}
		if (session.getAttribute("utilisateur")!=null){
			Utilisateur u = (Utilisateur) session.getAttribute("utilisateur");
			try {
				session.setAttribute("utilisateur", um.trouverUtilisateurParPseudo(u.getPseudo()));
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		}
		
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
