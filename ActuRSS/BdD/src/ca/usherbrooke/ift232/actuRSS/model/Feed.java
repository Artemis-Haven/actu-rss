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
 * @version 0.5
 */
public class Feed {
	
	//variables de classe
	private int id;
	private String name;
	private String url;
	private List<News> listNews = new ArrayList<News>();

	//constructeurs
	public Feed(){
		this.name = "";
		this.url = "";
		listNews = null;
	}
	
	/**
	 * @param name le nom du flux
	 * @param url l'url du flux
	 * @param entries la liste des news du flux
	 */
	public Feed(int id, String name, String url, List<News> entries){
		this.id = id;
		this.name = name;
		this.url = url;
		listNews = entries;
	}
	
	public Feed(int id, String name, String url){
		this.id = id;
		this.name = name;
		this.url = url;
		listNews = null;
	}
	//accesseurs
	public String getNom() {
		return name;
	}
	public String getUrl() {
		return url;
	}
	public List<News> getListNews() {
		return listNews;
	}
	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void setUrl(String url) {
		this.url = url;
	}	
	public void setListNews(List<News> entrees) {
		this.listNews = entrees;
	}	

	public void setId(int id) {
		this.id = id;
	}

}
