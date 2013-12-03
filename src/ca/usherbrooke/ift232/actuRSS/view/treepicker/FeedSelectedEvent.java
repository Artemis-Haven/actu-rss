package ca.usherbrooke.ift232.actuRSS.view.treepicker;

import javax.swing.event.ChangeEvent;

import ca.usherbrooke.ift232.actuRSS.Feed;

public class FeedSelectedEvent extends ChangeEvent
{

	private Feed _selectedFeed;
	
	/**FeedSelectedEvent : permet de sélectionner un évènement particulier
	 * @param objSource
	 * @param selectedSource
	 */
	public FeedSelectedEvent(Object objSource, Feed selectedSource) 
	{
		super(objSource);
	
		_selectedFeed = selectedSource;
	}
	
	/**
	 * getSelectedSource : Permet de récupérer le feed sélectionner
	 * @return this._selectedFeed 
	 */
	public Feed getSelectedSource()
	{
		return this._selectedFeed;
	}
	
}
