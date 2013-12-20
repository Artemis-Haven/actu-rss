package ca.usherbrooke.ift232.actuRSS.controller.command;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.Action;

import ca.usherbrooke.ift232.actuRSS.view.Toolbar;
import ca.usherbrooke.ift232.actuRSS.view.actulist.ActuList;

public class ActionRead extends AbstractAction {

	private Toolbar toolbar; 
	private ActuList newsList;
	
	public ActionRead(Toolbar toolbarHook, ActuList newsListHoock)
	{
		this.toolbar =  toolbarHook;
		this.newsList = newsListHoock;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		
	/*//	ReadFilter theDisplay = new ReadFilter();
		toolbar.getFavBtn().setSelected(false);
		toolbar.getReadBtn().setSelected(false);
		//TODO newsList.changeNews(news, theDisplay);
		*/
		
	}

	

	

}
