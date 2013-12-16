package ca.usherbrooke.ift232.actuRSS.view.sorter;

import java.util.Comparator;
import java.util.List;

import ca.usherbrooke.ift232.actuRSS.News;


public interface Sorter extends Comparator<News>
{
	public List<News> sort(List<News> news);
}
