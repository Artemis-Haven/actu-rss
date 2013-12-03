package ca.usherbrooke.ift232.actuRSS.model;

public class NoInternetConnectionException extends Exception {

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Impossible de se connecter à Internet.";
	}

}
