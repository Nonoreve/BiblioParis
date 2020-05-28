package fr.nonoreve.biblioParis.doc;

public abstract class Document {

	private String ean;
	private String titre;
	private String editeur;
	private int datePublication;
	private String titreSerie;
	private Integer ordreDansSerie;
	private String prenomAuteur;
	private String nomAuteur;

	/**
	 * Cree un nouveau document toutes les valeurs peuvent etre nulles sauf ean
	 * 
	 * @param ean
	 * @param titre
	 * @param langue
	 * @param prenomAuteur
	 * @param nomAuteur
	 * @param editeur
	 * @param datePublication
	 * @param ordreDansSerie
	 */
	public Document(String ean, String titre, String editeur, String datePublication, String prenomAuteur,
			String nomAuteur, Integer ordreDansSerie) throws NullPointerException {
		if (ean != null) {
			this.ean = ean;
			this.titre = titre;
			this.prenomAuteur = prenomAuteur;
			this.nomAuteur = nomAuteur;
			this.editeur = editeur;
			this.datePublication = Integer.parseInt(datePublication.replaceAll("[^0-9]", ""));
			this.ordreDansSerie = ordreDansSerie;
		}
		throw new NullPointerException("ean field can't be null");
	}

	/**
	 * @return the ean
	 */
	public String getEan() {
		return ean;
	}

	/**
	 * @return the titre
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * @return the prenomAuteur
	 */
	public String getPrenomAuteur() {
		return prenomAuteur;
	}

	/**
	 * @return the nomAuteur
	 */
	public String getNomAuteur() {
		return nomAuteur;
	}

	/**
	 * @return the editeur
	 */
	public String getEditeur() {
		return editeur;
	}

	/**
	 * @return the datePublication
	 */
	public int getDatePublication() {
		return datePublication;
	}

	/**
	 * @return the titreSerie
	 */
	public String getTitreSerie() {
		return titreSerie;
	}

	/**
	 * @return the ordreDansSerie
	 */
	public Integer getOrdreDansSerie() {
		return ordreDansSerie;
	}

}
