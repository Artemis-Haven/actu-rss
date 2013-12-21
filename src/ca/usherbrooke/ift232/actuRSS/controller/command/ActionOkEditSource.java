package ca.usherbrooke.ift232.actuRSS.controller.command;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import ca.usherbrooke.ift232.actuRSS.Category;
import ca.usherbrooke.ift232.actuRSS.Feed;
import ca.usherbrooke.ift232.actuRSS.model.FeedManager;
import ca.usherbrooke.ift232.actuRSS.model.Model;
import ca.usherbrooke.ift232.actuRSS.model.WrongURLException;
import ca.usherbrooke.ift232.actuRSS.view.parameters.DialogEditFeed;
import ca.usherbrooke.ift232.actuRSS.view.parameters.DialogFeedManager;

public class ActionOkEditSource extends AbstractAction {

	
	private DialogEditFeed editFeed;
	private FeedManager feedManager;
	private boolean newFeed;
	private DialogFeedManager gest;
	private Model model;
	
	
	public ActionOkEditSource(DialogEditFeed editFeedHook, FeedManager feedManagerHook,	boolean newFeedHook, DialogFeedManager gestHook, Model modelHook)
	{
		super();
		this.editFeed = editFeedHook;
		this.feedManager = feedManagerHook;
		this.newFeed = newFeedHook;
		this.gest = gestHook;
		this.model = modelHook;
	}


	public void actionPerformed(ActionEvent e) {
		if (editFeed.Valide()) {
			editFeed.finishedDialog();
			Feed feed = new Feed(editFeed.getId(), editFeed.getName(),
					editFeed.getUrl(), editFeed.getNews());
			String str = editFeed.getCategory();
			feed.setNameCategory(str);
			Category cat = feedManager.getCategoryByName(str);
			Category oldcat = feedManager.getCategoryByName(editFeed.getFeed().getNameCategory());
			// CREER METHODE MODIFYFEED
			try {
				newFeed = feedManager.modifyFeed(feed, cat);
				if (newFeed)
					feedManager.removeFeed(editFeed.getFeed(), oldcat);
			} catch (WrongURLException ex) {
				JDialog Dev = new JDialog();
				JOptionPane.showMessageDialog(Dev, "La nouvelle adresse adresse du flux " + feed.getTitle() + " semble être erroné", "Help",
						new Integer(JOptionPane.INFORMATION_MESSAGE).intValue());
			}	
			gest.getManageTree().refreshFeeds(
					feedManager.getOldListCategory());

			model.notifyObserver();
		}
		
	}

}
