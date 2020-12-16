package fr.eni.ENIEnchere.ihm;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Servlet implementation class ContextListener
 */
@WebListener
public class ContextListener implements ServletContextListener {
       
	@Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("The application started");
        RepetAction r = new RepetAction();
    }
     
    @Override
    public void contextDestroyed(ServletContextEvent event) {
        System.out.println("The application stopped");
    }

}

