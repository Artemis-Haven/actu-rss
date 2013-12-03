package ca.usherbrooke.ift232.actuRSS.modelTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import ca.usherbrooke.ift232.actuRSS.Category;
import ca.usherbrooke.ift232.actuRSS.Feed;
import ca.usherbrooke.ift232.actuRSS.News;
import ca.usherbrooke.ift232.actuRSS.model.FeedManager;

public class FeedManagerTest {

	private List<Feed> ListFeed;
    
    public void setUp() {
            
    }
	
    @Test
    public void testConstructor() {
            FeedManager feedManager = new FeedManager();
            assertNotNull(feedManager);
            //feedManager = new FeedManager(listCategory, oldListCategory)
    }


    @Test
    public void testMerge() {
    	/*
    	 * Création de deux categories
    	 */
    	ArrayList<News> mergedListNews = new ArrayList<News>();
    	List<News> ListNews = new ArrayList<News>();
        ListFeed = new ArrayList<Feed>();
    	Calendar cal = Calendar.getInstance();
        
        News news11 = new News("titre11", "url11", "auteur11", cal, "txt11", true, true);
        News news12 = new News("titre12", "url12", "auteur12", cal, "txt12", false, false);
        News news21 = new News("titre21", "url21", "auteur21", cal, "txt21", false, false);
        News news22 = new News("titre22", "url22", "auteur22", cal, "txt22", false, false);
        News news31 = new News("titre31", "url31", "auteur31", cal, "txt31", false, false);
        News news32 = new News("titre32", "url32", "auteur32", cal, "txt32", false, false);
        
        ListNews.add(news11);
        ListNews.add(news12);
        Feed feed1 = new Feed(0, "titreFeed1", "urlFeed1", ListNews);
        ListNews.clear();
        
        ListNews.add(news21);
        Feed feed2 = new Feed(1, "titreFeed2", "urlFeed2", ListNews);
        ListNews.clear();
        
        ListNews.add(news31);
        ListNews.add(news32);
        Feed feed3 = new Feed(1, "titreFeed3", "urlFeed3", ListNews);
        ListNews.clear();
        
        ListFeed.add(feed1);
        ListFeed.add(feed2);
        ListFeed.add(feed3);
        Category oldCategory = new Category(0, "oldCategory", ListFeed);
        
        ListNews.remove(news32);
        ListNews.add(news22);
        Category newCategory = new Category(1, "newCategory", ListFeed);
        
        ArrayList<Category> listOldCat = new ArrayList<Category>();
        ArrayList<Category> listNewCat = new ArrayList<Category>();
        listOldCat.add(oldCategory);
        listNewCat.add(newCategory);
        
        FeedManager feedManager = new FeedManager(listOldCat, listNewCat);
        
        feedManager.merge();
        
        assertTrue(feedManager.getOldListCategory().isEmpty());
        
        for (Feed listFeed : feedManager.getListCategory().get(0).getListFeed()) {
        	for (News news :  listFeed.getListNews())
        		mergedListNews.add(news);
        }
        
        assertTrue(mergedListNews.contains(news11));
        assertTrue(mergedListNews.contains(news12));
        assertTrue(mergedListNews.contains(news21));
        assertTrue(mergedListNews.contains(news22));
        assertTrue(mergedListNews.contains(news31));
        assertTrue(mergedListNews.contains(news32));        
    }

    @Test
    public void testGetListCategory() {
            fail("Not yet implemented");
    }

    @Test
    public void testGetOldListCategory() {
            fail("Not yet implemented");
    }

    @Test
    public void testSetListCategory() {
            fail("Not yet implemented");
    }

    @Test
    public void testSetOldListCategory() {
            fail("Not yet implemented");
    }

}
