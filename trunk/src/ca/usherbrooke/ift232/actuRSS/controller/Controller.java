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
import javax.swing.JToggleButton;

import ca.usherbrooke.ift232.actuRSS.Feed;
import ca.usherbrooke.ift232.actuRSS.News;
import ca.usherbrooke.ift232.actuRSS.model.Model;
import ca.usherbrooke.ift232.actuRSS.properties.ParamDialog;
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
	private ViewChangeProperties pref;
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
		feedTreePicker = mainPanel.getFeedTreePicker();
		newsList = mainPanel.getNewsList();
		theDisplay = defaultDisplay;

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
					System.out.println("Vous avez selectionne la source: " + feed );

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
		//System.out.println(action);
		if (action.equals("Tout")) {			
			//List<News> news = new ArrayList<News>();
			theDisplay = "All";
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

			newsList.changeNews(news,theDisplay);




		}

		if (action.equals("Non lus")) {			

			//List<News> news = new ArrayList<News>();
			theDisplay = "Not Read";
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
			newsList.changeNews(news,theDisplay);

		}

		if (action.equals("Lus")) {			

			//List<News> news = new ArrayList<News>();
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
		}
		if (action.equals("Help")) {			
				
		}
		if (action.equals("About")) {			
			
			JDialog Dev = new JDialog();
			JOptionPane.showMessageDialog(Dev,"Developp�s par plusieurs moustachus et quelques Zboubs", "Actu-RSS",new Integer(JOptionPane.INFORMATION_MESSAGE).intValue());
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

	}


}