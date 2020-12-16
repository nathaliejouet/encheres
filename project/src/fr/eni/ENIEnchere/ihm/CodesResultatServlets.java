package fr.eni.ENIEnchere.ihm;

/**
 * Les codes disponibles sont entre 30000 et 39999
 */
public abstract class CodesResultatServlets {
	
	/**
	 * Format Utilisateur.pseudo incorrect
	 */
	public static final int FORMAT_UTILISATEUR_PSEUDO_ERREUR=30000;
	/**
	 * Format Utilisateur.nom incorrect
	 */
	public static final int FORMAT_UTILISATEUR_NOM_ERREUR=30001;
	/**
	 * Format Utilisateur.prenom incorrect
	 */
	public static final int FORMAT_UTILISATEUR_PRENOM_ERREUR=30002;
	/**
	 * Format Utilisateur.emailincorrect
	 */
	public static final int FORMAT_UTILISATEUR_EMAIL_ERREUR=30003;
	/**
	 * Format Utilisateur.rue incorrect
	 */
	public static final int FORMAT_UTILISATEUR_RUE_ERREUR=30004;
	/**
	 * Format Utilisateur.ville incorrect
	 */
	public static final int FORMAT_UTILISATEUR_VILLE_ERREUR=30005;
	/**
	 * Format Utilisateur.code_postal incorrect
	 */
	public static final int FORMAT_UTILISATEUR_CODE_POSTAL_ERREUR=30006;
	/**
	 * Format Utilisateur.mot_de_passe incorrect
	 */
	public static final int FORMAT_UTILISATEUR_MOT_DE_PASSE_ERREUR=30007;
	/**
	 * Format Utilisateur.email contient un @
	 */
	public static final Integer FORMAT_UTILISATEUR_EMAIL_ERREUR_CHAR = 30008;
	/**
	 * Le montant de la proposition de l'enchère doit être supérieur à la meilleur enchère
	 */
	public static final Integer FORMAT_MONTANT_ENCHERE = 30009;
	/**
	 * L'utilisateur doit avoir assez de crédit
	 */
	public static final Integer FORMAT_MONTANT_CREDIT_ENCHERE = 30010;
	
	
}