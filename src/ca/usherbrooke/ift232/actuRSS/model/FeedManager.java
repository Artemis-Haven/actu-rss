package ca.usherbrooke.ift232.actuRSS.model;

import java.util.ArrayList;

public class FeedManager {

	private ArrayList<Category> listCategory;
	private ArrayList<Category> oldListCategory;

	public FeedManager()
	{		
		this.listCategory = new ArrayList<Category>();
		this.oldListCategory = new ArrayList<Category>();		
	}

	public FeedManager(ArrayList<Category>listCategory, ArrayList<Category>oldListCategory)
	{		
		this.listCategory = new ArrayList<Category>(listCategory);
		this.oldListCategory = new ArrayList<Category>(oldListCategory);		
	}

	public void merge()
	{
		ArrayList<Category> newList = new ArrayList<Category>(oldListCategory);

		for (Category elt : listCategory) 
		{					
			if (!newList.contains(elt))
			{
				newList.add(elt);
			}
		}
		
		oldListCategory.clear();	
		this.oldListCategory = newList;		
	}

	public ArrayList<Category> getListCategory() {return listCategory;}
	public ArrayList<Category> getOldListCategory() {return oldListCategory;}


	

}





