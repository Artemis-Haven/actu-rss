package ca.usherbrooke.ift232.actuRSS.model;
/**
 * Actu-RSS
 * Couche : Model
 * Date de création : 2013-10-30
 * Description : 
 *   Classe News
 *   
 * @author Benjamin Ferré
 * @version 0.1
 */
import java.util.Date;

public class News {
	private String titre;
	private String url;
	private String auteur;
	private Date date;
	private String contenu;
	private boolean estLu;
	private boolean estFavori;
	
	//accesseurs
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAuteur() {
		return auteur;
	}
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	
	public boolean isEstLu() {
		return estLu;
	}
	public void setEstLu(boolean estLu) {
		this.estLu = estLu;
	}
	public boolean isEstFavori() {
		return estFavori;
	}
	public void setEstFavori(boolean estFavori) {
		this.estFavori = estFavori;
	}
	
	//methodes
	public String toString() {
	    return "News [titre = " + titre + ", contenu = " + contenu 
	    		+ ", url = " + url + ", auteur = " + auteur + ", date = " + date + "]";
	}

}
