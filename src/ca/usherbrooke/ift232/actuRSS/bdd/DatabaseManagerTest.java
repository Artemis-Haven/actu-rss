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

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	@Test
	public void testCreateDB() {
		Database db = new Database("DBRSS");
		DatabaseManager dbManager = new DatabaseManager(db);
		
		dbManager.connect();
		dbManager.deleteDB();
		dbManager.createDB();
	}

	@Test
	public void testInsertObjetToDB() {
		Database db = new Database("DBRSS");
		DatabaseManager dbManager = new DatabaseManager(db);
		
		dbManager.connect();
		dbManager.deleteDB();
		dbManager.createDB();
				
		Feed feed1 = new Feed(1, "Le Monde", "www.lemonde.fr/rss/une/xml");
		Feed feed2 = new Feed(2, "Eurosport", "http://www.eurosport.fr/rss.xml");
		Feed feed3 = new Feed(3, "L'équipe", "http://www.lequipe.fr/rss/actu_rss.xml");
		
		ArrayList<Feed> listFeedExpected = new ArrayList<Feed>();
		
		listFeedExpected.add(feed1);
		listFeedExpected.add(feed2);
		listFeedExpected.add(feed3);
		
		
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 12);
		
		
		News news1 = new News("Football - Ligue 1 - L1 : Le top 10 des idées reçues battues en breche", "ttp://www.eurosport.fr/football/ligue-1/2013-2014/l1-le-top-10-des-idees-recues.-battues-en-breche_sto4004002/story.shtml", "Ta mï¿½re", date ,"Championnat homogï¿½ne, gardiens infranchissables, Paris et Monaco seuls au monde : la Ligue 1 charrie des clichï¿½s qui ne correspondent pas ï¿½ sa rï¿½alitï¿½. Notre top 10.", false, false  );
		
		Category category1 = new Category(1, "France", listFeedExpected);
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
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertCategory() {
		Database db = new Database("DBRSS");
		DatabaseManager dbManager = new DatabaseManager(db);
		
		dbManager.connect();
		dbManager.deleteDB();
		dbManager.createDB();
		
		Category category1 = new Category(1, "France");
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
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertFeed() {
		Database db = new Database("DBRSS");
		DatabaseManager dbManager = new DatabaseManager(db);
		
		dbManager.connect();
		dbManager.deleteDB();
		dbManager.createDB();
		Category category1 = new Category(1, "France");
		dbManager.insertCategory(category1);
		
		Feed feed1 = new Feed(1, "Le Monde", "www.lemonde.fr/rss/une/xml");
		Feed feed2 = new Feed(2, "Eurosport", "http://www.eurosport.fr/rss.xml");
		Feed feed3 = new Feed(3, "L'ï¿½quipe", "http://www.lequipe.fr/rss/actu_rss.xml");
		
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
		Database db = new Database("DBRSS");
		DatabaseManager dbManager = new DatabaseManager(db);
		
		dbManager.connect();
		dbManager.deleteDB();
		dbManager.createDB();
		Category category1 = new Category(1, "France");
		dbManager.insertCategory(category1);
		
		Feed feed1 = new Feed(1, "Le Monde", "www.lemonde.fr/rss/une/xml");
		dbManager.insertFeed(feed1, 1);
		Calendar date1 = Calendar.getInstance();
		date1.set(2013, 11, 12, 05, 05, 05);
				
		News news1 = new News("Football - Ligue 1 - L1 : Le top 10 des idï¿½es reï¿½uesï¿½ battues en brï¿½che", "http://www.eurosport.fr/football/ligue-1/2013-2014/l1-le-top-10-des-idees-recues.-battues-en-breche_sto4004002/story.shtml", "Ta mï¿½re", date1 ,"Championnat homogï¿½ne, gardiens infranchissables, Paris et Monaco seuls au monde : la Ligue 1 charrie des clichï¿½s qui ne correspondent pas ï¿½ sa rï¿½alitï¿½. Notre top 10.", false, false  );
		News news2 = new News("Football - Ligue 1 - L1 : Le top 10 des idï¿½es reï¿½uesï¿½ battues en brï¿½che", "http://www.eurosport.fr/football/ligue-1/2013-2014/l1-le-top-10-des-idees-recues.-battues-en-breche_sto4004002/story.shtml", "Ta mï¿½re", date1 ,"Championnat homogï¿½ne, gardiens infranchissables, Paris et Monaco seuls au monde : la Ligue 1 charrie des clichï¿½s qui ne correspondent pas ï¿½ sa rï¿½alitï¿½. Notre top 10.", false, false  );
		
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
				assertEquals(listNewsExpected.get(i).getDate(), listCategory.get(0).getListFeed().get(i).getListNews().get(i).getDate());
				assertEquals(listNewsExpected.get(i).getContents(), listCategory.get(0).getListFeed().get(i).getListNews().get(i).getContents());
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
	public void testReturnCategory() {
		Database db = new Database("DBRSS");
		DatabaseManager dbManager = new DatabaseManager(db);
		
		dbManager.connect();
	}

	@Test
	public void testConnect() {
		Database db = new Database("DBRSS");
		DatabaseManager dbManager = new DatabaseManager(db);
		
		dbManager.connect();
	}

	@Test
	public void testDisconnect() {
		Database db = new Database("DBRSS");
		DatabaseManager dbManager = new DatabaseManager(db);
		
		dbManager.connect();
		dbManager.disconnect();
	}

	@Test
	public void testEmptyNews() {
		Database db = new Database("DBRSS");
		DatabaseManager dbManager = new DatabaseManager(db);
		
		dbManager.connect();
	}

	@Test
	public void testGetDb() {
		Database db = new Database("DBRSS");
		DatabaseManager dbManager = new DatabaseManager(db);
		
		dbManager.connect();
	}

}
