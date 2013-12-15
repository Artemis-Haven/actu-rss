package ca.usherbrooke.ift232.actuRSS.view.Filter;

import java.util.ArrayList;
import java.util.List;

import ca.usherbrooke.ift232.actuRSS.News;

public class FavoriteFilter extends Filter
{

	@Override
	public List<News> filtrer(List<News> news)
	{
		List<News> filteredNews = new ArrayList<News>();
		for (News element : news) 
		{
			if(element.isFavorite())
				filteredNews.add(element);
			
		}
		
		return filteredNews;
	}

}
