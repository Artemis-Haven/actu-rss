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
 * @version 0.3
 */
public class Category {
	private String nom;
	private List<Feed> listFeed = new ArrayList<Feed>();

	public String getNom() {
		return nom;
	}
	
	public List<Feed> getListFeed() {
		return listFeed;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void setListFeed(List<Feed> entrees) {
		listFeed = entrees;
	}

}
