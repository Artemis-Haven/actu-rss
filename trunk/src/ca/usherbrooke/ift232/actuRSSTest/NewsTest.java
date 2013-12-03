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

        @Test
        public void testConstructors() {
                News news = new News();
                assertNotNull(news);
                Calendar cal = Calendar.getInstance();
                news = new News("titre", "exempleUrl", "Gusman", cal, "txt", true, true);
                assertNotNull(news);
        }
       
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

       
        /*
         * @Test
        public void testToString() {
                String testString = "";
                News news = new News();
               
                news.setTitle("titre");
                news.setUrl("exempleUrl");
                news.setAuthor("Gusman");
                Calendar cal = Calendar.getInstance();
                news.setDate(cal);
                news.setContents("Plein plein plein de texte");
                news.setRead(true);
                news.setFavorite(true);
               
                testString = "News [title = " + news.getTitle() + ", contents = " + news.getContents()
                                + ", url = " + news.getUrl() + ", author = " + news.getAuthor() + ", date = "
                                + "(jour de la semaine : " + news.getDate().get(Calendar.DAY_OF_WEEK)+"), "+ news.getDate().get(Calendar.DAY_OF_MONTH) +"/"
                                + news.getDate().get(Calendar.MONTH) + "/" + news.getDate().get(Calendar.YEAR) + " "
                                + news.getDate().get(Calendar.HOUR_OF_DAY) + ":" + news.getDate().get(Calendar.MINUTE) +":"
                                + news.getDate().get(Calendar.SECOND) + "]";
                assertEquals(testString, news.toString());
               
        }*/
       
}

