package ca.usherbrooke.ift232.actuRSS.modelTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.util.Calendar;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
//import org.jdom.input.SAXBuilder;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import ca.usherbrooke.ift232.actuRSS.Feed;
import ca.usherbrooke.ift232.actuRSS.News;
import ca.usherbrooke.ift232.actuRSS.model.RssParser;

public class RssParserTest {
	
	RssParser parser = new RssParser();
	File fileSimpleTest = new File("simpleTest.xml");
	//SAXBuilder sxb = new SAXBuilder();
	File fileRealTest = new File("realTest.xml");
	String StrSimpleTest = fileSimpleTest.toString();
			
	@Test
	public void testParse() throws MalformedURLException, IOException, ParserConfigurationException, SAXException {
		
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		URL url = new URL("http://feeds2.feedburner.com/KorbensBlog-UpgradeYourMind");
        Document doc = builder.parse(url.openStream());
		Feed feed = parser.parse(doc);
		//Feed feed = parser.parse("http://feeds2.feedburner.com/KorbensBlog-UpgradeYourMind");
		//Feed text = parser.parse("http://feeds.feedburner.com/abry");
		assertNotNull(feed);
		System.out.println(feed.getTitle());
		System.out.println(feed.getUrl());
		for (News news : feed.getListNews())			
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
		String nameFile = "simpleTest.xml";
		File testFile = new File("");
		
		//Connection connection = DriverManager.getConnection("https://actu-rss.googlecode.com/svn");
		URL url = new URL("https://actu-rss.googlecode.com/svn/branches/benjidavid/src/testFiles/" + nameFile);
		FileUtils.copyURLToFile(url, testFile);
		System.out.println(testFile);
		/*BufferedInputStream in = new BufferedInputStream(url.openStream());
		FileOutputStream fos = new FileOutputStream("test.xml", true);
		byte buff[] = new byte[2048];
		while ((in.read(buff)) != -1)
				fos.write(buff);
		while ((in.read(buff)) != -1)
			fos.read(buff);
		fos.close();*/
		//FileInputStream out = new fileInputStream(url);
		
		//File file = new File(url);
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
