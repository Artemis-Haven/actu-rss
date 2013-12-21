package ca.usherbrooke.ift232.actuRSS.controller.command;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import ca.usherbrooke.ift232.actuRSS.view.View;
import ca.usherbrooke.ift232.actuRSS.view.parameters.ViewChangeProperties;

public class ActionPref extends AbstractAction {

	private ViewChangeProperties pref;
	
	public ActionPref(ViewChangeProperties pref ) {
		this.pref = pref;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		pref.showDialog();
	}

}
