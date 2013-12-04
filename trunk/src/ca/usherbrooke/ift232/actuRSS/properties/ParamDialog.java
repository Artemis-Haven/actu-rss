package ca.usherbrooke.ift232.actuRSS.properties;

import javax.swing.JDialog;
import javax.swing.JFrame;

public abstract class ParamDialog extends JDialog {

	/**
	 * Permet d'initialiser les paramètres du Dialog
	 * @param parent
	 * @param title
	 * @param modal
	 */
	public ParamDialog(JFrame parent, String title, boolean modal){
		super(parent, title, modal);
		this.setSize(500, 400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	/**
	 * Permet de montrer le Dialog
	 */
	public void showDialog(){
		this.setVisible(true);
	}
	
	/**
	 * Permet de fermer le Dialog
	 */
	public void closeDialog(){
		this.dispose();
	}
}

