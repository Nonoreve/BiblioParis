package fr.nonoreve.biblioParis.doc;

public class Document {

	private String ean;
	private String titre;
	private String langue;
	private String prenomAuteur;
	private String nomAuteur;
	private String editeur;
	private String datePublication;
	private String ordreDansSerie;

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
	public Document(String ean, String titre, String langue, String prenomAuteur, String nomAuteur, String editeur,
			String datePublication, String ordreDansSerie) throws NullPointerException {
		if (ean != null) {
			this.ean = ean;
			this.titre = titre;
			this.langue = langue;
			this.prenomAuteur = prenomAuteur;
			this.nomAuteur = nomAuteur;
			this.editeur = editeur;
			this.datePublication = datePublication;
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
	 * @return the langue
	 */
	public String getLangue() {
		return langue;
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
	public String getDatePublication() {
		return datePublication;
	}

	/**
	 * @return the ordreDansSerie
	 */
	public String getOrdreDansSerie() {
		return ordreDansSerie;
	}

}
