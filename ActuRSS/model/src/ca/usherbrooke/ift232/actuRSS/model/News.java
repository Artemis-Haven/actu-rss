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
	/**
	 * Variables privees
	 * @param titre 
	 * 			titre de la news
	 * @param url
	 * 			url de la news
	 * @param auteur
	 * 			auteur de la news
	 * @param date
	 * 			date de la news
	 * @param contenu
	 * 			contenu de la news 
	 * @param estLu
	 * 			0 si la news n'est pas lu
	 * 			1 sinon
	 * @param estFavori
	 * 			0 si la news n'est pas en favori
	 * 			1 sinon
	 *
	 */
	private String titre;
	private String url;
	private String auteur;
	private Date date;
	private String contenu;
	private boolean estLu;
	private boolean estFavori;
	
	 /**
	 * Getters
	 * @param getTitre()
	 * Retourne le titre de la news
	 * @param getUrl()
	 * Retourne l'url de la news
	 * @param getAuteur()
	 * Retourne l'auteur de la news
	 * @param getDate()
	 * Retourne la date de la news
	 * @param getContenu()
	 * Retourne le contenu de la news
	 * @param isEstLu()
	 * Retourne la valeur de estLu
	 * @param isEstFavori()
	 * Retourne la valeur de estFavori
	 */
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
	public boolean isEstLu() {
		return estLu;
	}
	public boolean isEstFavori() {
		return estFavori;
	}
	
	
	 /**
	 * Setters
	 * @param setTitre()
	 * Permet de fixer le nom de la news
	 * @param setUrl()
	 * Permet de fixer l'url de la news
	 * @param setAuteur()
	 * Permet de fixer l'auteur de la news
	 * @param setDate()
	 * Permet de fixer la date de la news
	 * @param setContenu()
	 * Permet de fixer le contenu de la news
	 * @param setEstLu()
	 * Permet de fixer la valeur de estLu
	 * @param setEstFavori()
	 * Permet de fixer la valeur de estFavori
	 */
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
	public void setEstLu(boolean estLu) {
		this.estLu = estLu;
	}
	public void setEstFavori(boolean estFavori) {
		this.estFavori = estFavori;
	}
	
	 /**
	 * Methodes
	 * @param toString
	 * 			Retourne les caractéristiques de la news
	 */
	public String toString() {
	    return "News [titre = " + titre + ", contenu = " + contenu 
	    		+ ", url = " + url + ", auteur = " + auteur + ", date = " + date + "]";
	}

}
