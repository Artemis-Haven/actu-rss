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
	private String nom;
	private String url;
	
	public List<News> entrees = new ArrayList<News>();

	//accesseurs
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	//constructeur
	public Feed(String nom, String url){
		this.nom = nom;
		this.url = url;
	}

}
