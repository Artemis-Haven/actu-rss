package ca.usherbrooke.ift232.actuRSS.model;

import static org.junit.Assert.*;
import ca.usherbrooke.ift232.actuRSS.model.RssParser;

import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;
//import org.jdom.input.SAXBuilder;

public class RssParserTest {
	
	RssParser parser = new RssParser();
	File fileSimpleTest = new File("simpleTest.xml");
	//SAXBuilder sxb = new SAXBuilder();
	File fileRealTest = new File("realTest.xml");
	String StrSimpleTest = fileSimpleTest.toString();
			
	@Test
	public void testParse() {
		String text = parser.parse(StrSimpleTest);
		assertNotNull(text);
		System.out.println(text);
		System.out.println(StrSimpleTest);
	}

	@Test
	public void testReadNode() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetChildByName() {
		fail("Not yet implemented");
	}

	@Test
	public void testParsePubDate() {
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2013); 
		cal.set(Calendar.MONTH, Calendar.NOVEMBER); 
		cal.set(Calendar.DAY_OF_MONTH, 25); 
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); 
		cal.set(Calendar.HOUR_OF_DAY, 14); 
		cal.set(Calendar.MINUTE, 20); 
		cal.set(Calendar.SECOND, 1); 
		String pubDate = "Mon, 25 Nov 2013 19:20:01 +0000";
		
		Calendar calParse = Calendar.getInstance();
		calParse = parser.parsePubDate(pubDate);
		assertEquals(calParse.get(Calendar.YEAR), cal.get(Calendar.YEAR));
		assertEquals(calParse.get(Calendar.MONTH), cal.get(Calendar.MONTH));
		assertEquals(calParse.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.DAY_OF_MONTH));
		assertEquals(calParse.get(Calendar.DAY_OF_WEEK), cal.get(Calendar.DAY_OF_WEEK));
		assertEquals(calParse.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.HOUR_OF_DAY));
		assertEquals(calParse.get(Calendar.MINUTE), cal.get(Calendar.MINUTE));
		assertEquals(calParse.get(Calendar.SECOND), cal.get(Calendar.SECOND));
	}

}
