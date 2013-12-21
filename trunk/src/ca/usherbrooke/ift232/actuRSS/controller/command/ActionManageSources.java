package ca.usherbrooke.ift232.actuRSS.controller.command;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import ca.usherbrooke.ift232.actuRSS.view.parameters.DialogFeedManager;

public class ActionManageSources extends AbstractAction {

	private DialogFeedManager gest;
	
	public ActionManageSources(DialogFeedManager gestHook) {
	
		this.gest = gestHook;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		gest.showDialog();
		
	}

}
