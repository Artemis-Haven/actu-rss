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

		//List<Feed> sportSources = new ArrayList<Feed>();
		

		String title = "Microsoft se paye l'iPad Air dans deux nouvelles vidéos [iGen]";
		//TODO//String date = "26 Nov 2013 à 23:36";
		String feed = "MacGeneration";
		String author = "Florian Innocente";
		String content = "<p><strong>À l'approche des fêtes de Noël, Microsoft remet le couvert dans ses comparatifs entre sa tablette et celle d'Apple</strong>. Cette fois ce sont les Surface 2 et iPad Air qui sont opposés, d'abord <a href=\"http://www.microsoft.com/surface/en-us/products/compare\">au fil d'une page</a> avec des arguments connus (port USB, présence de Flash et d'Office, etc). </p><br /> <br /> <figure><img src=\"http://img.staticigen.com/2013/11/macgpic-1385508685-3912987173708-sc-op.jpg\" class=\"centre\" alt=\"\" /><figcaption></figcaption></figure><br /> <br /> <p>Ensuite, la compétition se poursuit au travers de deux nouvelles vidéos. La première met l'accent sur le support rétractable intégré à la Surface qui permet de la tenir face à l'utilisateur. Voilà la Surface devenue un parfait compagnon pour la cuisine (c'est très sérieux) puisqu'il sera possible de lire plus facilement ses recettes et même de balayer les pages d'un geste de la main sans avoir à toucher l'écran de ses doigts sales.</p><br /> <br /> <p><iframe width=\"480\" height=\"270\" src=\"http://www.youtube.com/embed/XsknmA96Jv0\" frameborder=\"0\"></iframe></p><br /> <br /> <p>Quand on pense qu'Apple dans son dernier clip montre des utilisateurs d'iPad au sommet d'une éolienne, au milieu des vignes, en pleine intervention de pompiers ou sous les fonds marins, c'est vraiment faire bien peu de cas du <i>vrai</i> quotidien des utilisateurs de tablettes.</p><br /> <br /> <p><iframe width=\"480\" height=\"270\" src=\"http://www.youtube.com/embed/B8Le9wvoY00?list=PLHFlHpPjgk713fMv5O4s4Fv7k6yTkXwkV\" frameborder=\"0\"  ></iframe></p><br /> <br /> <p>La seconde vidéo en revanche insiste à nouveau sur une lacune de plus en plus criante de l'iPad et plus particulièrement de son iOS : l'absence de sessions utilisateurs. Pour distinguer par exemple le contenu applicatif et les réglages des parents de ceux de leurs enfants. Là-dessus on peut difficilement donner tort à Microsoft. </p><br /> <br /> <p><iframe width=\"480\" height=\"270\" src=\"http://www.youtube.com/embed/knSd7-i9u1c\" frameborder=\"0\"  ></iframe></p><br /><br /><a href=\"http://www.igen.fr/ipad/microsoft-se-paye-l-ipad-air-dans-deux-nouvelles-videos-109067#commentaires\">Voir les commentaires et réagir</a><br /><br /> <p style=\"font-size: 78%;\">2013 - <a href=\"http://www.igen.fr/ipad/microsoft-se-paye-l-ipad-air-dans-deux-nouvelles-videos-109067\"><strong>\"Microsoft se paye l'iPad Air dans deux nouvelles vidéos\"</strong></a> publié sur <a href=\"http://www.igen.fr\">iGeneration</a> par <strong>Florian Innocente</strong>.</p><img width='1' height='1' src='http://feed.macg.co/c/302/f/435189/s/3422e188/sc/23/mf.gif' border='0'/><br clear='all'/><div class='mf-viral'><table border='0'><tr><td valign='middle'><a href=\"http://share.feedsportal.com/share/twitter/?u=http%3A%2F%2Fwww.igen.fr%2Fipad%2Fmicrosoft-se-paye-l-ipad-air-dans-deux-nouvelles-videos-109067&amp;t=Microsoft+se+paye+l%27iPad+Air+dans+deux+nouvelles+vid%C3%A9os+%5BiGen%5D\" target=\"_blank\"><img src=\"http://res3.feedsportal.com/social/twitter.png\" border=\"0\" /></a> <a href=\"http://share.feedsportal.com/share/facebook/?u=http%3A%2F%2Fwww.igen.fr%2Fipad%2Fmicrosoft-se-paye-l-ipad-air-dans-deux-nouvelles-videos-109067&amp;t=Microsoft+se+paye+l%27iPad+Air+dans+deux+nouvelles+vid%C3%A9os+%5BiGen%5D\" target=\"_blank\"><img src=\"http://res3.feedsportal.com/social/facebook.png\" border=\"0\" /></a> <a href=\"http://share.feedsportal.com/share/linkedin/?u=http%3A%2F%2Fwww.igen.fr%2Fipad%2Fmicrosoft-se-paye-l-ipad-air-dans-deux-nouvelles-videos-109067&amp;t=Microsoft+se+paye+l%27iPad+Air+dans+deux+nouvelles+vid%C3%A9os+%5BiGen%5D\" target=\"_blank\"><img src=\"http://res3.feedsportal.com/social/linkedin.png\" border=\"0\" /></a> <a href=\"http://share.feedsportal.com/share/gplus/?u=http%3A%2F%2Fwww.igen.fr%2Fipad%2Fmicrosoft-se-paye-l-ipad-air-dans-deux-nouvelles-videos-109067&amp;t=Microsoft+se+paye+l%27iPad+Air+dans+deux+nouvelles+vid%C3%A9os+%5BiGen%5D\" target=\"_blank\"><img src=\"http://res3.feedsportal.com/social/googleplus.png\" border=\"0\" /></a> <a href=\"http://share.feedsportal.com/share/email/?u=http%3A%2F%2Fwww.igen.fr%2Fipad%2Fmicrosoft-se-paye-l-ipad-air-dans-deux-nouvelles-videos-109067&amp;t=Microsoft+se+paye+l%27iPad+Air+dans+deux+nouvelles+vid%C3%A9os+%5BiGen%5D\" target=\"_blank\"><img src=\"http://res3.feedsportal.com/social/email.png\" border=\"0\" /></a></td><td valign='middle'></td></tr></table></div><br/><br/><a href=\"http://da.feedsportal.com/r/180265059809/u/192/f/435189/c/302/s/3422e188/sc/23/rc/1/rc.htm\"><img src=\"http://da.feedsportal.com/r/180265059809/u/192/f/435189/c/302/s/3422e188/sc/23/rc/1/rc.img\" border=\"0\"/></a><br/><a href=\"http://da.feedsportal.com/r/180265059809/u/192/f/435189/c/302/s/3422e188/sc/23/rc/2/rc.htm\"><img src=\"http://da.feedsportal.com/r/180265059809/u/192/f/435189/c/302/s/3422e188/sc/23/rc/2/rc.img\" border=\"0\"/></a><br/><a href=\"http://da.feedsportal.com/r/180265059809/u/192/f/435189/c/302/s/3422e188/sc/23/rc/3/rc.htm\"><img src=\"http://da.feedsportal.com/r/180265059809/u/192/f/435189/c/302/s/3422e188/sc/23/rc/3/rc.img\" border=\"0\"/></a><br/><br/><a href=\"http://da.feedsportal.com/r/180265059809/u/192/f/435189/c/302/s/3422e188/a2.htm\"><img src=\"http://da.feedsportal.com/r/180265059809/u/192/f/435189/c/302/s/3422e188/a2.img\" border=\"0\"/></a><img width=\"1\" height=\"1\" src=\"http://pi.feedsportal.com/r/180265059809/u/192/f/435189/c/302/s/3422e188/a2t.img\" border=\"0\"/>";
		GregorianCalendar date = new GregorianCalendar(2013,10,26,23,36);
		
		
		News mac = new News(title,"url1",author,date,content,false,false);
		
		
		News n1 = new News("titre1", "url1", "author1", new GregorianCalendar(), "contents1", false, false);
		News n2 = new News("titre2", "url2", "author2", new GregorianCalendar(), "contents2", false, false);
		News n3 = new News("titre3", "url3", "author3", new GregorianCalendar(), "contents3", false, false);
		
		News n4 = new News("titre4", "url4", "author4", new GregorianCalendar(), "contents4", false, false);
		News n5 = new News("titre5", "url5", "author5", new GregorianCalendar(), "contents5", false, false);
		News n6 = new News("titre6", "url6", "author6", new GregorianCalendar(), "contents6", false, false);

		ArrayList<News> macGenerationList = new ArrayList<News>();		
		ArrayList<News> f1List = new ArrayList<News>();
		ArrayList<News> f2List = new ArrayList<News>();		
		ArrayList<News> f3List = new ArrayList<News>();
		
		f3List.add(n1);
		f3List.add(n2);
		
		Feed macGeneration = new Feed(1,feed,"url",macGenerationList);
		Feed f1 = new Feed(1, "title feed 1", "url", f1List);
		Feed f2 = new Feed(2, "title feed 2", "url", f2List);

		mac.setFeed(macGeneration);
		macGeneration.getListNews().add(mac);
		n1.setFeed(f1);
		f1.getListNews().add(n1);
		n2.setFeed(f1);
		f1.getListNews().add(n2);
		n3.setFeed(f1);
		f1.getListNews().add(n3);
		
		n4.setFeed(f2);
		f2.getListNews().add(n4);
		n5.setFeed(f2);
		f2.getListNews().add(n5);
		n6.setFeed(f2);
		f2.getListNews().add(n6);		
		
		ArrayList<Feed> feedList = new ArrayList<Feed>();
		ArrayList<Feed> informatiqueList = new ArrayList<Feed>();
		
		
		Category informatique = new Category(1, "informatique", informatiqueList);		
		Category cat = new Category(2, "nom de la categorie", feedList);
		
		informatique.getListFeed().add(macGeneration);		
		cat.getListFeed().add(f1);
		cat.getListFeed().add(f2);		
		
		categoryList.add(informatique);
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
		this.contentPanel = new ContentPanel();

		// Dimensions des deux listes de gauche
		feedTreePicker.setMinimumSize(new Dimension(80,50));
		//feedTreePicker.setPreferredSize(new Dimension(100,50));
		newsList.setMinimumSize(new Dimension(150,50));
		//newsList.setPreferredSize(new Dimension(180,50));

		//On ajoute le panel (scrollable) des flux à gauche
		innerSplitPane.setLeftComponent(new JScrollPane(feedTreePicker));
		//On ajoute le panel (scrollable) des news au centre
		innerSplitPane.setRightComponent(new JScrollPane(newsList));
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




	public ContentPanel getContentPanel() {
		return contentPanel;
	}


	public void setContentPanel(ContentPanel contentPanel) {
		this.contentPanel = contentPanel;
	}


	public List<Category> getCategoryList() {return categoryList;}
	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}

	public ActuList getNewsList() {return newsList;}

	public Toolbar getToolbar() {return toolbar;}	
	



}
