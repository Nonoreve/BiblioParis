package fr.nonoreve.biblioParis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import fr.nonoreve.biblioParis.doc.Document;
import fr.nonoreve.biblioParis.doc.ISBNable;

public class Bibliotheque {

	private String nom;
	private String adresse;
	private int maxEmprunt;
	private HashMap<Document, Integer> hmDocu;
	private List<Utilisateur> lstUtil;

	public Bibliotheque(String nom, String adresse, int maxEmprunt) {
		this.nom = nom;
		this.adresse = adresse;
		this.maxEmprunt = maxEmprunt;
		this.hmDocu = new HashMap<Document, Integer>();
		this.lstUtil = new ArrayList<Utilisateur>();
	}

	/**
	 * ajoute un document a la bibliothequqe
	 * 
	 * @param document
	 * @param nb
	 */
	public void ajouterDocument(Document document, int nb) {
		if (hmDocu.containsKey(document)) {
			nb += hmDocu.get(document);
			this.hmDocu.put(document, nb);
		} else {
			this.hmDocu.put(document, nb);
		}
	}

	/**
	 * Liste les documents present dans la bibliotheque
	 */
	public List<Document> rechercherDocuments() {
		List<Document> result = new ArrayList<Document>();
		for (Document d : hmDocu.keySet()) {
			result.add(d);
		}
		return result;
	}

	/**
	 * Liste les documents present dans la bibliotheque avec le nom de l'auteur
	 * comme filtre
	 * 
	 * @param nomAuteur
	 */
	public List<Document> rechercherDocumentsNomAuteur(String nomAuteur) {
		List<Document> result = new ArrayList<Document>();
		for (Document d : hmDocu.keySet()) {
			if (d.getNomAuteur().equals(nomAuteur))
				result.add(d);
		}
		return result;
	}

	/**
	 * Liste les documents present dans la bibliotheque avec le prenom de l'auteur
	 * comme filtre
	 * 
	 * @param prenomAuteur
	 */
	public List<Document> rechercherDocumentsPrenomAuteur(String prenomAuteur) {
		List<Document> result = new ArrayList<Document>();
		for (Document d : hmDocu.keySet()) {
			if (d.getPrenomAuteur().equals(prenomAuteur))
				result.add(d);
		}
		return result;
	}

	/**
	 * Liste les documents present dans la bibliotheque avec le nom et prenom de
	 * l'auteur comme filtre
	 * 
	 * @param nomAuteur
	 * @param prenomAuteur
	 */
	public List<Document> rechercherDocumentsNomPrenomAuteur(String nomAuteur, String prenomAuteur) {
		List<Document> result = new ArrayList<Document>();
		for (Document d : hmDocu.keySet()) {
			if (d.getNomAuteur().equals(nomAuteur) && d.getPrenomAuteur().equals(prenomAuteur))
				result.add(d);
		}
		return result;
	}

	/**
	 * Liste les documents present dans la bibliotheque avec l'EAN du document comme
	 * filtre
	 * 
	 * @param EAN
	 */
	public List<Document> rechercherDocumentsEan(String ean) {
		List<Document> result = new ArrayList<Document>();
		for (Document d : hmDocu.keySet()) {
			if (d.getEan().equals(ean))
				result.add(d);
		}
		return result;
	}

	/**
	 * Liste les documents present dans la bibliotheque avec l'ISBN du document
	 * comme filtre
	 * 
	 * @param ISBN
	 */
	public List<Document> rechercherDocumentsIsbn(String isbn) {
		List<Document> result = new ArrayList<Document>();
		for (Document d : hmDocu.keySet()) {
			if (d instanceof ISBNable) {
				ISBNable i = (ISBNable) d;
				if (i.getISBN().contentEquals(isbn))
					result.add(d);
			}
		}
		return result;
	}

	/**
	 * Renvoie le nombre de document par type disponible dans la bibliotheque selon
	 * un intervalle de temps donne
	 * 
	 * @param debTemps
	 * @param finTemps
	 * @return
	 */
	public void NbDocTypeSerie(int debTemps, int finTemps) {
		int cptAutres = 0, cptBandeDessinee = 0, cptCarte = 0, cptCD = 0, cptDVD = 0, cptEnregistrementMusical = 0,
				cptLivre = 0, cptPartition = 0, cptRevue = 0, cptMethode = 0;
		for (Document d : hmDocu.keySet()) {
			if (!(d.getDatePublication().equals("?"))) {
				int datePublicationInt = Integer.parseInt(d.getDatePublication().replaceAll("[^0-9]", ""));
				if (datePublicationInt >= debTemps && datePublicationInt <= finTemps) {
					String typeDocu = d.getClass().getSimpleName();
					switch (typeDocu) {
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
					case "DVD":
						cptDVD++;
						break;
					case "EnregistrementMusical":
						cptEnregistrementMusical++;
						break;
					case "Livre":
						cptLivre++;
						break;
					case "Methode":
						cptMethode++;
						break;
					case "Partition":
						cptPartition++;
						break;
					case "Revue":
						cptRevue++;
						break;

					default:
						System.out.print("");
					}
				}
			}

		}
		System.out.println("Autres : " + cptAutres);
		System.out.println("BandeDessinee : " + cptBandeDessinee);
		System.out.println("Carte : " + cptCarte);
		System.out.println("CD : " + cptCD);
		System.out.println("DVD : " + cptDVD);
		System.out.println("EnregistrementMusical : " + cptEnregistrementMusical);
		System.out.println("Livre : " + cptLivre);
		System.out.println("Methode : " + cptMethode);
		System.out.println("Partition : " + cptPartition);
		System.out.println("Revue : " + cptRevue);

	}

	public String getNom() {
		return nom;
	}

	public String getAdresse() {
		return adresse;
	}

	/**
	 * inscrit une personne en tant qu'utlisateur a la bibliotheque
	 * 
	 * @param pers
	 * @return
	 */
	public Utilisateur inscrire(Personne pers) {
		Utilisateur util = new Utilisateur(pers, this, this.maxEmprunt);
		pers.getLstCarte().add(util);
		lstUtil.add(util);
		return util;
	}

	/**
	 * permet de donner un livre a une autre bibliotheque
	 * 
	 * @param docu
	 * @param biblio
	 */
	public void echanger(Document docu, Bibliotheque biblio) {
		if (this.getHmDocu().containsKey(docu)) {
			if (biblio.getHmDocu().containsKey(docu)) {
				biblio.getHmDocu().replace(docu, biblio.getHmDocu().get(docu) + 1);
			} else {
				biblio.getHmDocu().put(docu, 1);
			}
			this.getHmDocu().replace(docu, this.getHmDocu().get(docu) - 1);
			if (this.getHmDocu().get(docu) == 0) {
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

	/**
	 * Liste les documents d'une bibliotheque d'une serie en parametre
	 * 
	 * @param serie
	 */
	public List<Document> rechercherDocumentsSerie(String serie) {
		List<Document> lstDocuTrie = new ArrayList<Document>();
		for (Document d : hmDocu.keySet()) {
			if (d.getTitreSerie().equals(serie))
				lstDocuTrie.add(d);
		}
		Collections.sort(lstDocuTrie, new Comparator<Document>() {
			@Override
			public int compare(Document o1, Document o2) {
				return o1.getDatePublication().compareTo(o2.getDatePublication());
			}
		});
		return lstDocuTrie;
	}

}
