package fr.nonoreve.biblioParis;

import java.util.List;

import fr.nonoreve.biblioParis.doc.Document;

public class Bibliotheque {

	private String nom;
	private String adresse;
	private List<Document> documents;

	public Bibliotheque(String nom, String adresse) {
		this.nom = nom;
		this.adresse = adresse;
	}

	public void ajouterDocument(Document document) {
		this.documents.add(document);
	}

	/**
	 * Renvoie le nombre de document disponible dans la bibliotheque
	 * 
	 * @return int nb, nombre de document dans la bibliotheque
	 */
	public int getNombreDocuments() {

		return 1;
	}

	/**
	 * Liste les documents present dans la bibliotheque
	 */
	public void listerDocument() {

	}

	/**
	 * Liste les documents present dans la bibliotheque avec le nom de l'auteur
	 * comme filtre
	 * 
	 * @param nom
	 */
	public void listerDocumentNomAuteur(String nom) {

	}

	/**
	 * Liste les documents present dans la bibliotheque avec le prenom de l'auteur
	 * comme filtre
	 * 
	 * @param prenom
	 */
	public void listerDocumentPrenomAuteur(String prenom) {

	}

	/**
	 * Liste les documents present dans la bibliotheque avec le nom et prenom de
	 * l'auteur comme filtre
	 * 
	 * @param nom
	 * @param prenom
	 */
	public void listerDocumentNomPrenomAuteur(String nom, String prenom) {

	}

	/**
	 * Liste les documents present dans la bibliotheque avec l'EAN du document comme
	 * filtre
	 * 
	 * @param EAN
	 */
	public void listerDocumentEAN(String EAN) {

	}

	/**
	 * Renvoie le nombre de document par type disponible dans la bibliotheque selon
	 * un intervalle de temps donne
	 * 
	 * @param debTemps
	 * @param finTemps
	 * @return
	 */
	public int nbDocTypeTemps(String debTemps, String finTemps) {
		return 1;
	}

	public String getNom() {
		return nom;
	}

	public String getAdresse() {
		return adresse;
	}

}
