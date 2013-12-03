package ca.usherbrooke.ift232.actuRSS.view.treepicker;

import javax.swing.event.ChangeEvent;

import ca.usherbrooke.ift232.actuRSS.Feed;

public class FeedSelectedEvent extends ChangeEvent
{

	private Feed _selectedFeed;
	
	public FeedSelectedEvent(Object objSource, Feed selectedSource) 
	{
		super(objSource);
	
		_selectedFeed = selectedSource;
	}
	
	public Feed getSelectedSource()
	{
		return this._selectedFeed;
	}
	
}
