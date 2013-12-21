package ca.usherbrooke.ift232.actuRSS.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import ca.usherbrooke.ift232.actuRSS.Category;
import ca.usherbrooke.ift232.actuRSS.Feed;
import ca.usherbrooke.ift232.actuRSS.News;
import ca.usherbrooke.ift232.actuRSS.controller.command.ActionAbout;
import ca.usherbrooke.ift232.actuRSS.controller.command.ActionAll;
import ca.usherbrooke.ift232.actuRSS.controller.command.ActionCancelPref;
import ca.usherbrooke.ift232.actuRSS.controller.command.ActionFavButton;
import ca.usherbrooke.ift232.actuRSS.controller.command.ActionFavorite;
import ca.usherbrooke.ift232.actuRSS.controller.command.ActionHelp;
import ca.usherbrooke.ift232.actuRSS.controller.command.ActionManageSources;
import ca.usherbrooke.ift232.actuRSS.controller.command.ActionNotRead;
import ca.usherbrooke.ift232.actuRSS.controller.command.ActionPref;
import ca.usherbrooke.ift232.actuRSS.controller.command.ActionRead;
import ca.usherbrooke.ift232.actuRSS.controller.command.ActionReadButton;
import ca.usherbrooke.ift232.actuRSS.controller.command.ActionSync;
import ca.usherbrooke.ift232.actuRSS.model.FeedManager;
import ca.usherbrooke.ift232.actuRSS.model.Model;
import ca.usherbrooke.ift232.actuRSS.model.WrongURLException;
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

public class Controller implements ActionListener {

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
		view.addListener(this);		
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
		view.setAction(action);
		
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

	@Override
	public void actionPerformed(ActionEvent arg0) {

		String action = arg0.getActionCommand();


		/*if (action.equals("Tout")) {
			theDisplay = new AllFilter();
			toolbar.getFavBtn().setSelected(false);
			toolbar.getReadBtn().setSelected(false);

			newsList.changeNews(news, theDisplay, actualSorter);

		}*/

		/*if (action.equals("Non lus")) {

			theDisplay = new NotReadFilter();
			toolbar.getFavBtn().setSelected(false);
			toolbar.getReadBtn().setSelected(false);
			newsList.changeNews(news, theDisplay, actualSorter);

		}*/

		/*if (action.equals("Lus")) {

			theDisplay = new ReadFilter();
			toolbar.getFavBtn().setSelected(false);
			toolbar.getReadBtn().setSelected(false);
			newsList.changeNews(news, theDisplay, actualSorter);
		}*/

		/*if (action.equals("Favoris")) {

			theDisplay = new FavoriteFilter();
			toolbar.getFavBtn().setSelected(false);
			toolbar.getReadBtn().setSelected(false);
			newsList.changeNews(news, theDisplay, actualSorter);
		}*/

		/*if (action.equals("Sync")) {
			SyncRunnable.main();
			model.notifyObserver();

		}*/
		/*if (action.equals("Read")) {

			News newsSelected = this.getSelectedNews();

			if (newsSelected != null) {
				if (newsSelected.isRead()) {
					newsSelected.setRead(false);
				} else {
					newsSelected.setRead(true);
				}
			}

		}*/
		/*if (action.equals("FavBtn")) {

			News newsSelected = this.getSelectedNews();
			if (newsSelected != null) {
				if (newsSelected.isFavorite()) {
					newsSelected.setFavorite(false);
				} else {
					newsSelected.setFavorite(true);
					// System.out.println(newsSelected.getTitle());
				}
			}
		}*/

		/*if (action.equals("Pref")) {
			pref.showDialog();
		}*/
		/*if (action.equals("GererSources")) {
			gest.showDialog();
		}*/
		/*if (action.equals("Help")) {
			JDialog Dev = new JDialog();
			JOptionPane.showMessageDialog(Dev, "Si un problème survient lors de l'utilisation de ce logiciel, contacter la ActuRss Team!", "Help",
					new Integer(JOptionPane.INFORMATION_MESSAGE).intValue());
		}*/
		/*if (action.equals("About")) {

			JDialog Dev = new JDialog();
			JOptionPane.showMessageDialog(Dev,
					"Developpés par : \nYann SEREE\nDavid BOAS\nJulian BIRONNEAU\nVincent CHATAIGNIER\nGauthier CIBERT-VOLPE\nBenjamin FERRE\nBastien MEUNIER\nRémi PATRIZIO\nMatthieu POUPINEAU\n© ActuRSS dream team"
					,		
					"About",
					new Integer(JOptionPane.INFORMATION_MESSAGE).intValue());
		}*/
		// Preference

		if (action.equals("OkPref")) {
			pref.finishDialog();
			mainPanel.getContentPanel().display();
			newsList.changeNews(news, theDisplay, actualSorter);
		}
		/*if (action.equals("AnnulerPref")) {
			pref.closeDialog();
		}*/
		if (action.equals("ReinitialiserPref")) {
			pref.renewDialog();
		}
		if (action.equals("OpenFile")) {
			pref.setCSS();
		}

		// Gestion source

		// Ajout source
		if (action.equals("AddSource")) {
			addFeed.listerCategories(feedManager.getOldListCategory());
			addFeed.showDialog();
		}

		// Valide l'ajout du flux et l'ajoute
		boolean newFeed;
		if (action.equals("OkAddSource")) {
			if (addFeed.Valide()) {
				addFeed.finishedDialog();
				Feed feed = new Feed(-1, addFeed.getName(), addFeed.getUrl());
				String str = addFeed.getCategory();
				feed.setNameCategory(str);
				Category cat = feedManager.getCategoryByName(str);
				try {
					newFeed = feedManager.addFeed(feed, cat);
					if (newFeed == false) {
						JDialog Dev = new JDialog();
						JOptionPane.showMessageDialog(Dev, "Le flux Rss que vous essayer d'ajouter existe déjà !", "Help",
								new Integer(JOptionPane.INFORMATION_MESSAGE).intValue());
					}
				} catch (WrongURLException ex) {
					JDialog Dev = new JDialog();
					JOptionPane.showMessageDialog(Dev, "L'adresse du flux " + feed.getTitle() + " semble être erroné", "Help",
							new Integer(JOptionPane.INFORMATION_MESSAGE).intValue());
				}	

				gest.getManageTree().refreshFeeds(
						feedManager.getOldListCategory());
				// System.out.println(feedManager.getOldListCategory().toString());

				model.notifyObserver();
			}
		}
		// Annule l'ajout
		if (action.equals("CancelAddSource")) {
			addFeed.closeDialog();
		}
		// Remet a default les parametre de la source

		if (action.equals("RenewAddSource")) {
			addFeed.renewDialog();
		}
		// Ajoute un categorie
		if (action.equals("NewCatAddSource")) {
			String newCat = addFeed.newCategorie();
			if (newCat != null && !newCat.equals("")) {
				feedManager.getOldListCategory().add(
						new Category(-1, newCat, new ArrayList<Feed>()));
				gest.getManageTree().refreshFeeds(
						feedManager.getOldListCategory());

				model.notifyObserver();
			}
		}

		// Supprime le flux
		if (action.equals("DeleteSource")) {

			Category oldcat =feedManager.getCategoryByName(editFeed.getFeed().getNameCategory());
			feedManager.removeFeed(editFeed.getFeed(), oldcat);
			gest.getManageTree().refreshFeeds(
					feedManager.getOldListCategory());

			model.notifyObserver();


		}

		// Edite le flux

		if (action.equals("EditSource")) {
			editFeed.listerCategories(feedManager.getOldListCategory());
			editFeed.showDialog();
		}

		if (action.equals("OkEditSource")) {
			if (editFeed.Valide()) {
				editFeed.finishedDialog();
				Feed feed = new Feed(editFeed.getId(), editFeed.getName(),
						editFeed.getUrl(), editFeed.getNews());
				String str = editFeed.getCategory();
				feed.setNameCategory(str);
				Category cat = feedManager.getCategoryByName(str);
				Category oldcat = feedManager.getCategoryByName(editFeed.getFeed().getNameCategory());
				// CREER METHODE MODIFYFEED
				try {
					newFeed = feedManager.modifyFeed(feed, cat);
					if (newFeed)
						feedManager.removeFeed(editFeed.getFeed(), oldcat);
				} catch (WrongURLException ex) {
					JDialog Dev = new JDialog();
					JOptionPane.showMessageDialog(Dev, "La nouvelle adresse adresse du flux " + feed.getTitle() + " semble être erroné", "Help",
							new Integer(JOptionPane.INFORMATION_MESSAGE).intValue());
				}	
				gest.getManageTree().refreshFeeds(
						feedManager.getOldListCategory());

				model.notifyObserver();
			}
		}
		if (action.equals("CancelEditSource")) {
			editFeed.closeDialog();
		}
		if (action.equals("RenewEditSource")) {
			editFeed.renewDialog();
		}

		if (action.equals("NewCatEditSource")) {
			String newCat = editFeed.newCategorie();
			if (newCat != null && !newCat.equals("")) {
				feedManager.getOldListCategory().add(
						new Category(-1, newCat, new ArrayList<Feed>()));
				gest.getManageTree().refreshFeeds(
						feedManager.getOldListCategory());

				model.notifyObserver();
			}
		}

		// Sort de la fenetre de gestion
		if (action.equals("DeleteCategorie")){
			if(deletecat != null){
				feedManager.removeCategory(deletecat);
				gest.getManageTree().refreshFeeds(
						feedManager.getOldListCategory());
				gest.putDeleteCategoryEditable(false);
			}
			model.notifyObserver();

		}

		if (action.equals("ExitSource")) {
			gest.closeDialog();
		}

	}

}
