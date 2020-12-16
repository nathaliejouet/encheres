package fr.eni.ENIEnchere.bo;

import java.util.List;

public class Categorie {

	private int no_categorie;
	private String libelle;
	private List<Article> listeArticle;

	/**
	 * 
	 */
	public Categorie() {
	}

	public Categorie(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * @param libelle
	 * @param listeArticle
	 */
	public Categorie(String libelle, List<Article> listeArticle) {
		this.libelle = libelle;
		this.listeArticle = listeArticle;
	}

	/**
	 * @param no_categorie
	 * @param libelle
	 * @param listeArticle
	 */
	public Categorie(int no_categorie, String libelle, List<Article> listeArticle) {
		super();
		this.no_categorie = no_categorie;
		this.libelle = libelle;
		this.listeArticle = listeArticle;
	}
	
	/**
	 * @param no_categorie
	 * @param libelle
	 */
	public Categorie(int no_categorie, String libelle) {
		super();
		this.no_categorie = no_categorie;
		this.libelle = libelle;
	}

	/**
	 * @return the no_categorie
	 */
	public int getNo_categorie() {
		return no_categorie;
	}

	/**
	 * @param no_categorie the no_categorie to set
	 */
	public void setNo_categorie(int no_categorie) {
		this.no_categorie = no_categorie;
	}

	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * @return the listeArticle
	 */
	public List<Article> getListeArticle() {
		return listeArticle;
	}

	/**
	 * @param listeArticle the listeArticle to set
	 */
	public void setListeArticle(List<Article> listeArticle) {
		this.listeArticle = listeArticle;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Categorie [no_categorie=");
		builder.append(no_categorie);
		builder.append(", libelle=");
		builder.append(libelle);
		builder.append(", listeArticle=");
		builder.append(listeArticle);
		builder.append("]");
		return builder.toString();
	}

}
