package ca.usherbrooke.ift232.actuRSS.view.treepicker;

import javax.swing.event.ChangeEvent;

import ca.usherbrooke.ift232.actuRSS.common.Source;

public class SourceSelectedEvent extends ChangeEvent
{

	private Source _selectedSource;
	
	public SourceSelectedEvent(Object objSource, Source selectedSource) 
	{
		super(objSource);
	
		_selectedSource = selectedSource;
	}
	
	public Source getSelectedSource()
	{
		return this._selectedSource;
	}
	
}
