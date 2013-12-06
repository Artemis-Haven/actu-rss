package ca.usherbrooke.ift232.actuRSS.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import ca.usherbrooke.ift232.actuRSS.Category;
import ca.usherbrooke.ift232.actuRSS.Feed;
import ca.usherbrooke.ift232.actuRSS.News;
import ca.usherbrooke.ift232.actuRSS.model.FeedManager;
import ca.usherbrooke.ift232.actuRSS.model.Model;
import ca.usherbrooke.ift232.actuRSS.properties.DialogAddFeed;
import ca.usherbrooke.ift232.actuRSS.properties.DialogEditFeed;
import ca.usherbrooke.ift232.actuRSS.properties.DialogFeedManager;
import ca.usherbrooke.ift232.actuRSS.properties.ProgramProperties;
import ca.usherbrooke.ift232.actuRSS.properties.ViewChangeProperties;
import ca.usherbrooke.ift232.actuRSS.view.MainPanel;
import ca.usherbrooke.ift232.actuRSS.view.Toolbar;
import ca.usherbrooke.ift232.actuRSS.view.View;
import ca.usherbrooke.ift232.actuRSS.view.actulist.ActuList;
import ca.usherbrooke.ift232.actuRSS.view.actulist.ActuSelectedEvent;
import ca.usherbrooke.ift232.actuRSS.view.actulist.ActuSelectedListener;
import ca.usherbrooke.ift232.actuRSS.view.treepicker.FeedSelectedEvent;
import ca.usherbrooke.ift232.actuRSS.view.treepicker.FeedSelectedListener;
import ca.usherbrooke.ift232.actuRSS.view.treepicker.TreePicker;

public class Controller implements ActionListener{

	private Model model;
	private View view;	
	private MainPanel mainPanel;
	private Toolbar toolbar;
	private TreePicker feedTreePicker;
	private ActuList newsList;
	private FeedManager feedManager;
	private ViewChangeProperties pref;
	private DialogFeedManager gest;
	private DialogAddFeed addFeed;
	private DialogEditFeed editFeed;
	public static ProgramProperties properties = ProgramProperties.getInstance();
	public static String defaultDisplay = properties.getProperty("Default Display");
	public String theDisplay;
	List<News> news = new ArrayList<News>();

	public Controller(Model model, View view) {		
		this.model = model;
		this.view = view;
		this.mainPanel = view.getMainPanel();
		this.toolbar = mainPanel.getToolbar();
		this.pref = view.getPreference();
		this.gest = view.getSourceManager();
		this.addFeed = view.getAddFeedDialog();
		this.editFeed = view.getEditFeedDialog();
		this.feedManager = model.getFeedManager();
		feedTreePicker = mainPanel.getFeedTreePicker();
		newsList = mainPanel.getNewsList();
		theDisplay = defaultDisplay;	
		
		
		feedManager.setOldListCategory((ArrayList<Category>) mainPanel.getCategoryList());
		
		ArrayList<Category> categoryListTest =  new ArrayList<Category>();
		
		
		News n1 = new News("Sosh : pas de hausse de la TVA ? [iGen]", "http://feed.macg.co/c/302/f/435189/s/34620909/sc/23/l/0L0Sigen0Bfr0Crumeurs0Csosh0Epas0Ede0Ehausse0Ede0Ela0Etva0E10A9158/story01.htm", "Christophe Laporte", new GregorianCalendar(), "Aux dernières nouvelles, Sosh ne répercuterait pas sur ses prix la hausse de la TVA qui doit intervenir le 1er janvier 2014. Il s’agit pour le moment d’un bruit de couloir, qui pourrait être confirmé dans la semaine. ", false, false);
		News n2 = new News("Apple Store : des financements pour les entreprises", "http://feed.macg.co/c/302/f/435189/s/34628705/sc/23/l/0L0Smacg0Bco0C0Cmac0C20A130C120Capple0Estore0Edes0Efinancements0Epour0Eles0Eentreprises0E78495/story01.htm", "Christophe Laporte", new GregorianCalendar(), "Jusqu’à la fin de l’année, Apple propose deux formules pour permettre aux professionnels de louer ou acheter ses solutions à des conditions intéressantes.", false, false);
		
		News n3 = new News("Une alternative à Time Machine", "http://www.macbidouille.com/news/2013/12/03/une-alternative-a-time-machine", "lionel@macbidouille.com (Lionel)", new GregorianCalendar(), "Time Machine a eu l'indéniable intérêt de sensibiliser le plus grand nombre à la sauvegarde et inciter l'essentiel des utilisateurs de Mac à s'y mettre enfin. Toutefois, si le système d'Apple est très simple d'utilisation, il manque de souplesse pour une utilisation plus professionnelle des sauvegardes. Un logiciel, Qrecall, va bien plus loin que ce que propose Apple (Merci Roderich).", false, false);
		News n4 = new News("Un nouveau rachat de startup par Apple", "http://www.macbidouille.com/news/2013/12/03/un-nouveau-rachat-de-startup-par-apple", "lionel@macbidouille.com (Lionel)", new GregorianCalendar(), "Apple a confirmé avoir racheté la société Topsy Labs Inc. spécialisée dans l'analyse des échanges réalisés sur les réseaux sociaux comme Twitter ou Google+. On ignore ce qu'Apple compte faire de ce rachat qui dépasserait les 200 millions d'euros sachant que la société a jusqu'à maintenant échoué à mettre en place le moindre système social entre MacUsers si l'on met de côté le très spécifique iMessage.", false, false);
		
		News n5 = new News("Retrouver le mot de passe perdu d’un disque dur Seagate", "http://korben.info/retrouver-le-mot-de-passe-perdu-dun-disque-dur-seagate.html", "Korben", new GregorianCalendar(), "Sur les disques durs Seagate, il est possible de mettre un mot de passe au niveau du firmware du disque afin de protéger l'accès. Mais le plus souvent, les gens l'oublient et malheureusement, Seagate ne souhaite pas toujours aider ceux qui sont en galère parce qu'ils ne peuvent prouver qu'ils sont bien les propriétaires du disque.", false, false);
		News n6 = new News("OS X Mavericks : Boostez vos configurations pour en tirer le meilleur !", "http://korben.info/os-x-mavericks-boostez-vos-configurations-pour-en-tirer-le-meilleur.html", "Korben", new GregorianCalendar(), "Cet article fait partie d'une série sponsorisée sur OSX Mavericks et a été co-écrit avec Macway. Vous êtes éligible à la mise à jour vers OS X Mavericks et avez sauté le pas? Tant qu’à faire le changement, autant le faire de la meilleure des façons en redonnant un coup de « boost » à votre Mac ainsi qu’en l’accompagnant d’accessoires malins et pratiques. MacWay vous propose de découvrir des conseils et explications produits vous aidant à exploiter davantage votre ordinateur", false, false);

		News n7 = new News("Dark Vador aussi aime se prendre en photo, la preuve", "http://www.presse-citron.net/dark-vador-aussi-aime-se-prendre-en-photo-la-preuve", "Axel Cereloz", new GregorianCalendar(), "Dark Vador a aussi ses petits élans de narcissisme, en témoigne cette image de 'selfie' publié sur le nouveau compte Instagram de Star Wars.", false, false);
		News n8 = new News("[billet sponsorisé] Auchan Drive pour Android, faire ses courses sans courir", "http://www.presse-citron.net/billet-sponsorise-auchan-drive-pour-android-faire-ses-courses-sans-courir", "Eric", new GregorianCalendar(), "L'application Android Auchan Drive permet de faire ses courses sur son smartphone ou tablette et de retirer ensuite ses marchandises dans un Drive Auchan en quelques minutes.", false, false);
		News n9 = new News("Suce ma bite", "url", "Yann", new GregorianCalendar(), "ENCULER DE GAUTHIER ET VINCENT", false, false);
		
		
		ArrayList<News> macGenerationList = new ArrayList<News>();		
		ArrayList<News> macbidouilleList = new ArrayList<News>();
		ArrayList<News> korbenList = new ArrayList<News>();		
		ArrayList<News> pressecitronList = new ArrayList<News>();
		
		
		Feed macge = new Feed(1, "MacGeneration", "http://www.macg.co", macGenerationList);
		Feed macbidouille = new Feed(1, "MacBidouille", "http://www.macbidouille.com", macbidouilleList);
		Feed korben = new Feed(3, "Korben", "http://www.korben.info", korbenList);
		Feed pressecitron = new Feed(4, "Presse-citron", "http://www.presse-citron.net", pressecitronList);

		//n1.setFeed(macge);
		//macge.getListNews().add(n1);
		//n2.setFeed(macge);
		//macge.getListNews().add(n2);		

		n3.setFeed(macbidouille);
		macbidouille.getListNews().add(n3);
		n4.setFeed(macbidouille);
		macbidouille.getListNews().add(n4);		

		n5.setFeed(korben);
		korben.getListNews().add(n5);
		
		n6.setFeed(korben);
		korben.getListNews().add(n6);		

		n7.setFeed(pressecitron);
		pressecitron.getListNews().add(n7);
		n8.setFeed(pressecitron);
		pressecitron.getListNews().add(n8);	
		
		n9.setFeed(pressecitron);
		pressecitron.getListNews().add(n9);	
		
		
		ArrayList<Feed> webList = new ArrayList<Feed>();
		ArrayList<Feed> macList = new ArrayList<Feed>();
		
		
		Category mac = new Category(1, "Actu Mac", macList);		
		Category web = new Category(2, "Actu Web", webList);
		
		//mac.getListFeed().add(macge);		
		mac.getListFeed().add(macbidouille);		
		web.getListFeed().add(korben);
		web.getListFeed().add(pressecitron);		
		
		categoryListTest.add(mac);
		categoryListTest.add(web);
			
		
		
		//feedManager.setListCategory(categoryListTest);
		
		

		model.addObserver(view);
	    model.notifyObserver();
		view.addListener(this);
		
		
		//---                     Evenement sur la croix du Jdialog	
		
		this.pref.addWindowListener(new WindowAdapter() 
		{
		  public void windowClosing(WindowEvent e)
		  {
		    pref.closeDialog();
		  }
		});
		
		

		//---                     Evenement sur le treePicker

		feedTreePicker.addFeedSelectedListener(new FeedSelectedListener()
		{

			@Override
			public void onFeedSelected(FeedSelectedEvent event) 
			{		

				news.clear();					
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

				toolbar.getFavBtn().setSelected(false);
				toolbar.getReadBtn().setSelected(false);	

				newsList.changeNews(news,theDisplay);
			}	
		});			


		//---                     Evenement sur l'ActuList

		newsList.addActuSelectedListener(new ActuSelectedListener()
		{


			public void onActuSelected(ActuSelectedEvent event) 
			{

				toolbar.getFavBtn().setEnabled(true);
				toolbar.getReadBtn().setEnabled(true);

				/*Regarder si la source est lu, favori*/
				News newsSelected = getSelectedNews();
				if(newsSelected!=null){

					toolbar.getReadBtn().setSelected(false);
					newsSelected.setRead(true);
					if(newsSelected.isFavorite()){toolbar.getFavBtn().setSelected(true);}
					if(newsSelected.isFavorite()==false){toolbar.getFavBtn().setSelected(false);}
					mainPanel.getContentPanel().setContentPanel(newsSelected);
					mainPanel.getContentPanel().display();
			
				}	
			}

		});

	}

	public News getSelectedNews() {return newsList.getSelectedNew();}	




	@Override
	public void actionPerformed(ActionEvent arg0) {		

		String action = arg0.getActionCommand();
		if (action.equals("Tout")) {			
			theDisplay = "All";
			toolbar.getFavBtn().setSelected(false);
			toolbar.getReadBtn().setSelected(false);

			newsList.changeNews(news,theDisplay);

		}

		if (action.equals("Non lus")) {			

			theDisplay = "Not Read";
			toolbar.getFavBtn().setSelected(false);
			toolbar.getReadBtn().setSelected(false);
			newsList.changeNews(news,theDisplay);

		}

		if (action.equals("Lus")) {			

			theDisplay = "Read";			
			toolbar.getFavBtn().setSelected(false);
			toolbar.getReadBtn().setSelected(false);
			newsList.changeNews(news,theDisplay);
		}

		if (action.equals("Favoris")) {	
			
			theDisplay = "Favorite";			
			toolbar.getFavBtn().setSelected(false);
			toolbar.getReadBtn().setSelected(false);
			newsList.changeNews(news,theDisplay);
		}

		if (action.equals("Sync")) {			

			System.out.println("lolilol");	
			/*try {
				//model.synchronize();
				
				//feedManager.getOldListCategory();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			//feedManager.merge();
			model.notifyObserver();
			
			
		}
		if (action.equals("Read")) {				

			News newsSelected = this.getSelectedNews();

			if(newsSelected!=null){
				if(newsSelected.isRead())
				{
					newsSelected.setRead(false);
				}
				else 
				{
					newsSelected.setRead(true);
				}
			}


		}
		if (action.equals("FavBtn")) {			
		

			News newsSelected = this.getSelectedNews();
			if(newsSelected!=null){
				if(newsSelected.isFavorite())
				{
					newsSelected.setFavorite(false);
				}
				else 
				{
					newsSelected.setFavorite(true);
					//System.out.println(newsSelected.getTitle());
				}
			}
		}		

		if (action.equals("Pref")) {
			pref.showDialog();
		}
		if (action.equals("GererSources")) {
			gest.showDialog();
		}
		if (action.equals("Help")) {			
				
		}
		if (action.equals("About")) {			
			
			JDialog Dev = new JDialog();
			JOptionPane.showMessageDialog(Dev,"Developpés par plusieurs moustachus et quelques Zboubs", "Actu-RSS",new Integer(JOptionPane.INFORMATION_MESSAGE).intValue());
		}
		if (action.equals("OkPref")) {
			pref.finishDialog();
		}
		if (action.equals("AnnulerPref")) {			
			pref.closeDialog();	
		}
		if (action.equals("ReinitialiserPref")) {			
			pref.renewDialog();
		}
		if(action.equals("OpenFile")){
			pref.setCSS();
		}
		if (action.equals("AddSource")) {	
			addFeed.listerCategories(feedManager.getOldListCategory());
			addFeed.showDialog();			
		}
		if (action.equals("DeleteSource")) {			
			
		}
		if (action.equals("EditSource")) {
			editFeed.listerCategories(feedManager.getOldListCategory());
			editFeed.showDialog();
		}
		if(action.equals("ExitSource")){
			gest.closeDialog();
		}
		
		if(action.equals("OkAddSource")){
			addFeed.finishedDialog();
			Feed feed = new Feed(-1, addFeed.getName(), addFeed.getUrl());	
			String str = addFeed.getCategory();
			Category cat = feedManager.getCategoryByName(str);
			feedManager.addFeed(feed, cat);
			
			//System.out.println(feedManager.getOldListCategory().toString());
			
			model.notifyObserver();
		}
		if(action.equals("CancelAddSource")){
			addFeed.closeDialog();
		}
		if(action.equals("RenewAddSource")){
			addFeed.renewDialog();
		}
		
		if(action.equals("NewCatAddSource")){
			addFeed.newCategorie();
		}
		if(action.equals("NewCatEditSource")){
			editFeed.newCategorie();
		}
		if(action.equals("OkEditSource")){
			editFeed.finishedDialog();
		}
		if(action.equals("CancelEditSource")){
			editFeed.closeDialog();
		}
		if(action.equals("RenewEditSource")){
			editFeed.renewDialog();
		}
		

	}


}
