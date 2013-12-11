package ca.usherbrooke.ift232.actuRSS.model;

import java.util.ArrayList;
import java.util.List;

import ca.usherbrooke.ift232.actuRSS.Category;
import ca.usherbrooke.ift232.actuRSS.Feed;
import ca.usherbrooke.ift232.actuRSS.News;

/**
 * Le FeedManager est une des principales classes du Modele :
 * Elle contient toutes les données (Feed, News et Category)
 * que l'on manipule.
 * Elle contient deux listes de catégories : 
 * oldListCategory est la liste qui provient de la BdD, c'est
 * celle-ci que l'on manipule la plupart du temps.
 * listCategory est la liste qui provient du RssParser, c'est
 * là qu'est stocké le contenu de tous les flux parsés, avant
 * la fusion de cette liste avec l'ancienne.
 * 
 * @author Rémi Patrizio, Yann Seree, David Boas
 */
public class FeedManager {

	/**
	 * listCategory est la liste qui provient du RssParser, c'est
	 * là qu'est stocké le contenu de tous les flux parsés, avant
	 * la fusion de cette liste avec l'ancienne.
	 */
	private ArrayList<Category> listCategory;
	
	/**
	 * oldListCategory est la liste qui provient de la BdD, c'est
	 * celle-ci que l'on manipule la plupart du temps.
	 */
	private ArrayList<Category> oldListCategory;

	/**
	 * Constructeur du FeedManager vide
	 */
	public FeedManager() {
		this.listCategory = new ArrayList<Category>();
		this.oldListCategory = new ArrayList<Category>();
	}

	/**
	 * Constructeur du FeedManager avec des listes de
	 * catégories en paramètre
	 * 
	 * @param listCategory
	 * @param oldListCategory
	 */
	public FeedManager(ArrayList<Category> listCategory,
			ArrayList<Category> oldListCategory) {
		this.listCategory = new ArrayList<Category>(listCategory);
		this.oldListCategory = new ArrayList<Category>(oldListCategory);
	}

	/**
	 * La fonction merge() a pour but de réunir les anciennes news 
	 * provenant de la BdD (stockées dans oldListCategory) avec les nouvelles
	 * news qui proviennent du RssParser (stockées dans listCategory).
	 * Cette fonction prend chaque flux de listCategory et son équivalent dans
	 * oldListCategory. Si une news est dans listCategory mais pas dans l'ancienne
	 * alors elle y est ajoutée.
	 * 
	 * Pré-conditions : listCategory et oldListCategory doivent contenir la même
	 * liste de category et, à l'intérieur de chacune d'entre elle, la même liste
	 * de flux, mais bien sur pas forcément la même liste de news.
	 */
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
					String urlNewsTemmp="";
					for(int newsTemp = 0; newsTemp < listeNewsTemp.size(); newsTemp++)
					{
						urlNewsTemmp = listeNewsTemp.get(newsTemp).getUrl();
						
						//if (urlNews.equals(urlNewsTemmp))
						if (urlNews.equals(urlNewsTemmp))
						{
							contient = true;
							break;
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
	
	/**
	 * Ajoute un flux passé en paramètre dans la catégorie
	 * aussi passée en paramètre.
	 * 
	 * @param feed Le flux à ajouter
	 * @param cat La catégorie dans laquelle on veut l'ajouter
	 */
	public boolean addFeed(Feed feed, Category cat)
	{
		boolean NewFeed = true;
		//Ajouter le feed dans oldListCategory en supposant que la categorie est correct
		for (Category category : oldListCategory)
			for (Feed feedCat : category.getListFeed())
				if (feed.getUrl().equals(feedCat.getUrl()))	
					NewFeed = false;
		if (NewFeed)
			this.getCategoryByName(cat.getName()).getListFeed().add(feed);
		return NewFeed;
		//System.out.println(oldListCategory.toString());
		
	}

	/**
	 * Supprime un flux passé en paramètre dans la catégorie
	 * aussi passée en paramètre.
	 * 
	 * @param feed Le flux à supprimer
	 * @param cat La catégorie dans laquelle on veut le supprimer
	 */
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
	
	/**
	 * Retourne la catégorie (dans la liste oldListCategory)
	 * dont le nom est passé en paramètre.
	 * 
	 * @param name Nom de la catégorie recherchée
	 * @return La catégorie recherchée, null si non trouvée
	 */
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
