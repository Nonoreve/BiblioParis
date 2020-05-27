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
}
