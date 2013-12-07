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

import org.xhtmlrenderer.simple.FSScrollPane;

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
	//private JPanel contentPanel;
	private ContentPanel contentPanel;
	private ArrayList<Category> categoryList;

	/**
	 * Constructeur
	 * 
	 */
	public MainPanel()
	{
		// TODO TEMPORAIRE ! A SUPPRIMER BIENTOT
		this.setLayout(new BorderLayout(1, 2));
		categoryList = new ArrayList<Category>();

		this.setLayout(new BorderLayout());
		this.toolbar = new Toolbar();
		this.add(toolbar, BorderLayout.PAGE_START);

		// Création des éléments centraux de la fenetre
		this.feedTreePicker = new TreePicker(categoryList, false);
		
		this.innerSplitPane = new JSplitPane();
		this.newsList = new ActuList();
		//this.newsList = new ActuList(f3List);
		this.mainSplitPane = new JSplitPane();
		this.contentPanel = new ContentPanel();

		// Dimensions des deux listes de gauche
		feedTreePicker.setMinimumSize(new Dimension(80,50));
		//feedTreePicker.setPreferredSize(new Dimension(100,50));
		newsList.setMinimumSize(new Dimension(150,50));
		//newsList.setPreferredSize(new Dimension(180,50));

		//On ajoute le panel (scrollable) des flux à gauche
		innerSplitPane.setLeftComponent(new JScrollPane(feedTreePicker));
		innerSplitPane.getLeftComponent().setMinimumSize(new Dimension(150, 0));
		//On ajoute le panel (scrollable) des news au centre
		innerSplitPane.setRightComponent(new JScrollPane(newsList));
		innerSplitPane.getRightComponent().setMinimumSize(new Dimension(150, 0));
		//On ajoute le panel contenant feedTree et newsList à gauche
		mainSplitPane.setLeftComponent(innerSplitPane);
		//On ajoute le panel du contenu de la news à droite
		FSScrollPane fsScrollPane = new FSScrollPane(contentPanel);
		fsScrollPane.setHorizontalScrollBarPolicy(FSScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		fsScrollPane.setVerticalScrollBarPolicy(FSScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		mainSplitPane.setRightComponent(fsScrollPane);
		this.add(mainSplitPane, BorderLayout.CENTER);		

		/*TODO A mettre dans le controleur*/

				
	}

	public void addListener(ActionListener e){
		toolbar.addListener(e);		

	}

	public void update(ArrayList<Category> categoryList)
	{
		//this.categoryList.clear();
		//System.out.println("COUCOU SUCE MA BITE");
		//System.out.println("COUCOU"+categoryList.toString());
		this.setCategoryList(categoryList);
		//System.out.println("Ca devrait marcher connard"+this.categoryList);
	
		this.revalidate();
		//this.repaint();
		this.feedTreePicker.refreshFeeds(this.categoryList);
		
		
	}
	
	public TreePicker getFeedTreePicker() {
		return feedTreePicker;
	}

	public ContentPanel getContentPanel() {
		return contentPanel;
	}

	public void setContentPanel(ContentPanel contentPanel) {
		this.contentPanel = contentPanel;
	}


	public ArrayList<Category> getCategoryList() {return categoryList;}
	public void setCategoryList(ArrayList<Category> categoryList) {
		this.categoryList = categoryList;
		//System.out.println("\n\n\n\n TEST TEST \n \n "+this.categoryList);
	}

	public ActuList getNewsList() {return newsList;}

	public Toolbar getToolbar() {return toolbar;}	
	



}
