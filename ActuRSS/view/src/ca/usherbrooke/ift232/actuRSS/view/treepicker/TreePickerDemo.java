package ca.usherbrooke.ift232.actuRSS.view.treepicker;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import ca.usherbrooke.ift232.actuRSS.common.Category;
import ca.usherbrooke.ift232.actuRSS.common.Source;

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
		
		final HashMap<Category, List<Source>> feedByCat = new HashMap<Category, List<Source>>();
		
		List<Source> sportSources = new ArrayList<Source>();
		sportSources.add(new Source("SportSource1"));
		sportSources.add(new Source("SportSource2"));
		sportSources.add(new Source("SportSource3"));
		sportSources.add(new Source("SportSource4"));
		sportSources.add(new Source("SportSource5"));
		
		feedByCat.put(new Category("Sport"), sportSources);
		final List<Source> financeSources = new ArrayList<Source>();
		financeSources.add(new Source("FinanceSource1"));
		financeSources.add(new Source("FinanceSource2"));
		feedByCat.put(new Category("Finance"), financeSources);
		
		//================== Ajout du composant à la fenêtre
		
		final TreePicker tree = new TreePicker( feedByCat, true);
		tree.addSourceSelectedListener(new SourceSelectedListener(){

			@Override
			public void onSourceSelected(SourceSelectedEvent event) {
				System.out.println("Vous avez sélectionné la source: " + event.getSelectedSource());

				financeSources.clear();
				tree.refreshFeeds(feedByCat);
			}
			
		});
		
		JScrollPane scrollPane = new JScrollPane(tree);
		frame.add(scrollPane);
	}

}
