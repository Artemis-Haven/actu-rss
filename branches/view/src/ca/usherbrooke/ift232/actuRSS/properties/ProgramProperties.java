package ca.usherbrooke.ift232.actuRSS.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ProgramProperties extends Properties {

	final static public String DEFAULT_FILE_NAME = "default.properties";
	final static public String USER_FILE_NAME = "user.properties";

	static private ProgramProperties instance__;

	static synchronized public ProgramProperties getInstance() {

		if (instance__ == null) {

			// create and load default properties
			Properties defaultProps = new Properties();
			defaultProps.setProperty("NewsNumber", "20");
			defaultProps.setProperty("AfficherBase", "Tous");
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

	private ProgramProperties(Properties defaultProps){
		super(defaultProps);
	}
	
	public void save(){

		FileOutputStream out;

		try {

			out = new FileOutputStream(USER_FILE_NAME);
			this.store(out, "---No Comment---");
			out.close();

		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
	}
}

