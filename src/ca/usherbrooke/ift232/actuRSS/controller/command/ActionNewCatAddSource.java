package ca.usherbrooke.ift232.actuRSS.controller.command;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;

import ca.usherbrooke.ift232.actuRSS.Category;
import ca.usherbrooke.ift232.actuRSS.Feed;
import ca.usherbrooke.ift232.actuRSS.model.FeedManager;
import ca.usherbrooke.ift232.actuRSS.model.Model;
import ca.usherbrooke.ift232.actuRSS.view.parameters.DialogAddFeed;
import ca.usherbrooke.ift232.actuRSS.view.parameters.DialogFeedManager;

public class ActionNewCatAddSource extends AbstractAction {

	private DialogAddFeed addFeed;
	private FeedManager feedManager;
	private DialogFeedManager gest;
	private Model model;
	
	public ActionNewCatAddSource(DialogAddFeed addFeedHook, FeedManager feedManagerHook,
			DialogFeedManager gestHook, Model modelHook) {
		this.addFeed = addFeedHook;
		this.feedManager = feedManagerHook;
		this.gest = gestHook;
		this.model = modelHook;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String newCat = addFeed.newCategorie();
		if (newCat != null && !newCat.equals("")) {
			feedManager.getOldListCategory().add(
					new Category(-1, newCat, new ArrayList<Feed>()));
			gest.getManageTree().refreshFeeds(
					feedManager.getOldListCategory());

			model.notifyObserver();
		}
	}

}
