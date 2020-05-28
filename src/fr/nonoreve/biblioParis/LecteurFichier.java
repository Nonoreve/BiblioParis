package fr.nonoreve.biblioParis;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LecteurFichier {

	/**
	 * Contient toutes les donnees d'une line. Les variables sont accessibles
	 * directement.
	 * 
	 * @author nonoreve
	 *
	 */
	public static class LigneFichier {
		public String isbn;
		public String ean;
		public String titre;
		public String editeur;
		public String date;
		public Integer numeroSerie;
		public String titreSerie;
		public String nomAuteur;
		public String prenomAuteur;
		public String type;

		public int exemplairesTotal;
		public int exemplairesAimeCesaire;
		public int exemplairesEdmondRostand;
		public int exemplairesJeanPierreMelville;
		public int exemplairesOscarWilde;
		public int exemplairesSaintSimon;
	}

	/**
	 * Retourne la totalite d'un fichier csv sous forme d'une List de LigneFichier
	 * 
	 * @param cheminFichierCSV
	 */
	public static List<LigneFichier> getDonneesDepuisFichierCSV(String cheminFichierCSV) {
		String ligneString = "";
		String[] data = null;
		String separator = ";(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
		List<LigneFichier> result = new ArrayList<>();

		try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(cheminFichierCSV),
				StandardCharsets.ISO_8859_1)) {
			// Read the first line
			ligneString = bufferedReader.readLine();

			// Get data from the line
			data = ligneString.split(separator, -1);

			final int colonnes = 16;
			if (data.length != colonnes) {
				System.out.println("[LecteurFichier] Le fichier " + cheminFichierCSV
						+ " ne contient pas le bon nombre de colonnes. " + data.length + " au lien de " + colonnes
						+ ". ");
				return null;
			}

			// Read the file line by line
			while ((ligneString = bufferedReader.readLine()) != null) {
				LigneFichier line = new LigneFichier();
				// Get data from the line
				data = ligneString.split(separator, -1);

				// Sort data

				// Get the ISBN number
				line.isbn = data[0];

				// Get the EAN number
				line.ean = data[1];

				// Get the title of the document
				line.titre = data[2];

				// Get the name of the publisher
				line.editeur = data[3];

				// Get the publication date
				try {
					int dateInt = Integer.parseInt(data[4].replaceAll("[^0-9]", ""));

					if (dateInt % 10000 >= 2021 || dateInt % 10000 < 0) {
						line.date = "?";
					} else if (dateInt / 10000 == 0) {
						line.date = Integer.toString(dateInt % 10000);
					} else {
						line.date = dateInt % 10000 + "-" + dateInt / 10000;
					}
				} catch (Exception exception) {
					line.date = "?";
				}

				// Get the title of the series
				line.titreSerie = data[5];

				// Get the number of this document in the series
				try {
					line.numeroSerie = Integer.parseInt(data[6]);
				} catch (Exception exception) {
					line.numeroSerie = null;
				}

				// Get the name of the author
				line.prenomAuteur = data[7];

				// Get the surname of the author
				line.nomAuteur = data[8];

				// Get the type of the document
				line.type = data[9];

				// Get the total number of copy in Paris for this document
				try {
					line.exemplairesTotal = Integer.parseInt(data[10]);
				} catch (Exception exception) {
					line.exemplairesTotal = 0;
				}

				// Get the number of copy in the library "Aime Cesaire"
				try {
					line.exemplairesAimeCesaire = Integer.parseInt(data[11]);
				} catch (Exception exception) {
					line.exemplairesAimeCesaire = 0;
				}

				// Get the number of copy in the library "Edmond Rostand"
				try {
					line.exemplairesEdmondRostand = Integer.parseInt(data[11]);
				} catch (Exception exception) {
					line.exemplairesEdmondRostand = 0;
				}

				// Get the number of copy in the library "Jean-Pierre Melville"
				try {
					line.exemplairesJeanPierreMelville = Integer.parseInt(data[11]);
				} catch (Exception exception) {
					line.exemplairesJeanPierreMelville = 0;
				}

				// Get the number of copy in the library "Oscar Wilde"
				try {
					line.exemplairesOscarWilde = Integer.parseInt(data[11]);
				} catch (Exception exception) {
					line.exemplairesOscarWilde = 0;
				}

				// Get the number of copy in the library "Saint-Simon"
				try {
					line.exemplairesSaintSimon = Integer.parseInt(data[11]);
				} catch (Exception exception) {
					line.exemplairesSaintSimon = 0;
				}

				result.add(line);

			}
		} catch (IOException exception) {
			System.err.println(exception);
		}
		return result;
	}
}
