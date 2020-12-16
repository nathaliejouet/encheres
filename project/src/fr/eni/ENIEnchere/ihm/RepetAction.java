package fr.eni.ENIEnchere.ihm;

import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

import fr.eni.ENIEnchere.bll.ArticleManager;

public class RepetAction {
	ArticleManager am = new ArticleManager();
	Timer t;

	public RepetAction() {
		t = new Timer();
		t.schedule(new modifierArticle(), 0, 1 * 60000);
	}

	class modifierArticle extends TimerTask {

	    public void run() {
	      
	        try {
				am.modifierLesArticles();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }
	}
}
