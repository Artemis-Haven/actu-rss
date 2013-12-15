package ca.usherbrooke.ift232.actuRSS.model;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import ca.usherbrooke.ift232.actuRSS.Category;
import ca.usherbrooke.ift232.actuRSS.Feed;
import ca.usherbrooke.ift232.actuRSS.bdd.Database;
import ca.usherbrooke.ift232.actuRSS.bdd.DatabaseManager;

/**
 * C'est la classe principale du Model, comme son nom l'indique.
 * Elle fait le lien entre la base de données, le FeedManager
 * et le RssParser.
 * 
 * @author Rémi Patrizio, David Boas, Yann Seree
 *
 */
public class Model extends Observable{

	/**
	 * Permet de manipuler la base de données
	 */
	DatabaseManager dbManager;
	
	/**
	 * Contient toutes les données manipulables :
	 * News, Feed et Category
	 */
	FeedManager feedManager;
	
	/**
	 * Permet de transformer les fichiers RSS en objets Feed
	 */
	RssParser parser;
	
	/**
	 * Constructeur du Model
	 */
	public Model() {
		dbManager = new DatabaseManager(new Database("acturss.db"));
		//feedManager = new FeedManager();
		feedManager = FeedManager.getInstance();
		parser = new RssParser();	
	}
	
	/**
	 * Appelle la BdD et récupère tout son contenu, pour le mettre dans
	 * oldListCategory du FeedManager.
	 */
	public void loadAllFromDB() {
		try {
			feedManager.setOldListCategory(dbManager.getAllCategories());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Impossible d'accéder à la BD.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Envoie tout le contenu de la liste oldFeedCategory du FeedManager
	 * vers la BdD. On efface tout le contenu des tables avant de 
	 * renvoyer la liste complete des News.
	 */
	public void sendAllToDB() {
		//vider la BdD avec clearDB
		dbManager.clearDB();
		dbManager.insertObjetToDB(feedManager.getOldListCategory());
	}
	
	/**
	 * C'est une des méthodes centrales de l'application :
	 * - Elle commence par vérifier si la liste des news de FeedManager
	 * est vide. Si c'est le cas, elle fait appel à la BdD pour
	 * remplir la liste.
	 * - Pour chaque Feed dans chaque Category, on récupère son url 
	 * et on essaie de récupérer le fichier associé, sur internet.
	 * - Une fois fait, si cela a marché, on envoie le fichier au
	 * RssParser pour qu'il construise un nouveau Feed.
	 * - On construit ainsi une nouvelle liste de Category, de Feed et
	 * de News contenant exactement les mêmes Category et Feed, mais pas
	 * les mêmes news.
	 * - On appelle enfin la méthode merge() du FeedManager pour fusionner
	 * l'ancienne et la nouvelle liste.
	 * - Pour finir, on notifie la vue de la mise à jour de la liste.
	 * 
	 * @throws Exception
	 */
	public synchronized void  synchronize() {
        
        Document docFeed = null;
        //Si la liste des catÃ©gories est vide, on la charge depuis la BD
        if(feedManager.getOldListCategory().isEmpty()) {
                loadAllFromDB();
        }
        
        try {
        	this.gotInternetConnection();
        } catch (IOException ex) {
        	JDialog Dev = new JDialog();
			JOptionPane.showMessageDialog(Dev, "La connection Internet n'a pas pu être établie", "Help",
					new Integer(JOptionPane.INFORMATION_MESSAGE).intValue());
			return;
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
                	} catch (WrongURLException ex) {
                		JDialog Dev = new JDialog();
            			JOptionPane.showMessageDialog(Dev, "L'adresse du flux " + feed.getTitle() + " semble être erroné", "Help",
            					new Integer(JOptionPane.INFORMATION_MESSAGE).intValue());
            			return;
                	}
                	
                    Feed newFeed = RssParser.parse(docFeed);
                    newFeed.setNameCategory(category.getName());
                    newCat.getListFeed().add(newFeed);
                }
                newList.add(newCat);
        }
        feedManager.setListCategory(newList);
        //Ici, ListCategory contient la nouvelle liste de flux
        //et oldListCategory contient l'ancienne
       
        //On fusionne les deux listes
        feedManager.merge();
        //TODO supprimer les plus vieilles news, ne conserver que les n plus recentes
        //On fusionne les deux listes
        sendAllToDB();
        //Notifier la vue
        this.notifyObserver();
	}


	/**
	 * Teste la connection à internet.
	 * 
	 * @return true si l'on est connecté à internet, false sinon
	 */
	private void gotInternetConnection() throws IOException {
		URL url = new URL("http://www.google.fr");
		HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
		urlConn.connect();
	}


	/**
	 * notifie la vue de la mise à jour de la liste de Category.
	 * Cette méthode est appelée à la fin de le chaque synchronisation
	 */
	public void notifyObserver()
	{
		List<Category> oldListCategory = (List<Category>)feedManager.getOldListCategory();
		setChanged();
		
		//Mettre a jour la liste de cat�gorie
		notifyObservers(oldListCategory);	
	}
    
    /**
     * @param feedurl l'url du flux que l'on veut récupérer
     * @return le fichier récupéré à l'url demandée
     * @throws MalformedURLException L'url n'est pas valide
     */
    public static Document obtainDocument(String feedurl) throws WrongURLException {
    	Document doc = null;
    	try {	    
		    DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		    URL url = new URL(feedurl);
		    doc = builder.parse(url.openStream());
    	} catch (Exception ex) {
    		throw new WrongURLException("");
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
