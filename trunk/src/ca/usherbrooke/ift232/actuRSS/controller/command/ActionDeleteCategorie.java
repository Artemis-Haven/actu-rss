package ca.usherbrooke.ift232.actuRSS.controller.command;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import ca.usherbrooke.ift232.actuRSS.Category;
import ca.usherbrooke.ift232.actuRSS.model.FeedManager;
import ca.usherbrooke.ift232.actuRSS.model.Model;
import ca.usherbrooke.ift232.actuRSS.view.parameters.DialogFeedManager;


public class ActionDeleteCategorie extends AbstractAction {
	

private Category deletecat;
private FeedManager feedManager;
private DialogFeedManager gest;
private Model model;	

	public ActionDeleteCategorie(Category deletecatHook, FeedManager feedManagerHook, DialogFeedManager gestHook, Model modelHook)
{
	super();
	this.deletecat = deletecatHook;
	this.feedManager = feedManagerHook;
	this.gest = gestHook;
	this.model = modelHook;
}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(deletecat != null){
			feedManager.removeCategory(deletecat);
			gest.getManageTree().refreshFeeds(
					feedManager.getOldListCategory());
			gest.putDeleteCategoryEditable(false);
		}
		model.notifyObserver();
		
	}

}
