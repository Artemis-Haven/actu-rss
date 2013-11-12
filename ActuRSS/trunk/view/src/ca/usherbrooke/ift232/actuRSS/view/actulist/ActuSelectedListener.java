package ca.usherbrooke.ift232.actuRSS.view.actulist;


import java.util.EventListener;

public interface ActuSelectedListener extends EventListener
{
	public void onActuSelected(ActuSelectedEvent event);
}
