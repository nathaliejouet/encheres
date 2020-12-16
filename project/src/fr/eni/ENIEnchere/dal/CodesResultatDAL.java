package fr.eni.ENIEnchere.dal;

/**
 * Les codes disponibles sont entre 10000 et 19999
 */
public abstract class CodesResultatDAL {
	
	/**
	 * Echec général quand tentative d'ajouter un objet null
	 */
	public static final int INSERT_OBJET_NULL=10000;
	
	/**
	 * Echec général quand erreur non gérée à l'insertion 
	 */
	public static final int INSERT_OBJET_ECHEC=10001;
	
	/**
	 * Echec général quand erreur non gérée � la v�rification login/mp 
	 */
	
	public static final int VERIFI_LOGIN_ECHEC=10002;
	
	public static final int RECUP_ARTICLE_ECHEC=10003;
	public static final int RECUP_ENCHERE_ECHEC=10004;

	/**
	 * Echec general quand une erreur à la modification
	 */
	public static final int UPDATE_OBJET_ECHEC = 10005;
}