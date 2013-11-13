package ca.usherbrooke.ift232.actuRSS;

import ca.usherbrooke.ift232.actuRSS.model.Model;
import ca.usherbrooke.ift232.actuRSS.view.View;

public class ActuRSS {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello Moustache");
		Model model = new Model();
		View view  = new View(model);
		//Controller controller = new Controller(model,view);
		
		view.setVisible(true);
	}

}
