package ca.usherbrooke.ift232.actuRSS.bdd;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
		DatabaseUtil dbUtil = new DatabaseUtil();
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(2013, 1, 12, 5, 6, 3);
		
		String date = dbUtil.ConvertCalendarToString(calendar);
		//System.out.println(date);
		assertEquals(date, "2013-01-12 05:06:03");
	}

	@Test
	public void testConvertStringToCalendar() {
		DatabaseUtil dbUtil = new DatabaseUtil();
		String date = "2013-11-12 12:05:33";
		Calendar calendar = Calendar.getInstance();
		calendar = dbUtil.convertStringToCalendar(date);
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
