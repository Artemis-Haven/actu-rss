package ca.usherbrooke.ift232.actuRSS.view.filter;

import java.util.ArrayList;
import java.util.List;

import ca.usherbrooke.ift232.actuRSS.News;

public class ReadFilter extends Filter
{

	@Override
	public List<News> filtrer(List<News> news)
	{
		List<News> filteredNews = new ArrayList<News>();
		for (News element : news) 
		{
			if(element.isRead())
				filteredNews.add(element);
			
		}
		
		return filteredNews;
	}

}
