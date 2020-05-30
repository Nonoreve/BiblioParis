package fr.nonoreve.biblioParis;

import java.util.ArrayList;
import java.util.List;

public class Personne {

	private String nom;
	private String prenom;
	private List<Utilisateur> lstCarte;

	public Personne(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
		this.lstCarte = new ArrayList<Utilisateur>();
	}

	public List<Utilisateur> getLstCarte() {
		return lstCarte;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	@Override
	public String toString() {
		return "Personne : NOM : " + nom + ", PRENOM : " + prenom;
	}

}
