package ca.usherbrooke.ift232.actuRSS.modelTest;

import static org.junit.Assert.*;
import ca.usherbrooke.ift232.actuRSS.News;
import ca.usherbrooke.ift232.actuRSS.model.RssParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
//import org.jdom.input.SAXBuilder;

public class RssParserTest {
	
	RssParser parser = new RssParser();
	File fileSimpleTest = new File("simpleTest.xml");
	//SAXBuilder sxb = new SAXBuilder();
	File fileRealTest = new File("realTest.xml");
	String StrSimpleTest = fileSimpleTest.toString();
			
	@Test
	public void testParse() {
		List<News> text = parser.parse("http://feeds2.feedburner.com/KorbensBlog-UpgradeYourMind");
		//List<News> text = parser.parse("http://feeds.feedburner.com/abry");
		assertNotNull(text);
		for (News news : text)
			System.out.println(news);
	}

	@Test
	public void testReadNode() {
		fail("Not yet implemented");
	}

	@Test
	/*
	 * getChildByName(Node node, String name)
	 */
	public void testGetChildByName() throws SAXException, IOException {
		String nomFichier = "simpleTest.xml";
		//URL url = new URL(getDocumentBase(), nomFichier);
		//try {
			/*File fichierTest = new File();
			Document doc = builder.parse()*/
			//Properties properties = new Properties();
			//properties.load(new FileInputStream("https://actu-rss.googlecode.com/svn/src/ca/usherbrooke/ift232/actuRSS/model/simpleTest.xml"));
			//File testXmlFile = new File("https://actu-rss.googlecode.com/svn/src/ca/usherbrooke/ift232/actuRSS/model/simpleTest.xml");
			//DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			//Document doc = builder.parse(testXmlFile);
			//doc.getDocumentElement().normalize();
			//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		//} catch (ParserConfigurationException ex) {
		//	Logger.getLogger(RssParserTest.class.getName()).log(Level.SEVERE,  null, ex);
		//}
		
		
	}

	@Test
	public void testParsePubDate() {
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2013); 
		cal.set(Calendar.MONTH, Calendar.NOVEMBER); 
		cal.set(Calendar.DAY_OF_MONTH, 25); 
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); 
		cal.set(Calendar.HOUR_OF_DAY, 19); 
		cal.set(Calendar.MINUTE, 20); 
		cal.set(Calendar.SECOND, 1); 
		String pubDate = "Mon, 25 Nov 2013 14:20:01 -1000";
		
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
