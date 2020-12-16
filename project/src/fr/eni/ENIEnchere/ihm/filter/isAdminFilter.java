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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ENIEnchere.bo.Utilisateur;

/**
 * Servlet Filter implementation class isAdminFilter
 */
@WebFilter(dispatcherTypes = { 
		
		DispatcherType.REQUEST, 
		DispatcherType.FORWARD, 
		DispatcherType.INCLUDE,
		DispatcherType.ERROR 
		}, 

	urlPatterns = { 
			"/isAdminFilter",
			"/admin" 
			}, 
	
	servletNames = { 
			"AdministrateurServlet" 
			})

public class isAdminFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public isAdminFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest requete = (HttpServletRequest) request;
		HttpServletResponse reponce = (HttpServletResponse) response;
		Utilisateur userCurrent = (Utilisateur) requete.getSession().getAttribute("utilisateur");

		if (userCurrent != null && userCurrent.isAdministrateur()) {
			chain.doFilter(request, response);
		} else {
			reponce.sendRedirect(requete.getContextPath() + "/connexion");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
