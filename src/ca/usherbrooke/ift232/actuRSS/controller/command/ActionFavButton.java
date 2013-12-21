package ca.usherbrooke.ift232.actuRSS.controller.command;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import ca.usherbrooke.ift232.actuRSS.News;
import ca.usherbrooke.ift232.actuRSS.controller.Controller;

public class ActionFavButton extends AbstractAction {

	private Controller controller;
	
	public ActionFavButton(Controller controllerHook) 
	{
		this.controller = controllerHook;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		
		News newsSelected = controller.getSelectedNews();
		if (newsSelected != null) {
			if (newsSelected.isFavorite()) {
				newsSelected.setFavorite(false);
			} else {
				newsSelected.setFavorite(true);
				// System.out.println(newsSelected.getTitle());
			}
		}
		
	}

}
