package ca.usherbrooke.ift232.actuRSS.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import ca.usherbrooke.ift232.actuRSS.Category;
import ca.usherbrooke.ift232.actuRSS.Feed;
import ca.usherbrooke.ift232.actuRSS.News;
import ca.usherbrooke.ift232.actuRSS.model.FeedManager;
import ca.usherbrooke.ift232.actuRSS.model.Model;
import ca.usherbrooke.ift232.actuRSS.model.SyncRunnable;
import ca.usherbrooke.ift232.actuRSS.model.WrongURLException;
import ca.usherbrooke.ift232.actuRSS.view.MainPanel;
import ca.usherbrooke.ift232.actuRSS.view.Toolbar;
import ca.usherbrooke.ift232.actuRSS.view.View;
import ca.usherbrooke.ift232.actuRSS.view.actulist.ActuList;
import ca.usherbrooke.ift232.actuRSS.view.actulist.ActuSelectedEvent;
import ca.usherbrooke.ift232.actuRSS.view.actulist.ActuSelectedListener;
import ca.usherbrooke.ift232.actuRSS.view.properties.DialogAddFeed;
import ca.usherbrooke.ift232.actuRSS.view.properties.DialogEditFeed;
import ca.usherbrooke.ift232.actuRSS.view.properties.DialogFeedManager;
import ca.usherbrooke.ift232.actuRSS.view.properties.ProgramProperties;
import ca.usherbrooke.ift232.actuRSS.view.properties.ViewChangeProperties;
import ca.usherbrooke.ift232.actuRSS.view.treepicker.FeedSelectedEvent;
import ca.usherbrooke.ift232.actuRSS.view.treepicker.FeedSelectedListener;
import ca.usherbrooke.ift232.actuRSS.view.treepicker.TreePicker;

public class Controller implements ActionListener {

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
	public static ProgramProperties properties = ProgramProperties
			.getInstance();
	public static String defaultDisplay = properties
			.getProperty("Default Display");
	public String theDisplay;
	List<News> news = new ArrayList<News>();

	public Controller(final Model model, final View view) {
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

		this.model.loadAllFromDB();
		this.mainPanel.setCategoryList(feedManager.getOldListCategory());
		gest.setCategories(feedManager.getOldListCategory());

		model.addObserver(view);
		model.notifyObserver();
		view.addListener(this);

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

				newsList.changeNews(news, theDisplay);
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
								break;
							}
						}
					}
				});

	}

	public News getSelectedNews() {
		return newsList.getSelectedNew();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		String action = arg0.getActionCommand();
		if (action.equals("Tout")) {
			theDisplay = "All";
			toolbar.getFavBtn().setSelected(false);
			toolbar.getReadBtn().setSelected(false);

			newsList.changeNews(news, theDisplay);

		}

		if (action.equals("Non lus")) {

			theDisplay = "Not Read";
			toolbar.getFavBtn().setSelected(false);
			toolbar.getReadBtn().setSelected(false);
			newsList.changeNews(news, theDisplay);

		}

		if (action.equals("Lus")) {

			theDisplay = "Read";
			toolbar.getFavBtn().setSelected(false);
			toolbar.getReadBtn().setSelected(false);
			newsList.changeNews(news, theDisplay);
		}

		if (action.equals("Favoris")) {

			theDisplay = "Favorite";
			toolbar.getFavBtn().setSelected(false);
			toolbar.getReadBtn().setSelected(false);
			newsList.changeNews(news, theDisplay);
		}

		if (action.equals("Sync")) {

			System.out.println("lolilol");
			SyncRunnable.main();
				//model.synchronize();

				// feedManager.getOldListCategory();
				// TODO Auto-generated catch block

			// feedManager.merge();
			model.notifyObserver();

		}
		if (action.equals("Read")) {

			News newsSelected = this.getSelectedNews();

			if (newsSelected != null) {
				if (newsSelected.isRead()) {
					newsSelected.setRead(false);
				} else {
					newsSelected.setRead(true);
				}
			}

		}
		if (action.equals("FavBtn")) {

			News newsSelected = this.getSelectedNews();
			if (newsSelected != null) {
				if (newsSelected.isFavorite()) {
					newsSelected.setFavorite(false);
				} else {
					newsSelected.setFavorite(true);
					// System.out.println(newsSelected.getTitle());
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
			JDialog Dev = new JDialog();
			JOptionPane.showMessageDialog(Dev, "Contactez nous!!!!", "Help",
					new Integer(JOptionPane.INFORMATION_MESSAGE).intValue());
		}
		if (action.equals("About")) {

			JDialog Dev = new JDialog();
			JOptionPane.showMessageDialog(Dev,
					"Developpés par plusieurs moustachus et quelques Zboubs",
					"About",
					new Integer(JOptionPane.INFORMATION_MESSAGE).intValue());
		}
		// Preference

		if (action.equals("OkPref")) {
			pref.finishDialog();
			mainPanel.getContentPanel().display();
		}
		if (action.equals("AnnulerPref")) {
			pref.closeDialog();
		}
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

		if (action.equals("ExitSource")) {
			gest.closeDialog();
		}

	}

}
