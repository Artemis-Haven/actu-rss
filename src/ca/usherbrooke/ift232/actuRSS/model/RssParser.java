package ca.usherbrooke.ift232.actuRSS.model;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ca.usherbrooke.ift232.actuRSS.Feed;
import ca.usherbrooke.ift232.actuRSS.News;

/**
 * <b>Classe RssParser. Classe statique pour parser les documents xml (soit les
 * flux rss)</b>
 * 
 * <p>
 * La méthode principale est la méthode parse. Les autres méthodes sont là pour
 * l'assister. C'est donc celle-ci qui sera appelée dans les autres classes
 * </p>
 * 
 * @see 
 * @see RssParserTest
 * @author BOAS David, FERRE Benjamin
 */
public class RssParser {
	/**
	 * Transforme le flux RSS en objet Feed
	 * 
	 * @param feedDoc :
	 *          Objet document correspondant au flux Rss. 
	 * @return 
	 * 			Objet Feed avec tout ces champs initialisée et sa liste de News
	 *  
	 */
	public static Feed parse(Document feedDoc) {

		String nameFeed = "";
		String urlFeed = "";
		ArrayList<News> listNewsFeed = new ArrayList<News>();

		NodeList nodes = null;
		Element element = null;

		/**
		 * Affectation Titre et date du flux 
		 */
		nodes = feedDoc.getElementsByTagName("title");
		Node node = feedDoc.getDocumentElement();

		nameFeed = readNode(node, "channel|title");
		urlFeed = readNode(node, "channel|link");

		Feed feed = new Feed(nameFeed, urlFeed, listNewsFeed);
		/**
		 * Affectation listNews du flux RSS
		 */
		nodes = feedDoc.getElementsByTagName("item");
		for (int i = 0; i < nodes.getLength(); i++) {
			element = (Element) nodes.item(i);
			/*String link = readNode(element,"link");
			URL urlLink = null;
			try {
				urlLink = new URL(link);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			link = urlLink.getProtocol() + "://" + urlLink.getAuthority();
			News n = new News(readNode(element, "title"), link, readNode(element, "author"),parsePubDate(readNode(element, "pubDate")),
					readNode(element, "description"), false, false);s*/
			News n = new News(readNode(element, "title"), readNode(element,"link"), readNode(element, "author"),parsePubDate(readNode(element, "pubDate")),
					readNode(element, "description"), false, false);
		
			
			
			n.setFeed(feed);
			listNewsFeed.add(n);
		}
		return (feed);
	}

	/**
	 * Retourne ce que contient un noeud
	 * 
	 * @param toReadNode :
	 *            Le noeud à lire
	 * @param path :
	 *            Le nom de la balise du noeud que l'on veut lire. Pour lire des balises imbriquées, utiliser "|" pour les délimiter
	 * @return Un String contenant la valeur du noeud voulu, chaine vide si le noeud ne peut être lu
	 */
	public static String readNode(Node toReadNode, String path) {

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
	 * Renvoye un des noeud fils
	 * 
	 * @param node
	 *            noeud parent
	 * @param name
	 *            nom du noeud fils que l'on désire
	 * @return Le noeud fils correspondant au paramètre name
	 */
	public static Node getChildByName(Node node, String name) {
		if (node == null) {
			return null;
		}
		NodeList listChild = node.getChildNodes();

		if (listChild != null) {
			for (int i = 0; i < listChild.getLength(); i++) {
				Node child = listChild.item(i);
				if (child != null) {
					if ((child.getNodeName() != null && (name.equals(child.getNodeName()))) || 
							(child.getLocalName() != null && (name.equals(child.getLocalName())))) {
						return child;
					}
				}
			}
		}
		return null;
	}

	/**
	 * Renvoie le champ pubDate du fichier xml sous la forme d'un objet Calendar
	 * 
	 * @param pubDate
	 *            : date contenu dans la balise pubDate du fichier xml
	 * @return pubDate en Calendar
	 */
	public static Calendar parsePubDate(String pubDate) {
		Calendar cal = Calendar.getInstance();
		if (pubDate == "") {
			Date date = new Date();
			cal.setTime(date);
			return cal;
		}
		try {
			DateFormat formatter = new SimpleDateFormat(
					"EEE, d MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
			Date date = formatter.parse(pubDate);
			cal.setTime(date);

		} catch (ParseException ex) {
			Logger.getLogger(RssParser.class.getName()).log(Level.WARNING,
					null, ex);
		}
		return cal;
	}

}
