package ca.usherbrooke.ift232.actuRSS.view.parameters;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import ca.usherbrooke.ift232.actuRSS.view.filter.NotReadFilter;
import ca.usherbrooke.ift232.actuRSS.view.sorter.DefaultSorter;

public class ProgramProperties extends Properties {

	final static public String USER_FILE_NAME = "user.properties";

	static private ProgramProperties instance__;

	/**
	 * Initialise les propriétés
	 * 	 @param defaultProps
	 */
	private ProgramProperties(){
		super(new Properties());
		this.setProperty("News Number", "20");
		this.setProperty("Default Display", NotReadFilter.class.getName());
		this.setProperty("Default Sorter", DefaultSorter.class.getName());
		this.setProperty("CSS Style", "src/resources/default.css");
		FileInputStream in;
		try { 

			in = new FileInputStream(USER_FILE_NAME);
			this.load(in);
			in.close();

		} catch (FileNotFoundException e2) {
		} catch (IOException e3) {
		}
		this.save();

	}
	
	/**
	 * Récupère l'instance de ProgrammProperties ou la crée avec les valeurs de défault si elle n'existe pas 
	 * @return intance_ : l'instance de classe ProgrammProperties
	 */
	static synchronized public ProgramProperties getInstance() {

		if (instance__ == null)
			instance__ = new ProgramProperties();
		return instance__;
	}

	
	
	/**
	 * Permet de sauvegarder le flux
	 */
	public void save(){

		FileOutputStream out;

		try {
			out = new FileOutputStream(USER_FILE_NAME);
			this.store(out,"");
			out.close();

		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
	}
}

