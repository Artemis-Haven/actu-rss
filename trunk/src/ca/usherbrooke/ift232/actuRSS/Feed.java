package ca.usherbrooke.ift232.actuRSS;

import java.util.ArrayList;
import java.util.List;

/**
 * Actu-RSS Couche : Model 
 * Date de creation : 2013-10-30 
 * Description : Classe
 * Feed Un feed est determine par un nom, une url, une liste de news, une
 * categorie
 * 
 * @author Benjamin Ferre
 * @author David Boas
 * @version 0.4
 */
public class Feed {

	// variables de classe
	private int id;
	private String title;
	private String url;
	private String categoryName;
	private List<News> listNews;

	// constructeurs
	/**
	 * Permet de construire et initaliser un feed
	 */
	public Feed() {
		this.id = -1;
		this.title = "";
		this.url = "";
		listNews = new ArrayList<News>();
	}

	/**
	 * @param title
	 *            le nom du flux
	 * @param url
	 *            l'url du flux
	 * @param entries
	 *            la liste des news du flux
	 */

	public Feed(String title, String url, List<News> entries) {
		this.id = -1;
		this.title = title;
		this.url = url;
		listNews = entries;
	}

	public Feed(int id, String title, String url, List<News> entries) {
		this.id = id;
		this.title = title;
		this.url = url;
		listNews = entries;
	}

	public Feed(int id, String title, String url) {
		this.id = id;
		this.title = title;
		this.url = url;
		listNews = new ArrayList<News>();
	}

	// accesseurs

	public String getUrl() {
		return url;
	}

	public List<News> getListNews() {
		return listNews;
	}

	public int getId() {
		return id;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setNameCategory(String nameCategory){
		this.categoryName = nameCategory;
	}
	@Override
	public String toString() {
		/*String resultat = "\n    Feed [id=" + id + ", title=" + title + "]";
		for(News n : this.getListNews()) {
			resultat += "\n" + n;
		}
		return resultat;*/
		return this.getTitle();
	}

	public String getNameCategory() {
		return this.categoryName;
	}

}
