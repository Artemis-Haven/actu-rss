package ca.usherbrooke.ift232.actuRSSTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Date;
import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.usherbrooke.ift232.actuRSS.News;

public class NewsTest {

        /**
         * Permet de tester les constructeurs de News
         */
        @Test
        public void testConstructors() {
                News news = new News();
                assertNotNull(news);
                Calendar cal = Calendar.getInstance();
                news = new News("titre", "exempleUrl", "Gusman", cal, "txt", true, true);
                assertNotNull(news);
        }
       
        /**
         * Permet de tester les getters de News
         */
        @Test
        public void testGetters() {
                News news = new News();
                assertEquals(news.getTitle(), "");
                assertEquals(news.getUrl(), "");
                assertEquals(news.getAuthor(), "");
                assertEquals(news.getDate(), null);
                assertEquals(news.getContents(), "");
                assertEquals(news.isRead(), false);
                assertEquals(news.isFavorite(), false);
        }
       
        /**
         * Permet de tester les setters de News
         */
        @Test
        public void testSetters() {
                News news = new News();
               
                news.setTitle("titre");
                news.setUrl("exempleUrl");
                news.setAuthor("Gusman");
                Calendar cal = Calendar.getInstance();
                news.setDate(cal);
                news.setContents("Plein plein plein de texte");
                news.setRead(true);
                news.setFavorite(true);
               
                assertEquals(news.getTitle(), "titre");
                assertEquals(news.getUrl(), "exempleUrl");
                assertEquals(news.getAuthor(), "Gusman");
                assertEquals(news.getDate(), cal);
                assertEquals(news.getContents(), "Plein plein plein de texte");
                assertEquals(news.isRead(), true);
                assertEquals(news.isFavorite(), true);
        }

       
        @Test
        public void testToString() {
        		News n = new News();
        		n.setTitle("NEWS");
        		assertEquals(n.toString(), "NEWS");

        }
       
}

