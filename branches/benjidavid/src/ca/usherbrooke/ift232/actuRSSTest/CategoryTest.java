package ca.usherbrooke.ift232.actuRSSTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.usherbrooke.ift232.actuRSS.Category;
import ca.usherbrooke.ift232.actuRSS.Feed;

public class CategoryTest extends TestCase  {

	// Variable exemples
	private static final String Sport = "Sport";
	private static final String Politique = "Politique";
	private List<Feed> listFeedEx = new ArrayList<Feed>();
	
	// Cat�gories repr�sentant les diff�rents constructeurs
	private Category categoryExample1;
	private Category categoryExample2;
	private Category categoryExample3;

	
	@Before
	// setUp avec les trois differents constructeurs
	public void setUp() throws Exception {
		categoryExample1 = new Category();
		categoryExample2 = new Category(2,Sport,listFeedEx);
		categoryExample3 = new Category(3,Politique);
	}
	
	// Test des trois constructeurs et des accesseurs de type get
	public void testConstructeurs() {
		
		// constructeur 1
		assertEquals(-1,categoryExample1.getId());
		assertEquals("",categoryExample1.getName());
		assertEquals(new ArrayList<Feed>(),categoryExample1.getListFeed());
		
		// constructeur 2
		assertEquals(2,categoryExample2.getId());
		assertEquals("Sport",categoryExample2.getName());
		assertEquals(new ArrayList<Feed>(),categoryExample2.getListFeed());
		
		// constructeur 3
		assertEquals(3,categoryExample3.getId());
		assertEquals("Politique",categoryExample3.getName());
		assertEquals(null,categoryExample3.getListFeed());
	}
	
	// Test des accesseurs de type set
	public void testAccesseurs() {
		
		List<Feed> entries=null;
		categoryExample1.setId(4);
		categoryExample2.setName("People");
		categoryExample3.setListFeed(entries);
		
		assertEquals(4,categoryExample1.getId());
		assertEquals("People",categoryExample2.getName());
		assertEquals(null,categoryExample3.getListFeed());
		
	}

	// Test de override
	/*public void testOverride() {
		
		String voulu = "Category [id=2 name=Sport]";
		String testChaine = categoryExample2.toString();
		
		assertEquals(voulu,categoryExample2.getName());

	}*/

	
	@After
	// Nettoyage d'apr�s test
	public void tearDown() throws Exception {
		categoryExample1=null;
		categoryExample2=null;
		categoryExample3=null;
	}

}