package ca.usherbrooke.ift232.actuRSS.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Scrollbar;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.script.ScriptEngineManager;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import ca.usherbrooke.ift232.actuRSS.Category;
import ca.usherbrooke.ift232.actuRSS.Feed;
import ca.usherbrooke.ift232.actuRSS.News;
import ca.usherbrooke.ift232.actuRSS.view.actulist.ActuList;
import ca.usherbrooke.ift232.actuRSS.view.actulist.ActuSelectedEvent;
import ca.usherbrooke.ift232.actuRSS.view.actulist.ActuSelectedListener;
import ca.usherbrooke.ift232.actuRSS.view.treepicker.FeedSelectedEvent;
import ca.usherbrooke.ift232.actuRSS.view.treepicker.FeedSelectedListener;
import ca.usherbrooke.ift232.actuRSS.view.treepicker.TreePicker;

/**
 * Actu-RSS
 * Couche : View
 * Date de creation : 2013-11-01
 * Description : 
 *   Panel principal de notre application.
 *   Il contient les autres sous-panels.
 *   Il est de tye BorderLayout.
 *   
 * @author Yann Seree & Remi Patrizio
 * @version 0.1
 */

public class MainPanel extends JPanel {

	/**
	 * Variables 
	 * 
	 * MainPanel contient directement le panel mainSplitPane qui est coupé en deux
	 * 
	 * @param mainSplitPane: contient deux parties :
	 * à gauche il y a innerSplitPane et à droite contentPanel
	 * 
	 * @param contentPanel: affiche le contenu d'une news
	 * 
	 * @param innerSplitPane: contient deux parties :
	 * à gauche il y a feedTreeScrollPane et à droite newsListScrollPane
	 * 
	 * @param feedTreeScrollPane: est une zone scrollable qui contient feedTreePanel
	 *
	 * @param newsListScrollPane: est une zone scrollable qui contient newsListPanel
	 * 
	 * @param feedTreePanel: est un panel qui affiche la liste des flux et des catégories
	 * 
	 * @param newsListPanel: est un panel qui affiche la liste des news
	 *
	 */
	private Toolbar toolbar;

	//public TreeAndList treeandlist;
	private JSplitPane mainSplitPane;
	private JSplitPane innerSplitPane;
	private TreePicker feedTreePicker;
	private ActuList newsList;
	private JPanel contentPanel;
	private List<Category> categoryList;

	/**
	 * Constructeur
	 * 
	 */
	public MainPanel()
	{
		// TODO TEMPORAIRE ! A SUPPRIMER BIENTOT
		this.setLayout(new BorderLayout(1, 2));
		categoryList = new ArrayList<Category>();

		List<Feed> sportSources = new ArrayList<Feed>();
		
		

		
		/*sportSources.add(new Feed(-1, "Sport1", "url", null));
		sportSources.add(new Feed(-1, "Sport2", "url", null));
		sportSources.add(new Feed(-1, "Sport3", "url", null));
		sportSources.add(new Feed(-1, "Sport4", "url", null));
		sportSources.add(new Feed(-1, "Sport5", "url", null));

		Category sportCat = new Category(1, "Sport", sportSources);

		List<Feed> financeSources = new ArrayList<Feed>();
		financeSources.add(new Feed(-1, "finance1", "url", null));
		financeSources.add(new Feed(-1, "finance2", "url", null));
		financeSources.add(new Feed(-1, "finance3", "url", null));
		financeSources.add(new Feed(-1, "finance4", "url", null));
		financeSources.add(new Feed(-1, "finance5", "url", null));

		Category financeCat = new Category(2, "Finance", financeSources);

		categoryList.add(sportCat);
		categoryList.add(financeCat);*/
		//FIN DE LA ZONE A SUPPRIMER
		
		
		News n1 = new News("titre1", "url1", "author1", new GregorianCalendar(), "contents1", false, false);
		News n2 = new News("titre2", "url2", "author2", new GregorianCalendar(), "contents2", false, false);
		News n3 = new News("titre3", "url3", "author3", new GregorianCalendar(), "contents3", false, false);
		
		News n4 = new News("titre4", "url4", "author4", new GregorianCalendar(), "contents4", false, false);
		News n5 = new News("titre5", "url5", "author5", new GregorianCalendar(), "contents5", false, false);
		News n6 = new News("titre6", "url6", "author6", new GregorianCalendar(), "contents6", false, false);

		ArrayList<News> f1List = new ArrayList<News>();
		ArrayList<News> f2List = new ArrayList<News>();
		
		ArrayList<News> f3List = new ArrayList<News>();
		f3List.add(n1);
		f3List.add(n2);
		
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
		
		categoryList.add(cat);
		

		this.setLayout(new BorderLayout());
		this.toolbar = new Toolbar();
		this.add(toolbar, BorderLayout.PAGE_START);

		// Création des éléments centraux de la fenetre
		this.feedTreePicker = new TreePicker(categoryList, false);
		this.innerSplitPane = new JSplitPane();
		this.newsList = new ActuList();
		//this.newsList = new ActuList(f3List);
		this.mainSplitPane = new JSplitPane();
		this.contentPanel = new JPanel();

		// Dimensions des deux listes de gauche
		feedTreePicker.setMinimumSize(new Dimension(80,50));
		feedTreePicker.setPreferredSize(new Dimension(100,50));
		newsList.setMinimumSize(new Dimension(150,50));
		//newsList.setPreferredSize(new Dimension(180,50));

		//On ajoute le panel (scrollable) des flux à gauche
		innerSplitPane.setLeftComponent(new JScrollPane(feedTreePicker));
		//On ajoute le panel (scrollable) des news au centre
		innerSplitPane.setRightComponent(new JScrollPane(newsList));
		//On ajoute le panel contenant feedTree et newsList à gauche
		mainSplitPane.setLeftComponent(innerSplitPane);
		//On ajoute le panel du contenu de la news à droite
		mainSplitPane.setRightComponent(contentPanel);
		this.add(mainSplitPane, BorderLayout.CENTER);		

		/*TODO A mettre dans le controleur*/

				
	}
	
	
	// ---                                            Methode addListener

	

	public void addListener(ActionListener e){
		toolbar.addListener(e);		

	}

	public void update(List<Category> categoryList)
	{
		this.setCategoryList(categoryList);	
	}
	
	public TreePicker getFeedTreePicker() {
		return feedTreePicker;
	}

	public List<Category> getCategoryList() {return categoryList;}
	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}

	public ActuList getNewsList() {return newsList;}

	public Toolbar getToolbar() {return toolbar;}	
	



}
