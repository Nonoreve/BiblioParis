package fr.nonoreve.biblioParis;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.nonoreve.biblioParis.LecteurFichier.LigneFichier;
import fr.nonoreve.biblioParis.doc.Document;

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

		Bibliotheque aimeCesaire = new Bibliotheque("Aime Cesaire", "");
		Bibliotheque edmondRostand = new Bibliotheque("Edmond Rostand", "");
		Bibliotheque jeanPierreMelville = new Bibliotheque("Jean Pierre Melville", "");
		Bibliotheque oscarWilde = new Bibliotheque("Oscar Wilde", "");
		Bibliotheque saintSimon = new Bibliotheque("Saint Simon", "");

		int pasEan = 0;
		int pasType = 0;
		int typeInconnu = 0;
		int types[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		for (LigneFichier ligne : doneesBrutes) {
			if (ligne.ean != null && ligne.ean.length() > 0) {
				if (ligne.type != null && ligne.type.length() > 0) {
					String type = ligne.type.toLowerCase();
					if (type.contains("livre")) { // 3366
						types[0]++;
						if (type.contains("jeunesse")) {
							continue;
						}
						if (type.contains("adulte")) {
							continue;
						}
						if (type.contains("fonds specialises")) {
							continue;
						}
						if (type.contains("langue etrangere")) {
							continue;
						}
						if (type.contains("gros caracteres")) {
							continue;
						}
						continue;
					}
					if (type.contains("bande dessinee")) { // 442
						types[1]++;
						continue;
					}
					if (type.contains("partition")) { // 52
						types[2]++;
						continue;
					}
					if (type.contains("carte")) { // 12
						types[3]++;
						continue;
					}
					if (type.contains("disque compact")) { // 1054
						types[4]++;
						continue;
					}
					if (type.contains("dvd")) { // 614
						types[5]++;
						if (type.contains("jeunesse")) {
							continue;
						}
						if (type.contains("dvd-video tous publics")) {
							continue;
						}
						continue;
					}
					if (type.contains("methode")) { // 74
						types[6]++;
						if (type.contains("langue")) {
							continue;
						}
						if (type.contains("musicale")) {
							continue;
						}
						continue;
					}
					if (type.contains("enregistrement musical")) { // 49
						types[7]++;
						if (type.contains("jeunesse")) {
							continue;
						}
						continue;
					}
					if (type.contains("usuels")) { // 14
						types[8]++;
						continue;
					}
					/*
					 * if (type.contains("revue")) { types[3]++;// 2 continue; } if
					 * (type.contains("vinyle")) { types[5]++;// 6 continue; } if
					 * (type.contains("jeux de societe")) { types[6]++;// 1 continue; } if
					 * (type.contains("jeux video")) { types[7]++;// 4 continue; } if
					 * (type.contains("documents numeriques et multimedia jeunesse")) {
					 * types[13]++;// 2 continue; } if (type.contains("nouveaute")) { types[14]++;//
					 * 2 continue; }
					 */
					// System.out.println(ligne.ean + " avec type " + ligne.type + " place dans
					// autres.");
					typeInconnu++;
				} else {
					System.out.println("Pas de type pour " + ligne.ean + ".");
					pasType++;
				}
			} else {
				// System.out.println("Une ligne a ete ignoree par manque d'ean.");
				pasEan++;
			}
		}
		System.out.println("Documents sans ean : " + pasEan + ". Documents sans type : " + pasType
				+ ". Documents type autres : " + typeInconnu);
		
		System.out.print("Quantite pour chaque types : ");
		for (int i = 0; i < types.length; i++) {
			System.out.print("" + types[i] + ' ');
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
	public void listerDocuments(){
		for (Document d : documents){
			System.out.println(d.toString());
		}
	}

	/**
	 * Liste les documents present dans le reseau avec le nom de l'auteur comme
	 * filtre
	 * 
	 * @param nom
	 */
	public void listerDocumentsNomAuteur(String nomAuteur){
		
		for (Document d : documents){
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
	public void listerDocumentsPrenomAuteur(String prenomAuteur){
		for (Document d : documents){
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
	public void listerDocumentsNomPrenomAuteur(String nomAuteur, String prenomAuteur){
		for (Document d : documents){
			if (d.getNomAuteur().equals(nomAuteur)  && d.getPrenomAuteur().equals(prenomAuteur))
				System.out.println(d.toString());
		}
	}

	/**
	 * Liste les documents present dans le reseau avec l'EAN du document comme
	 * filtre
	 * 
	 * @param EAN
	 */
	public void listerDocumentsEan(String ean){
		for (Document d : documents){
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
	public void NbDocTypeSerie(int debTemps, int finTemps){
		int cptAutres = 0, cptBandeDessinee = 0, cptCarte = 0, cptCD = 0, cptJeuDeSociete = 0, cptJeuVideo = 0, cptLivre = 0, cptPartition = 0, cptRevue = 0, cptVinyle = 0;
		for (Document d : documents){
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


}
