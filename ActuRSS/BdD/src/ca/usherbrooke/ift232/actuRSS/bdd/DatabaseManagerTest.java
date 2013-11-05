package ca.usherbrooke.ift232.actuRSS.bdd;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.usherbrooke.ift232.actuRSS.model.Category;

public class DatabaseManagerTest {

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		
	}

	

	@Test
	public void testInsertFeed() {
		
		DatabaseManager dbManager = new DatabaseManager(new Database("RSS.db"));
		dbManager.connect();
		/*Category cat = new Category("sport");*/
		
		dbManager.insertCategory("sport");
		/*dbManager.insertFeed("url", "nom", cat);*/
		dbManager.returnCategory();
		System.out.println("Records created successfully");
		
		
	}

	

}
