package ca.usherbrooke.ift232.actuRSSTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.usherbrooke.ift232.actuRSS.Category;
import ca.usherbrooke.ift232.actuRSS.Feed;
import ca.usherbrooke.ift232.actuRSS.News;

public class CategoryTest extends TestCase  {


        /*private static final String Sport = "Sport";
        private static final String Politique = "Politique";
        private List<Feed> listFeedEx = new ArrayList<Feed>();
       

        private Category categoryExample1;
        private Category categoryExample2;
        private Category categoryExample3;

        public void setUp() throws Exception {
                categoryExample1 = new Category();
                categoryExample2 = new Category(2,Sport,listFeedEx);
                categoryExample3 = new Category(3,Politique);
        }*/
       
        private List<Feed> ListFeed;
       
        public void setUp() {
                Calendar cal = Calendar.getInstance();
                News news1 = new News("titre1", "url1", "auteur1", cal, "txt1", true, true);
                News news2 = new News("titre2", "url2", "auteur2", cal, "txt2", false, false);
                List<News> ListNews = new ArrayList<News>();
                ListNews.add(news1);
                ListNews.add(news2);
               
                Feed feed1 = new Feed(0, "titreFeed1", "urlFeed1", ListNews);
                Feed feed2 = new Feed(1, "titreFeed2", "urlFeed2", ListNews);
                ListFeed = new ArrayList<Feed>();
                ListFeed.add(feed1);
                ListFeed.add(feed2);
        }
       
        /**
         * Permet de tester les constructeurs de Category
         */
        public void testConstructors() {
               
                Category category = new Category();
                assertNotNull(category);
               
                category = new Category(10, "categoryName", ListFeed);
                assertNotNull(category);
               
                category = new Category(15, "categoryName");
                assertNotNull(category);
                /*assertEquals(-1,categoryExample1.getId());
                assertEquals("",categoryExample1.getName());
                assertEquals(new ArrayList<Feed>(),categoryExample1.getListFeed());
               
               
                assertEquals(2,categoryExample2.getId());
                assertEquals("Sport",categoryExample2.getName());
                assertEquals(new ArrayList<Feed>(),categoryExample2.getListFeed());
               
               
                assertEquals(3,categoryExample3.getId());
                assertEquals("Politique",categoryExample3.getName());
                assertEquals(null,categoryExample3.getListFeed());*/
        }
       
        // Test des accesseurs de type set
        /**
         * Permet de tester les getters de Category
         */
        @Test
        public void testGetters() {
                Category category = new Category(10, "categoryName", ListFeed);
                assertEquals(category.getId(), 10);
                assertEquals(category.getName(), "categoryName");
                assertEquals(category.getListFeed(), ListFeed);
                /*List<Feed> entries=null;
                categoryExample1.setId(4);
                categoryExample2.setName("People");
                categoryExample3.setListFeed(entries);
               
                assertEquals(4,categoryExample1.getId());
                assertEquals("People",categoryExample2.getName());
                assertEquals(null,categoryExample3.getListFeed());*/
        }
       
        /**
         * Permet de tester les setters de Category
         */
        @Test
        public void testSetters() {
                Category category = new Category();
                category.setId(42);
                category.setName("NameSetters");
                category.setListFeed(ListFeed);
               
                assertEquals(category.getId(), 42);
                assertEquals(category.getName(), "NameSetters");
                assertEquals(category.getListFeed(), ListFeed);
        }
        
        
        @Test
        public void testToString() {
        	Category cat = new Category();
        	cat.setName("CATEGORY");
        	assertEquals("CATEGORY", cat.toString());
        }


}
