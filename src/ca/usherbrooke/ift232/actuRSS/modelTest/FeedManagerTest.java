package ca.usherbrooke.ift232.actuRSS.modelTest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import ca.usherbrooke.ift232.actuRSS.Category;
import ca.usherbrooke.ift232.actuRSS.Feed;
import ca.usherbrooke.ift232.actuRSS.News;
import ca.usherbrooke.ift232.actuRSS.model.FeedManager;
import ca.usherbrooke.ift232.actuRSS.model.WrongURLException;

public class FeedManagerTest extends TestCase {

	private String URLValid = "http://feeds2.feedburner.com/Pressecitron";
	private ArrayList<Category> listOldCat;
	ArrayList<Category> listNewCat;
	private List<Feed> ListFeed;
	private FeedManager feedManager;
	private News news11;
	private News news12;
	private News news21;
	private News news22;
	private News news31;
	private News news32;
	private Feed feed1;

    public void setUp() {
    	/*
    	 * Création de deux categories
    	 */
    	List<News> listNews = new ArrayList<News>();
        ListFeed = new ArrayList<Feed>();
    	Calendar cal = Calendar.getInstance();

        news11 = new News("titre11", "url11", "auteur11", cal, "txt11", true, true);
        news12 = new News("titre12", "url12", "auteur12", cal, "txt12", false, false);
        news21 = new News("titre21", "url21", "auteur21", cal, "txt21", false, false);
        news22 = new News("titre22", "url22", "auteur22", cal, "txt22", false, false);
        news31 = new News("titre31", "url31", "auteur31", cal, "txt31", false, false);
        news32 = new News("titre32", "url32", "auteur32", cal, "txt32", false, false);
        
        listNews.add(news11);
        listNews.add(news12);
        Feed feed1 = new Feed(0, "titreFeed1", "urlFeed1", new ArrayList<News>(listNews));
        listNews.clear();
        
        listNews.add(news21);
        Feed feed2 = new Feed(1, "titreFeed2", "urlFeed2", new ArrayList<News>(listNews));
        listNews.clear();
        
        listNews.add(news31);
        listNews.add(news32);
        Feed feed3 = new Feed(2, "titreFeed3", "urlFeed3", new ArrayList<News>(listNews));
        listNews.clear();
        
        ListFeed.add(feed1);
        ListFeed.add(feed2);
        ListFeed.add(feed3);
        //Creation OldCategory 
        Category oldCategory = new Category(0, "oldCategory", new ArrayList<Feed>(ListFeed));
        
        ListFeed.clear();
        news11 = new News("title11", "url11", "author11", cal, "texte11", true, true);
        news12 = new News("title12", "url12", "author12", cal, "texte12", false, false);
        news21 = new News("title21", "url21", "author21", cal, "texte21", false, false);
        news22 = new News("title22", "url22", "author22", cal, "texte22", false, false);
        news31 = new News("title31", "url31", "author31", cal, "texte31", false, false);
        news32 = new News("title32", "url32", "author32", cal, "texte32", false, false);
        listNews.add(news11);
        listNews.add(news12);
        feed1 = new Feed(0, "titleFeed21", "urlFeed21", new ArrayList<News>(listNews));
        listNews.clear();
        listNews.add(news21);
        listNews.add(news22); 
        feed2 = new Feed(1, "titleFeed22", "urlFeed22", new ArrayList<News>(listNews));
        listNews.clear();
        listNews.add(news31);
        feed3 = new Feed(2, "titleFeed23", "urlFeed23", new ArrayList<News>(listNews));
        ListFeed.add(feed1);
        ListFeed.add(feed2);
        ListFeed.add(feed3);
        //creation NewCategory
        Category newCategory = new Category(1, "newCategory", new ArrayList<Feed>(ListFeed));
        
        listOldCat = new ArrayList<Category>();
        listNewCat = new ArrayList<Category>();
        listOldCat.add(oldCategory);
        listNewCat.add(newCategory);
        
        feedManager = FeedManager.getInstance();
        feedManager.setListCategory(new ArrayList<Category>(listNewCat));
        feedManager.setOldListCategory(new ArrayList<Category>(listOldCat));
    }
	
    /**
     * Permet de tester les constructeurs de FeedManager
     */
    @Test
    public void testConstructor() {
            FeedManager feedManager = FeedManager.getInstance();
            assertNotNull(feedManager);
    }
    
    /**
     * Permet de tester les getters de FeedManager
     */
    @Test
    public void testGetters() {
    	assertEquals(listNewCat, feedManager.getListCategory());
    	assertEquals(listOldCat, feedManager.getOldListCategory());
    }
    
    /**
     * Permet de tester les setters de FeedManager
     */
    @Test
    public void testSetters() {
    	assertEquals(listOldCat, feedManager.getOldListCategory());
    	assertEquals(listNewCat, feedManager.getListCategory());
    	assertEquals(listOldCat.get(0), feedManager.getCategoryByName("oldCategory"));
    	assertEquals(null, feedManager.getCategoryByName("falseNameCategory"));
    }

    @Test
    public void testAddFeed() {
    	Feed feed1 = new Feed(-1, "newFeed1", URLValid);
    	Feed feed2 = new Feed(-1, "newFeed2", URLValid);
    	Feed feed3 = new Feed(-1, "newFeed3", "URLInvalid");
    	
    	try {
			assertTrue(feedManager.addFeed(feed1, listOldCat.get(0)));
			assertFalse(feedManager.addFeed(feed2, listOldCat.get(0)));
			feedManager.addFeed(feed3, listOldCat.get(0));
			fail();
		} catch (WrongURLException e) {
		}
    }
    
    @Test 
    public void testModifyFeed() {
    	Feed feed1 = new Feed(-1, "newFeed1", URLValid);
    	Feed feed2 = new Feed(-1, "newFeed3", "URLInvalid");
    	String anotherURLValid = "http://www.korben.info/feed";
    	try {
			assertTrue(feedManager.modifyFeed(feed1, listOldCat.get(0)));
			assertFalse(feedManager.modifyFeed(feed1, listOldCat.get(0)));
			feed1 = new Feed(0, "newFeed1", URLValid);
			assertFalse(feedManager.modifyFeed(feed1, listOldCat.get(0)));
			feed1 = new Feed(-1, "newFeedTitle", URLValid);
			assertTrue(feedManager.modifyFeed(feed1, listOldCat.get(0)));
			feed1 = new Feed(-1, "newFeed1", anotherURLValid);
			assertTrue(feedManager.modifyFeed(feed1, listOldCat.get(0)));
			List<News> listNews = new ArrayList<News>();
			listNews.add(news11);
			feed1 = new Feed(-1, "newFeed1", URLValid, listNews);
			assertTrue(feedManager.modifyFeed(feed1, listOldCat.get(0)));
			feedManager.addFeed(feed2, listOldCat.get(0));
			fail();
		} catch (WrongURLException e) {
		}
    }
    
    @Test 
    public void testRemoveFeed() {
    	Feed feedUnknown = new Feed(-1, "unknownFeed", "randomURL");
    	feedManager.removeFeed(feed1, listOldCat.get(0));
    	assertEquals(listOldCat.get(0).getListFeed().size(), 3);
    	feedManager.removeFeed(feedUnknown, listOldCat.get(0));
    	assertEquals(listOldCat.get(0).getListFeed().size(), 3);
    }
    
    

    /**
     * Permet de tester la fonction Merge de FeedManager
     */
    @Test
    public void testMerge() {
    	ArrayList<News> mergedListNews = new ArrayList<News>();
        feedManager.merge();
        assertTrue(feedManager.getListCategory().isEmpty());
        assertEquals(feedManager.getOldListCategory().get(0).getListFeed().size(), 3);
        assertEquals(feedManager.getOldListCategory().get(0).getListFeed().get(0).getListNews().size(), 2);
        assertEquals(feedManager.getOldListCategory().get(0).getListFeed().get(1).getListNews().size(), 2);
        assertEquals(feedManager.getOldListCategory().get(0).getListFeed().get(2).getListNews().size(), 2);
        
        for (Category cat : feedManager.getOldListCategory())
        //for (Feed feed : feedManager.getOldListCategory().get(0).getListFeed()) {
        	for (Feed feed : cat.getListFeed())
        		for (News news :  feed.getListNews()) 
        			mergedListNews.add(news);
        
        
        assertEquals(mergedListNews.size(), 6);
        assertTrue(mergedListNews.contains(news11));
        assertTrue(mergedListNews.contains(news12));
        assertTrue(mergedListNews.contains(news21));
        assertTrue(mergedListNews.contains(news22));
        assertTrue(mergedListNews.contains(news31));
        assertTrue(mergedListNews.contains(news32));  
        
        mergedListNews.clear();
        feedManager.merge();
        for (Feed listFeed : feedManager.getOldListCategory().get(0).getListFeed()) {
        	for (News news :  listFeed.getListNews()) 
        		mergedListNews.add(news);
        }
        assertEquals(mergedListNews.size(), 6);
        assertEquals(feedManager.getOldListCategory().get(0).getListFeed().size(), 3);
        assertEquals(feedManager.getOldListCategory().get(0).getListFeed().get(0).getListNews().size(), 2);
        assertEquals(feedManager.getOldListCategory().get(0).getListFeed().get(1).getListNews().size(), 2);
        assertEquals(feedManager.getOldListCategory().get(0).getListFeed().get(2).getListNews().size(), 2);
        
        mergedListNews.clear();
        feedManager.setListCategory(listNewCat);
        feedManager.merge();
        for (Feed listFeed : feedManager.getOldListCategory().get(0).getListFeed()) {
        	for (News news :  listFeed.getListNews()) 
        		mergedListNews.add(news);
        }
        assertEquals(mergedListNews.size(), 6);
        assertEquals(feedManager.getOldListCategory().get(0).getListFeed().size(), 3);
        assertEquals(feedManager.getOldListCategory().get(0).getListFeed().get(0).getListNews().size(), 2);
        assertEquals(feedManager.getOldListCategory().get(0).getListFeed().get(1).getListNews().size(), 2);
        assertEquals(feedManager.getOldListCategory().get(0).getListFeed().get(2).getListNews().size(), 2);
    }

}
