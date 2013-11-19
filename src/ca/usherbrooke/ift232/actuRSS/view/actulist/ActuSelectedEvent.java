package ca.usherbrooke.ift232.actuRSS.view.actulist;

import javax.swing.event.ChangeEvent;

import ca.usherbrooke.ift232.actuRSS.News;


public class ActuSelectedEvent extends ChangeEvent
{

	private News _selectedActu;
	
	public ActuSelectedEvent(Object objSource, News selectedActu) 
	{
		super(objSource);
	
		_selectedActu = selectedActu;
	}
	
	public News getSelectedSource()
	{
		return this._selectedActu;
	}
	
}