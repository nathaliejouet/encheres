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

/**
 * Servlet Filter implementation class isConnectedFilter
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
				"/isConnectedFilter", 
				"/MonProfile",
				"/afficherVente"
		}, 
		servletNames = { 
				"ConnexionServlet", 
				"DeconnexionServlet", 
				"SupprimerCompteServlet", 
				"ModificationProfileServlet",
				"AfficherVenteServlet"
		})
public class isConnectedFilter implements Filter {

    /**
     * Default constructor. 
     */
    public isConnectedFilter() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest requete = (HttpServletRequest) request;
		HttpServletResponse reponce = (HttpServletResponse) response;
		
		if (requete.getMethod().equals("GET") && requete.getRequestURI().equals(requete.getContextPath()+"/afficherVente")) {
			chain.doFilter(request, response);
		}
		else if (requete.getSession().getAttribute("utilisateur")==null) {
			reponce.sendRedirect(requete.getContextPath()+"/connexion");
		}else {
			chain.doFilter(request, response);
		}
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
