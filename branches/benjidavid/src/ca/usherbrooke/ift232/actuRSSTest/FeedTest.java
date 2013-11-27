package ca.usherbrooke.ift232.actuRSSTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.usherbrooke.ift232.actuRSS.Feed;
import ca.usherbrooke.ift232.actuRSS.News;
import junit.framework.TestCase;

/**
 * Actu-RSS Couche : Model 
 * Date de creation : 2013-11-26
 * Description : Test de la classe Feed
 * @author Julian Bironneau
 * @version 0.1
 */

public class FeedTest {

	// Variables exemples
	private int id=8;
	private String title="New robot sent to Mars";
	private String url="http://www.cnn.com/";
	private List<News> listNews=null;
	
	// Feeds représentant les quatre différents constructeurs
	private Feed feed1;
	private Feed feed2;
	private Feed feed3;
	private Feed feed4;
	
	// setUp avec les quatre differents constructeurs
	@Before
	public void setUp() throws Exception {
		feed1 = new Feed();
		feed2 = new Feed(title,url,listNews);
		feed3 = new Feed(id,title,url,listNews);
		feed4 = new Feed(id,title,url);
	}
	
	
	// Test des quatres constructeurs et des accesseurs de type get
	public void testConstructeurs() {
		
		// Constructeur 1
		assertEquals(-1,feed1.getId());
		assertEquals("",feed1.getTitle());
		assertEquals("",feed1.getUrl());
		assertEquals(new ArrayList<News>(),feed1.getListNews());
		
		// Constructeur 2
		assertEquals(-1,feed2.getId());
		assertEquals(title,feed2.getTitle());
		assertEquals(url,feed2.getUrl());
		assertEquals(listNews,feed2.getListNews());
		
		// Constructeur 3
		assertEquals(id,feed3.getId());
		assertEquals(title,feed3.getTitle());
		assertEquals(url,feed3.getUrl());
		assertEquals(listNews,feed3.getListNews());
		
		// Constructeur 4
		assertEquals(id,feed4.getId());
		assertEquals(title,feed4.getTitle());
		assertEquals(url,feed4.getUrl());
		assertEquals(new ArrayList<News>(),feed4.getListNews());
		
	}
	
	// Test des accesseurs de type set
	

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
