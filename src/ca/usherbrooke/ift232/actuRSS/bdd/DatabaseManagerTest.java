package ca.usherbrooke.ift232.actuRSS.bdd;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import java.util.Calendar;

import ca.usherbrooke.ift232.actuRSS.Category;
import ca.usherbrooke.ift232.actuRSS.Feed;
import ca.usherbrooke.ift232.actuRSS.News;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DatabaseManagerTest {
	
	private static Database db;
	private static DatabaseManager dbManager;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		db = new Database("DBRSS");
		dbManager = new DatabaseManager(db);
		dbManager.connect();
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		dbManager.disconnect();
	}

	@Before
	public void setUp() throws Exception {
		dbManager.deleteDB();
		dbManager.createDB();
		dbManager.clearDB();
	}

	@After
	public void tearDown() throws Exception {
		dbManager.deleteDB();
	}

	@Test
	public void testInsertObjetToDB() throws SQLException {
		Calendar date1 = Calendar.getInstance();
		date1.set(Calendar.DAY_OF_MONTH, 12);
		
		
		News news1 = new News("Football - Ligue 1 - L1 : Le top 10 des id�es re�ues� battues en br�che", "http://www.eurosport.fr/football/ligue-1/2013-2014/l1-le-top-10-des-idees-recues.-battues-en-breche_sto4004002/story.shtml", "Ta m�re", date1 ,"Championnat homog�ne, gardiens infranchissables, Paris et Monaco seuls au monde : la Ligue 1 charrie des clich�s qui ne correspondent pas � sa r�alit�. Notre top 10.", true, true  );
		News news2 = new News("Football - Ligue 1 - L1 : Le top 10 des id�es re�ues� battues en br�che", "http://www.eurosport.fr/football/ligue-1/2013-2014/l1-le-top-10-des-idees-recues.-battues-en-breche_sto4004000/story.shtml", "Ta m�re", date1 ,"Championnat homog�ne, gardiens infranchissables, Paris et Monaco seuls au monde : la Ligue 1 charrie des clich�s qui ne correspondent pas � sa r�alit�. Notre top 10.", false, false  );
		ArrayList<News> listNewsExpected = new ArrayList<News>();
		//listNewsExpected.add(news1);
		//listNewsExpected.add(news2);
		
		Feed feed1 = new Feed(1, "Le Monde", "www.lemonde.fr/rss/une/xml");
		Feed feed2 = new Feed(2, "Eurosport", "http://www.eurosport.fr/rss.xml");
		Feed feed3 = new Feed(3, "L'�quipe", "http://www.lequipe.fr/rss/actu_rss.xml");
		ArrayList<Feed> listFeedExpected = new ArrayList<Feed>();
		//listFeedExpected.add(feed1);
		//listFeedExpected.add(feed2);
		//listFeedExpected.add(feed3);
		
		Category category1 = new Category(1, "France");
		Category category2 = new Category(2, "Sport");
		Category category3 = new Category(3, "Culture");
		ArrayList<Category> listCategoryExpected = new ArrayList<Category>();
		//listCategoryExpected.add(category1);
		//listCategoryExpected.add(category2);
		//listCategoryExpected.add(category3);
		// 0 Time 
		dbManager.insertObjetToDB(listCategoryExpected);
		
		// 1 Time
		listNewsExpected.add(news1);
		feed1.setListNews(listNewsExpected);
		listFeedExpected.add(feed1);
		category1.setListFeed(listFeedExpected);
		listCategoryExpected.add(category1);
		
		assertEquals(listCategoryExpected.get(0).getName(), "France" );
		assertEquals(listCategoryExpected.get(0).getListFeed().get(0).getTitle(), "Le Monde");
		assertEquals(listCategoryExpected.get(0).getListFeed().get(0).getListNews().get(0).getTitle(), "Football - Ligue 1 - L1 : Le top 10 des id�es re�ues� battues en br�che");
		
		dbManager.insertObjetToDB(listCategoryExpected);
		
		ArrayList<Category> listCategoryBDD = new ArrayList<Category>();
		listCategoryBDD = dbManager.getAllCategories();
		assertEquals(listCategoryExpected.get(0).getName(), listCategoryBDD.get(0).getName());
		assertEquals(listCategoryExpected.get(0).getListFeed().get(0).getTitle(), listCategoryBDD.get(0).getListFeed().get(0).getTitle());
		assertEquals(listCategoryExpected.get(0).getListFeed().get(0).getListNews().get(0).getTitle(), listCategoryBDD.get(0).getListFeed().get(0).getListNews().get(0).getTitle());
		
		dbManager.deleteDB();
		dbManager.createDB();
		
		// Many Time
		listNewsExpected.add(news2);
		feed1.setListNews(listNewsExpected);
		listFeedExpected.add(feed2);
		listFeedExpected.add(feed3);
		category1.setListFeed(listFeedExpected);
		listCategoryExpected.add(category2);
		listCategoryExpected.add(category3);
		
		dbManager.insertObjetToDB(listCategoryExpected);
		
		
		try {
			ArrayList<Category> listCategory = dbManager.getAllCategories();
			for(int i=0;i<listCategory.size();i++)
			{
				assertEquals(listCategoryExpected.get(i).getId(), listCategory.get(i).getId());
				assertEquals(listCategoryExpected.get(i).getName(), listCategory.get(i).getName());

				for(int j = 0; j < listCategory.get(i).getListFeed().size(); j++)
				{
					assertEquals(listFeedExpected.get(j).getTitle(), listCategory.get(i).getListFeed().get(j).getTitle());
					assertEquals(listFeedExpected.get(j).getUrl(), listCategory.get(i).getListFeed().get(j).getUrl());
						
					
					for(int k = 0; k < listCategory.get(i).getListFeed().get(j).getListNews().size(); k++)
					{
						assertEquals(listNewsExpected.get(k).getUrl(), listCategory.get(i).getListFeed().get(j).getListNews().get(k).getUrl());
						assertEquals(listNewsExpected.get(k).getTitle(), listCategory.get(i).getListFeed().get(j).getListNews().get(k).getTitle());
						assertEquals(listNewsExpected.get(k).getAuthor(), listCategory.get(i).getListFeed().get(j).getListNews().get(k).getAuthor());
						assertEquals(DatabaseUtil.ConvertCalendarToString(listNewsExpected.get(k).getDate()), DatabaseUtil.ConvertCalendarToString(listCategory.get(i).getListFeed().get(j).getListNews().get(k).getDate()));
						assertEquals(listNewsExpected.get(k).getContents(), listCategory.get(i).getListFeed().get(j).getListNews().get(k).getContents());
					
						
					}
					
				}
				
			}
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertCategory() {
				
		ArrayList<Feed> list = new ArrayList<Feed>();
		Feed feed = new Feed(0, "titre de mon feed", "url de mon feed");
		list.add(feed);
		Category category1 = new Category(1, "France", list);
		Category category2 = new Category(2, "Sport");
		Category category3 = new Category(3, "Culture");
			
		dbManager.insertCategory(category1);
		dbManager.insertCategory(category2);
		dbManager.insertCategory(category3);
		
		ArrayList<Category> listCategoryExpected = new ArrayList<Category>();
		listCategoryExpected.add(category1);
		listCategoryExpected.add(category2);
		listCategoryExpected.add(category3);
		
		try {
			ArrayList<Category> listCategory = dbManager.getAllCategories();
			for(int i=0;i<listCategory.size();i++)
			{
				assertEquals(listCategoryExpected.get(i).getId(), listCategory.get(i).getId());
				assertEquals(listCategoryExpected.get(i).getName(), listCategory.get(i).getName());
			
			}
			assertEquals(listCategoryExpected.get(0).getListFeed().get(0).getTitle(), feed.getTitle() );
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertFeed() {
		
		Category category1 = new Category(1, "France");
		dbManager.insertCategory(category1);
		
		Feed feed1 = new Feed(1, "Le Monde", "www.le\\\"monde.fr/rss/une/xml");
		Feed feed2 = new Feed(2, "Eurosport", "http://www.eurosport.fr/rss.xml");
		Feed feed3 = new Feed(3, "L'�quipe", "http://www.lequipe.fr/rss/actu_rss.xml");
		
		dbManager.insertFeed(feed1, 1);
		dbManager.insertFeed(feed2, 1);
		dbManager.insertFeed(feed3, 1);
		
		ArrayList<Feed> listFeedExpected = new ArrayList<Feed>();
		
		listFeedExpected.add(feed1);
		listFeedExpected.add(feed2);
		listFeedExpected.add(feed3);
		
		
		
		try {
			ArrayList<Category> listCategory = dbManager.getAllCategories();
			for(int i=0;i<listCategory.get(0).getListFeed().size();i++)
			{
				assertEquals(listFeedExpected.get(i).getTitle(), listCategory.get(0).getListFeed().get(i).getTitle());
				assertEquals(listFeedExpected.get(i).getUrl(), listCategory.get(0).getListFeed().get(i).getUrl());
			}
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertNews() {
				
		Category category1 = new Category(1, "France");
		dbManager.insertCategory(category1);
		
		Feed feed1 = new Feed(1, "Le Monde", "www.lemonde.fr/rss/une/xml");
		dbManager.insertFeed(feed1, 1);
		Calendar date1 = Calendar.getInstance();
		date1.set(2013, 11, 12, 05, 05, 05);
				
		News news1 = new News("Football - Ligue 1 - L1 : Le top 10 des id�es re�ues� battues en br�che", "http://www.eurosport.fr\\/foo\"tb'all/li<gue-1/20>13-2014/l1-le-top-10-des-idees-recues.-battues-en-breche_sto4004002/story.shtml", "Ta m�re", date1 ,"Championnat homog�ne, gardiens infranchissables, Paris et Monaco seuls au monde : la Ligue 1 charrie des clich�s qui ne correspondent pas � sa r�alit�. Notre top 10.", true, true  );
		News news2 = new News("Football - Ligue 1 - L1 : Le top 10 des id�es re�ues� battues en br�che", "http://www.eurosport.fr/football/ligue-1/2013-2014/l1-le-top-10-des-idees-recues.-battues-en-breche_sto4004003/story.shtml", "Ta m�re", date1 ,"Championnat homog�ne, gardiens infranchissables, Paris et Monaco seuls au monde : la Ligue 1 charrie des clich�s qui ne correspondent pas � sa r�alit�. Notre top 10.", false, false  );
		
		ArrayList<News> listNewsExpected = new ArrayList<News>();
		dbManager.insertNews(news1, 1);
		dbManager.insertNews(news2, 1);
		listNewsExpected.add(news1);
		listNewsExpected.add(news2);
		try {
			ArrayList<Category> listCategory = dbManager.getAllCategories();
			for(int i=0;i<listCategory.get(0).getListFeed().get(0).getListNews().size();i++)
			{
				assertEquals(listNewsExpected.get(i).getUrl(), listCategory.get(0).getListFeed().get(0).getListNews().get(i).getUrl());
				assertEquals(listNewsExpected.get(i).getTitle(), listCategory.get(0).getListFeed().get(0).getListNews().get(i).getTitle());
				assertEquals(listNewsExpected.get(i).getAuthor(), listCategory.get(0).getListFeed().get(0).getListNews().get(i).getAuthor());
				assertEquals(DatabaseUtil.ConvertCalendarToString(listNewsExpected.get(i).getDate()), DatabaseUtil.ConvertCalendarToString(listCategory.get(0).getListFeed().get(0).getListNews().get(i).getDate()));
				assertEquals(listNewsExpected.get(i).getContents(), listCategory.get(0).getListFeed().get(0).getListNews().get(i).getContents());
			}
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetAllCategories() {
		Database db = new Database("DBRSS");
		DatabaseManager dbManager = new DatabaseManager(db);
		
		dbManager.connect();
	}

	@Test
	public void testClearCategory() throws SQLException {
		Feed feed1 = new Feed(1, "Le Monde", "www.lemonde.fr/rss/une/xml");
		dbManager.insertFeed(feed1, 1);
		ArrayList<Feed> listFeed = new ArrayList<Feed>();
		listFeed.add(feed1);
		Category category1 = new Category(1, "France");
		Category category2 = new Category(2, "Sport");
		Category category3 = new Category(3, "Culture", listFeed);
			
		dbManager.insertCategory(category1);
		dbManager.insertCategory(category2);
		//dbManager.insertCategory(category3);
		
		dbManager.clearCategory(category1);
		dbManager.clearCategory(category2);
		dbManager.clearCategory(category3);
		
		try {
			ArrayList<Category> listCategory = dbManager.getAllCategories();
			assertTrue(listCategory.isEmpty());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	
	@Test
	public void testClearFeed() {
		Calendar date1 = Calendar.getInstance();
		date1.set(2013, 11, 12, 05, 05, 05);
		
		News news1 = new News("Football - Ligue 1 - L1 : Le top 10 des id�es re�ues� battues en br�che", "http://www.eurosport.fr/football/ligue-1/2013-2014/l1-le-top-10-des-idees-recues.-battues-en-breche_sto4004002/story.shtml", "Ta m�re", date1 ,"Championnat homog�ne, gardiens infranchissables, Paris et Monaco seuls au monde : la Ligue 1 charrie des clich�s qui ne correspondent pas � sa r�alit�. Notre top 10.", false, false  );
		ArrayList<News> listNews = new ArrayList<News>();
		listNews.add(news1);
		
		Category category1 = new Category(1, "France");
		dbManager.insertCategory(category1);
		
		Feed feed1 = new Feed(1, "Le Monde", "www.lemonde.fr/rss/une/xml");
		Feed feed2 = new Feed(2, "Eurosport", "http://www.eurosport.fr/rss.xml");
		Feed feed3 = new Feed(3, "L'�quipe", "http://www.lequipe.fr/rss/actu_rss.xml", listNews);
		
		dbManager.insertFeed(feed1, 1);
		dbManager.insertFeed(feed2, 1);
		//dbManager.insertFeed(feed3, 1);
		
		dbManager.clearFeed(feed1);
		dbManager.clearFeed(feed2);
		dbManager.clearFeed(feed3);
		try {
			ArrayList<Category> listCategory = dbManager.getAllCategories();
			assertTrue(listCategory.get(0).getListFeed().isEmpty());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testClearNews() {
		
		Category category1 = new Category(1, "France");
		dbManager.insertCategory(category1);
		
		Feed feed1 = new Feed(1, "Le Monde", "www.lemonde.fr/rss/une/xml");
		dbManager.insertFeed(feed1, 1);
		Calendar date1 = Calendar.getInstance();
		date1.set(2013, 11, 12, 05, 05, 05);
		
		News news1 = new News("Football - Ligue 1 - L1 : Le top 10 des id�es re�ues� battues en br�che", "http://www.eurosport.fr/football/ligue-1/2013-2014/l1-le-top-10-des-idees-recues.-battues-en-breche_sto4004002/story.shtml", "Ta m�re", date1 ,"Championnat homog�ne, gardiens infranchissables, Paris et Monaco seuls au monde : la Ligue 1 charrie des clich�s qui ne correspondent pas � sa r�alit�. Notre top 10.", false, false  );
		News news2 = new News("Football - Ligue 1 - L1 : Le top 10 des id�es re�ues� battues en br�che", "http://www.eurosport.fr/football/ligue-1/2013-2014/l1-le-top-10-des-idees-recues.-battues-en-breche_sto4004003/story.shtml", "Ta m�re", date1 ,"Championnat homog�ne, gardiens infranchissables, Paris et Monaco seuls au monde : la Ligue 1 charrie des clich�s qui ne correspondent pas � sa r�alit�. Notre top 10.", false, false  );
		

		dbManager.insertNews(news1, 1);
		dbManager.insertNews(news2, 1);
		
		dbManager.clearNews(news1);
		dbManager.clearNews(news2);
		try {
			ArrayList<Category> listCategory = dbManager.getAllCategories();
			assertTrue(listCategory.get(0).getListFeed().get(0).getListNews().isEmpty());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Test
	public void testDisconnect() {
		Database db = new Database("DBRSS");
		DatabaseManager dbManager = new DatabaseManager(db);
		
		dbManager.connect();
		dbManager.disconnect();
	}


}
