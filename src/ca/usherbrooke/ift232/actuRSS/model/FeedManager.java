package ca.usherbrooke.ift232.actuRSS.model;

import java.util.ArrayList;
import java.util.Iterator;

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

		Iterator iterCategory = listCategory.iterator();
		while (iterCategory.hasNext()) {
			Category cat = (Category) iterCategory.next();
			Iterator iterFeed = cat.getListFeed().iterator();
			while (iterFeed.hasNext()) {
				Feed feed = (Feed) iterFeed.next();
				//On suppose que les deux listes de catégories et de flux 
				// (pas de news) sont identiques
				for(News news : feed.getListNews()) {
					
					indexCategory = listCategory.indexOf(cat);
					indexFeed = listCategory.get(indexCategory).getListFeed().indexOf(feed);
					
					if(!newList.get(indexCategory).getListFeed().get(indexFeed).getListNews().contains(news))
					{
						newList.get(indexCategory).getListFeed().get(indexFeed).getListNews().add(news);
					}
				}
			}
		}	      

		oldListCategory.clear();
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
