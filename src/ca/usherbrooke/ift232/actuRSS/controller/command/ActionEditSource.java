package ca.usherbrooke.ift232.actuRSS.controller.command;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import ca.usherbrooke.ift232.actuRSS.model.FeedManager;
import ca.usherbrooke.ift232.actuRSS.view.parameters.DialogEditFeed;

public class ActionEditSource extends AbstractAction {

	
	private DialogEditFeed editFeed;
	private FeedManager feedManager;
	
	public ActionEditSource(DialogEditFeed editFeedHook, FeedManager feedManagerHook) {
		super();
		this.editFeed = editFeedHook;
		this.feedManager = feedManagerHook;
	}

	public void actionPerformed(ActionEvent e) {
		editFeed.listerCategories(feedManager.getOldListCategory());
		editFeed.showDialog();
		
	}

}
