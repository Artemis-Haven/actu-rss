package ca.usherbrooke.ift232.actuRSS.controller.command;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import ca.usherbrooke.ift232.actuRSS.view.parameters.DialogEditFeed;

public class ActionRenewEditSource extends AbstractAction {

	private DialogEditFeed editFeed;
	
	public ActionRenewEditSource(DialogEditFeed editFeedHook) {
		super();
		this.editFeed = editFeedHook;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		editFeed.renewDialog();
		
	}

}
