package fr.eni.ENIEnchere.bo;

import java.time.LocalDateTime;
import java.util.List;

import fr.eni.ENIEnchere.ArticleEtat;

public class Article {

	private int no_article;
	private String nom_article;
	private String description;
	private LocalDateTime date_debut_enchere;
	private LocalDateTime date_fin_enchere;
	private int prix_initial;
	private int prix_vente;
	private String etat_vente;
	private String image;
	private Utilisateur utilisateur;
	private Categorie categorieArticle;
	private Retrait lieuRetrait;
	private List<Enchere> listeEnchere;
	
	
	/**
	 * 
	 */
	public Article() {
	}

	/**
	 * @param nom_article
	 * @param description
	 * @param date_debut_enchere
	 * @param date_fin_enchere
	 * @param prix_initial
	 * @param prix_vente
	 * @param etat_vente
	 * @param utilisateur
	 * @param categorieArticle
	 */
	public Article(String nom_article, String description, LocalDateTime date_debut_enchere, LocalDateTime date_fin_enchere,
			int prix_initial, int prix_vente, String etat_vente, Utilisateur utilisateur, Categorie categorieArticle) {
		super();
		this.nom_article = nom_article;
		this.description = description;
		this.date_debut_enchere = date_debut_enchere;
		this.date_fin_enchere = date_fin_enchere;
		this.prix_initial = prix_initial;
		this.prix_vente = prix_vente;
		this.etat_vente = etat_vente;
		this.utilisateur = utilisateur;
		this.categorieArticle = categorieArticle;
	}

	/**
	 * @param no_article
	 * @param nom_article
	 * @param description
	 * @param date_debut_enchere
	 * @param date_fin_enchere
	 * @param prix_initial
	 * @param prix_vente
	 * @param etat_vente
	 * @param utilisateur
	 * @param categorieArticle
	 */
	public Article(int no_article, String nom_article, String description, LocalDateTime date_debut_enchere,
			LocalDateTime date_fin_enchere, int prix_initial, int prix_vente, String etat_vente,
			Utilisateur utilisateur, Categorie categorieArticle) {
		super();
		this.no_article = no_article;
		this.nom_article = nom_article;
		this.description = description;
		this.date_debut_enchere = date_debut_enchere;
		this.date_fin_enchere = date_fin_enchere;
		this.prix_initial = prix_initial;
		this.prix_vente = prix_vente;
		this.etat_vente = etat_vente;
		this.utilisateur = utilisateur;
		this.categorieArticle = categorieArticle;
	}

	/**
	 * @param no_article
	 * @param nom_article
	 * @param description
	 * @param date_debut_enchere
	 * @param date_fin_enchere
	 * @param prix_initial
	 * @param prix_vente
	 * @param etat_vente
	 * @param utilisateur
	 * @param categorieArticle
	 * @param lieuRetrait
	 * @param listeEnchere
	 */
	public Article(int no_article, String nom_article, String description, LocalDateTime date_debut_enchere,
			LocalDateTime date_fin_enchere, int prix_initial, int prix_vente, String etat_vente,
			Utilisateur utilisateur, Categorie categorieArticle, Retrait lieuRetrait, List<Enchere> listeEnchere) {
		super();
		this.no_article = no_article;
		this.nom_article = nom_article;
		this.description = description;
		this.date_debut_enchere = date_debut_enchere;
		this.date_fin_enchere = date_fin_enchere;
		this.prix_initial = prix_initial;
		this.prix_vente = prix_vente;
		this.etat_vente = etat_vente;
		this.utilisateur = utilisateur;
		this.categorieArticle = categorieArticle;
		this.lieuRetrait = lieuRetrait;
		this.listeEnchere = listeEnchere;
	}

	public Article(int no_article, String nom_article, String description, LocalDateTime date_debut_enchere,
			LocalDateTime date_fin_enchere, int prix_initial, int prix_vente, String etat_vente,
			Utilisateur utilisateur, Categorie categorieArticle, Retrait lieuRetrait) {
		super();
		this.no_article = no_article;
		this.nom_article = nom_article;
		this.description = description;
		this.date_debut_enchere = date_debut_enchere;
		this.date_fin_enchere = date_fin_enchere;
		this.prix_initial = prix_initial;
		this.prix_vente = prix_vente;
		this.etat_vente = etat_vente;
		this.utilisateur = utilisateur;
		this.categorieArticle = categorieArticle;
		this.lieuRetrait = lieuRetrait;
	}


	
	public Article(int no_article) {
		this.no_article = no_article;
	}


	public Article(String nomArticle, String description, LocalDateTime dateTimes, LocalDateTime dateTimess,
			int prix_initial, String etat_vente, Utilisateur utilisateur, Categorie categorieArticle) {
		this.nom_article = nomArticle;
		this.description = description;
		this.date_debut_enchere = dateTimes ;
		this.date_fin_enchere = dateTimess ;
		this.prix_initial = prix_initial;
		this.etat_vente = etat_vente;
		this.utilisateur = utilisateur;
		this.categorieArticle = categorieArticle;
	}

	/**
	 * @return the no_article
	 */
	public int getNo_article() {
		return no_article;
	}

	/**
	 * @param no_article the no_article to set
	 */
	public void setNo_article(int no_article) {
		this.no_article = no_article;
	}

	/**
	 * @return the nom_article
	 */
	public String getNom_article() {
		return nom_article;
	}

	/**
	 * @param nom_article the nom_article to set
	 */
	public void setNom_article(String nom_article) {
		this.nom_article = nom_article;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the date_debut_enchere
	 */
	public LocalDateTime getDate_debut_enchere() {
		return date_debut_enchere;
	}

	/**
	 * @param date_debut_enchere the date_debut_enchere to set
	 */
	public void setDate_debut_enchere(LocalDateTime date_debut_enchere) {
		this.date_debut_enchere = date_debut_enchere;
	}

	/**
	 * @return the date_fin_enchere
	 */
	public LocalDateTime getDate_fin_enchere() {
		return date_fin_enchere;
	}

	/**
	 * @param date_fin_enchere the date_fin_enchere to set
	 */
	public void setDate_fin_enchere(LocalDateTime date_fin_enchere) {
		this.date_fin_enchere = date_fin_enchere;
	}

	/**
	 * @return the prix_initial
	 */
	public int getPrix_initial() {
		return prix_initial;
	}

	/**
	 * @param prix_initial the prix_initial to set
	 */
	public void setPrix_initial(int prix_initial) {
		this.prix_initial = prix_initial;
	}

	/**
	 * @return the prix_vente
	 */
	public int getPrix_vente() {
		return prix_vente;
	}

	/**
	 * @param prix_vente the prix_vente to set
	 */
	public void setPrix_vente(int prix_vente) {
		this.prix_vente = prix_vente;
	}

	/**
	 * @return the etat_vente
	 */
	public String getEtat_vente() {
		return etat_vente;
	}

	/**
	 * @param etat_vente the etat_vente to set
	 */
	public void setEtat_vente(String etat_vente) {
		this.etat_vente = etat_vente;
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
	 * @return the categorieArticle
	 */
	public Categorie getCategorieArticle() {
		return categorieArticle;
	}

	/**
	 * @param categorieArticle the categorieArticle to set
	 */
	public void setCategorieArticle(Categorie categorieArticle) {
		this.categorieArticle = categorieArticle;
	}

	/**
	 * @return the lieuRetrait
	 */
	public Retrait getLieuRetrait() {
		return lieuRetrait;
	}

	/**
	 * @param lieuRetrait the lieuRetrait to set
	 */
	public void setLieuRetrait(Retrait lieuRetrait) {
		this.lieuRetrait = lieuRetrait;
	}

	/**
	 * @return the listeEnchere
	 */
	public List<Enchere> getListeEnchere() {
		return listeEnchere;
	}

	/**
	 * @param listeEnchere the listeEnchere to set
	 */
	public void setListeEnchere(List<Enchere> listeEnchere) {
		this.listeEnchere = listeEnchere;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Article [no_article=");
		builder.append(no_article);
		builder.append(", nom_article=");
		builder.append(nom_article);
		builder.append(", description=");
		builder.append(description);
		builder.append(", date_debut_enchere=");
		builder.append(date_debut_enchere);
		builder.append(", date_fin_enchere=");
		builder.append(date_fin_enchere);
		builder.append(", prix_initial=");
		builder.append(prix_initial);
		builder.append(", prix_vente=");
		builder.append(prix_vente);
		builder.append(", etat_vente=");
		builder.append(etat_vente);
		builder.append(", utilisateur=");
		builder.append(utilisateur);
		builder.append(", categorieArticle=");
		builder.append(categorieArticle);
		builder.append(", lieuRetrait=");
		builder.append(lieuRetrait);
		builder.append(", listeEnchere=");
		builder.append(listeEnchere);
		builder.append("]");
		return builder.toString();
	}


	public String getImage() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	@Override
	public boolean equals(Object obj) {
		Boolean isEquals = true;
		Article a = (Article) obj;
		
		if (a.getNo_article()!=this.no_article) {
			isEquals=false;
		}
		
		
		return isEquals;
	}

	public String dateFinEnchere() {
		return this.date_fin_enchere.format(ArticleEtat.format);
	}
	
	public String dateDebutEnchere() {
		return this.date_debut_enchere.format(ArticleEtat.format);
	}
	
	public int meilleurEncher() {
		 int m=0;
		 try {
		 m = this.listeEnchere.get(0).getMontant_enchere();
		 } catch (Exception e) {
			 m=0;
		 }
		 return m;
	 }
}
