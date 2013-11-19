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

import ca.usherbrooke.ift232.actuRSS.Category;
import ca.usherbrooke.ift232.actuRSS.Feed;
import ca.usherbrooke.ift232.actuRSS.News;
import ca.usherbrooke.ift232.actuRSS.view.actulist.ActuList;
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
	 MainPanel()
	 {
		 // TODO TEMPORAIRE ! A SUPPRIMER BIENTOT
		this.setLayout(new BorderLayout(1, 2));
	    categoryList = new ArrayList<Category>();

		List<Feed> sportSources = new ArrayList<Feed>();
		sportSources.add(new Feed(-1, "Sport1", "url", null));
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
		categoryList.add(financeCat);
		//FIN DE LA ZONE A SUPPRIMER
			
		 this.setLayout(new BorderLayout());
		 this.toolbar = new Toolbar();
		 this.add(toolbar, BorderLayout.PAGE_START);
		 
		 // Création des éléments centraux de la fenetre
		 this.feedTreePicker = new TreePicker(categoryList, false);
		 this.innerSplitPane = new JSplitPane();
		 this.newsList = new ActuList();
		 this.mainSplitPane = new JSplitPane();
		 this.contentPanel = new JPanel();

		 // Dimensions des deux listes de gauche
		 feedTreePicker.setMinimumSize(new Dimension(80,50));
		 feedTreePicker.setPreferredSize(new Dimension(150,50));
		 newsList.setMinimumSize(new Dimension(150,50));
		 newsList.setPreferredSize(new Dimension(180,50));

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
		 feedTreePicker.addFeedSelectedListener(new FeedSelectedListener(){

				@Override
				public void onFeedSelected(FeedSelectedEvent event) {
					
					
					List<News> news = new ArrayList<News>();
					
					for(Feed feed : feedTreePicker.getSelectedFeeds())
					{
						if(feed == null)
							continue;
						
						for(News actu : feed.getListNews())
						{
							if(actu == null)
								continue;
							
							news.add(actu);
						}
							
					}
						
					
					newsList.changeNews(news);
				
					
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
	 public List<Category> getCategoryList() {return categoryList;}

		public void setCategoryList(List<Category> categoryList) {
			this.categoryList = categoryList;
		}
	
}
