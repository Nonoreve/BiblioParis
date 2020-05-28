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
	
	public void inscrire(Utilisateur util) {
		lstUtil.add(util);
	}
	
	
	public void emprunter(Document docu, Utilisateur util) {
		if (lstUtil.contains(util)) {
			if(hmDocu.containsKey(docu)) {
				if(util.getLstDocEmprunte().size() < util.getMaxEmprunt()) {
					util.getLstDocEmprunte().add(docu);
					hmDocu.replace(docu , hmDocu.get(docu) -1 );
					if (hmDocu.get(docu)==0) {
						hmDocu.remove(docu);
					}
				}
			}
		}
	}
	
	public void rendre(Document docu, Utilisateur util) {
		if (lstUtil.contains(util)) {
			if(util.getLstDocEmprunte().contains(docu)) {
				util.getLstDocEmprunte().remove(docu);
				if(hmDocu.containsKey(docu)) {
					hmDocu.replace(docu, hmDocu.get(docu) +1);
				}
				else {
					hmDocu.put(docu, 1);
				}
			}
		}
	}
	
	public void echanger(Document docu, Bibliotheque biblio) {
		if (this.getHmDocu().containsKey(docu)) {
			if (biblio.getHmDocu().containsKey(biblio)) {
				biblio.getHmDocu().replace(docu, biblio.getHmDocu().get(docu) +1);	
			}
			else {
				biblio.getHmDocu().put(docu, 1);
			}
			this.getHmDocu().replace(docu, this.getHmDocu().get(docu) -1);
			if (this.getHmDocu().get(docu)==0) {
				this.getHmDocu().remove(docu);
			}
		}
	}

	public HashMap<Document, Integer> getHmDocu() {
		return hmDocu;
	}

	public List<Utilisateur> getLstUtil() {
		return lstUtil;
	}
	
	

}
