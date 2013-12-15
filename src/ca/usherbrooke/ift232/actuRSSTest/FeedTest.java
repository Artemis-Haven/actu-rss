package ca.usherbrooke.ift232.actuRSSTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
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

       
        private int id=8;
        private String title="New robot sent to Mars";
        private String url="http://www.cnn.com/";
        private List<News> listNews=null;
       
       
        private Feed feed1;
        private Feed feed2;
        private Feed feed3;
        private Feed feed4;
       
  
       
        private List<News> ListNews;
       
        public void setUp() {
                Calendar cal = Calendar.getInstance();
                News news1 = new News("titre1", "url1", "auteur1", cal, "txt1", true, true);
                News news2 = new News("titre2", "url2", "auteur2", cal, "txt2", false, false);
                News news3 = new News("titre3", "url3", "auteur3", cal, "txt3", true, false);
                ListNews = new ArrayList<News>();
                ListNews.add(news1);
                ListNews.add(news2);
                ListNews.add(news3);
                
                feed1 = new Feed();
                feed2 = new Feed(title,url,listNews);
                feed3 = new Feed(id,title,url,listNews);
                feed4 = new Feed(id,title,url);
        }
       
        /**
         * Permet de tester les constructeurs de Feeds
         */
        @Test
        public void testConstructors() {
                Feed feed = new Feed();
                assertNotNull(feed);
               
                feed = new Feed("titre", "url", ListNews);
                assertNotNull(feed);
               
                feed = new Feed(20, "titre", "url", ListNews);
                assertNotNull(feed);
               
                feed = new Feed(20, "titre", "url");
                assertNotNull(feed);
                }
       
        /**
         * Permet de tester les getters de Feeds
         */
        @Test
        public void testGetters() {
                Feed feed = new Feed(20, "titre", "url", ListNews);
                assertEquals(feed.getId(), 20);
                assertEquals(feed.getTitle(), "titre");
                assertEquals(feed.getUrl(), "url");
                assertEquals(feed.getListNews(), ListNews);
        }
       
        /**
         * Permet de tester les setters de Feeds
         */
        @Test
        public void testSetters() {
                Feed feed = new Feed();
                feed.setId(15);
                feed.setTitle("TitreSetter");
                feed.setUrl("UrlSetter");
                feed.setListNews(ListNews);
                feed.setNameCategory("Sport");
               
                assertEquals(feed.getId(), 15);
                assertEquals(feed.getTitle(), "TitreSetter");
                assertEquals(feed.getUrl(), "UrlSetter");
                assertEquals(feed.getListNews(), ListNews);
                assertEquals(feed.getNameCategory(), "Sport");

        }
        
        @Test
        public void testToString() {
        	Feed feed = new Feed();
        	feed.setTitle("FLUX");
        	assertEquals("FLUX", feed.toString());
        	
        }
               
       
       

}
