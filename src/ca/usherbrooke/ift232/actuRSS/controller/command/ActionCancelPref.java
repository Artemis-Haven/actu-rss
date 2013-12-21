package ca.usherbrooke.ift232.actuRSS.controller.command;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import ca.usherbrooke.ift232.actuRSS.view.parameters.ViewChangeProperties;

public class ActionCancelPref extends AbstractAction {

	private ViewChangeProperties pref;
	
	public ActionCancelPref(ViewChangeProperties prefHook) {
		this.pref = prefHook;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		pref.closeDialog();
	}

}
