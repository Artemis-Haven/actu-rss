package ca.usherbrooke.ift232.actuRSS.view.treepicker;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.xml.transform.Source;

import ca.usherbrooke.ift232.actuRSS.Category;
import ca.usherbrooke.ift232.actuRSS.Feed;

public class TreePickerDemo {

	private JFrame frame;

	/**
	 * main : lance l'application
	 * @param args
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
	 * TreePickerDemo : Crée l'application
	 */
	public TreePickerDemo() {
		initialize();
	}

	/**
	 * initialize : Initialize le contenu du paquet
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//================== Crï¿½ation des donnï¿½es de dï¿½monstration
		
		Category sportCat = new Category();
		
		sportCat.setName("Sport");
		
		final List<Category> cats = new ArrayList<Category>();
		
		List<Feed> sportSources = new ArrayList<Feed>();
		
		Feed sportSource1 = new Feed();
		sportSource1.setTitle("sportSource1");
		Feed sportSource2 = new Feed();
		sportSource1.setTitle("sportSource1");
		Feed sportSource3 = new Feed();
		sportSource1.setTitle("sportSource1");
		Feed sportSource4 = new Feed();
		sportSource1.setTitle("sportSource4");
		sportCat.setListFeed(sportSources);
		
		sportCat.setListFeed(sportSources);
		
		sportSources.add(sportSource1);
		sportSources.add(sportSource2);
		sportSources.add(sportSource3);
		sportSources.add(sportSource4);
		
		
		
		final List<Feed> financeSources = new ArrayList<Feed>();
		
		Feed financeSource1 = new Feed();
		financeSource1.setTitle("FinanceSource1");
		Feed financeSource2 = new Feed();
		financeSource2.setTitle("sportSource1");
		Feed financeSource3 = new Feed();
		financeSource3.setTitle("sportSource1");
		
		financeSources.add(financeSource1);
		financeSources.add(financeSource2);
		financeSources.add(financeSource3);
		
		Category financeCat = new Category(1, "Finance", financeSources);
		
		cats.add(sportCat);
		cats.add(sportCat);
		
		//================== Ajout du composant ï¿½ la fenï¿½tre
		
		final TreePicker tree = new TreePicker( cats, true);
		tree.addFeedSelectedListener(new FeedSelectedListener(){

			@Override
			public void onFeedSelected(FeedSelectedEvent event) {
				System.out.println("Vous avez sï¿½lectionnï¿½ la source: " + event.getSelectedSource());

				//financeSources.clear();
				//tree.refreshFeeds(cats);
			}
			
		});
		
		JScrollPane scrollPane = new JScrollPane(tree);
		frame.add(scrollPane);
	}

}
