package fr.eni.ENIEnchere.dal;

import java.util.List;

import fr.eni.ENIEnchere.BusinessException;
import fr.eni.ENIEnchere.ArticleEtat;
import fr.eni.ENIEnchere.bo.Article;
import fr.eni.ENIEnchere.bo.Enchere;
import fr.eni.ENIEnchere.bo.Utilisateur;

public interface EnchereDao {

	void add(Enchere e) throws BusinessException;
	void update(Enchere e) throws BusinessException;
	void delete(Enchere e) throws BusinessException;
	void deleteAll(Enchere e) throws BusinessException;
	
	List<Enchere> selectByFilter(String etat,String categorie,String nom, String  no_user) throws BusinessException;
	List<Enchere> selectByArticle(Article a) throws BusinessException;
	List<Enchere> selectByUtilisateur(Utilisateur u) throws BusinessException;
	List<Enchere> selectAll() throws BusinessException;
	List<Enchere> selectByFilter(String etat, String categorie, String nom) throws BusinessException;
}
