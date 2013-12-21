package ca.usherbrooke.ift232.actuRSS.controller.command;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import ca.usherbrooke.ift232.actuRSS.News;
import ca.usherbrooke.ift232.actuRSS.controller.Controller;


public class ActionReadButton extends AbstractAction {

	private Controller controller;
	
	public ActionReadButton(Controller controllerHook) {
		this.controller = controllerHook;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		News newsSelected = controller.getSelectedNews();

		if (newsSelected != null) {
			if (newsSelected.isRead()) {
				newsSelected.setRead(false);
			} else {
				newsSelected.setRead(true);
			}
		}
	}

}
