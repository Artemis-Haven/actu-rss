package ca.usherbrooke.ift232.actuRSS.controller.command;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import ca.usherbrooke.ift232.actuRSS.model.Model;
import ca.usherbrooke.ift232.actuRSS.model.SyncRunnable;

public class ActionSync extends AbstractAction {

	private Model model;
	
	public ActionSync(Model modelHook) {
		this.model = modelHook;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		SyncRunnable.main();
		model.notifyObserver();
	}

}
