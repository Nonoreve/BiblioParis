package fr.nonoreve.biblioParis.doc;

public class BandeDessinee extends Document implements ISBNable {

	private String isbn;

	public BandeDessinee(String ean, String isbn, String titre, String editeur, String datePublication, String prenomAuteur,
			String nomAuteur, Integer ordreDansSerie, String titreSerie) {
		super(ean, titre, editeur, datePublication, prenomAuteur, nomAuteur, ordreDansSerie, titreSerie);
		this.isbn = isbn;
	}

	@Override
	public String getISBN() {
		return this.isbn;
	}

}
