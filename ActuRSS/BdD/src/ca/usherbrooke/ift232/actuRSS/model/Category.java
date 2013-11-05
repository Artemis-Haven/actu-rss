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
 * @version 0.4
 */
public class Category {
	private int id;
	private String nom;
	private List<Feed> listFeed = new ArrayList<Feed>();
	
	public Category(int id, String nom)
	{
	
	}
	
	public Category(String nom)
	{
		this.id = 1;
		this.nom = nom;
	}
	

	public String getNom() {
		return nom;
	}
	public List<Feed> getListFeed() {
		return listFeed;
	}
	public int getId() {
		return id;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public void setListFeed(List<Feed> entrees) {
		listFeed = entrees;
	}
	public void setId(int id) {
		this.id = id;
	}
}
