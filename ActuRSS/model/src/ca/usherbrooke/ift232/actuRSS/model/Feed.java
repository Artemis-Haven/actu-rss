package ca.usherbrooke.ift232.actuRSS.model;
/**
 * Actu-RSS
 * Couche : Model
 * Date de création : 2013-10-30
 * Description : 
 *   Classe Feed
 *   
 * @author Benjamin Ferré
 * @version 0.1
 */
import java.util.ArrayList;
import java.util.List;

public class Feed {
	/**
	 * Variables privees
	 * @param nom 
	 * 			nom du feed
	 * @param url
	 * 			url du feed
	 * @param entrees
	 * 			un feed contient plusieurs news
	 */
	private String nom;
	private String url;
	private List<News> listNews = new ArrayList<News>();
	private Categorie categorie;

	 /**
	 * Getters
	 * @param getNom()
	 * Retourne le nom du feed
	 * @param getUrl()
	 * Retourne l'url du feed
	 * 
	 */
	public String getNom() {
		return nom;
	}

	public String getUrl() {
		return url;
	}
	
	public List<News> getListNews() {
		return listNews;
	}
	
	/**
	 * @return the categorie
	 */
	public Categorie getCategorie() {
		return categorie;
	}
	
	 /**
	 * Setters
	 * @param setNom()
	 * Permet de fixer le nom du feed
	 * @param setUrl()
	 * Permet de fixer l'url du feed
	 */
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
	 * Constructeurs
	 * @param Feed
	 * 			Construit un feed en spécifiant un nom et son url
	 * 
	 */
	public Feed(String nom, String url, List<News> entrees){
		this.nom = nom;
		this.url = url;
		listNews = entrees;
	}
	
}
