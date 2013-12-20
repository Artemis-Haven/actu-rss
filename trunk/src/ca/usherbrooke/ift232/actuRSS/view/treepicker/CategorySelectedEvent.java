package ca.usherbrooke.ift232.actuRSS.view.treepicker;
import javax.swing.event.ChangeEvent;

import ca.usherbrooke.ift232.actuRSS.Category;

public class CategorySelectedEvent extends ChangeEvent
{

	private Category _selectedFeed;
	
	/**FeedSelectedEvent : permet de s�lectionner un �v�nement particulier
	 * @param objSource
	 * @param selectedSource
	 */
	public CategorySelectedEvent(Object objSource, Category selectedSource) 
	{
		super(objSource);
	
		_selectedFeed = selectedSource;
	}
	
	/**
	 * getSelectedSource : Permet de r�cup�rer le feed s�lectionner
	 * @return this._selectedFeed 
	 */
	public Category getSelectedSource()
	{
		return this._selectedFeed;
	}
	
}
