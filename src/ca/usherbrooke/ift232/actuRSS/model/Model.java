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
		parser = new RssParser();	
	}
	
	public void loadAllFromDB() {
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
		dbManager.clearDB();
		dbManager.insertObjetToDB(feedManager.getOldListCategory());
	}
	
	public void synchronize() throws Exception {
        
        Document docFeed = null;
        //Si la liste des catÃ©gories est vide, on la charge depuis la BD
        if(feedManager.getOldListCategory().isEmpty()) {
                loadAllFromDB();
        }
        ArrayList<Category> newList = new ArrayList<Category>();
       
        // Pour chaque feed dans chaque catÃ©gorie :
        for(Category category : feedManager.getOldListCategory()) {
                Category newCat = new Category(category.getId(), category.getName());
               
                for(Feed feed : category.getListFeed()) {
                        // On rÃ©cupÃ¨re les fichiers via leur url,
                        // on les parse
            
                	// et on les envoie dans le feedManager
                        //File f = new File(feed.getUrl());
                	try {
                    docFeed = Model.obtainDocument(feed.getUrl());
                	} catch (MalformedURLException ex) {
                		ex.getMessage();
                	}
                	
                    if (docFeed != null) {
                            Feed newFeed = RssParser.parse(docFeed);
                            newCat.getListFeed().add(newFeed);
                    } else throw new Exception("Le document n'a pas été correctement parsé");
                }
                newList.add(newCat);
        }
        feedManager.setListCategory(newList);
        //Ici, ListCategory contient la nouvelle liste de flux
        //et oldListCategory contient l'ancienne
       
        //On fusionne les deux listes
        int i =0;
        for (Category cat : feedManager.getOldListCategory())
        	for (Feed feed : cat.getListFeed())
        		for (News news : feed.getListNews()) {
        			System.out.println("news("+i+") : " + news.getTitle());
        			System.out.println("news("+i+") : " + news.getUrl());
        			i++;
        		}
        i =0;
        for (Category cat : feedManager.getListCategory())
        	for (Feed feed : cat.getListFeed())
        		for (News news : feed.getListNews()) {
        			System.out.println("Newnews("+i+") : " + news.getTitle());
        			System.out.println("Newnews("+i+") : " + news.getUrl());
        			i++;
        		}
        feedManager.merge();

        //On
        sendAllToDB();
        i =0;
        for (Category cat : feedManager.getOldListCategory())
        	for (Feed feed : cat.getListFeed())
        		for (News news : feed.getListNews()) {
        			System.out.println("news("+i+") : " + news.getTitle());
        			System.out.println("news("+i+") : " + news.getUrl());
        			i++;
        		}
        i =0;
        for (Category cat : feedManager.getListCategory())
        	for (Feed feed : cat.getListFeed())
        		for (News news : feed.getListNews()) {
        			System.out.println("Newnews("+i+") : " + news.getTitle());
        			System.out.println("Newnews("+i+") : " + news.getUrl());
        			i++;
        		}
        //Notifier la vue
        this.notifyObserver();
       
       
        //TODO          
}

	
	public void notifyObserver()
	{
		List<Category> oldListCategory = (List<Category>)feedManager.getOldListCategory();
		setChanged();
		
		//Mettre a jour la liste de cat�gorie
		notifyObservers(oldListCategory);	
	}
    
    public static Document obtainDocument(String feedurl) throws MalformedURLException {
        Document doc = null;
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            URL url = new URL(feedurl);
            doc = builder.parse(url.openStream());
        } catch (ParserConfigurationException ex) {
        	Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
	    } catch (IOException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
	    } catch (SAXException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    return doc;
    }

	public DatabaseManager getDbManager() {
		return dbManager;
	}

	public FeedManager getFeedManager() {
		return feedManager;
	}

	
	
	
	
}
