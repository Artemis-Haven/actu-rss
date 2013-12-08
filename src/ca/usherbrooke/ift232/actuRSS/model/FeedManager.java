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
					String urlNews = listeNews.get(news).getUrl();
					
					for(int newsTemp = 0; newsTemp < listeNewsTemp.size(); newsTemp++)
					{
						String urlNewsTemmp = listeNewsTemp.get(newsTemp).getUrl();
						
						if (urlNews == urlNewsTemmp)
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
	
	public void addFeed(Feed feed, Category cat)
	{
		//Ajouter le feed dans oldListCategory en supposant que la categorie est correct
		this.getCategoryByName(cat.getName()).getListFeed().add(feed);
		//System.out.println(oldListCategory.toString());
		
	}
	
	public void removeFeed(Feed feed, Category cat)
	{
		//Ajouter le feed dans oldListCategory en supposant que la categorie et le feed soient correct
		this.getCategoryByName(cat.getName()).getListFeed().remove(feed);
	}

	public ArrayList<Category> getListCategory() {return listCategory;}

	public ArrayList<Category> getOldListCategory() {return oldListCategory;}

	public void setListCategory(ArrayList<Category> listCategory) 
	{
		this.listCategory = listCategory;
	}

	public void setOldListCategory(ArrayList<Category> oldListCategory)
	{
		this.oldListCategory = oldListCategory;
	}
	
	public Category getCategoryByName(String name)
	{	
		
		//System.out.println(oldListCategory);
		for(Category cat : this.oldListCategory)
		{
			
			//System.out.println("Nom de la cat envoye : " + cat.getName());
			if(cat.getName().equals(name))
			{
				return cat;
			}
		}		
		//Si la categorie n'a pas été trouvé
		return null;		
	}
	

}
