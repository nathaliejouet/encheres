package fr.eni.ENIEnchere.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.ENIEnchere.BusinessException;
import fr.eni.ENIEnchere.bo.Article;
import fr.eni.ENIEnchere.bo.Utilisateur;
public interface ArticleDao {

	void add(Article a) throws BusinessException;
	void update(Article a) throws BusinessException;
	void delete(Article a) throws BusinessException;
	void pc_updateArticle() throws SQLException;


	Article selectById(int id) throws BusinessException;
	
	List<Article> selectByEtat(String etat) throws BusinessException;
	List<Article> selectByUtilisateur(Utilisateur u) throws BusinessException;
	List<Article> selectAll() throws BusinessException;
	List<Article> selectAchatByFilter(String etat, String categorie, String nom, String no_user,Boolean vendeur) throws BusinessException;
	List<Article> selectVenteByFilter(String etat, String categorie, String nom, String no_user) throws BusinessException;
}
