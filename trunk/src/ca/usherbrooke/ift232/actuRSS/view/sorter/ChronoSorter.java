package ca.usherbrooke.ift232.actuRSS.view.sorter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ca.usherbrooke.ift232.actuRSS.News;

public class ChronoSorter implements Sorter
{


	public int compare(News arg0, News arg1)
	{
		return arg0.getDate().compareTo(arg1.getDate());
	}

	@Override
	public List<News> sort(List<News> news)
	{
		ArrayList<News> newNews = new ArrayList<News>(news);
		Collections.sort(newNews, this);	
		return newNews;
	}

}
