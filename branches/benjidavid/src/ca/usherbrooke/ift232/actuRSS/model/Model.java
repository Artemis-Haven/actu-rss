package ca.usherbrooke.ift232.actuRSS.model;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.text.html.parser.Parser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import ca.usherbrooke.ift232.actuRSS.Category;
import ca.usherbrooke.ift232.actuRSS.Feed;
import ca.usherbrooke.ift232.actuRSS.News;
import ca.usherbrooke.ift232.actuRSS.bdd.Database;
import ca.usherbrooke.ift232.actuRSS.bdd.DatabaseManager;

public class Model extends Observable{

	DatabaseManager dbManager;
	FeedManager feedManager;
	RssParser parser;
	
	public Model() {

		dbManager = new DatabaseManager(new Database("acturss.db"));
		feedManager = new FeedManager();
		
		/*dbManager.createDB();

		News n1 = new News("titre1", "url1", "author1", new GregorianCalendar(), "contents1", false, false);
		News n2 = new News("titre2", "url2", "author2", new GregorianCalendar(), "contents2", false, false);
		News n3 = new News("titre3", "url3", "author3", new GregorianCalendar(), "contents3", false, false);
		
		News n4 = new News("titre4", "url4", "author4", new GregorianCalendar(), "contents4", false, false);
		News n5 = new News("titre5", "url5", "author5", new GregorianCalendar(), "contents5", false, false);
		News n6 = new News("titre6", "url6", "author6", new GregorianCalendar(), "contents6", false, false);

		ArrayList<News> f1List = new ArrayList<News>();
		ArrayList<News> f2List = new ArrayList<News>();
		
		
		Feed f1 = new Feed(1, "title feed 1", "url", f1List);
		Feed f2 = new Feed(2, "title feed 2", "url", f2List);

		f1.getListNews().add(n1);
		f1.getListNews().add(n2);
		f1.getListNews().add(n3);
		f2.getListNews().add(n4);
		f2.getListNews().add(n5);
		f2.getListNews().add(n6);
		
		ArrayList<Feed> feedList = new ArrayList<Feed>();
		
		Category cat = new Category(1, "nom de la categorie", feedList);
		cat.getListFeed().add(f1);
		cat.getListFeed().add(f2);
		
		ArrayList<Category> catList = new ArrayList<Category>();
		catList.add(cat);
		

		feedManager = new FeedManager(catList, catList);
		
		dbManager.insertObjetToDB(feedManager.getListCategory());*/
		
	}
	
	private void loadAllFromDB() {
		try {
			feedManager.setOldListCategory(dbManager.getAllCategories());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Impossible d'accéder à la BD.");
			e.printStackTrace();
		}
	}
	
	private void sendAllToDB() {
		//vider la BdD avec clearDB
		//dbManager.clearDB();
		dbManager.insertObjetToDB(feedManager.getOldListCategory());
	}
	
	public void synchronize() throws Exception {
		
		Document docFeed = null;
		//Si la liste des catégories est vide, on la charge depuis la BD
		if(feedManager.getOldListCategory().isEmpty()) {
			loadAllFromDB();
		}
		ArrayList<Category> newList = new ArrayList<Category>();
		
		// Pour chaque feed dans chaque catégorie :
		for(Category category : feedManager.getOldListCategory()) {
			Category newCat = new Category();
			
			for(Feed feed : category.getListFeed()) {
				// On récupère les fichiers via leur url, 
				// on les parse 
				// et on les envoie dans le feedManager
				//File f = new File(feed.getUrl());
				docFeed = Model.obtainDocument(feed.getUrl());
				if (docFeed != null) {
					Feed newFeed = parser.parse(docFeed);
					newCat.getListFeed().add(newFeed);
				} else throw new Exception("Le document n'a pas �t� correctement pars�");
			}
			newList.add(newCat);
		}
		feedManager.setListCategory(newList);
		//Ici, ListCategory contient la nouvelle liste de flux
		//et oldListCategory contient l'ancienne
		
		//On fusionne les deux listes
		feedManager.merge();
		
		//On
		sendAllToDB();
		
		//Notifier la vue
		this.notifyObserver();
		
		
		//TODO		
	}
	
	public void notifyObserver()
	{
		List<Category> oldListCategory = (List<Category>)feedManager.getOldListCategory();
		setChanged();
		notifyObservers(oldListCategory);	
	}
	
	public static Document obtainDocument(String feedurl) {
		Document doc = null;
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			URL url = new URL(feedurl);
            doc = builder.parse(url.openStream());
		} catch (ParserConfigurationException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
        	Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
        	Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
        	Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return doc;
	}
	
	
	
	
}
