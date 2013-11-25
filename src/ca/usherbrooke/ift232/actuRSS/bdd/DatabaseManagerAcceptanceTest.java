package ca.usherbrooke.ift232.actuRSS.bdd;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.usherbrooke.ift232.actuRSS.Category;
import ca.usherbrooke.ift232.actuRSS.Feed;
import ca.usherbrooke.ift232.actuRSS.News;

public class DatabaseManagerAcceptanceTest {

	private static DatabaseManager dbManager;

	@BeforeClass
	public static void beforeClass() {
		dbManager = new DatabaseManager(new Database(
				"dbManagerAcceptanceTest.db"));
		dbManager.createDB();
	}

	@AfterClass
	public static void afterClass() {
		dbManager.deleteDB();
		dbManager.disconnect();
	}

	@Before
	public void setUp() throws Exception {
		dbManager.clearDB();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_getAllCategories() {
		buildRandomListOfCategories();
		
		try {
			ArrayList<Category> list = dbManager.getAllCategories();
			assertEquals(list.size(), 5);
			for (Category cat : list) {
				assertEquals(cat.getListFeed().size(), 10);
				for (Feed f : cat.getListFeed()) {
					assertTrue(f.getUrl().startsWith("http://www.'\"\\"));
					assertEquals(f.getListNews().size(), 20);
					assertEquals(f.getTitle().length(), 50);
					for (News n : f.getListNews()) {
						assertTrue(n.getUrl().startsWith("http://www.'\"\\"));
						assertEquals(n.getTitle().length(), 50);
						assertTrue(n.getAuthor().contains(" "));
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_insertCategory() {
		Category cat = new Category(-1, "Nouvelle catégorie");
		dbManager.insertCategory(cat);
		try {
			ArrayList<Category> list = dbManager.getAllCategories();
			assertEquals(list.size(), 1);
			assertEquals(list.get(0).getName(), "Nouvelle catégorie");
		} catch (SQLException e) {
			fail("impossible de récupérer les catégories dans la BdD");
		}
		
	}

	@Test
	public void test_insertions() {
		Category cat = new Category(0, "nouvelle cat");
		Feed feed = new Feed(0, "titre", "url");
		News news = new News("title", "http://www.'\"\\", "Author author", new GregorianCalendar(), "<a href =\"http://www.blabla.com?asdf=thfg&oijhljn=345\\\"", false, false);
		feed.getListNews().add(news);
		cat.getListFeed().add(feed);
		System.out.println(cat.getListFeed().get(0));
		dbManager.insertCategory(cat);
		try {
			ArrayList<Category> list = dbManager.getAllCategories();
			System.out.println(list.get(0).getListFeed().get(0));
			assertEquals(list.get(0).getListFeed().get(0).getListNews().get(0).getTitle(), "title");
			
			assertEquals(list.get(0).getListFeed().get(0).getListNews().get(0).getUrl(), "http://www.'\"\\");
			assertEquals(list.get(0).getListFeed().get(0).getListNews().get(0).getContents(), "<a href =\"http://www.blabla.com?asdf=thfg&oijhljn=345\\\"");
		} catch (SQLException e) {
			fail("impossible de récupérer les catégories dans la BdD");
		}
	}

	private int getRandomInt(int min, int max) {
		Random rn = new Random();
		return rn.nextInt(max - min + 1) + min;
	}

	private String getRandomString(int nbCharMax) {
		Random rn = new Random();
		char data = ' ';
		String str = "";

		for (int i = 0; i <= nbCharMax; i++) {
			data = (char) (rn.nextInt(25) + 97);
			str = data + str;
		}
		return str;
	}

	private void buildRandomListOfCategories() {
		ArrayList<Category> list = new ArrayList<Category>();
		for (int k = 1; k < 5; k++) {
			Category cat = new Category(-1, getRandomString(10));
			for (int j = 1; j < 10; j++) {
				Feed feed = new Feed(-1, getRandomString(50),
						"http://www.'\"\\" + getRandomString(50));
				for (int i = 1; i < 20; i++) {
					News news = new News(
							getRandomString(50),
							"http://www.'\"\\" + getRandomString(50),
							getRandomString(20) + " " + getRandomString(20),
							new GregorianCalendar(),
							getRandomString(50)
									+ "<a href =\"http://www.blabla.com?asdf=thfg&oijhljn=345\\\""
									+ getRandomString(50), false, false);
					feed.getListNews().add(news);
				}
				cat.getListFeed().add(feed);
				
			}
			list.add(cat);
		}
		dbManager.insertObjetToDB(list);
	}

}
