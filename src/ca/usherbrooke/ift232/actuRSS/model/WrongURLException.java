package ca.usherbrooke.ift232.actuRSS.model;

public class WrongURLException extends Exception{
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "L'URL fournie est incorrecte";
	}
}
