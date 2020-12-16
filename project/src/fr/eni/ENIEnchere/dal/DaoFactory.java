package fr.eni.ENIEnchere.dal;

import fr.eni.ENIEnchere.dal.jdbc.ArticleDaoJdbcImpl;
import fr.eni.ENIEnchere.dal.jdbc.CategorieDaoJdbcImpl;
import fr.eni.ENIEnchere.dal.jdbc.EnchereDaoJdbcImpl;
import fr.eni.ENIEnchere.dal.jdbc.RetraitDaoJdbcImpl;
import fr.eni.ENIEnchere.dal.jdbc.UtilisateurDaoJdbcImpl;

public class DaoFactory {

	public static UtilisateurDao getUtilisateurDao() {
		return new UtilisateurDaoJdbcImpl();
	}
	
	public static ArticleDao getArticleDao() {
		return new ArticleDaoJdbcImpl();
	}
	
	public static RetraitDao getRetraitDao() {
		return new RetraitDaoJdbcImpl();
	}
	
	public static CategorieDao getCategorieDao() {
		return new CategorieDaoJdbcImpl();
	}
	
	public static EnchereDao getEnchereDao() {
		return new EnchereDaoJdbcImpl();
	}

}
