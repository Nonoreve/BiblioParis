package fr.nonoreve.biblioParis;

import java.io.File;
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
		Bibliotheque aimeCesaire = new Bibliotheque("Aime Cesaire", "");
		Bibliotheque edmondRostand = new Bibliotheque("Edmond Rostand", "");
		Bibliotheque jeanPierreMelville = new Bibliotheque("Jean Pierre Melville", "");
		Bibliotheque oscarWilde = new Bibliotheque("Oscar Wilde", "");
		Bibliotheque saintSimon = new Bibliotheque("Saint Simon", "");

		for (LigneFichier ligne : doneesBrutes) {

		}

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
