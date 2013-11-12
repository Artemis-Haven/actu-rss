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
import javax.xml.transform.Source;

import ca.usherbrooke.ift232.actuRSS.model.Category;
import ca.usherbrooke.ift232.actuRSS.model.Feed;

public class TreePicker extends JTree {
	public TreePicker(List<Category> categories,
			boolean multipleSelection) {
		super(generateHierarchy(categories));
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
					fireSourceSelectedEvent(new FeedSelectedEvent(this,
							(Feed) obj));
			}

		});
	}

	/**
	 * Génère une hiérarchie (Catégorie => Source) à partir d'un dictionnaire.
	 * 
	 * @param categories
	 *            Dictionnaire
	 * 
	 * @return Hiérarchie générée.
	 */
	private static DefaultMutableTreeNode generateHierarchy(
			List<Category> categories) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode();

		for (Category cat : categories) {
			DefaultMutableTreeNode catNode = new DefaultMutableTreeNode(cat);

			for (Feed src : cat.getListFeed()) {
				catNode.add(new DefaultMutableTreeNode(src));
			}

			root.add(catNode);
		}

		return root;
	}

	/**
	 * 
	 * @param categories
	 */
	public void refreshFeeds(List<Category> categories) {
		DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) this
				.getModel().getRoot();
		rootNode.removeAllChildren();

		DefaultMutableTreeNode generated = generateHierarchy(categories);

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
			FeedSelectedListener sourceSelectedListener) {
		listenerList.add(FeedSelectedListener.class, sourceSelectedListener);
	}

	public void removeSourceSelectedListener(FeedSelectedListener l) {
		listenerList.remove(FeedSelectedListener.class, l);
	}

	protected void fireSourceSelectedEvent(FeedSelectedEvent event) {
		for (FeedSelectedListener l : listenerList
				.getListeners(FeedSelectedListener.class)) {
			l.onSourceSelected(event);
		}
	}

	// #endregion
}
