package ca.usherbrooke.ift232.actuRSS.view.treepicker;

import java.util.EventListener;

public interface FeedSelectedListener extends EventListener
{
	/** onFeedSelected
	 * @param event
	 */
	public void onFeedSelected(FeedSelectedEvent event);

	public void onCategorySelected(CategorySelectedEvent event);
}
