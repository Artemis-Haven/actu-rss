package ca.usherbrooke.ift232.actuRSS;

import javax.swing.UIManager;

import ca.usherbrooke.ift232.actuRSS.model.Model;
import ca.usherbrooke.ift232.actuRSS.view.View;

/**
 * Actu-RSS
 * Couche : Main
 * Date de creation : 2013-11-01
 * Description : 
 *   Classe principale de l'application, contenant la
 *   fonction main(), le modele, la vue et le controlleur
 *   
 * @author Rémi Patrizio, Yann Seree
 * @version 0.1
 */

public class ActuRSS {

	/**
	 * main() : méthode principale qui se lance lors de 
	 * l'exécution de l'application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Hello Moustache");
		
		Model model = new Model();
		View view  = new View(model);
		//Controller controller = new Controller(model,view);
		
		view.setVisible(true);
	}
}
