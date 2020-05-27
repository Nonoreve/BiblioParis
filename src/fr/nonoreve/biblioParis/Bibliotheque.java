package fr.nonoreve.biblioParis;

public class Bibliotheque {
	
	String nom;
	String adresse;
	
	public Bibliotheque(String nom,String adresse) {
		this.nom = nom;
		this.adresse = adresse;
	}
	
	/**
	 * Renvoie le nombre de document disponible dans la biblioth�que
	 * @return int nb, nombre de document dans la biblioth�que
	 */
	public int getNombreDocuments() {
		
		return 1;
	}
	
	/**
	 * Liste les documents pr�sent dans la biblioth�que
	 */
	public void listerDocument() {
		
	}
	
	/**
	 * Liste les documents pr�sent dans la biblioth�que avec le nom de l'auteur comme filtre
	 * @param nom
	 */
	public void listerDocumentNomAuteur(String nom) {
		
	}
	
	/**
	 * Liste les documents pr�sent dans la biblioth�que avec le pr�nom de l'auteur comme filtre
	 * @param prenom
	 */
	public void listerDocumentPrenomAuteur(String prenom) {
		
	}
	
	/**
	 * Liste les documents pr�sent dans la biblioth�que avec le nom et pr�nom de l'auteur comme filtre
	 * @param nom
	 * @param prenom
	 */
	public void listerDocumentNomPrenomAuteur(String nom, String prenom) {
		
	}
	
	/**
	 * Liste les documents pr�sent dans la biblioth�que avec l'EAN du document comme filtre
	 * @param EAN
	 */
	public void listerDocumentEAN(String EAN) {
		
	}
	
	/**
	 * Renvoie le nombre de document par type disponible dans la biblioth�que selon un intervalle de temps donn�
	 * @param debTemps
	 * @param finTemps
	 * @return
	 */
	public int nbDocTypeTemps(String debTemps, String finTemps) {
		return 1;
	}
	
}
