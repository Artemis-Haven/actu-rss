package ca.usherbrooke.ift232.actuRSS.properties;

import javax.swing.JDialog;
import javax.swing.JFrame;

public abstract class ParamDialog extends JDialog {

	public ParamDialog(JFrame parent, String title, boolean modal){
		super(parent, title, modal);
		this.setSize(400, 300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	public void showDialog(){
		this.setVisible(true);
	}
	
	public void closeDialog(){
		this.dispose();
	}
}

