package ca.usherbrooke.ift232.actuRSS;

import ca.usherbrooke.ift232.actuRSS.controller.Controller;
import ca.usherbrooke.ift232.actuRSS.model.Model;
import ca.usherbrooke.ift232.actuRSS.view.View;

/**
 * Actu-RSS est un logiciel d'aggrégation de flux RSS.
 * Il propose des fonctionnalités telles que : <br/>
 * - Stockage des flux dans une base de donnée <br/>
 * - Lecture des news en l'absence de connexion <br/>
 * - Classement par statut (lus/non lus/favoris) <br/>
 * 
 * Il a été développé dans le cadre d'un projet à 
 * l'Université de Sherbrooke.<br/>
 * 
 * Nous avons utilisé quelques composants de tierces parties : <br/>
 * - La classe Database provient de Leucistic, membre de OpenClassrooms <br/>
 * - La librairie pour afficher les news avec un moteur de rendu HTML
 * s'appelle FlyingSaucer. <br/>
 * - Pour modifier l'affichage de la liste des News dans la colonne du mileu
 * nous avons utilisé la librairie JGoodies.
 * 
 * @author Julian BIRONNEAU
 * @author David BOAS
 * @author Vincent CHATAIGNIER
 * @author Gauthier CIBERT-VOLPE 
 * @author Benjamin FERRE
 * @author Bastien MEUNIER
 * @author Rémi PATRIZIO
 * @author Matthieu POUPINEAU
 * @author Yann SEREE
 */
public class ActuRSS {

	/** Classe principale de l'application
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
