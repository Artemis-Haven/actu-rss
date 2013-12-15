package ca.usherbrooke.ift232.actuRSS.view.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ProgramProperties extends Properties {

	final static public String DEFAULT_FILE_NAME = "default.properties";
	final static public String USER_FILE_NAME = "user.properties";

	static private ProgramProperties instance__;

	/**
	 * Initialise les propri�t�s
	 * @param defaultProps
	 */
	private ProgramProperties(Properties defaultProps){
		super(defaultProps);
	}
	
	
	
	static synchronized public ProgramProperties getInstance() {

		if (instance__ == null) {

			// create and load default properties
			Properties defaultProps = new Properties();
			defaultProps.setProperty("News Number", "20");
			defaultProps.setProperty("Default Display", "Not Read");
			defaultProps.setProperty("CSS Style", "src/resources/default.css");
			FileInputStream in;

			try { 

				in = new FileInputStream(DEFAULT_FILE_NAME);
				defaultProps.load(in);
				in.close();

			} catch (FileNotFoundException e2) {
			} catch (IOException e3) {
			}
			// create program properties with default
			ProgramProperties applicationProps; 

			applicationProps = new ProgramProperties(defaultProps);

			try { 

				// now load properties from last invocation
				in = new FileInputStream(USER_FILE_NAME);
				applicationProps.load(in);
				in.close(); 

			} catch (FileNotFoundException e) {
			} catch (IOException e1) {
			}

			instance__ = applicationProps;

		} // end if

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

