package ca.usherbrooke.ift232.actuRSS.view.treepicker;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTree;
import javax.swing.event.EventListenerList;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import ca.usherbrooke.ift232.actuRSS.Category;
import ca.usherbrooke.ift232.actuRSS.Feed;

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
				if (obj instanceof Feed)
					fireFeedSelectedEvent(new FeedSelectedEvent(this,
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
	public List<Feed> getSelectedFeeds() {
		List<Feed> feedSelected = new ArrayList<Feed>();

		TreePath[] selectedPaths = this.getSelectionPaths();

		if (selectedPaths != null) {
			for (TreePath path : selectedPaths) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) path
						.getLastPathComponent();
				feedSelected.add((Feed) node.getUserObject());
			}
		}

		return feedSelected;

	}

	// #region SourceSelectedEvent

	private final EventListenerList listenerList = new EventListenerList();

	public void addFeedSelectedListener(
			FeedSelectedListener feedSelectedListener) {
		listenerList.add(FeedSelectedListener.class, feedSelectedListener);
	}

	public void removeFeedSelectedListener(FeedSelectedListener l) {
		listenerList.remove(FeedSelectedListener.class, l);
	}

	protected void fireFeedSelectedEvent(FeedSelectedEvent event) {
		for (FeedSelectedListener l : listenerList
				.getListeners(FeedSelectedListener.class)) {
			l.onFeedSelected(event);
		}
	}

	// #endregion
}
