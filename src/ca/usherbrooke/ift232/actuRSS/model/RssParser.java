package ca.usherbrooke.ift232.actuRSS.model;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Calendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ca.usherbrooke.ift232.actuRSS.Feed;
import ca.usherbrooke.ift232.actuRSS.News;
import ca.usherbrooke.ift232.actuRSS.Category;

public class RssParser {
	/**
     * Parser le fichier XML
     * @param feedurl URL du flux RSS
     */
    public List<News> parse(String feedurl) {
    	//String result = "";
    	ArrayList<News> listNewsFeed = new ArrayList<News>();
        try {
        	//On créé le document à partir du fichier xml pointé par l'url
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            URL url = new URL(feedurl);
            Document doc = builder.parse(url.openStream());
            NodeList nodes = null;
            Element element = null;
            
            /**
             * Titre et date du flux
             * En commentaire : on cherche (pour le moment) à renvoyer juste la liste de News
             * 
             * String nameFeed = "";
        	 * String urlFeed = "";
        	 * 
        	 * nodes = doc.getElementsByTagName("title");
             * Node node = doc.getDocumentElement();
			 *
			 * nameFeed = this.readNode(node, "channel|title");
             * urlFeed = this.readNode(node, "channel|link");
             * 
             */
        	

            /**
             * Elements du flux RSS
             **/
            nodes = doc.getElementsByTagName("item");
            for (int i = 0; i < nodes.getLength(); i++) {
                element = (Element) nodes.item(i);

                listNewsFeed.add(new News(readNode(element, "title"), readNode(element, "link"), readNode(element, "author"), parsePubDate(readNode(element, "pubDate")), readNode(element, "description"), false, false));

            } 
            
        } catch (SAXException ex) {
            Logger.getLogger(RssParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RssParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(RssParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listNewsFeed;
    }

    /**
     * Méthode permettant de retourner ce que contient d'un noeud
     * @param toReadNode le noeud principal
     * @param path suite des noms des noeud sans espace séparé par des "|"
     * @return un string contenant la valeur du noeud voulu
     */
    public String readNode(Node toReadNode, String path) {

        String[] paths = path.split("\\|");
        Node node = null;

        if (paths != null && paths.length > 0) {
        	node = toReadNode;

            for (int i = 0; i < paths.length; i++) {
            	node = getChildByName(node, paths[i].trim());
            }
        }

        if (node != null) {
            return node.getTextContent();
        } else {
            return "";
        }
    }

    /**
     * renvoye le nom d'un noeud fils à partir de son nom
     * @param node noeud pricipal
     * @param name nom du noeud fils
     * @return le noeud fils
     */
    public Node getChildByName(Node node, String name) {
        if (node == null) {
            return null;
        }
        NodeList listChild = node.getChildNodes();

        if (listChild != null) {
            for (int i = 0; i < listChild.getLength(); i++) {
                Node child = listChild.item(i);
                if (child != null) {
                    if ((child.getNodeName() != null && (name.equals(child.getNodeName()))) || (child.getLocalName() != null && (name.equals(child.getLocalName())))) {
                        return child;
                    }
                }
            }
        }
        return null;
    }
    
    /**
     * Renvoie le champ pubDate du fichier xml sous la forme d'un objet Calendar
     * @param pubDate date en xml
     * @return pubDate en Calendar
     */
    public Calendar parsePubDate(String pubDate) {
    	Calendar cal = Calendar.getInstance();
    	try {
    		DateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
        	Date date = formatter.parse(pubDate);
        	cal.setTime(date);

    	} catch (ParseException ex) {
    		Logger.getLogger(RssParser.class.getName()).log(Level.WARNING, null, ex);
    	}
    	return cal;
    }

}
