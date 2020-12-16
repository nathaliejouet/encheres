package fr.eni.ENIEnchere.bo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import fr.eni.ENIEnchere.ArticleEtat;

public class Enchere {


	private LocalDateTime date_enchere;
	private int montant_enchere;
	private Utilisateur utilisateur;
	private Article article;

	/**
	 * 
	 */
	public Enchere() {
		super();
	}

	/**
	 * @param date_enchere
	 * @param montant_enchere
	 */
	public Enchere(LocalDateTime date_enchere, int montant_enchere) {
		this.setDate_enchere(date_enchere);
		this.montant_enchere = montant_enchere;
	}

	/**
	 * @param date_enchere
	 * @param montant_enchere
	 * @param utilisateur
	 * @param article
	 */
	public Enchere(LocalDateTime date_enchere, int montant_enchere, Utilisateur utilisateur, Article article) {
		this.setDate_enchere(date_enchere);
		this.montant_enchere = montant_enchere;
		this.utilisateur = utilisateur;
		this.article = article;
	}

	/**
	 * @return the date_enchere
	 */
	public LocalDateTime getDate_enchere() {
		return date_enchere;
	}

	/**
	 * @param date_enchere the date_enchere to set
	 */
	public void setDate_enchere(LocalDateTime date_enchere) {
		this.date_enchere = date_enchere;
	}

	/**
	 * @return the montant_enchere
	 */
	public int getMontant_enchere() {
		return montant_enchere;
	}

	/**
	 * @param montant_enchere the montant_enchere to set
	 */
	public void setMontant_enchere(int montant_enchere) {
		this.montant_enchere = montant_enchere;
	}

	/**
	 * @return the utilisateur
	 */
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	/**
	 * @param utilisateur the utilisateur to set
	 */
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	/**
	 * @return the article
	 */
	public Article getArticle() {
		return article;
	}

	/**
	 * @param article the article to set
	 */
	public void setArticle(Article article) {
		this.article = article;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Enchere [date_enchere=");
		builder.append(date_enchere);
		builder.append(", montant_enchere=");
		builder.append(montant_enchere);
		builder.append(", utilisateur=");
		builder.append(utilisateur);
		builder.append(", article=");
		builder.append(article);
		builder.append("]");
		return builder.toString();
	}
	
	public String dateEnchereString() {
		return this.date_enchere.format(ArticleEtat.format);
	}

}
