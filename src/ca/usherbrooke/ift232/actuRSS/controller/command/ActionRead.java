package ca.usherbrooke.ift232.actuRSS.controller.command;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;

import ca.usherbrooke.ift232.actuRSS.News;
import ca.usherbrooke.ift232.actuRSS.view.Toolbar;
import ca.usherbrooke.ift232.actuRSS.view.actulist.ActuList;
import ca.usherbrooke.ift232.actuRSS.view.filter.Filter;
import ca.usherbrooke.ift232.actuRSS.view.filter.ReadFilter;
import ca.usherbrooke.ift232.actuRSS.view.sorter.Sorter;

public class ActionRead extends AbstractAction {

	private Toolbar toolbar; 
	private ActuList newsList;
	private Filter theDisplay;
	private List<News> news;
	private Sorter actualSorter;
	
	public ActionRead(Filter theDisplayHook, Toolbar toolbarHook, ActuList newsListHook,List<News> newsHook, Sorter actualSorterHook)
	{
		this.theDisplay = theDisplayHook;
		this.toolbar = toolbarHook;
		this.newsList = newsListHook;
		this.news = newsHook;
		this.actualSorter = actualSorterHook;		
	}
	
	
	public void actionPerformed(ActionEvent e) {
		
	    theDisplay = new ReadFilter();
		toolbar.getFavBtn().setSelected(false);
		toolbar.getReadBtn().setSelected(false);
		newsList.changeNews(news, theDisplay, actualSorter);
		
	}	

}
