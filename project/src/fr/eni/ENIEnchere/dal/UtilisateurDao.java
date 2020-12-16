package fr.eni.ENIEnchere.dal;

import java.util.List;

import fr.eni.ENIEnchere.BusinessException;
import fr.eni.ENIEnchere.bo.Article;
import fr.eni.ENIEnchere.bo.Enchere;
import fr.eni.ENIEnchere.bo.Utilisateur;

public interface UtilisateurDao {

	void add(Utilisateur utilisateur) throws BusinessException;
	void update(Utilisateur utilisateur) throws BusinessException;
	List<String> selectPseudo() throws BusinessException;
	List<String> selectEmail();
	Utilisateur selectByLogin(String login, String password) throws BusinessException;
	Utilisateur selectByPseudo(String pseudo) throws BusinessException;
	Utilisateur selectByArticle(Article a) throws BusinessException;
	void delete(int id) throws BusinessException;
	Utilisateur select_user_by_no(int no_utilisateur) throws BusinessException;
	List<Utilisateur> selectAll() throws BusinessException;
}
