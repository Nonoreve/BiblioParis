package fr.nonoreve.biblioParis;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.nonoreve.biblioParis.LecteurFichier.LigneFichier;

public class Reseau {

	private List<Bibliotheque> bibliotheques;
	private List<Utilisateur> utilisateurs;

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
		int sousTypes[] = { 0, 0, 0, 0};
		int sousType = 0;

		for (LigneFichier ligne : doneesBrutes) {
			if (ligne.ean != null && ligne.ean.length() > 0) {
				if (ligne.type != null && ligne.type.length() > 0) {
					String type = ligne.type.toLowerCase();
					if (type.contains("livre")) {
						if (type.contains("jeunesse")) {
							sousTypes[0]++;
							continue;
						}
						if (type.contains("pour adulte")) {
							sousTypes[1]++;
							continue;
						}
						if (type.contains("fonds specialises")) {
							sousTypes[2]++;
							continue;
						}
						if (type.contains("langue etrangere")) {
							sousTypes[3]++;
							continue;
						}
						if (!type.contentEquals("livre")) {
							System.out.println("Sous type : " + type);
							sousType++;
						}
						types[0]++;
						continue;
					}
					if (type.contains("bande dessinee")) {
						types[1]++;
						continue;
					}
					if (type.contains("partition")) {
						types[2]++;
						continue;
					}
					if (type.contains("carte")) {
						types[3]++;
						continue;
					}
					if (type.contains("disque compact")) {
						types[4]++;
						continue;
					}
					if (type.contains("dvd")) {
						types[5]++;
						continue;
					}
					if (type.contains("methode")) {
						types[6]++;
						continue;
					}
					if (type.contains("enregistrement musical")) {
						types[7]++;
						continue;
					}
					if (type.contains("usuels")) {
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
				+ ". Documents type autres : " + typeInconnu + ". Sous types : " + sousType);
		
		System.out.print("Quantite pour chaque types : ");
		for (int i = 0; i < types.length; i++) {
			System.out.print("" + types[i] + ' ');
		}
		System.out.println();

		System.out.print("Quantite pour chaque sous types : ");
		for (int i = 0; i < sousTypes.length; i++) {
			System.out.print("" + sousTypes[i] + ' ');
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
	 * Renvoie le nombre de document disponible dans le reseau
	 * 
	 * @return int nb, nombre de document dans la bibliotheque
	 */
	public int getNombreDocuments() {

		return 1;
	}

	/**
	 * Liste les documents present dans le reseau
	 */
	public void listerDocument() {

	}

	/**
	 * Liste les documents present dans le reseau avec le nom de l'auteur comme
	 * filtre
	 * 
	 * @param nom
	 */
	public void listerDocumentNomAuteur(String nom) {

	}

	/**
	 * Liste les documents present dans le reseau avec le prenom de l'auteur comme
	 * filtre
	 * 
	 * @param prenom
	 */
	public void listerDocumentPrenomAuteur(String prenom) {

	}

	/**
	 * Liste les documents present dans le reseau avec le nom et prenom de l'auteur
	 * comme filtre
	 * 
	 * @param nom
	 * @param prenom
	 */
	public void listerDocumentNomPrenomAuteur(String nom, String prenom) {

	}

	/**
	 * Liste les documents present dans le reseau avec l'EAN du document comme
	 * filtre
	 * 
	 * @param EAN
	 */
	public void listerDocumentEAN(String EAN) {

	}

	/**
	 * Renvoie le nombre de document par type disponible dans le reseau selon un
	 * intervalle de temps donne
	 * 
	 * @param debTemps
	 * @param finTemps
	 * @return
	 */
	public int nbDocTypeTemps(String debTemps, String finTemps) {
		return 1;
	}

}
