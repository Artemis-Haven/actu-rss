package ca.usherbrooke.ift232.actuRSS.view.treepicker;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.xml.transform.Source;

import ca.usherbrooke.ift232.actuRSS.model.Category;
import ca.usherbrooke.ift232.actuRSS.model.Feed;

public class TreePickerDemo {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TreePickerDemo window = new TreePickerDemo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TreePickerDemo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//================== Création des données de démonstration
		
		Category sportCat = new Category();
		
		sportCat.setName("Sport");
		
		final List<Category> cats = new ArrayList<Category>();
		
		List<Feed> sportSources = new ArrayList<Feed>();
		
		Feed sportSource1 = new Feed();
		sportSource1.setName("sportSource1");
		Feed sportSource2 = new Feed();
		sportSource1.setName("sportSource1");
		Feed sportSource3 = new Feed();
		sportSource1.setName("sportSource1");
		Feed sportSource4 = new Feed();
		sportSource1.setName("sportSource4");
		sportCat.setListFeed(sportSources);
		
		sportCat.setListFeed(sportSources);
		
		sportSources.add(sportSource1);
		sportSources.add(sportSource2);
		sportSources.add(sportSource3);
		sportSources.add(sportSource4);
		
		
		
		final List<Feed> financeSources = new ArrayList<Feed>();
		
		Feed financeSource1 = new Feed();
		financeSource1.setName("FinanceSource1");
		Feed financeSource2 = new Feed();
		financeSource2.setName("sportSource1");
		Feed financeSource3 = new Feed();
		financeSource3.setName("sportSource1");
		
		financeSources.add(financeSource1);
		financeSources.add(financeSource2);
		financeSources.add(financeSource3);
		
		Category financeCat = new Category(1, "Finance", financeSources);
		
		cats.add(sportCat);
		cats.add(sportCat);
		
		//================== Ajout du composant à la fenêtre
		
		final TreePicker tree = new TreePicker( cats, true);
		tree.addSourceSelectedListener(new FeedSelectedListener(){

			@Override
			public void onSourceSelected(FeedSelectedEvent event) {
				System.out.println("Vous avez sélectionné la source: " + event.getSelectedSource());

				financeSources.clear();
				tree.refreshFeeds(cats);
			}
			
		});
		
		JScrollPane scrollPane = new JScrollPane(tree);
		frame.add(scrollPane);
	}

}
