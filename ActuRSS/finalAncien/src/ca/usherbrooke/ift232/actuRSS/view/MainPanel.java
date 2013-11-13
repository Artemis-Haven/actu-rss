package ca.usherbrooke.ift232.actuRSS.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Scrollbar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.script.ScriptEngineManager;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import ca.usherbrooke.ift232.actuRSS.model.Category;
import ca.usherbrooke.ift232.actuRSS.model.Feed;
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
 * @author Prenom Nom
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
	 public Toolbar toolbar;
	 //public TreeAndList treeandlist;
	 public JSplitPane mainSplitPane;
	 public JSplitPane innerSplitPane;
	 public TreePicker feedTreePicker;
	 public NewsListPanel newsListPanel;
	 public JPanel contentPanel;
	 
	/**
	 * Constructeur
	 * 
	 */
	 MainPanel()
	 {
		 // TODO TEMPORAIRE ! A SUPPRIMER BIENTOT
		this.setLayout(new BorderLayout(1, 2));
		
		List<Category> categoryList = new ArrayList<Category>();

		List<Feed> sportSources = new ArrayList<Feed>();
		sportSources.add(new Feed("Sport1", "url", null));
		sportSources.add(new Feed("Sport2", "url", null));
		sportSources.add(new Feed("Sport3", "url", null));
		sportSources.add(new Feed("Sport4", "url", null));
		sportSources.add(new Feed("Sport5", "url", null));
		
		Category sportCat = new Category(1, "Sport", sportSources);

		List<Feed> financeSources = new ArrayList<Feed>();
		financeSources.add(new Feed("finance1", "url", null));
		financeSources.add(new Feed("finance2", "url", null));
		financeSources.add(new Feed("finance3", "url", null));
		financeSources.add(new Feed("finance4", "url", null));
		financeSources.add(new Feed("finance5", "url", null));
		
		Category financeCat = new Category(2, "Finance", financeSources);

		categoryList.add(sportCat);
		categoryList.add(financeCat);
		//FIN DE LA ZONE A SUPPRIMER
			
		 this.setLayout(new BorderLayout());
		 this.toolbar = new Toolbar();
		 this.add(toolbar, BorderLayout.PAGE_START);
		 
		 // Création des éléments centraux de la fenetre
		 this.feedTreePicker = new TreePicker(categoryList, false);
		 this.innerSplitPane = new JSplitPane();
		 this.newsListPanel = new NewsListPanel();
		 this.mainSplitPane = new JSplitPane();
		 this.contentPanel = new JPanel();

		 // Dimensions des deux listes de gauche
		 feedTreePicker.setMinimumSize(new Dimension(80,50));
		 feedTreePicker.setPreferredSize(new Dimension(150,50));
		 newsListPanel.setMinimumSize(new Dimension(150,50));
		 newsListPanel.setPreferredSize(new Dimension(180,50));

		 //On ajoute le panel (scrollable) des flux à gauche
		 innerSplitPane.setLeftComponent(new JScrollPane(feedTreePicker));
		 //On ajoute le panel (scrollable) des news au centre
		 innerSplitPane.setRightComponent(new JScrollPane(newsListPanel));
		 //On ajoute le panel contenant feedTree et newsList à gauche
		 mainSplitPane.setLeftComponent(innerSplitPane);
		 //On ajoute le panel du contenu de la news à droite
		 mainSplitPane.setRightComponent(contentPanel);
		 this.add(mainSplitPane, BorderLayout.CENTER);
		 
		 
		 /*TODO A mettre dans le controleur*/
		 feedTreePicker.addFeedSelectedListener(new FeedSelectedListener(){

				@Override
				public void onFeedSelected(FeedSelectedEvent event) {
					
					DefaultListModel<String> listModel = (DefaultListModel<String>) newsListPanel.getModel();					
					listModel.removeAllElements();			
				
					for(int i = 0; i < feedTreePicker.getSelectedFeeds().size();i++){
						listModel.addElement("" + feedTreePicker.getSelectedFeeds().get(i) + "\n testttt");
						System.out.println("Vous avez s�lectionn� la source: " + feedTreePicker.getSelectedFeeds().get(i));
					}
					
				}				
				
			});				 
		 
		 	 
		 /*
		 this.treeandlist = new TreeAndList();
		 this.add(toolbar, BorderLayout.PAGE_START);
		 mainSplitPane = new JSplitPane();
		 mainSplitPane.setLeftComponent(treeandlist);
		 mainSplitPane.setRightComponent(new JPanel());
		 this.add(mainSplitPane);*/
	 }
	
}
