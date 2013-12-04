package ca.usherbrooke.ift232.actuRSS.modelTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import ca.usherbrooke.ift232.actuRSS.Category;
import ca.usherbrooke.ift232.actuRSS.Feed;
import ca.usherbrooke.ift232.actuRSS.News;
import ca.usherbrooke.ift232.actuRSS.model.FeedManager;

public class FeedManagerTest extends TestCase {

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

    public void setUp() {
    	/*
    	 * Création de deux categories
    	 */
    	List<News> ListNews = new ArrayList<News>();
        ListFeed = new ArrayList<Feed>();
    	Calendar cal = Calendar.getInstance();
        
        news11 = new News("titre11", "url11", "auteur11", cal, "txt11", true, true);
        news12 = new News("titre12", "url12", "auteur12", cal, "txt12", false, false);
        news21 = new News("titre21", "url21", "auteur21", cal, "txt21", false, false);
        news22 = new News("titre22", "url22", "auteur22", cal, "txt22", false, false);
        news31 = new News("titre31", "url31", "auteur31", cal, "txt31", false, false);
        news32 = new News("titre32", "url32", "auteur32", cal, "txt32", false, false);
        
        ListNews.add(news11);
        ListNews.add(news12);
        Feed feed1 = new Feed(0, "titreFeed1", "urlFeed1", new ArrayList<News>(ListNews));
        ListNews.clear();
        
        ListNews.add(news21);
        Feed feed2 = new Feed(1, "titreFeed2", "urlFeed2", new ArrayList<News>(ListNews));
        ListNews.clear();
        
        ListNews.add(news31);
        ListNews.add(news32);
        Feed feed3 = new Feed(2, "titreFeed3", "urlFeed3", new ArrayList<News>(ListNews));
        ListNews.clear();
        
        ListFeed.add(feed1);
        ListFeed.add(feed2);
        ListFeed.add(feed3);
        //Creation OldCategory 
        Category oldCategory = new Category(0, "oldCategory", new ArrayList<Feed>(ListFeed));
        
        ListFeed.clear();
        news11 = new News("titre11", "url11", "auteur11", cal, "txt11", true, true);
        news12 = new News("titre12", "url12", "auteur12", cal, "txt12", false, false);
        news21 = new News("titre21", "url21", "auteur21", cal, "txt21", false, false);
        news22 = new News("titre22", "url22", "auteur22", cal, "txt22", false, false);
        news31 = new News("titre31", "url31", "auteur31", cal, "txt31", false, false);
        news32 = new News("titre32", "url32", "auteur32", cal, "txt32", false, false);
        ListNews.add(news11);
        ListNews.add(news12);
        feed1 = new Feed(0, "titreFeed1", "urlFeed1", new ArrayList<News>(ListNews));
        ListNews.clear();
        ListNews.add(news21);
        ListNews.add(news22); 
        feed2 = new Feed(1, "titreFeed2", "urlFeed2", new ArrayList<News>(ListNews));
        ListNews.clear();
        ListNews.add(news31);
        feed3 = new Feed(2, "titreFeed3", "urlFeed3", new ArrayList<News>(ListNews));
        ListFeed.add(feed1);
        ListFeed.add(feed2);
        ListFeed.add(feed3);
        //creation NewCategory
        Category newCategory = new Category(1, "newCategory", new ArrayList<Feed>(ListFeed));
        
        listOldCat = new ArrayList<Category>();
        listNewCat = new ArrayList<Category>();
        listOldCat.add(oldCategory);
        listNewCat.add(newCategory);
        
        feedManager = new FeedManager(new ArrayList<Category>(listNewCat), new ArrayList<Category>(listOldCat));
    }
	
    @Test
    public void testConstructor() {
            FeedManager feedManager = new FeedManager();
            assertNotNull(feedManager);
            feedManager = new FeedManager(new ArrayList<Category>(listNewCat), new ArrayList<Category>(listOldCat));
            assertNotNull(feedManager);
    }
    
    @Test
    public void testGetters() {
    	feedManager = new FeedManager(new ArrayList<Category>(listNewCat), new ArrayList<Category>(listOldCat));
    	assertEquals(listNewCat, feedManager.getListCategory());
    	assertEquals(listOldCat, feedManager.getOldListCategory());
    }
    
    @Test
    public void testSetters() {
    	feedManager = new FeedManager(new ArrayList<Category>(listNewCat), new ArrayList<Category>(listOldCat));
    	feedManager.setListCategory(listOldCat);
    	feedManager.setOldListCategory(listNewCat);
    	assertEquals(listNewCat, feedManager.getOldListCategory());
    	assertEquals(listOldCat, feedManager.getListCategory());
    }


    @Test
    public void testMerge() {
    	ArrayList<News> mergedListNews = new ArrayList<News>();
        feedManager.merge();
        
        assertTrue(feedManager.getListCategory().isEmpty());
        
        for (Feed listFeed : feedManager.getOldListCategory().get(0).getListFeed()) {
        	for (News news :  listFeed.getListNews())
        		mergedListNews.add(news);
        }
        
        assertEquals(mergedListNews.size(), 6);
        assertTrue(mergedListNews.contains(news11));
        assertTrue(mergedListNews.contains(news12));
        assertTrue(mergedListNews.contains(news21));
        assertTrue(mergedListNews.contains(news22));
        assertTrue(mergedListNews.contains(news31));
        assertTrue(mergedListNews.contains(news32));        
    }

}
