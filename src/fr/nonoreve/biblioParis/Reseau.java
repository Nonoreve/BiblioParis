package fr.nonoreve.biblioParis;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import fr.nonoreve.biblioParis.LecteurFichier.LigneFichier;

public class Reseau {

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
	 * Renvoie le nombre de document disponible dans le réseau
	 * @return int nb, nombre de document dans la bibliothèque
	 */
	public int getNombreDocuments() {
		
		return 1;
	}
	
	/**
	 * Liste les documents présent dans le réseau
	 */
	public void listerDocument() {
		
	}
	
	/**
	 * Liste les documents présent dans le réseau avec le nom de l'auteur comme filtre
	 * @param nom
	 */
	public void listerDocumentNomAuteur(String nom) {
		
	}
	
	/**
	 * Liste les documents présent dans le réseau avec le prénom de l'auteur comme filtre
	 * @param prenom
	 */
	public void listerDocumentPrenomAuteur(String prenom) {
		
	}
	
	/**
	 * Liste les documents présent dans le réseau avec le nom et prénom de l'auteur comme filtre
	 * @param nom
	 * @param prenom
	 */
	public void listerDocumentNomPrenomAuteur(String nom, String prenom) {
		
	}
	
	/**
	 * Liste les documents présent dans le réseau avec l'EAN du document comme filtre
	 * @param EAN
	 */
	public void listerDocumentEAN(String EAN) {
		
	}
	
	/**
	 * Renvoie le nombre de document par type disponible dans le réseau selon un intervalle de temps donné
	 * @param debTemps
	 * @param finTemps
	 * @return
	 */
	public int nbDocTypeTemps(String debTemps, String finTemps) {
		return 1;
	}
	
}
