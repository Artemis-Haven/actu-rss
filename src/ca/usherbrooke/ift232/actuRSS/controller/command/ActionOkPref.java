package ca.usherbrooke.ift232.actuRSS.controller.command;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;

import ca.usherbrooke.ift232.actuRSS.News;
import ca.usherbrooke.ift232.actuRSS.view.MainPanel;
import ca.usherbrooke.ift232.actuRSS.view.actulist.ActuList;
import ca.usherbrooke.ift232.actuRSS.view.filter.Filter;
import ca.usherbrooke.ift232.actuRSS.view.parameters.ViewChangeProperties;
import ca.usherbrooke.ift232.actuRSS.view.sorter.Sorter;

public class ActionOkPref extends AbstractAction {

	private ViewChangeProperties pref;
	private MainPanel mainPanel;
	private ActuList newsList;
	private Filter theDisplay;
	private List<News> news;
	private Sorter actualSorter;
	
	public ActionOkPref(ViewChangeProperties prefHook, MainPanel mainPanelHook, ActuList newsListHook, Filter theDisplayHook, List<News> newsHook, Sorter actualSorterHook)
	{
		this.pref = prefHook;
		this.mainPanel = mainPanelHook;
		this.newsList = newsListHook;
		this.theDisplay = theDisplayHook;
		this.news = newsHook;
		this.actualSorter = actualSorterHook;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		pref.finishDialog();
		mainPanel.getContentPanel().display();
		newsList.changeNews(news, theDisplay, actualSorter);
		
	}

}
