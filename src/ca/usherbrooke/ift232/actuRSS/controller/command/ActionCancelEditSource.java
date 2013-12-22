package ca.usherbrooke.ift232.actuRSS.controller.command;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import ca.usherbrooke.ift232.actuRSS.view.parameters.DialogEditFeed;

public class ActionCancelEditSource extends AbstractAction {

	private DialogEditFeed editFeed;
	
	public ActionCancelEditSource(DialogEditFeed editFeedHook) {
		this.editFeed = editFeedHook;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		editFeed.closeDialog();
	}

}
