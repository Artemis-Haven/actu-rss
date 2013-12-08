package ca.usherbrooke.ift232.actuRSS.modelTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ca.usherbrooke.ift232.actuRSS.Feed;
import ca.usherbrooke.ift232.actuRSS.model.RssParser;



/**
 * <b>Classe de test de Model.RssParser</b>
 *
 * <p>Teste avec les fichiers simpleTest.xml pour les tests simples sur les méthodes et realTest.xml pour un test réel.
 * Les fichiers de test sont dans le package testFiles</p>
 * @see testFiles
 * @author BOAS David
 */
public class RssParserTest {
	
        /**
         * <b>Teste la fonction parse</b>
         * <p>Teste avec le fichier realTest.xml .</p>
         * <p>Vérifie que le feed existe, que ses champs Title et Url sont corrects,
         * puis teste aux limites différents champs de la liste de News ListNews</p>
         * @throws MalformedURLException
         * @throws IOException
         * @throws ParserConfigurationException
         * @throws SAXException
         */
        @Test
        public void testParse() throws MalformedURLException, IOException, ParserConfigurationException, SAXException {
               
            	DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                URL url = new URL("https://actu-rss.googlecode.com/svn/branches/benjidavid/src/testFiles/realTest.xml");
                Document doc = builder.parse(url.openStream());

                Feed feed = RssParser.parse(doc);
                assertNotNull(feed);
               
                assertEquals(feed.getTitle(), "Korben");
                assertEquals(feed.getUrl(), "http://korben.info");
               
                assertEquals(feed.getListNews().get(0).getTitle(), "La démocratie, ce malentendu");
                assertEquals(feed.getListNews().get(1).getTitle(), "Une petite partie de GoldenEye 007, ça vous branche ?");
                System.out.println(feed.getListNews().get(1).getUrl());
                assertEquals(feed.getListNews().get(2).getContents(), "<p>Si vous vous intéressez un peu à l'archéologie logicielle, vous connaissez surement les abandonwares. Il s'agit de logiciels qui ne sont plus vendus ou distribués que des passionnés mettent en ligne pour le plaisir. Ce sont surtout des jeux comme sur myabandonware ou abandonware-france. Mais pour ceux qui souhaiteraient aussi remettre la main sur d'anciens [&#8230;]</p><p>Cet article merveilleux et sans aucun égal intitulé : <a href=\"http://korben.info/logiciels-depoussierer.html\">Logiciels à dépoussiérer</a> ; a été publié sur <a href=\"http://korben.info\">Korben</a>, le seul site qui t'aime plus fort que tes parents.</p>");
                assertEquals(feed.getListNews().get(5).getAuthor(), "");
                assertEquals(feed.getListNews().get(29).getDate().get(Calendar.DAY_OF_WEEK), 2);
                assertEquals(feed.getListNews().get(29).getDate().get(Calendar.DAY_OF_MONTH), 18);
                assertEquals(feed.getListNews().get(29).getDate().get(Calendar.MONTH), 10);
                assertEquals(feed.getListNews().get(29).getDate().get(Calendar.YEAR), 2013);
                assertEquals(feed.getListNews().get(29).getDate().get(Calendar.HOUR_OF_DAY), 9);
                assertEquals(feed.getListNews().get(29).getDate().get(Calendar.MINUTE), 30);
                assertEquals(feed.getListNews().get(29).getDate().get(Calendar.SECOND), 55);

                /*
                 * A décommenter si l'on veut voir le contenu du feed dans la console
                 */
                /*
                System.out.println(feed.getTitle());
                System.out.println(feed.getUrl());
                for (News news : feed.getListNews())            
                        System.out.println(news);
                */
        }

        /**
         * <b>Teste la fonction ReadNote de RssParser</b>
         * <p>Teste avec le fichier simpleTest.xml</p>
         * <p>Le test consiste à :
         * 	<ul>
         * 		<li>Vérifier qu'il renvoie chaine vide dans le cas où le noeud n'est pas initialisé (pour ne pas interrompre le lecteur)</li>
         * 		<li>Tester la relation entre readNode et getChildByName </li>
         * 		<li>Tester 4 champs des trois premiers item du fichier : title, description, pubDate et link </li>
         * @throws ParserConfigurationException
         * @throws IOException
         * @throws SAXException
         */
        @Test
        public void testReadNode() throws ParserConfigurationException, IOException, SAXException {
                String testString = "";
                DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                URL url = new URL("https://actu-rss.googlecode.com/svn/branches/benjidavid/src/testFiles/simpleTest.xml");
               
        Document doc = builder.parse(url.openStream());
       
        Node node = null;
        String nameFeed = RssParser.readNode(node, "channel|title");
        assertEquals(nameFeed, "");
       
        node = doc.getDocumentElement();
        nameFeed = RssParser.readNode(node, "channel|title");
        assertEquals(nameFeed, "Mon site");
       
        NodeList nodes = doc.getElementsByTagName("item");
       
        Element element = (Element) nodes.item(0);
       
        testString = RssParser.readNode(element,"title");
        assertEquals(testString, "Actualité N°1");
        assertFalse(testString == "fauseChaine");
        testString = RssParser.readNode(element,"description");
        assertEquals(testString, "Ceci est ma première actualité");
        testString = RssParser.readNode(element,"pubDate");
        assertEquals(testString, "Sat, 07 Sep 2002 00:00:01 GMT");
        testString = RssParser.readNode(element,"link");
        assertEquals(testString, "http://www.example.org/actu1");
       
        element = (Element) nodes.item(1);
       
        testString = RssParser.readNode(element,"title");
        assertEquals(testString, "Actualité N°2");
        testString = RssParser.readNode(element,"description");
        assertEquals(testString, "Ceci est ma seconde actualité");
        testString = RssParser.readNode(element,"pubDate");
        assertEquals(testString, "Sat, 24 Nov 2002 00:00:01 GMT");
        testString = RssParser.readNode(element,"link");
        assertEquals(testString, "http://www.example2.org/actu2");
       
        element = (Element) nodes.item(2);
       
        testString = RssParser.readNode(element,"title");
        assertEquals(testString, "Actualité N°3");
        testString = RssParser.readNode(element,"description");
        assertEquals(testString, "Ceci est ma troisième actualité");
        testString = RssParser.readNode(element,"pubDate");
        assertEquals(testString, "Sat, 28 Dec 2002 00:00:01 GMT");
        testString = RssParser.readNode(element,"link");
        assertEquals(testString, "http://www.example3.org/actu3");
        }

        /**
         * <b>Teste la fonction GetChildByName de RssParser</b>
         * <p>Teste avec le fichier simpleTest.xml</p>
         * <p>Le test vérifie que le noeud n'est pas null, qu'il lit bien les enfants désirés,
         * et qu'il prend les champs dans l'ordre s'il y en a plusieurs identiques </p>
         * @throws SAXException
         * @throws IOException
         * @throws ParserConfigurationException
         */
        @Test
        public void testGetChildByName() throws SAXException, IOException, ParserConfigurationException {
               
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            URL url = new URL("https://actu-rss.googlecode.com/svn/branches/benjidavid/src/testFiles/simpleTest.xml");
               
	        Document doc = builder.parse(url.openStream());
	       
	        Node node = doc.getDocumentElement();
	        assertNotNull(node);
	        node = RssParser.getChildByName(node, "channel");
	        node = RssParser.getChildByName(node, "title");
	        assertEquals(node.getTextContent(), "Mon site");
	       
	        NodeList nodes = doc.getElementsByTagName("item");
	        node = nodes.item(0);
	        node = RssParser.getChildByName(node, "description");
	        assertEquals(node.getTextContent(), "Ceci est ma première actualité");
	       
	        node = nodes.item(1);
	        node = RssParser.getChildByName(node, "description");
	        assertEquals(node.getTextContent(), "Ceci est ma seconde actualité");      
        }

        /**
         * <b>Teste la fonction ParsePubDate de RssParser</b>
         * <p>Teste que la date est au bon format, et qu'il est bien enregistré dans un calendar.
         * De plus, teste que si la lecture du noeud pubDate ne s'est pas faite, qu'il renvoie bien la date actuelle </p>
         */
        @Test
        public void testParsePubDate() {
               
                Calendar cal = Calendar.getInstance();
                Date date = new Date();
               
                Calendar calParse = RssParser.parsePubDate("");
                assertEquals(calParse.getTime(), date);
               
                cal.set(Calendar.YEAR, 2013);
                cal.get(Calendar.YEAR);
                cal.set(Calendar.MONTH, Calendar.NOVEMBER);
                cal.get(Calendar.MONTH);
                cal.set(Calendar.DAY_OF_MONTH, 25);
                cal.get(Calendar.DAY_OF_MONTH);
                cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                cal.get(Calendar.DAY_OF_WEEK);
                cal.set(Calendar.HOUR_OF_DAY, 19);
                cal.get(Calendar.HOUR_OF_DAY);
                cal.set(Calendar.MINUTE, 20);
                cal.get(Calendar.MINUTE);
                cal.set(Calendar.SECOND, 1);
                cal.get(Calendar.SECOND);
                
                String pubDate = "Mon, 25 Nov 2013 14:20:01 -1000";
               
                calParse = Calendar.getInstance();
                calParse = RssParser.parsePubDate(pubDate);
                assertEquals(calParse.get(Calendar.YEAR), cal.get(Calendar.YEAR));
                assertEquals(calParse.get(Calendar.MONTH), cal.get(Calendar.MONTH));
                assertEquals(calParse.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.DAY_OF_MONTH));
                assertEquals(calParse.get(Calendar.DAY_OF_WEEK), cal.get(Calendar.DAY_OF_WEEK));
                assertEquals(calParse.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.HOUR_OF_DAY));
                assertEquals(calParse.get(Calendar.MINUTE), cal.get(Calendar.MINUTE));
                assertEquals(calParse.get(Calendar.SECOND), cal.get(Calendar.SECOND));
        }

}

