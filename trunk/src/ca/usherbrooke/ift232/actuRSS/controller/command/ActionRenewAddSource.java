package ca.usherbrooke.ift232.actuRSS.controller.command;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import ca.usherbrooke.ift232.actuRSS.view.parameters.DialogAddFeed;

public class ActionRenewAddSource extends AbstractAction {

private DialogAddFeed addFeed;
	
	public ActionRenewAddSource(DialogAddFeed addFeedHook) {
		this.addFeed = addFeedHook;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		addFeed.renewDialog();
	}

}
