package ca.usherbrooke.ift232.actuRSS.controller.command;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import ca.usherbrooke.ift232.actuRSS.view.parameters.ViewChangeProperties;

public class ActionResetPref extends AbstractAction {

	private ViewChangeProperties pref;
	
	public ActionResetPref(ViewChangeProperties prefHook) {
		super();		
		this.pref = prefHook;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		pref.renewDialog();
		
	}



}
