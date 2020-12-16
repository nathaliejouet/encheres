package fr.eni.ENIEnchere.bo;

public class Retrait {

	private Article article;
	private String rue;
	private String code_postal;
	private String ville;
	
	
	/**
	 * 
	 */
	public Retrait() {
	}
	
	/**
	 * @param rue
	 * @param code_postal
	 * @param ville
	 */
	public Retrait(String rue, String code_postal, String ville) {
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
	}

	/**
	 * @param article
	 * @param rue
	 * @param code_postal
	 * @param ville
	 */
	public Retrait(Article article, String rue, String code_postal, String ville) {
		this.article = article;
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
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

	
	/**
	 * @return the rue
	 */
	public String getRue() {
		return rue;
	}

	
	/**
	 * @param rue the rue to set
	 */
	public void setRue(String rue) {
		this.rue = rue;
	}

	
	/**
	 * @return the code_postal
	 */
	public String getCode_postal() {
		return code_postal;
	}

	
	/**
	 * @param code_postal the code_postal to set
	 */
	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}

	
	/**
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}

	
	/**
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}

	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Retrait [article=");
		builder.append(article);
		builder.append(", rue=");
		builder.append(rue);
		builder.append(", code_postal=");
		builder.append(code_postal);
		builder.append(", ville=");
		builder.append(ville);
		builder.append("]");
		return builder.toString();
	}

	
}
