package ca.usherbrooke.ift232.actuRSS.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

import ca.usherbrooke.ift232.actuRSS.Feed;
import ca.usherbrooke.ift232.actuRSS.News;
import ca.usherbrooke.ift232.actuRSS.model.Model;
import ca.usherbrooke.ift232.actuRSS.properties.ParamDialog;
import ca.usherbrooke.ift232.actuRSS.properties.ProgramProperties;
import ca.usherbrooke.ift232.actuRSS.properties.VewChangeProperties;
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
	ParamDialog pref;
	public static ProgramProperties properties = ProgramProperties.getInstance();
	public static String afficherBase = properties.getProperty("AfficherBase");
	List<News> news = new ArrayList<News>();

	public Controller(Model model, View view) {		
		this.model = model;
		this.view = view;
		this.mainPanel = view.getMainPanel();
		this.toolbar = mainPanel.getToolbar();
		this.pref = new VewChangeProperties(null, "Préference", true);
		feedTreePicker = mainPanel.getFeedTreePicker();
		newsList = mainPanel.getNewsList();
		
		
		model.addObserver(view);
		model.notifyObserver();
		view.addListener(this);

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
					System.out.println("Vous avez selectionne la source: " + feed );

				}	
				
				toolbar.getFavBtn().setSelected(false);
				toolbar.getReadBtn().setSelected(false);	

				newsList.changeNews(news,afficherBase);
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

					if(newsSelected.isFavorite())
					{
						toolbar.getFavBtn().setSelected(true);

					}
					if(newsSelected.isFavorite()==false)
					{
						toolbar.getFavBtn().setSelected(false);
					}
				}




			}

		});

	}

	public News getSelectedNews() {return newsList.getSelectedNew();}	




	@Override
	public void actionPerformed(ActionEvent arg0) {		

		String action = arg0.getActionCommand();
		System.out.println(action);

		if (action.equals("Tout")) {			
			//List<News> news = new ArrayList<News>();
			afficherBase = "Tous";
			/*for(Feed feed : feedTreePicker.getSelectedFeeds())
			{
				if(feed == null)
					continue;						

				for(News actu : feed.getListNews())
				{
					if(actu == null)
						continue;							
					news.add(actu);
				}					

			}*/
			toolbar.getFavBtn().setSelected(false);
			toolbar.getReadBtn().setSelected(false);

			newsList.changeNews(news,afficherBase);




		}

		if (action.equals("Non lus")) {			

			//List<News> news = new ArrayList<News>();
			afficherBase = "Non Lu";
			/*for(Feed feed : feedTreePicker.getSelectedFeeds())
			{
				if(feed == null)
					continue;						

				for(News actu : feed.getListNews())
				{
					if(actu == null)
						continue;							

					if(actu.isRead()==false)
					{
						news.add(actu);
					}

				}	


			}*/
			toolbar.getFavBtn().setSelected(false);
			toolbar.getReadBtn().setSelected(false);
			newsList.changeNews(news,afficherBase);

		}

		if (action.equals("Lus")) {			

			//List<News> news = new ArrayList<News>();
			afficherBase = "Lu";
			/*for(Feed feed : feedTreePicker.getSelectedFeeds())
			{
				if(feed == null)
					continue;						

				for(News actu : feed.getListNews())
				{
					if(actu == null)
						continue;							

					if(actu.isRead()==true)
					{
						news.add(actu);
					}

				}	


			}*/
			toolbar.getFavBtn().setSelected(false);
			toolbar.getReadBtn().setSelected(false);
			newsList.changeNews(news,afficherBase);


		}




		if (action.equals("Favoris")) {	

			//System.out.println("Favoris");	
			//List<News> news = new ArrayList<News>();	
			afficherBase = "Favoris";
			/*for(Feed feed : feedTreePicker.getSelectedFeeds())
			{
				if(feed == null)
					continue;						

				for(News actu : feed.getListNews())
				{
					if(actu == null)
						continue;							

					if(actu.isFavorite())
					{
						news.add(actu);
					}

				}	

			}*/
			toolbar.getFavBtn().setSelected(false);
			toolbar.getReadBtn().setSelected(false);
			newsList.changeNews(news,afficherBase);
		}

		if (action.equals("Sync")) {			
			//System.out.println("Sync");	
			//TODO ne fonctionne pas model.synchronize();
		}
		if (action.equals("Read")) {			
			//System.out.println("Read");	

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
			//System.out.println("FavBtn");	

			News newsSelected = this.getSelectedNews();
			if(newsSelected!=null){
				if(newsSelected.isFavorite())
				{
					newsSelected.setFavorite(false);
				}
				else 
				{
					newsSelected.setFavorite(true);
					System.out.println(newsSelected.getTitle());
				}
			}
		}		

		if (action.equals("Pref")) {
			pref.showDialog();
		}
		if (action.equals("GererSources")) {			
			//System.out.println("GererSources");		
		}
		if (action.equals("Help")) {			
			//System.out.println("Help");		
		}
		if (action.equals("About")) {			
			//System.out.println("About");
			JDialog Dev = new JDialog();
			JOptionPane.showMessageDialog(Dev,"Developpés par plusieurs moustachus et quelques Zboubs", "Actu-RSS",new Integer(JOptionPane.INFORMATION_MESSAGE).intValue());
		}

	}


}
