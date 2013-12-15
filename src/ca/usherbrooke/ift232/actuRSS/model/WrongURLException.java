package ca.usherbrooke.ift232.actuRSS.model;

/**
 * Exception créée pour faciliter la gestion des exceptions dans le modèle.
 * Elle est utlisée dans la méthode obtainDocument de la classe Model pour identifier les problèmes liés à l'url d'un flux
 * @author DAVID
 */
@SuppressWarnings("serial")
public class WrongURLException extends Exception {
	
	
	public WrongURLException(String message) {
		super(message);
	}
}
