package ca.usherbrooke.ift232.actuRSS.view.treepicker;

import java.util.EventListener;

public interface SourceSelectedListener extends EventListener
{
	public void onSourceSelected(SourceSelectedEvent event);
}
