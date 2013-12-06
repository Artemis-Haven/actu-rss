package ca.usherbrooke.ift232.actuRSS.modelTest;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Test;

import ca.usherbrooke.ift232.actuRSS.Feed;
import ca.usherbrooke.ift232.actuRSS.bdd.Database;
import ca.usherbrooke.ift232.actuRSS.bdd.DatabaseManager;
import ca.usherbrooke.ift232.actuRSS.model.FeedManager;
import ca.usherbrooke.ift232.actuRSS.model.Model;
import ca.usherbrooke.ift232.actuRSS.model.RssParser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ModelTest extends TestCase {

	final String URL_TEST1 = "http://feeds2.feedburner.com/Pressecitron";
	final String URL_TEST2 = "http://feeds2.feedburner.com/Pressecitron";
	final String URL_TEST3 = "http://feeds2.feedburner.com/Pressecitron";
	
	@Test
	public void testConstructor() {
		Model model = new Model();
		assertNotNull(model);
	}

	@Test
	public void testGetters() {
		Model model = new Model();
		assertNotNull(model.getDbManager());
		assertNotNull(model.getFeedManager());
		//model.getDbManager().
	}

	@Test
	public void testSynchronize() {
		fail("Not yet implemented");
	}

	@Test
	public void testNotifyObserver() {
		fail("Not yet implemented");
	}

	@Test
	public void testObtainDocument() {
		Element element = null;
		String str = "";
		Feed feed = new Feed();

		String feedurl = URL_TEST1;
		assertTrue(Model.obtainDocument(feedurl) instanceof Document);

		Document feedDoc = Model.obtainDocument(feedurl);

		NodeList nodes = feedDoc.getElementsByTagName("item");
		for (int i = 0; i < nodes.getLength(); i++) {
			str = feed.getTitle();
			element = (Element) nodes.item(i);
			assertTrue(RssParser.readNode(element, "title") != str);
			str = RssParser.readNode(element, "title");
		}
	}

}
