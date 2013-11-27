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


	public Controller(Model model, View view) {		
		this.model = model;
		this.view = view;
		this.mainPanel = view.getMainPanel();
		this.toolbar = mainPanel.getToolbar();

		final TreePicker feedTreePicker = mainPanel.getFeedTreePicker();
		ActuList newsList = mainPanel.getNewsList();

		model.addObserver(view);
		model.notifyObserver();
		view.addListener(this);

		//---                     Evenement sur le treePicker

		feedTreePicker.addFeedSelectedListener(new FeedSelectedListener()
		{

			@Override
			public void onFeedSelected(FeedSelectedEvent event) 
			{					


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
					System.out.println("Vous avez selectionne la source: " + feed );

				}	

				mainPanel.getNewsList().changeNews(news);
			}	
		});			


		//---                     Evenement sur l'ActuList

		newsList.addActuSelectedListener(new ActuSelectedListener()
		{


			public void onActuSelected(ActuSelectedEvent event) 
			{

			
				/*Regarder si la source est lu, favori*/
				News newsSelected = getSelectedNews();
				if(newsSelected!=null){
					if(newsSelected.isRead())
					{
						toolbar.getReadBtn().setSelected(true);
					}
					if(newsSelected.isRead()==false)
					{
						toolbar.getReadBtn().setSelected(false);
					}
					
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

	public News getSelectedNews() {return mainPanel.getNewsList().getSelectedNew();}	




	@Override
	public void actionPerformed(ActionEvent arg0) {		

		String action = arg0.getActionCommand();				

		if (action.equals("Tout")) {			
			System.out.println("tout");		
		}
		if (action.equals("Non lus")) {			
			System.out.println("Non lus");				
		}
		if (action.equals("Favoris")) {	

			System.out.println("Favoris");

		}

		if (action.equals("Sync")) {			
			System.out.println("Sync");	
			//TODO ne fonctionne pas model.synchronize();
		}
		if (action.equals("Read")) {			
			System.out.println("Read");	

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
			System.out.println("FavBtn");	

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
			System.out.println("Pref");		
		}
		if (action.equals("GererSources")) {			
			System.out.println("GererSources");		
		}
		if (action.equals("Help")) {			
			System.out.println("Help");		
		}
		if (action.equals("About")) {			
			System.out.println("About");
			JDialog Dev = new JDialog();
			JOptionPane.showMessageDialog(Dev,"Developpés par plusieurs moustachus et quelques Zboubs", "Actu-RSS",new Integer(JOptionPane.INFORMATION_MESSAGE).intValue());
		}


	}


}
