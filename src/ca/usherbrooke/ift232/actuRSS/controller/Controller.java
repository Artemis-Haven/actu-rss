package ca.usherbrooke.ift232.actuRSS.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.AbstractAction;

import ca.usherbrooke.ift232.actuRSS.Category;
import ca.usherbrooke.ift232.actuRSS.Feed;
import ca.usherbrooke.ift232.actuRSS.News;
import ca.usherbrooke.ift232.actuRSS.controller.command.ActionAbout;
import ca.usherbrooke.ift232.actuRSS.controller.command.ActionAddSource;
import ca.usherbrooke.ift232.actuRSS.controller.command.ActionAll;
import ca.usherbrooke.ift232.actuRSS.controller.command.ActionCancelAddSource;
import ca.usherbrooke.ift232.actuRSS.controller.command.ActionCancelEditSource;
import ca.usherbrooke.ift232.actuRSS.controller.command.ActionCancelPref;

import ca.usherbrooke.ift232.actuRSS.controller.command.ActionDeleteSource;
import ca.usherbrooke.ift232.actuRSS.controller.command.ActionExitSource;

import ca.usherbrooke.ift232.actuRSS.controller.command.ActionDeleteCategorie;
import ca.usherbrooke.ift232.actuRSS.controller.command.ActionEditSource;

import ca.usherbrooke.ift232.actuRSS.controller.command.ActionFavButton;
import ca.usherbrooke.ift232.actuRSS.controller.command.ActionFavorite;
import ca.usherbrooke.ift232.actuRSS.controller.command.ActionHelp;
import ca.usherbrooke.ift232.actuRSS.controller.command.ActionManageSources;
import ca.usherbrooke.ift232.actuRSS.controller.command.ActionNewCatAddSource;
import ca.usherbrooke.ift232.actuRSS.controller.command.ActionNewCatEditSource;
import ca.usherbrooke.ift232.actuRSS.controller.command.ActionNotRead;
import ca.usherbrooke.ift232.actuRSS.controller.command.ActionOkAddSource;
import ca.usherbrooke.ift232.actuRSS.controller.command.ActionOkEditSource;
import ca.usherbrooke.ift232.actuRSS.controller.command.ActionOkPref;
import ca.usherbrooke.ift232.actuRSS.controller.command.ActionOpenFile;
import ca.usherbrooke.ift232.actuRSS.controller.command.ActionPref;
import ca.usherbrooke.ift232.actuRSS.controller.command.ActionRead;
import ca.usherbrooke.ift232.actuRSS.controller.command.ActionReadButton;

import ca.usherbrooke.ift232.actuRSS.controller.command.ActionRenewAddSource;

import ca.usherbrooke.ift232.actuRSS.controller.command.ActionRenewEditSource;

import ca.usherbrooke.ift232.actuRSS.controller.command.ActionResetPref;
import ca.usherbrooke.ift232.actuRSS.controller.command.ActionSync;
import ca.usherbrooke.ift232.actuRSS.model.FeedManager;
import ca.usherbrooke.ift232.actuRSS.model.Model;
import ca.usherbrooke.ift232.actuRSS.view.MainPanel;
import ca.usherbrooke.ift232.actuRSS.view.Toolbar;
import ca.usherbrooke.ift232.actuRSS.view.View;
import ca.usherbrooke.ift232.actuRSS.view.actulist.ActuList;
import ca.usherbrooke.ift232.actuRSS.view.actulist.ActuSelectedEvent;
import ca.usherbrooke.ift232.actuRSS.view.actulist.ActuSelectedListener;
import ca.usherbrooke.ift232.actuRSS.view.filter.Filter;
import ca.usherbrooke.ift232.actuRSS.view.parameters.DialogAddFeed;
import ca.usherbrooke.ift232.actuRSS.view.parameters.DialogEditFeed;
import ca.usherbrooke.ift232.actuRSS.view.parameters.DialogFeedManager;
import ca.usherbrooke.ift232.actuRSS.view.parameters.ProgramProperties;
import ca.usherbrooke.ift232.actuRSS.view.parameters.ViewChangeProperties;
import ca.usherbrooke.ift232.actuRSS.view.sorter.Sorter;
import ca.usherbrooke.ift232.actuRSS.view.treepicker.CategorySelectedEvent;
import ca.usherbrooke.ift232.actuRSS.view.treepicker.FeedSelectedEvent;
import ca.usherbrooke.ift232.actuRSS.view.treepicker.FeedSelectedListener;
import ca.usherbrooke.ift232.actuRSS.view.treepicker.TreePicker;

public class Controller  {

	private HashMap<String, AbstractAction> action;
	private Model model;	
	private MainPanel mainPanel;
	private View view;
	private Toolbar toolbar;
	private TreePicker feedTreePicker;
	private ActuList newsList;
	private FeedManager feedManager;
	private ViewChangeProperties pref;
	private DialogFeedManager gest;
	private DialogAddFeed addFeed;
	private DialogEditFeed editFeed;
	private boolean newFeed;
	public static ProgramProperties properties = ProgramProperties.getInstance();

	public static Filter defaultDisplay;
	public Filter theDisplay;

	public static Sorter defaultSorter; //TODO Fichier properties pareil que defaultDisplay
	public static Sorter actualSorter;

	List<News> news = new ArrayList<News>();
	private Category deletecat = null;

	public Controller() 
	{

		model = new Model();
		action = new HashMap();
		view  = new View(action); 	
		
		newFeed = false;
		
		try
		{
			defaultDisplay  = (Filter) Class.forName(properties.getProperty("Default Display")).newInstance();
			defaultSorter = (Sorter) Class.forName(properties.getProperty("Default Sorter")).newInstance();
			actualSorter = defaultSorter;
		} catch (Exception e1)
		{
			e1.printStackTrace();
		} 		
		
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

		actualSorter = defaultSorter;

		this.model.loadAllFromDB();
		this.mainPanel.setCategoryList(feedManager.getOldListCategory());
		gest.setCategories(feedManager.getOldListCategory());

		model.addObserver(view);
		model.notifyObserver();
		
		createEvenement();
		createAction();		
		view.addListener();		
		view.setVisible(true);		



	}

	public void createAction() {
		
		action.put("ActionFavButton",new ActionFavButton(this));
		action.put("ActionAll",new ActionAll(theDisplay, toolbar, newsList, news, actualSorter));

		action.put("ActionNotRead", new ActionNotRead(theDisplay, toolbar, newsList, news, actualSorter));
		action.put("ActionSync", new ActionSync(model));
		action.put("ActionReadButton", new ActionReadButton(this));
		action.put("ActionPref", new ActionPref(pref));

		action.put("ActionFavorite",new ActionFavorite(theDisplay, toolbar, newsList, news, actualSorter));
		action.put("ActionRead",new ActionRead(theDisplay, toolbar, newsList, news, actualSorter));
		action.put("ActionManageSources",new ActionManageSources(gest));
		
		
		action.put("ActionHelp", new ActionHelp());
		action.put("ActionAbout", new ActionAbout());
		action.put("ActionCancelPref", new ActionCancelPref(pref));

		action.put("ActionOpenFile", new ActionOpenFile(pref));
		action.put("ActionAddSource", new ActionAddSource(feedManager, addFeed));
		action.put("ActionOkAddSource", new ActionOkAddSource(addFeed, feedManager, newFeed, gest, model));

		
		action.put("ActionOkPref", new ActionOkPref(pref, mainPanel, newsList, theDisplay, news, actualSorter));
		action.put("ActionResetPref", new ActionResetPref(pref));
		action.put("ActionCancelAddSource", new ActionCancelAddSource(addFeed));
		action.put("ActionRenewAddSource", new ActionRenewAddSource(addFeed));
		action.put("ActionNewCatAddSource", new ActionNewCatAddSource(addFeed, feedManager, gest, model));
		action.put("ActionDeleteSource", new ActionDeleteSource(feedManager, editFeed, gest, model));
		action.put("ActionCancelEditSource", new ActionCancelEditSource(editFeed));
		action.put("ActionNewCatEditSource", new ActionNewCatEditSource(editFeed, feedManager, gest, model));
		action.put("ActionExitSource", new ActionExitSource(gest));
		
		//TODO AJOUTER EDIT SOURCE et du coup aussi dans la bonne fucking classe
	
		action.put("ActionEditSource", new ActionEditSource(editFeed, feedManager));
		action.put("ActionOkEditSource", new ActionOkEditSource(editFeed,feedManager,newFeed, gest, model));
		action.put("ActionRenewEditSource", new ActionRenewEditSource(editFeed));
		
		action.put("ActionDeleteCategorie", new ActionDeleteCategorie(this, feedManager, gest, model));
	

		view.setAction(action);
		
	}

	public Category getDeletecat() {
		return deletecat;
	}

	public void setDeletecat(Category deletecat) {
		this.deletecat = deletecat;
	}

	public void createEvenement() 
	{		
		// --- Evenement sur la croix de la fenetre principale		
		this.view.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				model.sendAllToDB();
				System.exit(0);
			}
		});

		// --- Evenement sur la croix du Jdialog
		this.pref.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				pref.closeDialog();
			}
		});

		// --- Evenement sur le treePicker
		feedTreePicker.addFeedSelectedListener(new FeedSelectedListener() {

			@Override
			public void onFeedSelected(FeedSelectedEvent event) {

				news.clear();
				for (Feed feed : feedTreePicker.getSelectedFeeds()) {
					if (feed == null)
						continue;

					for (News actu : feed.getListNews()) {
						if (actu == null)
							continue;
						news.add(actu);
					}

				}

				toolbar.getFavBtn().setSelected(false);
				toolbar.getReadBtn().setSelected(false);

				newsList.changeNews(news, theDisplay, actualSorter);
			}

			@Override
			public void onCategorySelected(CategorySelectedEvent event) {
				// TODO Auto-generated method stub

			}
		});

		// --- Evenement sur l'ActuList
		newsList.addActuSelectedListener(new ActuSelectedListener() {

			public void onActuSelected(ActuSelectedEvent event) {

				toolbar.getFavBtn().setEnabled(true);
				toolbar.getReadBtn().setEnabled(true);

				/* Regarder si la source est lu, favori */
				News newsSelected = getSelectedNews();
				if (newsSelected != null) {

					toolbar.getReadBtn().setSelected(false);
					newsSelected.setRead(true);
					if (newsSelected.isFavorite()) {
						toolbar.getFavBtn().setSelected(true);
					}
					if (newsSelected.isFavorite() == false) {
						toolbar.getFavBtn().setSelected(false);
					}
					mainPanel.getContentPanel().setContentPanel(newsSelected, feedTreePicker.getSelectedFeeds().get(0).getTitle());
					mainPanel.getContentPanel().display();

				}
			}

		});

		gest.getManageTree().addFeedSelectedListener(
				new FeedSelectedListener() {

					@Override
					public void onFeedSelected(FeedSelectedEvent event) {
						for (Feed f : gest.getManageTree().getSelectedFeeds()) {

							if (f == null) {
								continue;
							} else {
								editFeed.setFeed(f);
								gest.putEditable();
								gest.putDeleteCategoryEditable(false);
								break;
							}
						}
					}

					@Override
					public void onCategorySelected(CategorySelectedEvent event) {
						deletecat  = event.getSelectedSource();
						gest.putDeleteCategoryEditable(true);
					}
				});



	}

	public News getSelectedNews() {
		return newsList.getSelectedNew();
	}

}
