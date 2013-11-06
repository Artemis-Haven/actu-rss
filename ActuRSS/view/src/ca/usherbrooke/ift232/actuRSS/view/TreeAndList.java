package ca.usherbrooke.ift232.actuRSS.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import ca.usherbrooke.ift232.actuRSS.common.Category;
import ca.usherbrooke.ift232.actuRSS.common.Source;
import ca.usherbrooke.ift232.actuRSS.view.treepicker.SourceSelectedEvent;
import ca.usherbrooke.ift232.actuRSS.view.treepicker.SourceSelectedListener;
import ca.usherbrooke.ift232.actuRSS.view.treepicker.TreePicker;

public class TreeAndList extends JPanel{
	
	JSplitPane splitPane;
	
	public TreeAndList(){
		
		
		this.setLayout(new BorderLayout(1,2));
		splitPane = new JSplitPane();
		
		HashMap<Category, List<Source>> feedByCat = new HashMap<Category, List<Source>>();
		
		List<Source> sportSources = new ArrayList<Source>();
		
		sportSources.add(new Source("SportSource1"));
		sportSources.add(new Source("SportSource2"));
		sportSources.add(new Source("SportSource3"));
		sportSources.add(new Source("SportSource4"));
		sportSources.add(new Source("SportSource5"));
		
		feedByCat.put(new Category("Sport"), sportSources);
		List<Source> financeSources = new ArrayList<Source>();
		financeSources.add(new Source("FinanceSource1"));
		financeSources.add(new Source("FinanceSource2"));
		feedByCat.put(new Category("Finance"), financeSources);
		
		//================== Ajout du composant à la fenêtre
		
		TreePicker tree = new TreePicker( feedByCat);
		JList lst = new JList();
		lst.setPreferredSize(new Dimension(75,50));
		lst.setMinimumSize(new Dimension(30,50));
		
		tree.addSourceSelectedListener(new SourceSelectedListener(){

			@Override
			public void onSourceSelected(SourceSelectedEvent event) {
				System.out.println("Vous avez sélectionné la source: " + event.getSelectedSource());
				
			}
			
		});
		
		add(splitPane);
		JScrollPane scrollPane = new JScrollPane(tree);
		scrollPane.setMinimumSize(new Dimension(30,50));
		scrollPane.setPreferredSize(new Dimension(75,50));
		splitPane.setLeftComponent(scrollPane);
		splitPane.setRightComponent(lst);
	}

}
