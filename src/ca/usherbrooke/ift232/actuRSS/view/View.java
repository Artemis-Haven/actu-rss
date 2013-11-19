package ca.usherbrooke.ift232.actuRSS.view;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.UIManager;

import ca.usherbrooke.ift232.actuRSS.model.Model;

/**
 * Actu-RSS
 * Couche : View
 * Date de creation : 2013-11-01
 * Description : 
 *   Classe principale de la Vue. C'est une JFrame qui 
 *   contient le Panel principal et qui implémente 
 *   l'interface Observer.
 *   
 * @author Rémi Patrizio, Yann Seree
 * @version 0.1
 */

public class View extends JFrame implements Observer {
	
	/**
	 * Variables 
	 * 
	 * @param mainPanel : Panel principal qui contient tous
	 * les autres sous-panels.
	 * @param model : C'est un lien vers le Modele de 
	 * l'application
	 *
	 */
	private MainPanel mainPanel;
	//private Model model;
	
	/**
	 * Constructeur
	 * @param model : 
	 * 
	 */
	public View() {
		// Utiliser le look and feel de l'OS utilisé
	    //si possible.
		try {
        UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
		} 
		catch (Exception e) {
			System.out.println("Impossible d'utiliser le thème graphique de l'OS.");
		}
		
		// Initialisation des variables		
		this.mainPanel = new MainPanel();
		
		
		// Définir les caractéristiques de la fenetre.
		this.setTitle("MoustachuRSS");
	    this.setSize(800, 500);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
	    
	    // Ajouter le panel principal
	    this.setContentPane(mainPanel);
	    
	}

	public MainPanel getMainPanel() {return mainPanel;}

	/**
	 * update(Observable, Object)
	 * 
	 * Surcharge de la méthode update de Observer.
	 * 
	 * @param Observable
	 * @param Object
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
		
	}
}
