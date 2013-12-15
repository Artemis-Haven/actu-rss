package ca.usherbrooke.ift232.actuRSS.modelTest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;

import junit.framework.TestCase;

import org.junit.Test;

import ca.usherbrooke.ift232.actuRSS.Category;
import ca.usherbrooke.ift232.actuRSS.Feed;
import ca.usherbrooke.ift232.actuRSS.News;
import ca.usherbrooke.ift232.actuRSS.model.FeedManager;
import ca.usherbrooke.ift232.actuRSS.model.Model;
import ca.usherbrooke.ift232.actuRSS.model.RssParser;
import ca.usherbrooke.ift232.actuRSS.model.WrongURLException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ModelTest extends TestCase implements Observer {

	final String URL_TEST1 = "http://feeds2.feedburner.com/Pressecitron";
	
	// Liste de catégorie utilisée par le test de notifyObserver
	private ArrayList<Category> notifyObserverCategoryList;
	
	@Test
	public void testConstructor() {
		Model model = new Model();
		assertNotNull(model);
	}

	@Test
	public void testGetters() {
		Model model = new Model();
		assertNotNull(model.getDbManager());
		assertNotNull(model.getFeedManager());
		//model.getDbManager().
	}

	@Test
	public void testLoadAllFromDB() {
		Model model = new Model();
		model.getDbManager().clearDB();
		
		//Créer des categories, flux et news
		ArrayList<Category> categoryList = buildCategoryList();
		
		//insérer les objets dans le DBManager
		model.getDbManager().insertObjetToDB(categoryList);
		
		//Tout récupérer de la BdD
		model.loadAllFromDB();
		
		// récupérer le contenu de oldListCategory et vérifier la concordance
		ArrayList<Category> oldListCategory = FeedManager.getInstance().getOldListCategory();
		assertNotNull(FeedManager.getInstance().getOldListCategory());
		assertEquals(categoryList.size(), oldListCategory.size());
		assertEquals(3, oldListCategory.get(1).getListFeed().size());
		assertEquals(2, oldListCategory.get(1).getListFeed().get(1).getListNews().size());

	}

	@Test
	public void testSendAllToDB() {
		Model model = new Model();
		model.getDbManager().clearDB();
		
		//Créer des categories, flux et news
		ArrayList<Category> categoryList = buildCategoryList();
		
		model.getFeedManager().setOldListCategory(categoryList);
		
		//Tout envoyer dans la BdD
		model.sendAllToDB();
		
		ArrayList<Category> categoryListInDb;
		
		try {
			// récupérer le contenu de la BdD et vérifier la concordance
			categoryListInDb = model.getDbManager().getAllCategories();
			assertNotNull(categoryListInDb);
			assertEquals(3, categoryListInDb.size());
			assertEquals(3, categoryListInDb.get(1).getListFeed().size());
			assertEquals(2, categoryListInDb.get(1).getListFeed().get(1).getListNews().size());
		} catch (SQLException e) {
			fail("Impossible de récupérer les catégories de la BdD");
		}
	}

	private ArrayList<Category> buildCategoryList() {
		ArrayList<Category> categoryList = new ArrayList<Category>();
		Category category1 = new Category(1, "France");
		Category category2 = new Category(2, "Sport");
		Category category3 = new Category(3, "Culture");
		categoryList.add(category1);
		categoryList.add(category2);
		categoryList.add(category3);
		
		Feed feed1 = new Feed(1, "Le Monde", "www.lemonde.fr/rss/une/xml");
		Feed feed2 = new Feed(2, "Eurosport", "http://www.eurosport.fr/rss.xml");
		Feed feed3 = new Feed(3, "L'equipe", "http://www.lequipe.fr/rss/actu_rss.xml");
		category2.getListFeed().add(feed1);
		category2.getListFeed().add(feed2);
		category2.getListFeed().add(feed3);
		
		News news1 = new News("nimportequoi", "http://www.eurosport.fr/football/ligue-1/2013-2014/l1-le-top-10-des-idees-recues.-battues-en-breche_sto4004002/story.shtml", "Ta mere", new GregorianCalendar() ,"Championnat homogene, gardiens infranchissables, Paris et Monaco seuls au monde : la Ligue 1 charrie des cliches qui ne correspondent pas a sa realite. Notre top 10.", true, true  );
		News news2 = new News("autrenews", "http://www.eurosport.fr/football/ligue-1/2013-2014/l1-le-top-10-des-idees-recues.-battues-en-breche_sto4004000/story.shtml", "Ta m�re", new GregorianCalendar() ,"Championnat homog�ne, gardiens infranchissables, Paris et Monaco seuls au monde : la Ligue 1 charrie des clich�s qui ne correspondent pas � sa r�alit�. Notre top 10.", false, false  );
		feed2.getListNews().add(news1);
		feed2.getListNews().add(news2);
		
		return categoryList;
	}

	@Test
	public void testNotifyObserver() {
		//initialisation : toutes les listes vides
		Model model = new Model();
		notifyObserverCategoryList = new ArrayList<Category>();
		model.getFeedManager().getOldListCategory().clear();
		
		//on remplit le feedManager avec des categories
		ArrayList<Category> categoryList = buildCategoryList();
		
		// On notifie les observers : cette classe de test est
		// aussi un observer
		model.notifyObservers();
		
		assertEquals(3, notifyObserverCategoryList.size());
	}

	@Test
	public void testObtainDocument() {
		Element element = null;
		String str = "";
		Feed feed = new Feed();
		Document feedDoc = null;

		String feedurl = URL_TEST1;
		
		try {
		assertTrue(Model.obtainDocument(feedurl) instanceof Document);
		feedDoc = Model.obtainDocument(feedurl);
		} catch (WrongURLException ex) {
			ex.getMessage();
		}

		NodeList nodes = feedDoc.getElementsByTagName("item");
		for (int i = 0; i < nodes.getLength(); i++) {
			str = feed.getTitle();
			element = (Element) nodes.item(i);
			assertTrue(RssParser.readNode(element, "title") != str);
			str = RssParser.readNode(element, "title");
		}
	}

	//Observe le modele dans le test de notifyObserver
	@Override
	public void update(Observable arg0, Object arg1) {
		notifyObserverCategoryList = (ArrayList<Category>)arg1;
		System.out.println("UPDATE");
	}

}
