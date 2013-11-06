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
	private int id;
	private String name;
	private String url;
	private List<News> listNews = new ArrayList<News>();
	private Category category;

	public String getNom() {
		return name;
	}
	public String getUrl() {
		return url;
	}
	public List<News> getListNews() {
		return listNews;
	}
	public Category getCategory() {
		return category;
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
	public void setCategory(Category category) {
		this.category = category;
	}
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @param nom : le nom du flux
	 * @param url : l'url du flux
	 * @param entrees : la liste des news du flux
	 */
	public Feed(String name, String url, List<News> entries){
		this.name = name;
		this.url = url;
		listNews = entries;
	}
}
