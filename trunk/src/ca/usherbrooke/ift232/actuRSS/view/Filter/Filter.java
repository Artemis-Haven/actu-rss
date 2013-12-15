package ca.usherbrooke.ift232.actuRSS.view.Filter;

import java.util.List;

import ca.usherbrooke.ift232.actuRSS.News;

public abstract class Filter
{
	
	public abstract List<News> filtrer(List<News> news);
}
