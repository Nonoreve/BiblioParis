package fr.nonoreve.biblioParis;

import java.util.ArrayList;
import java.util.List;

import fr.nonoreve.biblioParis.doc.Document;

public class Utilisateur {

	private String nom;
	private String prenom;
	private int maxEmprunt;
	private List<Document> lstDocEmprunte;
	

	public Utilisateur(String nom, String prenom, int maxEmprunt) {
		this.nom = nom;
		this.prenom = prenom;
		this.maxEmprunt = maxEmprunt;
		this.lstDocEmprunte = new ArrayList<Document>();
	}


	public String getNom() {
		return nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public int getMaxEmprunt() {
		return maxEmprunt;
	}


	public List<Document> getLstDocEmprunte() {
		return lstDocEmprunte;
	}
	
	

}
