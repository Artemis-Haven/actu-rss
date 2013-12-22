package ca.usherbrooke.ift232.actuRSS.controller.command;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;

import ca.usherbrooke.ift232.actuRSS.Category;
import ca.usherbrooke.ift232.actuRSS.Feed;
import ca.usherbrooke.ift232.actuRSS.model.FeedManager;
import ca.usherbrooke.ift232.actuRSS.model.Model;
import ca.usherbrooke.ift232.actuRSS.view.parameters.DialogEditFeed;
import ca.usherbrooke.ift232.actuRSS.view.parameters.DialogFeedManager;

public class ActionNewCatEditSource extends AbstractAction {

	private DialogEditFeed editFeed;
	private FeedManager feedManager;
	private DialogFeedManager gest;
	private Model model;
	
	public ActionNewCatEditSource(DialogEditFeed editFeedHook, FeedManager feedManagerHook,
			DialogFeedManager gestHook, Model modelHook) {
		this.editFeed = editFeedHook;
		this.feedManager = feedManagerHook;
		this.gest = gestHook;
		this.model = modelHook;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String newCat = editFeed.newCategorie();
		
		feedManager.getOldListCategory().add(
				new Category(-1, newCat, new ArrayList<Feed>()));
		gest.getManageTree().refreshFeeds(
				feedManager.getOldListCategory());

		model.notifyObserver();
		
	}

}
