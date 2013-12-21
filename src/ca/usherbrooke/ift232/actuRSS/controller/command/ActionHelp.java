package ca.usherbrooke.ift232.actuRSS.controller.command;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class ActionHelp extends AbstractAction {
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JDialog Dev = new JDialog();
		JOptionPane.showMessageDialog(Dev, "Si un problème survient lors de l'utilisation de ce logiciel, contacter la ActuRss Team!", "Help",
				new Integer(JOptionPane.INFORMATION_MESSAGE).intValue());
	}

}
