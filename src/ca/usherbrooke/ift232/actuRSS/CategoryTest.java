package ca.usherbrooke.ift232.actuRSS;

import static org.junit.Assert.*;

/**
 * Actu-RSS Couche : Model 
 * Date de creation : 2013-11-26
 * Description : Test de la classe Category
 * @author Julian Bironneau
 * @version 0.1
 */

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CategoryTest extends TestCase  {

	// Variable d'exemples
	private static final String Sport = "Sport";
	private static final String Politique = "Politique";
	private List<Feed> listFeedEx = new ArrayList<Feed>();
	
	// Catégories représentant les différents constructeurs
	private Category categoryExample1;
	private Category categoryExample2;
	private Category categoryExample3;

	
	@Before
	// setUp des trois differents constructeurs
	public void setUp() throws Exception {
		categoryExample1 = new Category();
		categoryExample2 = new Category(2,Sport,listFeedEx);
		categoryExample3 = new Category(3,Politique);
	}
	
	// Test des trois constructeurs et des accesseurs de type get indirectement
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

	
	@After
	// Nettoyage d'après test
	public void tearDown() throws Exception {
		categoryExample1=null;
		categoryExample2=null;
		categoryExample3=null;
	}

}
