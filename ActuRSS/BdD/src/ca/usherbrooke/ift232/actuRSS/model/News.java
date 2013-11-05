package ca.usherbrooke.ift232.actuRSS.model;

import java.util.Date;

/**
 * Actu-RSS
 * Couche : Model
 * Date de creation : 2013-10-30
 * Description : 
 *   Classe News
 *   Une news est determinee par un titre, une url, un auteur, une date, un contenu, 
 *   et des booleens marquant si la news a ete lue et/ou mise en favori. 
 *   
 * @author Benjamin Ferre
 * @author David Boas
 * @version 0.4
 */
public class News {
	private int id;
	private String titre;
	private String url;
	private String auteur;
	private Date date;
	private String contenu;
	private boolean Lu;
	private boolean Favori;
	private Feed feed;

	public String getTitre() {
		return titre;
	}
	public String getUrl() {
		return url;
	}
	public String getAuteur() {
		return auteur;
	}
	public Date getDate() {
		return date;
	}
	public String getContenu() {
		return contenu;
	}
	public boolean isLu() {
		return Lu;
	}
	public boolean isFavori() {
		return Favori;
	}
	public Feed getFeed() {
		return feed;
	}
	public int getId() {
		return id;
	}
	
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	public void setLu(boolean Lu) {
		this.Lu = Lu;
	}
	public void setFavori(boolean Favori) {
		this.Favori = Favori;
	}
	public void setFeed(Feed feed) {
		this.feed = feed;
	}
	public void setId(int id) {
		this.id = id;
	}
	

	public String toString() {
	    return "News [titre = " + titre + ", contenu = " + contenu 
	    		+ ", url = " + url + ", auteur = " + auteur + ", date = " + date + "]";
	}
}
