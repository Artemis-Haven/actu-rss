package ca.usherbrooke.ift232.actuRSS.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.DefaultListModel;
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
	DefaultListModel<String> listModel;
	TreePicker tree;
	
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
		
		//================== Ajout du composant � la fen�tre
		
		tree = new TreePicker( feedByCat, false);
		listModel = new DefaultListModel<String>();
		tree.addSourceSelectedListener(new SourceSelectedListener(){

			@Override
			public void onSourceSelected(SourceSelectedEvent event) {
				listModel.removeAllElements();
				for(int i = 0; i < tree.getSelectedSources().size();i++){
					listModel.addElement("" + tree.getSelectedSources().get(i) + "\n testttt");
					System.out.println("Vous avez s�lectionn� la source: " + tree.getSelectedSources().get(i));
				}
				
			}
			
		});
		
		JList lst = new JList(listModel);
		add(splitPane);
		JScrollPane treescrollPane = new JScrollPane(tree);
		treescrollPane.setMinimumSize(new Dimension(30,50));
		treescrollPane.setPreferredSize(new Dimension(75,50));
		JScrollPane listcrollPane = new JScrollPane(lst);
		listcrollPane.setMinimumSize(new Dimension(75,50));
		listcrollPane.setPreferredSize(new Dimension(120,50));
		splitPane.setLeftComponent(treescrollPane);
		splitPane.setRightComponent(listcrollPane);
	}

}