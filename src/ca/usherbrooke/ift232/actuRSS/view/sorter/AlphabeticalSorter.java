package ca.usherbrooke.ift232.actuRSS.view.sorter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ca.usherbrooke.ift232.actuRSS.News;

public class AlphabeticalSorter implements Sorter
{

	@Override
	public int compare(News o1, News o2)
	{
		return o1.getTitle().compareTo(o2.getTitle());
	}

	@Override
	public List<News> sort(List<News> news)
	{
		ArrayList<News> newNews = new ArrayList<News>(news);
		Collections.sort(newNews, this);	
		return newNews;
	}

}
