package ca.usherbrooke.ift232.actuRSS.view.treepicker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JTree;
import javax.swing.event.EventListenerList;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import ca.usherbrooke.ift232.actuRSS.common.Category;
import ca.usherbrooke.ift232.actuRSS.common.Source;

public class TreePicker extends JTree
{
	public TreePicker( HashMap<Category, List<Source>> feeds)
	{
		super(getHierarchy(feeds));
		this.setRootVisible(false);
		
		
		this.getSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
		//Sélection multiple 
		
		this.addTreeSelectionListener(new TreeSelectionListener(){

			@Override
			public void valueChanged(TreeSelectionEvent event) {
			
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) event.getPath().getLastPathComponent();
				Object obj = node.getUserObject();
				if(obj instanceof Source)
					fireSourceSelectedEvent(new SourceSelectedEvent(this, (Source) obj));
			}
			
		});
	}
	
	
	/**
	 * Génère une hiérarchie (Catégorie => Source) à partir d'un dictionnaire.
	 * 
	 * @param feeds Dictionnaire
	 * 
	 * @return Hiérarchie générée.
	 */
	private static TreeNode getHierarchy(HashMap<Category, List<Source>> feeds) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode();
		 
		 for(Category cat : feeds.keySet())
		 {
			 DefaultMutableTreeNode catNode = new DefaultMutableTreeNode(cat);
			 
			 for(Source src : feeds.get(cat))
			 {
				 catNode.add(new DefaultMutableTreeNode(src));
			 }
			 
			 root.add(catNode);
		 }
		 
		return root;
	}
	
	/**
	 * Sert à obtenir la liste des sources sélectionnées
	 * 
	 * @return Liste des source sélectionnées
	 */
	public List<Source> getSelectedSources()
	{
		List<Source> sourcesSelected = new ArrayList<Source>();
		
		TreePath[] plop = this.getSelectionPaths();
		
		
		for(TreePath path : plop)
		{
			sourcesSelected.add((Source) ((DefaultMutableTreeNode)path.getLastPathComponent()).getUserObject());
		}
		
		return sourcesSelected;
		
	}
	
	//#region SourceSelectedEvent
	
	private final EventListenerList listenerList = new EventListenerList();
	
	
		public void addSourceSelectedListener(SourceSelectedListener sourceSelectedListener) 
		{
		    listenerList.add(SourceSelectedListener.class, sourceSelectedListener);
		}
		
		public void removeSourceSelectedListener(SourceSelectedListener l) 
		{
		    listenerList.remove(SourceSelectedListener.class, l);
		}
		
		protected void fireSourceSelectedEvent(SourceSelectedEvent event) 
		{
		    for (SourceSelectedListener l: listenerList.getListeners(SourceSelectedListener.class)) {
		        l.onSourceSelected(event);
		    }
		}
		
	//#endregion
}
