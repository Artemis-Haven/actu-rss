package ca.usherbrooke.ift232.actuRSS.model;
/**
 * Actu-RSS
 * Couche : Model
 * Date de création : 2013-11-04
 * Description : 
 *   Classe Categorie
 *   
 * @author Benjamin Ferré
 * @version 0.1
 */

import java.util.ArrayList;
import java.util.List;

public class Categorie {
	/**
	 * Variables privees
	 * @param nom 
	 * 			nom de la categorie
	 * @param entrees
	 * 			une categorie contient plusieurs feeds
	 */
	private String nom;
	private List<Feed> listFeed = new ArrayList<Feed>();

	 /**
	 * Getters
	 * @param getNom()
	 * Retourne le nom de la categorie
	 */
	public String getNom() {
		return nom;
	}
	
	public List<Feed> getListFeed() {
		return listFeed;
	}

	 /**
	 * Setters
	 * @param setNom()
	 * Permet de fixer le nom de la categorie
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void setListFeed(List<Feed> entrees) {
		listFeed = entrees;
	}

}
