package ca.usherbrooke.ift232.actuRSS.controller.command;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import ca.usherbrooke.ift232.actuRSS.Category;
import ca.usherbrooke.ift232.actuRSS.model.FeedManager;
import ca.usherbrooke.ift232.actuRSS.model.Model;
import ca.usherbrooke.ift232.actuRSS.view.parameters.DialogEditFeed;
import ca.usherbrooke.ift232.actuRSS.view.parameters.DialogFeedManager;

public class ActionDeleteSource extends AbstractAction {

	private FeedManager feedManager;
	private DialogEditFeed editFeed;
	private DialogFeedManager gest;
	private Model model;
	
	public ActionDeleteSource(FeedManager feedManagerHook, DialogEditFeed editFeedHook,
			DialogFeedManager gestHook, Model modelHook) {
		this.feedManager = feedManagerHook;
		this.editFeed = editFeedHook;
		this.gest = gestHook;
		this.model = modelHook;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Category oldcat = feedManager.getCategoryByName(editFeed.getFeed().getNameCategory());
		feedManager.removeFeed(editFeed.getFeed(), oldcat);
		gest.getManageTree().refreshFeeds(
				feedManager.getOldListCategory());

		model.notifyObserver();
	}

}
