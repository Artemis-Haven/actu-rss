package ca.usherbrooke.ift232.actuRSS;

import ca.usherbrooke.ift232.actuRSS.controller.Controller;
import ca.usherbrooke.ift232.actuRSS.model.Model;
import ca.usherbrooke.ift232.actuRSS.view.View;

public class ActuRSS {

	/**Permet de lancer l'application
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("heRmeSS");
		Model model = new Model();
		View view  = new View();
		Controller controller = new Controller(model,view);
		
		view.setVisible(true);
	}

}
