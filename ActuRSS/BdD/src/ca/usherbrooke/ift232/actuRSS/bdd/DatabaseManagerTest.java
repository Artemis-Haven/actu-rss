package bdd;
import model.*;
import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class DatabaseManagerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testClearDB() {
		/*Database db = new Database("RSS.db");
		DatabaseManager dbManager = new DatabaseManager(db);
		dbManager.clearDB();*/
		fail("Not yet implemented");
	}

	@Test
	public void testCreateDB() {
		Database db = new Database("RSS.db");
		DatabaseManager dbManager = new DatabaseManager(db);
		dbManager.createDB();
	}

	@Test
	public void testInsertCategory() {
		Database db = new Database("RSS.db");
		DatabaseManager dbManager = new DatabaseManager(db);
		dbManager.clearDB();
		dbManager.createDB();
		Category category = new Category("France");
		dbManager.insertCategory(category);
		dbManager.returnCategory();
		
	}

	@Test
	public void testInsertNews() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertFeed() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteCategory() {
		fail("Not yet implemented");
	}

	@Test
	public void testConnect() {
		fail("Not yet implemented");
	}

	@Test
	public void testDisconnect() {
		fail("Not yet implemented");
	}

}
