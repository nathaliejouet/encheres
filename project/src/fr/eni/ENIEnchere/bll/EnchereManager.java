package fr.eni.ENIEnchere.bll;

import java.util.List;

import fr.eni.ENIEnchere.BusinessException;
import fr.eni.ENIEnchere.bo.Article;
import fr.eni.ENIEnchere.bo.Enchere;
import fr.eni.ENIEnchere.bo.Utilisateur;
import fr.eni.ENIEnchere.dal.CodesResultatDAL;
import fr.eni.ENIEnchere.dal.DaoFactory;
import fr.eni.ENIEnchere.dal.EnchereDao;

public class EnchereManager {
	EnchereDao eDao;

	public EnchereManager() {

		eDao = DaoFactory.getEnchereDao();
	}

	public List<Enchere> recupereEncheres(Article article) throws BusinessException {

		List<Enchere> listEncheres = eDao.selectByArticle(article);

		if (listEncheres == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.RECUP_ENCHERE_ECHEC);
			throw businessException;
		}

		return listEncheres;

	}
	
	public List<Enchere> recupererParEtat(String etat,String categorie,String nom,String no_user) throws BusinessException {
		
		etat = etat==null? "%%":etat;
		categorie = categorie==null? "%%":categorie;
		nom = nom==null? "":nom;
		no_user = no_user==null? "%%":no_user;
		List<Enchere> listEncheres = eDao.selectByFilter(etat, categorie, nom, no_user);

		if (listEncheres == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.RECUP_ENCHERE_ECHEC);
			throw businessException;
		}

		return listEncheres;
	}

	public void ajouterEnchere(Enchere enchere) throws BusinessException {

		eDao.add(enchere);
	}

	public void modifierEnchere(Enchere enchere) throws BusinessException {

		eDao.update(enchere);

	}

	public void supprimerEnchere(Enchere enchere) throws BusinessException {

		eDao.delete(enchere);

	}

	public void deleteAll(Enchere enchere) throws BusinessException {

		eDao.deleteAll(enchere);

	}

}
