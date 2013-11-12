package ca.usherbrooke.ift232.actuRSS.view.actulist;

import javax.swing.event.ChangeEvent;

import ca.usherbrooke.ift232.actuRSS.common.Actu;


public class ActuSelectedEvent extends ChangeEvent
{

	private Actu _selectedActu;
	
	public ActuSelectedEvent(Object objSource, Actu selectedActu) 
	{
		super(objSource);
	
		_selectedActu = selectedActu;
	}
	
	public Actu getSelectedSource()
	{
		return this._selectedActu;
	}
	
}