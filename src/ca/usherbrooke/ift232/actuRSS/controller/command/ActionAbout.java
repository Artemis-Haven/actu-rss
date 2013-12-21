package ca.usherbrooke.ift232.actuRSS.controller.command;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class ActionAbout extends AbstractAction {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JDialog Dev = new JDialog();
		JOptionPane.showMessageDialog(Dev,
				"Développé par : \nYann SEREE\nDavid BOAS\nJulian BIRONNEAU\nVincent CHATAIGNIER\nGauthier CIBERT-VOLPE\nBenjamin FERRE\nBastien MEUNIER\nRémi PATRIZIO\nMatthieu POUPINEAU\n© ActuRSS dream team"
				,		
				"About",
				new Integer(JOptionPane.INFORMATION_MESSAGE).intValue());
		
	}

}
