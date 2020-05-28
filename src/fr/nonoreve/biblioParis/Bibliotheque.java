package fr.nonoreve.biblioParis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import fr.nonoreve.biblioParis.doc.Document;

public class Bibliotheque {

	private String nom;
	private String adresse;
	private HashMap<Document, Integer> hmDocu;
	private List<Utilisateur> lstUtil;

	public Bibliotheque(String nom, String adresse) {
		this.nom = nom;
		this.adresse = adresse;
		this.hmDocu = new HashMap<Document,Integer>();
		this.lstUtil = new ArrayList<Utilisateur>();
	}

	public void ajouterDocument(Document document,int nb) {
		if (hmDocu.containsKey(document)) {
			nb += hmDocu.get(document);
			this.hmDocu.put(document, nb);
		}
		else {
			this.hmDocu.put(document, nb);
		}
	}

	/**
	 * Liste les documents present dans la bibliotheque
	 */
	public void listerDocuments(){
		for (Document d : hmDocu.keySet()){
			System.out.println(d.toString());
		}
	}

	/**
	 * Liste les documents present dans la bibliotheque avec le nom de l'auteur
	 * comme filtre
	 * 
	 * @param nom
	 */
	public void listerDocumentsNomAuteur(String nomAuteur){
		
		for (Document d : hmDocu.keySet()){
			if (d.getNomAuteur().equals(nomAuteur))
				System.out.println(d.toString());
		}
	}

	/**
	 * Liste les documents present dans la bibliotheque avec le prenom de l'auteur
	 * comme filtre
	 * 
	 * @param prenom
	 */
	public void listerDocumentsPrenomAuteur(String prenomAuteur){
		for (Document d : hmDocu.keySet()){
			if (d.getPrenomAuteur().equals(prenomAuteur))
				System.out.println(d.toString());
		}
	}

	/**
	 * Liste les documents present dans la bibliotheque avec le nom et prenom de
	 * l'auteur comme filtre
	 * 
	 * @param nom
	 * @param prenom
	 */
	public void listerDocumentsNomPrenomAuteur(String nomAuteur, String prenomAuteur){
		for (Document d : hmDocu.keySet()){
			if (d.getNomAuteur().equals(nomAuteur)  && d.getPrenomAuteur().equals(prenomAuteur))
				System.out.println(d.toString());
		}
	}

	/**
	 * Liste les documents present dans la bibliotheque avec l'EAN du document comme
	 * filtre
	 * 
	 * @param EAN
	 */
	public void listerDocumentsEan(String ean){
		for (Document d : hmDocu.keySet()){
			if (d.getEan().equals(ean))
				System.out.println(d.toString());
		}
	}

	/**
	 * Renvoie le nombre de document par type disponible dans la bibliotheque selon
	 * un intervalle de temps donne
	 * 
	 * @param debTemps
	 * @param finTemps
	 * @return
	 */
	public void NbDocTypeSerie(int debTemps, int finTemps){
		int cptAutres = 0, cptBandeDessinee = 0, cptCarte = 0, cptCD = 0, cptJeuDeSociete = 0, cptJeuVideo = 0, cptLivre = 0, cptPartition = 0, cptRevue = 0, cptVinyle = 0;
		for (Document d : hmDocu.keySet()){
			if (d.getDatePublication()>= debTemps && d.getDatePublication()<= finTemps) {
				String typeDocu = d.getClass().getSimpleName();
				switch(typeDocu) {
				  case "Autres":
					  cptAutres++;
				    break;
				  case "BandeDessinee":
					  cptBandeDessinee++;
				    break;
				  case "Carte":
					  cptCarte++;
				    break;
				  case "CD":
					  cptCD++;
				    break;
				  case "JeuDeSociete":
					  cptJeuDeSociete++;
				    break;
				  case "JeuVideo":
					  cptJeuVideo++;
				    break;
				  case "Livre":
					  cptLivre++;
				    break;
				  case "Partition":
					  cptPartition++;
				    break;
				  case "Revue":
					  cptRevue++;
				    break;
				  case "Vinyle":
					  cptVinyle++;
				    break;
				  default:
					  System.out.print("");
				}
			}
		}
		System.out.println("Autres : " + cptAutres);
		System.out.println("BandeDessinee : " + cptBandeDessinee);
		System.out.println("Carte : " + cptCarte);
		System.out.println("CD : " + cptCD);
		System.out.println("JeuDeSociete : " + cptJeuDeSociete);
		System.out.println("JeuVideo : " + cptJeuVideo);
		System.out.println("Livre : " + cptLivre);
		System.out.println("Partition : " + cptPartition);
		System.out.println("Revue : " + cptRevue);
		System.out.println("Vinyle : " + cptVinyle);
	}

	public String getNom() {
		return nom;
	}

	public String getAdresse() {
		return adresse;
	}

}
