package ca.usherbrooke.ift232.actuRSS.view;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Actu-RSS
 * Couche : View
 * Date de creation : 2013-11-01
 * Description : 
 *   La Toolbar est la barre avec les boutons qui
 *   figure en haut de notre fenetre principale.
 *   
 * @author Prenom Nom
 * @version 0.1
 */
 
public class Toolbar extends JPanel {

	/**
	 * Variables privee
	 * @param Description d'une variable
	 *
	 */
	
	/**
	 * Constructeur
	 * 
	 */
	public Toolbar(){
		JButton btn = new JButton("Texte");
		this.add(btn);
	}
}
