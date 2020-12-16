package fr.eni.ENIEnchere.bll;

import java.util.List;

import fr.eni.ENIEnchere.BusinessException;
import fr.eni.ENIEnchere.bo.Utilisateur;
import fr.eni.ENIEnchere.dal.CodesResultatDAL;
import fr.eni.ENIEnchere.dal.DaoFactory;
import fr.eni.ENIEnchere.dal.UtilisateurDao;

public class UtilisateurManager {

	UtilisateurDao uDao;

	public UtilisateurManager() {

		uDao = DaoFactory.getUtilisateurDao();
	}

	public void ajouterUtilisateur(Utilisateur utilisateur) throws BusinessException {

		BusinessException businessException = new BusinessException();
		validerUtilisateur(utilisateur, businessException);

		if (!businessException.hasErreurs()) {
			uDao.add(utilisateur);
		} else {
			throw businessException;
		}
	}

	public Utilisateur recupereUtilisateur(String login, String password) throws BusinessException {

		Utilisateur u = uDao.selectByLogin(login, password);

		if (u == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.VERIFI_LOGIN_ECHEC);
			throw businessException;
		}

		return u;

	}

	public void modifierUtilisateur(Utilisateur u) throws BusinessException {
		uDao.update(u);
	}

	public List<String> listePseudo() throws BusinessException {

		return uDao.selectPseudo();
	}

	public List<String> listeEmail() throws BusinessException {

		return uDao.selectEmail();
	}

	public void supprimerUtilisateur(int id) throws BusinessException {
		uDao.delete(id);
	}

	private void validerUtilisateur(Utilisateur utilisateur, BusinessException businessException)
			throws BusinessException {
		if (utilisateur != null && utilisateur.getNom() != null && utilisateur.getNom().trim().length() > 0
				&& utilisateur.getPrenom() != null && utilisateur.getPrenom().trim().length() > 0
				&& utilisateur.getEmail() != null && utilisateur.getEmail().trim().length() > 0
				&& utilisateur.getRue() != null && utilisateur.getRue().trim().length() > 0
				&& utilisateur.getCode_postal() != null && utilisateur.getCode_postal().trim().length() > 0
				&& utilisateur.getVille() != null && utilisateur.getVille().trim().length() > 0
				&& utilisateur.getMot_de_passe() != null && utilisateur.getMot_de_passe().trim().length() > 0) {

		} else {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEUR);
		}
	}

	public Utilisateur trouverUtilisateurParPseudo(String pseudo) throws BusinessException {

		return uDao.selectByPseudo(pseudo);
	}

	public Utilisateur trouverUtilisateurParId(int no_utilisateur) throws BusinessException {

		return uDao.select_user_by_no(no_utilisateur);
	}
	
	public List<Utilisateur> selectAllUsers() throws BusinessException {

		return uDao.selectAll();
	}
}
