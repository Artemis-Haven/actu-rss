package ca.usherbrooke.ift232.actuRSS.model;

import java.util.Observable;

public class Model extends Observable {

	public Model() {
		
		
	}
	
	
	public void notifyView(Object o)	
	{
		/*TODO A completer*/
		setChanged();
		notifyObservers(o);
	}
	
}
