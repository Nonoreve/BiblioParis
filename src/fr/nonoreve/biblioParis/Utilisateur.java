package fr.nonoreve.biblioParis;

import java.util.ArrayList;
import java.util.List;

import fr.nonoreve.biblioParis.doc.Document;

public class Utilisateur {

	private Personne pers;
	private Bibliotheque biblio;
	private int maxEmprunt;
	private List<Document> lstDocEmprunte;
	

	public Utilisateur(Personne pers, Bibliotheque biblio, int maxEmprunt) {
		this.pers = pers;
		this.biblio = biblio;
		this.maxEmprunt = maxEmprunt;
		this.lstDocEmprunte = new ArrayList<Document>();
	}


	public Personne getPers() {
		return pers;
	}


	public Bibliotheque getBiblio() {
		return biblio;
	}


	public int getMaxEmprunt() {
		return maxEmprunt;
	}


	public List<Document> getLstDocEmprunte() {
		return lstDocEmprunte;
	}
	
	/**
	 * permet a l'utilisateur s'emprunter un livre dans la bibliotheque ou il est inscrit
	 * @param docu
	 * @param biblio
	 */
	public void emprunter(Document docu, Bibliotheque biblio) {
		if(biblio.getLstUtil().contains(this)) {
			if(biblio.getHmDocu().containsKey(docu)) {
				if(this.getLstDocEmprunte().size() < this.getMaxEmprunt()) {
					this.getLstDocEmprunte().add(docu);
					biblio.getHmDocu().replace(docu, biblio.getHmDocu().get(docu) - 1);
					if (biblio.getHmDocu().get(docu)==0) {
						biblio.getHmDocu().remove(docu);
					}
				}
			}
		}
	}
	
	/**
	 * permet a l'utilisateur de rendre un livre dans une bibliotheque ou il est inscrit
	 * @param docu
	 * @param biblio
	 */
	public void rendre (Document docu, Bibliotheque biblio) {
		if (this.getLstDocEmprunte().contains(docu)) {
			if (this.getBiblio().equals(biblio)) {
				this.getLstDocEmprunte().remove(docu);
				if(biblio.getHmDocu().containsKey(docu)) {
					biblio.getHmDocu().replace(docu, biblio.getHmDocu().get(docu) +1);
				}
				else {
					biblio.getHmDocu().put(docu, 1);
				}
			}
			else {
				boolean inscrit = false;
				for (Utilisateur u : this.getPers().getLstCarte()) {
					if (biblio.getLstUtil().contains(u)) inscrit = true;
				}
				if (inscrit) {
					this.getLstDocEmprunte().remove(docu);
					if(biblio.getHmDocu().containsKey(docu)) {
						biblio.getHmDocu().replace(docu, biblio.getHmDocu().get(docu) +1);
					}
					else {
						biblio.getHmDocu().put(docu, 1);
					}
				}
			}
		}
	}

}
