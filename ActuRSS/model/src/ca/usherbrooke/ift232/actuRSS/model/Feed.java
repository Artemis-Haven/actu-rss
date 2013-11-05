package ca.usherbrooke.ift232.actuRSS.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Actu-RSS
 * Couche : Model
 * Date de creation : 2013-10-30
 * Description : 
 *   Classe Feed
 *   Un feed est determine par un nom, une url, une liste de news, une categorie 
 *   
 * @author Benjamin Ferre
 * @author David Boas
 * @version 0.3
 */
public class Feed {
	private String nom;
	private String url;
	private List<News> listNews = new ArrayList<News>();
	private Categorie categorie;

	public String getNom() {
		return nom;
	}
	public String getUrl() {
		return url;
	}
	public List<News> getListNews() {
		return listNews;
	}
	public Categorie getCategorie() {
		return categorie;
	}
	
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	public void setUrl(String url) {
		this.url = url;
	}	
	public void setListNews(List<News> entrees) {
		this.listNews = entrees;
	}	
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	
	/**
	 * @param nom : le nom du flux
	 * @param url : l'url du flux
	 * @param entrees : la liste des news du flux
	 */
	public Feed(String nom, String url, List<News> entrees){
		this.nom = nom;
		this.url = url;
		listNews = entrees;
	}
	
}
