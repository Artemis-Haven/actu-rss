package ca.usherbrooke.ift232.actuRSS.view.treepicker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JTree;
import javax.swing.event.EventListenerList;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import ca.usherbrooke.ift232.actuRSS.common.Category;
import ca.usherbrooke.ift232.actuRSS.common.Source;

public class TreePicker extends JTree {
	public TreePicker(HashMap<Category, List<Source>> feeds,
			boolean multipleSelection) {
		super(generateHierarchy(feeds));
		this.setRootVisible(false);

		// Multiple selection or not (Ternary operation ftw)
		int selectModel = (multipleSelection) ? TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION
				: TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION;
		this.getSelectionModel().setSelectionMode(selectModel);

		this.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent event) {

				DefaultMutableTreeNode node = (DefaultMutableTreeNode) event
						.getPath().getLastPathComponent();
				Object obj = node.getUserObject();
				if (obj instanceof Source)
					fireSourceSelectedEvent(new SourceSelectedEvent(this,
							(Source) obj));
			}

		});
	}

	/**
	 * Génère une hiérarchie (Catégorie => Source) à partir d'un dictionnaire.
	 * 
	 * @param feeds
	 *            Dictionnaire
	 * 
	 * @return Hiérarchie générée.
	 */
	private static DefaultMutableTreeNode generateHierarchy(
			HashMap<Category, List<Source>> feeds) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode();

		for (Category cat : feeds.keySet()) {
			DefaultMutableTreeNode catNode = new DefaultMutableTreeNode(cat);

			for (Source src : feeds.get(cat)) {
				catNode.add(new DefaultMutableTreeNode(src));
			}

			root.add(catNode);
		}

		return root;
	}

	/**
	 * 
	 * @param feeds
	 */
	public void refreshFeeds(HashMap<Category, List<Source>> feeds) {
		DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) this
				.getModel().getRoot();
		rootNode.removeAllChildren();

		DefaultMutableTreeNode generated = generateHierarchy(feeds);

		DefaultTreeModel plop = (DefaultTreeModel) this.getModel();
		plop.setRoot(generated);

		this.setSelectionPaths(null);

	}

	/**
	 * Sert à obtenir la liste des sources sélectionnées
	 * 
	 * @return Liste des source sélectionnées
	 */
	public List<Source> getSelectedSources() {
		List<Source> sourcesSelected = new ArrayList<Source>();

		TreePath[] selectedPaths = this.getSelectionPaths();

		if (selectedPaths != null) {
			for (TreePath path : selectedPaths) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) path
						.getLastPathComponent();
				sourcesSelected.add((Source) node.getUserObject());
			}
		}

		return sourcesSelected;

	}

	// #region SourceSelectedEvent

	private final EventListenerList listenerList = new EventListenerList();

	public void addSourceSelectedListener(
			SourceSelectedListener sourceSelectedListener) {
		listenerList.add(SourceSelectedListener.class, sourceSelectedListener);
	}

	public void removeSourceSelectedListener(SourceSelectedListener l) {
		listenerList.remove(SourceSelectedListener.class, l);
	}

	protected void fireSourceSelectedEvent(SourceSelectedEvent event) {
		for (SourceSelectedListener l : listenerList
				.getListeners(SourceSelectedListener.class)) {
			l.onSourceSelected(event);
		}
	}

	// #endregion
}
