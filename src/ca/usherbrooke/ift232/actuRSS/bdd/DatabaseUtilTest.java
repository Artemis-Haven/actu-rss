package ca.usherbrooke.ift232.actuRSS.bdd;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.usherbrooke.ift232.actuRSS.News;

public class DatabaseUtilTest {

        @BeforeClass
        public static void setUpBeforeClass() throws Exception {
        }

        @AfterClass
        public static void tearDownAfterClass() throws Exception {
        }

        @Before
        public void setUp() throws Exception {
        }

        @After
        public void tearDown() throws Exception {
        }

        @Test
        public void testConvertCalendarToString() {
               
                Calendar calendar = Calendar.getInstance();
                calendar.set(2013, 01, 12, 05, 06, 03);
               
                String date = DatabaseUtil.ConvertCalendarToString(calendar);
                //System.out.println(date);
                assertEquals(date, "2013-01-12 05:06:03");
               
               
                News news1 = new News("Football - Ligue 1 - L1 : Le top 10 des id�es re�ues� battues en br�che", "http://www.eurosport.fr/football/ligue-1/2013-2014/l1-le-top-10-des-idees-recues.-battues-en-breche_sto4004002/story.shtml", "Ta m�re", calendar ,"Championnat homog�ne, gardiens infranchissables, Paris et Monaco seuls au monde : la Ligue 1 charrie des clich�s qui ne correspondent pas � sa r�alit�. Notre top 10.", false, false  );
                date = DatabaseUtil.ConvertCalendarToString(news1.getDate());
                assertEquals(date, "2013-01-12 05:06:03");
        }

        @Test
        public void testConvertStringToCalendar() {
                String date = "2013-11-12 12:05:33";
                Calendar calendar = Calendar.getInstance();
                calendar = DatabaseUtil.convertStringToCalendar(date);
                //date = dbUtil.ConvertCalendarToString(calendar);
                //System.out.println(date);
                assertEquals(calendar.get(Calendar.YEAR), 2013);
                assertEquals(calendar.get(Calendar.MONTH), 11);
                assertEquals(calendar.get(Calendar.DATE), 12);
                assertEquals(calendar.get(Calendar.HOUR_OF_DAY), 12);
                assertEquals(calendar.get(Calendar.MINUTE), 5);
                assertEquals(calendar.get(Calendar.SECOND), 33);
                       
        }

}

