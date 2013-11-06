package ca.usherbrooke.ift232.actuRSS.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Actu-RSS
 * Couche : Model
 * Date de creation : 2013-10-30
 * Description : 
 *   Classe Category
 *   Une categorie est determinee par un nom, et contient une liste de feeds 
 *   
 * @author Benjamin Ferre
 * @author David Boas
 * @version 0.5
 */
public class Category {
	
	//variables de classe
	private int id;
	private String name;
	private List<Feed> listFeed = new ArrayList<Feed>();
	
	//constructeurs
	public Category(){
		this.id = 0;
		this.name = "";
		this.listFeed = null;
	}
	
	/**
	 * @param name le nom de la categorie
	 * @param url l'url de la categorie
	 * @param listFeed la liste des flux de la categorie
	 */
	public Category(int id, String name, List<Feed> listFeed){
		this.id = id;
		this.name = name;
		this.listFeed = listFeed;
	}
	
	public Category(int id, String name){
		this.id = id;
		this.name = name;
		this.listFeed =  null;
	}

	//accesseurs
	public String getName() {
		return name;
	}
	public List<Feed> getListFeed() {
		return listFeed;
	}
	public int getId() {
		return id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setListFeed(List<Feed> entries) {
		listFeed = entries;
	}
	public void setId(int id) {
		this.id = id;
	}
}
