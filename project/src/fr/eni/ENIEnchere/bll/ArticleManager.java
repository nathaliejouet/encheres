package fr.eni.ENIEnchere.bll;

import java.sql.SQLException;
import java.util.List;

import fr.eni.ENIEnchere.BusinessException;
import fr.eni.ENIEnchere.bo.Article;
import fr.eni.ENIEnchere.bo.Utilisateur;
import fr.eni.ENIEnchere.dal.ArticleDao;
import fr.eni.ENIEnchere.dal.CodesResultatDAL;
import fr.eni.ENIEnchere.dal.DaoFactory;

public class ArticleManager {

	ArticleDao aDao;
	String etat, categorie, nom, no_user;
	public ArticleManager() {

		aDao = DaoFactory.getArticleDao();
	}
	
	public void ajouterArticle (Article a) throws BusinessException {
		aDao.add(a);
	}

	public Article recupereArticle(int no_article) throws BusinessException {

		Article article = aDao.selectById(no_article);

		if (article == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.RECUP_ARTICLE_ECHEC);
			throw businessException;
		}
		return article;

	}
	
	public List<Article> recupererVenteParEtat(String etat,String categorie,String nom,String no_user) throws BusinessException {
		
		verificationValue(etat, categorie, nom, no_user);
		List<Article> listEncheres = aDao.selectVenteByFilter(this.etat, this.categorie, this.nom, this.no_user);
		
		verificationList(listEncheres);
		
		
		return listEncheres;
	}
	
	public List<Article> recupererAchatParEtat(String etat,String categorie,String nom,String no_user, Boolean isVendeur) throws BusinessException {
		
		verificationValue(etat, categorie, nom, no_user);
		List<Article> listEncheres = aDao.selectAchatByFilter(this.etat, this.categorie, this.nom, this.no_user, isVendeur);
		
		verificationList(listEncheres);
		
		
		return listEncheres;
	}
	
	void verificationValue(String etat,String categorie,String nom,String no_user){
		if (etat == null || etat.equals("")) {
			this.etat="%%";
		}
		else {
			this.etat=etat;
		}
		if (categorie == null || categorie.equals("")) {
			this.categorie="%%";	
		}
		else {
			this.categorie=categorie;
		}
		if (nom == null || nom.equals("")) {
			this.nom="%%";
		}
		else {
			this.nom="%"+nom+"%";
		}
		if (no_user == null || no_user.equals("")) {
			this.no_user="%%";
		}
		else {
			this.no_user=no_user;
		}
	}
	
	void verificationList(List<Article> listEncheres) throws BusinessException {
		if (listEncheres == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.RECUP_ENCHERE_ECHEC);
			throw businessException;
		}
	}

	public List<Article> recupererParNoUtilisateur(Utilisateur u) throws BusinessException {
		return aDao.selectByUtilisateur(u);
	}

	public void deleteById(Article article) throws BusinessException {
		aDao.delete(article);
	}

	public void modifierArticle(Article a) throws BusinessException {

        aDao.update(a);
    }

	public void modifierLesArticles() throws SQLException {


		aDao.pc_updateArticle();
		
	}


}


