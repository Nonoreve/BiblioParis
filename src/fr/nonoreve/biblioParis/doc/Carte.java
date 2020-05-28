package fr.nonoreve.biblioParis.doc;

public class Carte extends Document {

	public Carte(String ean, String titre, String editeur, String datePublication, String prenomAuteur,
			String nomAuteur, Integer ordreDansSerie) {
		super(ean, titre, editeur, datePublication, prenomAuteur, nomAuteur, ordreDansSerie);
	}

}
