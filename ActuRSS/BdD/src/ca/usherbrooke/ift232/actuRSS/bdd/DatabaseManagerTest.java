package bdd;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;




import java.util.Calendar;

import model.Category;
import model.Feed;
import model.News;

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
	public void testInsertCategory() {
		
		/* Initialisation de la Base de données *****************************/
		Database db = new Database("DBRSS");
		DatabaseManager dbManager = new DatabaseManager(db);
		
		dbManager.connect();
		dbManager.clearDB();
		dbManager.createDB();
		
		
		
		Feed feed1 = new Feed(1, "Le Monde", "www.lemonde.fr/rss/une/xml");
		Feed feed2 = new Feed(2, "Eurosport", "http://www.eurosport.fr/rss.xml");
		Feed feed3 = new Feed(3, "L'équipe", "http://www.lequipe.fr/rss/actu_rss.xml");
		
		ArrayList<Feed> listFeedExpected = new ArrayList<Feed>();
		
		listFeedExpected.add(feed1);
		listFeedExpected.add(feed1);
		listFeedExpected.add(feed1);
		
		
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 12);
		
		
		News news1 = new News("Football - Ligue 1 - L1 : Le top 10 des idées reçues… battues en brèche", "ttp://www.eurosport.fr/football/ligue-1/2013-2014/l1-le-top-10-des-idees-recues.-battues-en-breche_sto4004002/story.shtml", "Ta mère", date ,"Championnat homogène, gardiens infranchissables, Paris et Monaco seuls au monde : la Ligue 1 charrie des clichés qui ne correspondent pas à sa réalité. Notre top 10.", false, false  );
		
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

}
