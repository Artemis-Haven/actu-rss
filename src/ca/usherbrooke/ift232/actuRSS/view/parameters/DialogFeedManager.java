package ca.usherbrooke.ift232.actuRSS.view.parameters;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ca.usherbrooke.ift232.actuRSS.Category;
import ca.usherbrooke.ift232.actuRSS.controller.command.ActionAddSource;
import ca.usherbrooke.ift232.actuRSS.controller.command.ActionDeleteCategorie;
import ca.usherbrooke.ift232.actuRSS.controller.command.ActionDeleteSource;
import ca.usherbrooke.ift232.actuRSS.controller.command.ActionEditSource;
import ca.usherbrooke.ift232.actuRSS.controller.command.ActionExitSource;
import ca.usherbrooke.ift232.actuRSS.view.treepicker.TreePicker;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class DialogFeedManager extends ParamDialog {

	private JPanel content;
	private TreePicker tree;
	private JPanel control;
	private JButton add;
	private JButton delete;
	private JButton update;
	private JButton deleteCategorie;
	private JButton exit;
	private JScrollPane treebar;
	private HashMap action;
	
	List<Category> categories = new ArrayList<Category>();

	public DialogFeedManager(JFrame parent, String title, boolean modal, HashMap action) {
		super(parent, title, modal);
		this.action = action;
		this.initDialog();
	}

	private void initDialog() {
		content = new JPanel();
		content.setBorder(BorderFactory.createTitledBorder("Liste des flux"));

		control = new JPanel(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
				new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));;

				
				tree = new TreePicker(categories, false);
				tree.setBackground(Color.WHITE);
				
				treebar = new JScrollPane(tree);
				treebar.setPreferredSize(new Dimension(300,330));
				content.add(treebar,BorderLayout.CENTER);

				add = new JButton("Ajouter un Flux");
				add.setActionCommand("AddSource");
				control.add(add,"10, 8");

				delete = new JButton("Supprimer");
				delete.setEnabled(false);
				delete.setActionCommand("DeleteSource");

				control.add(delete,"10, 18");

				update = new JButton("Modifier");
				update.setEnabled(false);
				update.setActionCommand("EditSource");
				control.add(update,"10, 28");
				
				deleteCategorie = new JButton("Supprimer Categorie");
				deleteCategorie.setEnabled(false);
				deleteCategorie.setActionCommand("DeleteCategorie");
				control.add(deleteCategorie, "10, 38");

				exit = new JButton("Fermer");
				exit.setActionCommand("ExitSource");
				control.add(exit,"10, 54, default, bottom");
				
				this.getContentPane().add(content,BorderLayout.CENTER);
				this.getContentPane().add(control,BorderLayout.EAST);

	}
	
	public HashMap getAction() {
		return action;
	}

	public void setAction(HashMap action) {
		this.action = action;
	}


	public void showDialog(){
		putNotEditable();
		super.showDialog();
	}

	public void finishedDialog(){
		super.closeDialog();
	}
	
	public void setCategories(List<Category> list) {
		categories = list;
		tree.refreshFeeds(categories);
		
	}
	
	public void putEditable(){
		this.delete.setEnabled(true);
		this.update.setEnabled(true);
	}
	
	public void putDeleteCategoryEditable(boolean editable){
		this.deleteCategorie.setEnabled(editable);
	}
	
	public void putNotEditable(){
		this.delete.setEnabled(false);
		this.update.setEnabled(false);
	}
	
	public void setTree(TreePicker aTree) {
		tree = aTree;
	}
	public TreePicker getManageTree() {
		return tree;
	}

	public void addListener(ActionListener e)
	{
		add.addActionListener((ActionAddSource)action.get("ActionAddSource"));
		delete.addActionListener((ActionDeleteSource)action.get("ActionDeleteSource"));
		update.addActionListener((ActionEditSource)action.get("ActionEditSource"));
		exit.addActionListener((ActionExitSource)action.get("ActionExitSource"));
		deleteCategorie.addActionListener((ActionDeleteCategorie)action.get("ActionDeleteCategorie"));
	}
}

