package ca.usherbrooke.ift232.actuRSS.controller.command;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import ca.usherbrooke.ift232.actuRSS.model.FeedManager;
import ca.usherbrooke.ift232.actuRSS.view.parameters.DialogAddFeed;

public class ActionAddSource extends AbstractAction {

	private FeedManager feedManager;
	private DialogAddFeed addFeed;
	
	public ActionAddSource(FeedManager feedManagerHook, DialogAddFeed addFeedHook) {
		this.feedManager = feedManagerHook;
		this.addFeed = addFeedHook;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		addFeed.listerCategories(feedManager.getOldListCategory());
		addFeed.showDialog();
	}

}
