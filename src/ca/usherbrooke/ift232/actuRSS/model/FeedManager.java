package ca.usherbrooke.ift232.actuRSS.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ca.usherbrooke.ift232.actuRSS.Category;
import ca.usherbrooke.ift232.actuRSS.Feed;
import ca.usherbrooke.ift232.actuRSS.News;

public class FeedManager {

	private ArrayList<Category> listCategory;
	private ArrayList<Category> oldListCategory;

	public FeedManager() {
		this.listCategory = new ArrayList<Category>();
		this.oldListCategory = new ArrayList<Category>();
	}

	public FeedManager(ArrayList<Category> listCategory,
			ArrayList<Category> oldListCategory) {
		this.listCategory = new ArrayList<Category>(listCategory);
		this.oldListCategory = new ArrayList<Category>(oldListCategory);
	}

	public void merge() {
		ArrayList<Category> newList = new ArrayList<Category>(oldListCategory);
		int indexCategory;
		int indexFeed;
		Boolean contient = false;
		

		for(int category = 0; category<listCategory.size();category++)
		{
			List<Feed> listFeed = listCategory.get(category).getListFeed();
			for(int feed = 0; feed<listFeed.size(); feed++)
			{
				List<News> listeNews = listFeed.get(feed).getListNews();
				List<News> listeNewsTemp = newList.get(category).getListFeed().get(feed).getListNews();
				for(int news = 0; news<listeNews.size(); news++) 
				{
					contient = false;
					String titreNews = listeNews.get(news).getTitle();
					
					for(int newsTemp = 0; newsTemp < listeNewsTemp.size(); newsTemp++)
					{
						String titreNewsTemmp = listeNewsTemp.get(newsTemp).getTitle();
						
						if (titreNews == titreNewsTemmp)
						{
							contient = true;
						}
					}
					if(contient == false)
					{
						listeNewsTemp.add(listeNews.get(news));
					}
				}
			}
		}

		this.oldListCategory.clear();
		listCategory.clear();
		this.oldListCategory = newList;
	}

	public ArrayList<Category> getListCategory() {
		return listCategory;
	}

	public ArrayList<Category> getOldListCategory() {
		return oldListCategory;
	}

	public void setListCategory(ArrayList<Category> listCategory) {
		this.listCategory = listCategory;
	}

	public void setOldListCategory(ArrayList<Category> oldListCategory) {
		this.oldListCategory = oldListCategory;
	}

}
