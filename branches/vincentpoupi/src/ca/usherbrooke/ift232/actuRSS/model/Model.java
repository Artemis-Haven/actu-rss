package ca.usherbrooke.ift232.actuRSS.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import ca.usherbrooke.ift232.actuRSS.Category;
import ca.usherbrooke.ift232.actuRSS.Feed;
import ca.usherbrooke.ift232.actuRSS.News;
import ca.usherbrooke.ift232.actuRSS.bdd.Database;
import ca.usherbrooke.ift232.actuRSS.bdd.DatabaseManager;

public class Model {

	DatabaseManager dbManager;
	FeedManager feedManager;
	
	public Model() {

		dbManager = new DatabaseManager(new Database("acturss.db"));
		dbManager.createDB();

		News n1 = new News("titre1", "url1", "author1", new GregorianCalendar(), "contents1", false, false);
		News n2 = new News("titre2", "url2", "author2", new GregorianCalendar(), "contents2", false, false);
		News n3 = new News("titre3", "url3", "author3", new GregorianCalendar(), "contents3", false, false);
		
		News n4 = new News("titre4", "url4", "author4", new GregorianCalendar(), "contents4", false, false);
		News n5 = new News("titre5", "url5", "author5", new GregorianCalendar(), "contents5", false, false);
		News n6 = new News("titre6", "url6", "author6", new GregorianCalendar(), "contents6", false, false);

		ArrayList<News> f1List = new ArrayList<News>();
		ArrayList<News> f2List = new ArrayList<News>();
		
		
		Feed f1 = new Feed(1, "title feed 1", "url", f1List);
		Feed f2 = new Feed(2, "title feed 2", "url", f2List);

		f1.getListNews().add(n1);
		f1.getListNews().add(n2);
		f1.getListNews().add(n3);
		f2.getListNews().add(n4);
		f2.getListNews().add(n5);
		f2.getListNews().add(n6);
		
		ArrayList<Feed> feedList = new ArrayList<Feed>();
		
		Category cat = new Category(1, "nom de la categorie", feedList);
		cat.getListFeed().add(f1);
		cat.getListFeed().add(f2);
		
		ArrayList<Category> catList = new ArrayList<Category>();
		catList.add(cat);
		

		feedManager = new FeedManager(catList, catList);
		
		dbManager.insertObjetToDB(feedManager.getListCategory());
		
	}
}

