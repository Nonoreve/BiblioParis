package fr.nonoreve.biblioParis;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.nonoreve.biblioParis.LecteurFichier.LigneFichier;
import fr.nonoreve.biblioParis.doc.Autres;
import fr.nonoreve.biblioParis.doc.BandeDessinee;
import fr.nonoreve.biblioParis.doc.CD;
import fr.nonoreve.biblioParis.doc.Carte;
import fr.nonoreve.biblioParis.doc.DVD;
import fr.nonoreve.biblioParis.doc.Document;
import fr.nonoreve.biblioParis.doc.EnregistrementMusical;
import fr.nonoreve.biblioParis.doc.Livre;
import fr.nonoreve.biblioParis.doc.Methode;
import fr.nonoreve.biblioParis.doc.Partition;
import fr.nonoreve.biblioParis.doc.Revue;

public class Reseau {

	private List<Bibliotheque> bibliotheques;
	private List<Utilisateur> utilisateurs;
	private List<Document> documents;

	/**
	 * Construit le reseau a partir des donnes initiales
	 * 
	 * @param doneesBrutes
	 */
	public Reseau(List<LigneFichier> doneesBrutes) {
		this.bibliotheques = new ArrayList<Bibliotheque>();
		this.utilisateurs = new ArrayList<Utilisateur>();
		this.documents = new ArrayList<Document>();

		Bibliotheque aimeCesaire = new Bibliotheque("Aime Cesaire", "");
		Bibliotheque edmondRostand = new Bibliotheque("Edmond Rostand", "");
		Bibliotheque jeanPierreMelville = new Bibliotheque("Jean Pierre Melville", "");
		Bibliotheque oscarWilde = new Bibliotheque("Oscar Wilde", "");
		Bibliotheque saintSimon = new Bibliotheque("Saint Simon", "");

		int pasEan = 0;
		int pasType = 0;
		String typesNames[] = { "livre", "bande dessinee", "partition", "carte", "disque compact", "dvd", "methode",
				"enregistrement musical", "revue", "autres" };
		int typesQuantities[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		for (LigneFichier ligne : doneesBrutes) {
			Document doc;
			String ean;
			if (ligne.ean == null || ligne.ean.length() < 1) {
				ean = "None";
				pasEan++;
			} else {
				ean = ligne.ean;
			}
			// on peut pas utilsie de switch car on utilise String.contains()
			if (ligne.type != null && ligne.type.length() > 0) {
				String type = ligne.type.toLowerCase();
				if (type.contains(typesNames[0])) { // 5860
					typesQuantities[0]++;
					doc = new Livre(ligne.ean, ligne.titre, ligne.editeur, ligne.date, ligne.prenomAuteur,
							ligne.nomAuteur, ligne.numeroSerie);
				} else if (type.contains(typesNames[1]) || type.contains("bd")) { // 594
					typesQuantities[1]++;
					doc = new BandeDessinee(ligne.ean, ligne.titre, ligne.editeur, ligne.date, ligne.prenomAuteur,
							ligne.nomAuteur, ligne.numeroSerie);
				} else if (type.contains(typesNames[2])) { // 236
					typesQuantities[2]++;
					doc = new Partition(ligne.ean, ligne.titre, ligne.editeur, ligne.date, ligne.prenomAuteur,
							ligne.nomAuteur, ligne.numeroSerie);
				} else if (type.contains(typesNames[3])) { // 18
					typesQuantities[3]++;
					doc = new Carte(ligne.ean, ligne.titre, ligne.editeur, ligne.date, ligne.prenomAuteur,
							ligne.nomAuteur, ligne.numeroSerie);
				} else if (type.contains(typesNames[4])) { // 2257
					typesQuantities[4]++;
					doc = new CD(ligne.ean, ligne.titre, ligne.editeur, ligne.date, ligne.prenomAuteur, ligne.nomAuteur,
							ligne.numeroSerie);
				} else if (type.contains(typesNames[5])) { // 715
					typesQuantities[5]++;
					doc = new DVD(ligne.ean, ligne.titre, ligne.editeur, ligne.date, ligne.prenomAuteur,
							ligne.nomAuteur, ligne.numeroSerie);
				} else if (type.contains(typesNames[6])) { // 156
					typesQuantities[6]++;
					doc = new Methode(ligne.ean, ligne.titre, ligne.editeur, ligne.date, ligne.prenomAuteur,
							ligne.nomAuteur, ligne.numeroSerie);
				} else if (type.contains(typesNames[7])) { // 67
					typesQuantities[7]++;
					doc = new EnregistrementMusical(ligne.ean, ligne.titre, ligne.editeur, ligne.date,
							ligne.prenomAuteur, ligne.nomAuteur, ligne.numeroSerie);
				} else if (type.contains(typesNames[8])) { // 17
					typesQuantities[8]++;
					doc = new Revue(ligne.ean, ligne.titre, ligne.editeur, ligne.date, ligne.prenomAuteur,
							ligne.nomAuteur, ligne.numeroSerie);
				} else {
					// System.out.println(ean + " avec type " + ligne.type + " place dans autres.");
					typesQuantities[9]++;
					doc = new Autres(ligne.ean, ligne.titre, ligne.editeur, ligne.date, ligne.prenomAuteur,
							ligne.nomAuteur, ligne.numeroSerie);
				}
				this.documents.add(doc);
			} else {
				System.out.println("Pas de type pour " + ean + ".");
				pasType++;
				doc = new Autres(ligne.ean, ligne.titre, ligne.editeur, ligne.date, ligne.prenomAuteur, ligne.nomAuteur,
						ligne.numeroSerie);
			}
			if (ligne.exemplairesAimeCesaire != 0) {
				aimeCesaire.ajouterDocument(doc, ligne.exemplairesAimeCesaire);
			}
			if (ligne.exemplairesEdmondRostand != 0) {
				edmondRostand.ajouterDocument(doc, ligne.exemplairesEdmondRostand);
			}
			if (ligne.exemplairesJeanPierreMelville != 0) {
				jeanPierreMelville.ajouterDocument(doc, ligne.exemplairesJeanPierreMelville);
			}
			if (ligne.exemplairesOscarWilde != 0) {
				oscarWilde.ajouterDocument(doc, ligne.exemplairesOscarWilde);
			}
			if (ligne.exemplairesSaintSimon != 0) {
				saintSimon.ajouterDocument(doc, ligne.exemplairesSaintSimon);
			}
		}
		System.out.println("Documents sans ean : " + pasEan + ". Documents sans type : " + pasType);

		System.out.println("Quantite pour chaque types : ");
		for (int i = 0; i < typesQuantities.length; i++) {
			System.out.println("\t" + typesNames[i] + " : " + typesQuantities[i] + ' ');
		}
		System.out.println();

		bibliotheques.add(aimeCesaire);
		bibliotheques.add(edmondRostand);
		bibliotheques.add(jeanPierreMelville);
		bibliotheques.add(oscarWilde);
		bibliotheques.add(saintSimon);

	}

	public static void main(String[] args) {
		List<LigneFichier> doneesBrutes;
		String cheminFichier;

		if (args.length > 0) {
			if (!checkFichier(args[0]))
				return;
			cheminFichier = args[0];
		} else {
			Scanner sc = new Scanner(System.in);
			do {
				System.out.println("Entrez le nom du fichier : ");
				cheminFichier = sc.nextLine();
			} while (!checkFichier(cheminFichier));
			sc.close();
		}
		doneesBrutes = LecteurFichier.getDonneesDepuisFichierCSV(cheminFichier);
		System.out.println("Lecture de " + doneesBrutes.size() + " lignes dans " + cheminFichier);

		Reseau reseau = new Reseau(doneesBrutes);
		// TODO systeme d'interactions avec la console
		// TODO faire tous les test necessaires
	}

	private static boolean checkFichier(String cheminFichier) {
		File fichierTemp = new File(cheminFichier);
		if (!fichierTemp.exists()) {
			System.out.println("Le fichier " + cheminFichier + " est introuvable.");
			return false;
		}
		return true;
	}

	/**
	 * Liste les documents present dans le reseau
	 */
	public void listerDocuments() {
		for (Document d : documents) {
			System.out.println(d.toString());
		}
	}

	/**
	 * Liste les documents present dans le reseau avec le nom de l'auteur comme
	 * filtre
	 * 
	 * @param nom
	 */
	public void listerDocumentsNomAuteur(String nomAuteur) {

		for (Document d : documents) {
			if (d.getNomAuteur().equals(nomAuteur))
				System.out.println(d.toString());
		}
	}

	/**
	 * Liste les documents present dans le reseau avec le prenom de l'auteur comme
	 * filtre
	 * 
	 * @param prenom
	 */
	public void listerDocumentsPrenomAuteur(String prenomAuteur) {
		for (Document d : documents) {
			if (d.getPrenomAuteur().equals(prenomAuteur))
				System.out.println(d.toString());
		}
	}

	/**
	 * Liste les documents present dans le reseau avec le nom et prenom de l'auteur
	 * comme filtre
	 * 
	 * @param nom
	 * @param prenom
	 */
	public void listerDocumentsNomPrenomAuteur(String nomAuteur, String prenomAuteur) {
		for (Document d : documents) {
			if (d.getNomAuteur().equals(nomAuteur) && d.getPrenomAuteur().equals(prenomAuteur))
				System.out.println(d.toString());
		}
	}

	/**
	 * Liste les documents present dans le reseau avec l'EAN du document comme
	 * filtre
	 * 
	 * @param EAN
	 */
	public void listerDocumentsEan(String ean) {
		for (Document d : documents) {
			if (d.getEan().equals(ean))
				System.out.println(d.toString());
		}
	}

	/**
	 * Renvoie le nombre de document par type disponible dans le reseau selon un
	 * intervalle de temps donne
	 * 
	 * @param debTemps
	 * @param finTemps
	 * @return
	 */
	public void NbDocTypeSerie(int debTemps, int finTemps) {
		int cptAutres = 0, cptBandeDessinee = 0, cptCarte = 0, cptCD = 0, cptJeuDeSociete = 0, cptJeuVideo = 0,
				cptLivre = 0, cptPartition = 0, cptRevue = 0, cptVinyle = 0;
		for (Document d : documents) {
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

}
