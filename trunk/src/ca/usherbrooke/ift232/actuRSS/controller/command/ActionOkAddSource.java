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
import ca.usherbrooke.ift232.actuRSS.view.parameters.DialogAddFeed;
import ca.usherbrooke.ift232.actuRSS.view.parameters.DialogFeedManager;

public class ActionOkAddSource extends AbstractAction {

	private DialogAddFeed addFeed;
	private FeedManager feedManager;
	private boolean newFeed;
	private DialogFeedManager gest;
	private Model model;
	
	public ActionOkAddSource(DialogAddFeed addFeedHook, FeedManager feedManagerHook, boolean newFeedHook,
			DialogFeedManager gestHook, Model modelHook) {
		this.addFeed = addFeedHook;
		this.feedManager = feedManagerHook;
		this.newFeed = newFeedHook;
		this.gest = gestHook;
		this.model = modelHook;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (addFeed.Valide()) {
			addFeed.finishedDialog();
			Feed feed = new Feed(-1, addFeed.getName(), addFeed.getUrl());
			String str = addFeed.getCategory();
			feed.setNameCategory(str);
			Category cat = feedManager.getCategoryByName(str);
			try {
				newFeed = feedManager.addFeed(feed, cat);
				if (newFeed == false) {
					JDialog Dev = new JDialog();
					JOptionPane.showMessageDialog(Dev, "Le flux Rss que vous essayer d'ajouter existe déjà !", "Help",
							new Integer(JOptionPane.INFORMATION_MESSAGE).intValue());
				}
			} catch (WrongURLException ex) {
				JDialog Dev = new JDialog();
				JOptionPane.showMessageDialog(Dev, "L'adresse du flux " + feed.getTitle() + " semble être erroné", "Help",
						new Integer(JOptionPane.INFORMATION_MESSAGE).intValue());
			}	

			gest.getManageTree().refreshFeeds(
					feedManager.getOldListCategory());
			// System.out.println(feedManager.getOldListCategory().toString());

			model.notifyObserver();
		}

	}
}
