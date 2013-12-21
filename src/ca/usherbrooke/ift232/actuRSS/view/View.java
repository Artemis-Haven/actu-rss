package ca.usherbrooke.ift232.actuRSS.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.UIManager;

import ca.usherbrooke.ift232.actuRSS.Category;
import ca.usherbrooke.ift232.actuRSS.view.parameters.DialogAddFeed;
import ca.usherbrooke.ift232.actuRSS.view.parameters.DialogEditFeed;
import ca.usherbrooke.ift232.actuRSS.view.parameters.DialogFeedManager;
import ca.usherbrooke.ift232.actuRSS.view.parameters.ViewChangeProperties;

/**
 * Actu-RSS
 * Couche : View
 * Date de creation : 2013-11-01
 * Description : 
 *   Classe principale de la Vue. C'est une JFrame qui 
 *   contient le Panel principal et qui implémente 
 *   l'interface Observer.
 *   
 * @author Remi Patrizio, Yann Seree
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
	private ViewChangeProperties preference;
	private DialogFeedManager sourceManager;
	private DialogEditFeed editFeed;
	private DialogAddFeed addFeed;
	private HashMap action;

	//private Model model;
	
	/**
	 * Constructeur
	 * @param model : 
	 * 
	 */
	public View(HashMap action) {
		// Utiliser le look and feel de l'OS utilisé
	    //si possible.
		this.action = action;
		try {
        UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
		} 
		catch (Exception e) {
			System.out.println("Impossible d'utiliser le thème graphique de l'OS.");
		}
		
		// Initialisation des variables		
		this.mainPanel = new MainPanel(action);
		this.preference = new ViewChangeProperties(null, "Préférence", true);
		this.sourceManager = new DialogFeedManager(null, "Gestion des sources", true);
		this.editFeed = new DialogEditFeed(null,"Modification d'un Flux",true);
		this.addFeed = new DialogAddFeed(null,"Ajout d'un Flux",true);		
		
		// Définir les caractéristiques de la fenetre.
		this.setTitle("heRmeSS");
	    this.setSize(800, 500);
	    this.setMinimumSize(new Dimension(680,200));
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
	    
	    // Ajouter le panel principal
	    this.setContentPane(mainPanel);
	    
	}

	public MainPanel getMainPanel() {return mainPanel;}

	
	public ViewChangeProperties getPreference() {return preference;}
	
	public DialogFeedManager getSourceManager(){ return sourceManager;}

	public void addListener(ActionListener e)
	{
		this.mainPanel.addListener(e);
		this.preference.addListener(e);
		this.sourceManager.addListener(e);
		this.editFeed.addListener(e);
		this.addFeed.addListener(e);
	}
	
	
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
		
		ArrayList<Category> categoryList = (ArrayList<Category>)arg1;		
		
		/* Refactorisation : la méthode update du mainPanel est appelée	*/	
		this.getMainPanel().update(categoryList);
		this.repaint();		
	}
	
	

	public DialogAddFeed getAddFeedDialog() {
		return addFeed;
	}
	
	public DialogEditFeed getEditFeedDialog(){
		return editFeed;
	}

	public HashMap getAction() {return action;}

	
	public void setAction(HashMap action) 
	{
		this.action = action;
		this.mainPanel.setAction(action);
		this.preference.setAction(action);
		this.addFeed.setAction(action);
		this.sourceManager.setAction(action);
	}
}
