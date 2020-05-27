package fr.nonoreve.biblioParis;

public class Bibliotheque {
	
	String nom;
	String adresse;
	
	public Bibliotheque(String nom,String adresse) {
		this.nom = nom;
		this.adresse = adresse;
	}
	
	/**
	 * Renvoie le nombre de document disponible dans la bibliothèque
	 * @return int nb, nombre de document dans la bibliothèque
	 */
	public int getNombreDocuments() {
		
		return 1;
	}
	
	/**
	 * Liste les documents présent dans la bibliothèque
	 */
	public void listerDocument() {
		
	}
	
	/**
	 * Liste les documents présent dans la bibliothèque avec le nom de l'auteur comme filtre
	 * @param nom
	 */
	public void listerDocumentNomAuteur(String nom) {
		
	}
	
	/**
	 * Liste les documents présent dans la bibliothèque avec le prénom de l'auteur comme filtre
	 * @param prenom
	 */
	public void listerDocumentPrenomAuteur(String prenom) {
		
	}
	
	/**
	 * Liste les documents présent dans la bibliothèque avec le nom et prénom de l'auteur comme filtre
	 * @param nom
	 * @param prenom
	 */
	public void listerDocumentNomPrenomAuteur(String nom, String prenom) {
		
	}
	
	/**
	 * Liste les documents présent dans la bibliothèque avec l'EAN du document comme filtre
	 * @param EAN
	 */
	public void listerDocumentEAN(String EAN) {
		
	}
	
	/**
	 * Renvoie le nombre de document par type disponible dans la bibliothèque selon un intervalle de temps donné
	 * @param debTemps
	 * @param finTemps
	 * @return
	 */
	public int nbDocTypeTemps(String debTemps, String finTemps) {
		return 1;
	}
	
}
