package fr.nonoreve.biblioParis;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
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
import fr.nonoreve.biblioParis.doc.ISBNable;
import fr.nonoreve.biblioParis.doc.Livre;
import fr.nonoreve.biblioParis.doc.Methode;
import fr.nonoreve.biblioParis.doc.Partition;
import fr.nonoreve.biblioParis.doc.Revue;

public class Reseau {

	private List<Bibliotheque> bibliotheques;
	private List<Personne> personnes;
	private HashMap<String, Document> documents;
	public static final String typesNames[] = { "livre", "bande dessinee", "partition", "carte", "disque compact",
			"dvd", "methode", "enregistrement musical", "revue", "autres" };

	/**
	 * Construit le reseau a partir des donnes initiales
	 * 
	 * @param doneesBrutes
	 */
	public Reseau(List<LigneFichier> doneesBrutes) {
		this.bibliotheques = new ArrayList<Bibliotheque>();
		this.personnes = new ArrayList<Personne>();
		this.documents = new HashMap<>();

		Bibliotheque aimeCesaire = new Bibliotheque("Aime Cesaire", "", 2);
		Bibliotheque edmondRostand = new Bibliotheque("Edmond Rostand", "", 3);
		Bibliotheque jeanPierreMelville = new Bibliotheque("Jean Pierre Melville", "", 3);
		Bibliotheque oscarWilde = new Bibliotheque("Oscar Wilde", "", 4);
		Bibliotheque saintSimon = new Bibliotheque("Saint Simon", "", 6);

		int pasEan = 0;
		int pasType = 0;
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
					doc = new Livre(ligne.ean, ligne.isbn, ligne.titre, ligne.editeur, ligne.date, ligne.prenomAuteur,
							ligne.nomAuteur, ligne.numeroSerie, ligne.titreSerie);
				} else if (type.contains(typesNames[1]) || type.contains("bd")) { // 594
					typesQuantities[1]++;
					doc = new BandeDessinee(ligne.ean, ligne.isbn, ligne.titre, ligne.editeur, ligne.date,
							ligne.prenomAuteur, ligne.nomAuteur, ligne.numeroSerie, ligne.titreSerie);
				} else if (type.contains(typesNames[2])) { // 236
					typesQuantities[2]++;
					doc = new Partition(ligne.ean, ligne.isbn, ligne.titre, ligne.editeur, ligne.date,
							ligne.prenomAuteur, ligne.nomAuteur, ligne.numeroSerie, ligne.titreSerie);
				} else if (type.contains(typesNames[3])) { // 18
					typesQuantities[3]++;
					doc = new Carte(ligne.ean, ligne.titre, ligne.editeur, ligne.date, ligne.prenomAuteur,
							ligne.nomAuteur, ligne.numeroSerie, ligne.titreSerie);
				} else if (type.contains(typesNames[4])) { // 2257
					typesQuantities[4]++;
					doc = new CD(ligne.ean, ligne.titre, ligne.editeur, ligne.date, ligne.prenomAuteur, ligne.nomAuteur,
							ligne.numeroSerie, ligne.titreSerie);
				} else if (type.contains(typesNames[5])) { // 715
					typesQuantities[5]++;
					doc = new DVD(ligne.ean, ligne.titre, ligne.editeur, ligne.date, ligne.prenomAuteur,
							ligne.nomAuteur, ligne.numeroSerie, ligne.titreSerie);
				} else if (type.contains(typesNames[6])) { // 156
					typesQuantities[6]++;
					doc = new Methode(ligne.ean, ligne.isbn, ligne.titre, ligne.editeur, ligne.date, ligne.prenomAuteur,
							ligne.nomAuteur, ligne.numeroSerie, ligne.titreSerie);
				} else if (type.contains(typesNames[7])) { // 67
					typesQuantities[7]++;
					doc = new EnregistrementMusical(ligne.ean, ligne.isbn, ligne.titre, ligne.editeur, ligne.date,
							ligne.prenomAuteur, ligne.nomAuteur, ligne.numeroSerie, ligne.titreSerie);
				} else if (type.contains(typesNames[8])) { // 17
					typesQuantities[8]++;
					doc = new Revue(ligne.ean, ligne.titre, ligne.editeur, ligne.date, ligne.prenomAuteur,
							ligne.nomAuteur, ligne.numeroSerie, ligne.titreSerie);
				} else {
					// System.out.println(ean + " avec type " + ligne.type + " place dans autres.");
					typesQuantities[9]++;
					doc = new Autres(ligne.ean, ligne.titre, ligne.editeur, ligne.date, ligne.prenomAuteur,
							ligne.nomAuteur, ligne.numeroSerie, ligne.titreSerie);
				}
				if (ligne.ean != null)
					this.documents.put(ean, doc);
			} else {
				System.out.println("Pas de type pour " + ean + ".");
				pasType++;
				doc = new Autres(ligne.ean, ligne.titre, ligne.editeur, ligne.date, ligne.prenomAuteur, ligne.nomAuteur,
						ligne.numeroSerie, ligne.titreSerie);
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
		bibliotheques
				.forEach(b -> System.out.println(b.getNom() + " contient " + b.getHmDocu().size() + " documents."));
	}

	public static void main(String[] args) {
		boolean stop = false;
		List<LigneFichier> doneesBrutes;
		String cheminFichier;
		Scanner sc = new Scanner(System.in);

		if (args.length > 0) {
			// argumetn pour sauter la partie interactive (utile pour debig le fonctions)
			if (args.length > 1 && args[0].contentEquals("--non-interactif")) {
				stop = true;
				args[0] = args[1]; // pour pas avoir a modifier le reste
			}
			if (!checkFichier(args[0]))
				return;
			cheminFichier = args[0];
		} else {
			do {
				System.out.println("Entrez le nom du fichier : ");
				cheminFichier = sc.nextLine();
			} while (!checkFichier(cheminFichier));
		}
		doneesBrutes = LecteurFichier.getDonneesDepuisFichierCSV(cheminFichier);
		System.out.println("Lecture de " + doneesBrutes.size() + " lignes dans " + cheminFichier);

		Reseau reseau = new Reseau(doneesBrutes);
		System.out.println();

		if (!stop) {
			final String[] commandes = { "stop", "help", "ajouter", "inscrire", "distribuer", "consulter" };
			final String[] desc = { "Arrete l'application.", "Affiche l'aide.",
					"Ajoute un personne, un document ou une bibliotheque. (ajouter <document|bibliotheque|personne> <arguments> [arguments] ... [arguments])",
					"Inscrit une personne dans une bibliotheque. (inscrire <nomUtilisateur> <prenomUtilisateur> <nomBibliotheque>)",
					"Distribue un document a une bibliotheque. (distribuer <nomBilbiotheque> <eanDocument> <exemplaires>)",
					"Affiche le resultat d'une recherche. (non implemente)" };
			System.out.println("\n\nCommandes disponibles : ");
			for (int i = 0; i < commandes.length; i++) {
				System.out.print(commandes[i] + " ");
			}
			System.out.println("\n");

			while (!stop) {
				System.out.print("$~: ");
				String[] commandLine = sc.nextLine().split(" ");
				String command = commandLine[0];
				if (command.contentEquals("stop")) // STOP
					stop = true;
				String[] arguments = null;
				if (commandLine.length > 1) {
					arguments = new String[commandLine.length - 1];
					for (int i = 0; i < commandLine.length - 1; i++) {
						arguments[i] = commandLine[i + 1];
					}
				}

				if (command.contentEquals(commandes[1])) { // HELP
					for (int i = 0; i < desc.length; i++) {
						System.out.println(commandes[i] + " : " + desc[i]);
					}
					continue;
				}

				if (command.contentEquals(commandes[2])) { // AJOUTE
					if (arguments == null || arguments.length < 1) {
						System.out.println("Mauvais nombre d'arguments. (Voir help)");
						continue;
					}
					commandeAjoute(arguments, reseau);
					continue;
				}

				if (command.contentEquals(commandes[3])) { // INSCRIRE
					if (arguments == null || arguments.length != 3) {
						System.out.println("Mauvais nombre d'arguments. (Voir help)");
						continue;
					}
					List<Personne> recherchePersonnes = new ArrayList<>();
					for (Personne mec : reseau.personnes) {
						if(mec.getNom().contentEquals(arguments[0]) && mec.getPrenom().contentEquals(arguments[1]))
							recherchePersonnes.add(mec);
					}
					Personne elut;
					if (recherchePersonnes.size() > 1) {
						System.out.println("Nous avons trouve plusieurs personnes correspondant a votre recherche. ");
						Personne citoyen;
						for (int i = 0; i < recherchePersonnes.size(); i++) {
							citoyen = recherchePersonnes.get(i);
							System.out.println("[" + i + "] " + citoyen);
						}
						System.out.println("Qui choisissez-vous ?");
						int choix = sc.nextInt();
						elut = recherchePersonnes.get(choix);
					} else {
						elut = recherchePersonnes.get(0);
					}

					Bibliotheque biblio = rechercherBibliotheque(arguments[2], reseau);
					biblio.inscrire(elut);
					continue;
				}

				if (command.contentEquals(commandes[4])) { // DISTRTIBUER
					if (arguments == null || arguments.length != 3) {
						System.out.println("Mauvais nombre d'arguments. (Voir help)");
						continue;
					}

					Integer exemplaires;
					try {
						exemplaires = Integer.parseInt(arguments[2]);
					} catch (Exception exception) {
						System.out.println("Le parametre exemplaires doit etre un nombre.");
						continue;
					}
					if (exemplaires < 1) {
						System.out.println("Le minimum d'exemplaires est 1");
						continue;
					}
					Bibliotheque biblio = rechercherBibliotheque(arguments[0], reseau);
					List<Document> recherche = reseau.rechercherDocumentsEan(arguments[1]);
					if (recherche.size() > 1) {
						System.out.println("Erreur plusieurs documents trouves avec ean " + arguments[1]);
						return;
					}
					if (recherche.size() == 0) {
						System.out.println("Le document n'existe pas.");
						continue;
					}
					biblio.ajouterDocument(recherche.get(0), exemplaires);
					continue;
				}

				if (command.contentEquals("egg")) {
					System.out.println("Nobody expects the spanish inquisition.");
					continue;
				}

			}
		}

		// TODO faire tous les test necessaires
		
		//Test consultation reseau
		
		//Test de consultation de tout les documents du reseau
		//for (Document d : reseau.rechercherDocuments()) System.out.println(d);
		
		//Test de consultation des documents du reseau selon le prenom d'auteur
		//for (Document d : reseau.rechercherDocumentsPrenomAuteur("Boris")) System.out.println(d);
		
		//Test de consultation des documents du reseau selon le nom d'auteur
		//for (Document d : reseau.rechercherDocumentsNomAuteur("Aurelia")) System.out.println(d);
		
		//Test de consultation des documents du reseau selon le nom et prenom d'auteur
		//for (Document d : reseau.rechercherDocumentsNomPrenomAuteur("Aurelia","Bolle")) System.out.println(d);
		
		//Test de consultation des documents du reseau selon l'ean
		//for (Document d : reseau.rechercherDocumentsEan("9782742429158")) System.out.println(d);
		
		//Test de consultation des documents du reseau selon l'isbn
		//for (Document d : reseau.rechercherDocumentsIsbn("978-2-501-06545-0")) System.out.println(d);
		
		//Test de consultation des documents du reseau selon la serie
		//for (Document d : reseau.rechercherDocumentsSerie("Calamity Mamie")) System.out.println(d);
		
		//Test de consultation des documents du reseau par type dans un intervalle de temps
		//reseau.nbDocTypeTemps(2005, 2015);
		
		
		
		//Test consultation Biblio
		
		//Test de consultation de tout les documents de la biblio
		//for (Document d : reseau.bibliotheques.get(0).rechercherDocuments()) System.out.println(d);
		
		//Test de consultation des documents de la biblio selon le prenom d'auteur
		//for (Document d : reseau.bibliotheques.get(0).rechercherDocumentsPrenomAuteur("Davis")) System.out.println(d);
		
		//Test de consultation des documents de la biblio selon le nom d'auteur
		//for (Document d : reseau.bibliotheques.get(0).rechercherDocumentsNomAuteur("Miki")) System.out.println(d);
		
		//Test de consultation des documents de la biblio selon le nom et prenom d'auteur
		//for (Document d : reseau.bibliotheques.get(0).rechercherDocumentsNomPrenomAuteur("Gabrielle","Vincent")) System.out.println(d);
		
		//Test de consultation des documents de la biblio selon l'ean
		//for (Document d : reseau.bibliotheques.get(0).rechercherDocumentsEan("9782021089707")) System.out.println(d);
		
		//Test de consultation des documents de la biblio selon l'isbn
		//for (Document d : reseau.bibliotheques.get(0).rechercherDocumentsIsbn("978-2-8001-4765-9")) System.out.println(d);
		
		//Test de consultation des documents de la biblio selon la serie
		//for (Document d : reseau.bibliotheques.get(0).rechercherDocumentsSerie("Garfield")) System.out.println(d);
		
		//Test de consultation des documents de la biblio par type dans un intervalle de temps
		//reseau.bibliotheques.get(0).nbDocTypeTemps(2005, 2015);

		sc.close();
	}

	private static Bibliotheque rechercherBibliotheque(String nomBibliotheque, Reseau reseau) {
		List<Bibliotheque> recherche = new ArrayList<>();
		for(Bibliotheque biblio : reseau.bibliotheques) {
			if(biblio.getNom().contentEquals(nomBibliotheque))
				recherche.add(biblio);
		}
		if (recherche.size() > 1) {
			System.out.println("Nous avons trouve plusieurs bibliotheques correspondant a votre recherche. ");
			Bibliotheque bibli;
			for (int i = 0; i < recherche.size(); i++) {
				bibli = recherche.get(i);
				System.out.println("[" + i + "] " + bibli);
			}
			Scanner sc = new Scanner(System.in);
			System.out.println("Qui choisissez-vous ?");
			int choix = sc.nextInt();
			return recherche.get(choix);
		}
		return recherche.get(0);
	}

	private static void commandeAjoute(String[] arguments, Reseau reseau) {

		if (arguments[0].contentEquals("document")) {
			if (arguments.length < 10) {
				// les arg sont tous obligatoires sauf l'isbn sinon c beaucoup plus complique a
				// gerer
				System.out.println(
						"Mauvais nombre d'arguments. usage : ajouter document <ean> <titre> <editeur> <date> <prenomAuteur> <nomAuteur> <numeroSerie> <titreSerie> [isbn]");
				return;
			}
			for (String ean : reseau.documents.keySet()) {
				if (ean.contentEquals(arguments[2])) {
					System.out.println("Impossible de creer le doc. Un document existe deja avec l'ean " + ean);
					return;
				}
			}
			for (String ean : reseau.documents.keySet()) {
				Document d = reseau.documents.get(ean);
				if (d instanceof ISBNable && arguments.length > 10) {
					String isbn = ((ISBNable) d).getISBN();
					if (isbn.contentEquals(arguments[10])) {
						System.out.println("Impossible de creer le doc. Un document existe deja avec l'isbn " + isbn);
						return;
					}
				}
			}
			Integer numeroSerie;
			try {
				numeroSerie = Integer.parseInt(arguments[8]);
			} catch (Exception exception) {
				numeroSerie = null;
			}
			String type = arguments[1];
			Document doc;
			if (type.contains(typesNames[0])) {
				doc = new Livre(arguments[2], arguments.length > 10 ? arguments[10] : null, arguments[3], arguments[4],
						arguments[5], arguments[6], arguments[7], numeroSerie, arguments[9]);
			} else if (type.contains(typesNames[1]) || type.contains("bd")) {

				doc = new BandeDessinee(arguments[2], arguments.length > 10 ? arguments[10] : null, arguments[3],
						arguments[4], arguments[5], arguments[6], arguments[7], numeroSerie, arguments[9]);
			} else if (type.contains(typesNames[2])) {

				doc = new Partition(arguments[2], arguments.length > 10 ? arguments[10] : null, arguments[3],
						arguments[4], arguments[5], arguments[6], arguments[7], numeroSerie, arguments[9]);
			} else if (type.contains(typesNames[3])) {

				doc = new Carte(arguments[2], arguments[3], arguments[4], arguments[5], arguments[6], arguments[7],
						numeroSerie, arguments[9]);
			} else if (type.contains(typesNames[4])) {

				doc = new CD(arguments[2], arguments[3], arguments[4], arguments[5], arguments[6], arguments[7],
						numeroSerie, arguments[9]);
			} else if (type.contains(typesNames[5])) {

				doc = new DVD(arguments[2], arguments[3], arguments[4], arguments[5], arguments[6], arguments[7],
						numeroSerie, arguments[9]);
			} else if (type.contains(typesNames[6])) {

				doc = new Methode(arguments[2], arguments.length > 10 ? arguments[10] : null, arguments[3],
						arguments[4], arguments[5], arguments[6], arguments[7], numeroSerie, arguments[9]);
			} else if (type.contains(typesNames[7])) {

				doc = new EnregistrementMusical(arguments[2], arguments.length > 10 ? arguments[10] : null,
						arguments[3], arguments[4], arguments[5], arguments[6], arguments[7], numeroSerie,
						arguments[9]);
			} else if (type.contains(typesNames[8])) {

				doc = new Revue(arguments[2], arguments[3], arguments[4], arguments[5], arguments[6], arguments[7],
						numeroSerie, arguments[9]);
			} else {
				// System.out.println(ean + " avec type " + ligne.type + " place dans autres.");

				doc = new Autres(arguments[2], arguments[3], arguments[4], arguments[5], arguments[6], arguments[7],
						numeroSerie, arguments[9]);
			}
			reseau.documents.put(arguments[2], doc); // la verif de l'ean est faite au debut de la fonction
			System.out.println("Document cree : " + doc
					+ "\nVous pouvez utiliser la commande distribuer pour l'ajouter a une biblioteque.");
			return;
		}
		if (arguments[0].contentEquals("bibliotheque")) {
			if (arguments.length < 4) {
				System.out.println(
						"Mauvais nombre d'arguments. usage : ajouter bibliotheque <nom> <maxEmprunt> <adresse> [complementAdresse] ... [complementAdresse]");
				return;
			}

			Integer maxEmprunt;
			try {
				maxEmprunt = Integer.parseInt(arguments[2]);
			} catch (Exception exception) {
				System.out.println("Max emprunt doit etre un nombre.");
				return;
			}
			String addr = "";
			for (int i = 3; i < arguments.length; i++) {
				addr += arguments[i] + ' ';
			}
			Bibliotheque bibli = new Bibliotheque(arguments[1], addr, maxEmprunt);
			reseau.bibliotheques.add(bibli);
			System.out.println("Bibliotheque creee : " + bibli);
			return;
		}
		if (arguments[0].contentEquals("personne")) {
			if (arguments.length < 3) {
				System.out.println("Mauvais nombre d'arguments. usage : ajouter personne <nom> <prenom>");
				return;
			}
			Personne mec = new Personne(arguments[1], arguments[2]);
			reseau.personnes.add(mec);
			System.out.println(
					"Personne creee : " + mec + "\nUtilisez la commande inscrire pour l'ajouter a une bibliotheque.");
			return;
		}
		System.out.println("Argument inconnu. Voir help.");
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
	public List<Document> rechercherDocuments() {
		List<Document> result = new ArrayList<Document>();
		documents.forEach((s, d) -> {
			result.add(d);
		});
		return result;
	}

	/**
	 * Liste les documents present dans le reseau avec le nom de l'auteur comme
	 * filtre
	 * 
	 * @param nom
	 */
	public List<Document> rechercherDocumentsNomAuteur(String nomAuteur) {
		List<Document> result = new ArrayList<Document>();
		documents.forEach((s, d) -> {
			if (d.getNomAuteur().equals(nomAuteur))
				result.add(d);
		});
		return result;
	}

	/**
	 * Liste les documents present dans le reseau avec le prenom de l'auteur comme
	 * filtre
	 * 
	 * @param prenom
	 */
	public List<Document> rechercherDocumentsPrenomAuteur(String prenomAuteur) {
		List<Document> result = new ArrayList<Document>();
		documents.forEach((s, d) -> {
			if (d.getPrenomAuteur().equals(prenomAuteur))
				result.add(d);
		});
		return result;
	}

	/**
	 * Liste les documents present dans le reseau avec le nom et prenom de l'auteur
	 * comme filtre
	 * 
	 * @param nom
	 * @param prenom
	 */
	public List<Document> rechercherDocumentsNomPrenomAuteur(String nomAuteur, String prenomAuteur) {
		List<Document> result = new ArrayList<Document>();
		documents.forEach((s, d) -> {
			if (d.getNomAuteur().equals(nomAuteur) && d.getPrenomAuteur().equals(prenomAuteur))
				result.add(d);
		});
		return result;
	}

	/**
	 * Liste les documents present dans le reseau avec l'EAN du document comme
	 * filtre
	 * 
	 * @param EAN
	 */
	public List<Document> rechercherDocumentsEan(String ean) {
		List<Document> result = new ArrayList<Document>();
		documents.forEach((s, d) -> {
			if (d.getEan().equals(ean))
				result.add(d);
		});
		return result;
	}

	/**
	 * Liste les documents present dans le reseau avec l'ISBN du document comme
	 * filtre
	 * 
	 * @param ISBN
	 */
	public List<Document> rechercherDocumentsIsbn(String isbn) {
		List<Document> result = new ArrayList<Document>();
		documents.forEach((s, d) -> {
			if (d instanceof ISBNable) {
				ISBNable i = (ISBNable) d;
				if (i.getISBN().contentEquals(isbn))
					result.add(d);
			}
		});
		return result;
	}

	/**
	 * Renvoie le nombre de document par type disponible dans le reseau selon un
	 * intervalle de temps donne
	 * 
	 * @param debTemps
	 * @param finTemps
	 * @return
	 */
	public void nbDocTypeTemps(int debTemps, int finTemps) {
		int cptAutres = 0, cptBandeDessinee = 0, cptCarte = 0, cptCD = 0, cptDVD = 0, cptEnregistrementMusical = 0,
				cptLivre = 0, cptPartition = 0, cptRevue = 0, cptMethode = 0;
		for (String ean : documents.keySet()) {
			Document d = documents.get(ean);
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

	/**
	 * Liste les documents du reseau d'une serie en parametre
	 * 
	 * @param serie
	 */
	public List<Document> rechercherDocumentsSerie(String serie) {
		List<Document> lstDocuTrie = new ArrayList<Document>();
		for (String ean : documents.keySet()) {
			Document d = documents.get(ean);
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
