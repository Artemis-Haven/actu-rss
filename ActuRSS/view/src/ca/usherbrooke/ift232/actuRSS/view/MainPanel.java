package ca.usherbrooke.ift232.actuRSS.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

/**
 * Actu-RSS
 * Couche : View
 * Date de creation : 2013-11-01
 * Description : 
 *   Panel principal de notre application.
 *   Il contient les autres sous-panels.
 *   Il est de tye BorderLayout.
 *   
 * @author Prenom Nom
 * @version 0.1
 */

public class MainPanel extends JPanel {

	/**
	 * Variables privee
	 * @param Description d'une variable
	 *
	 */
	 public Toolbar toolbar;
	 public TreeAndList treeandlist;
	 JSplitPane splitPane;
	 
	/**
	 * Constructeur
	 * 
	 */
	 MainPanel()
	 {
		 this.setLayout(new BorderLayout());
		 this.toolbar = new Toolbar();
		 this.treeandlist = new TreeAndList();
		 this.add(toolbar, BorderLayout.PAGE_START);
		 splitPane = new JSplitPane();
		 splitPane.setLeftComponent(treeandlist);
		 splitPane.setRightComponent(new JPanel());
		 this.add(splitPane);
	 }
	
}
