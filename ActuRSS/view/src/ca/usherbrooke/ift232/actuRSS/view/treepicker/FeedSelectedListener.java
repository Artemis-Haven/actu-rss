package ca.usherbrooke.ift232.actuRSS.view.treepicker;

import java.util.EventListener;

public interface FeedSelectedListener extends EventListener
{
	public void onFeedSelected(FeedSelectedEvent event);
}
