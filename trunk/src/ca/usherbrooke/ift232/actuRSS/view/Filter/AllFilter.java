package ca.usherbrooke.ift232.actuRSS.view.Filter;

import java.util.ArrayList;
import java.util.List;

import ca.usherbrooke.ift232.actuRSS.News;

public class AllFilter extends Filter
{

	@Override
	public List<News> filtrer(List<News> news)
	{
		return new ArrayList(news);
	}

}
