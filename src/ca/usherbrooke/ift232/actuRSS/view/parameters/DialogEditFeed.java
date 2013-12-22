package ca.usherbrooke.ift232.actuRSS.view.parameters;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ca.usherbrooke.ift232.actuRSS.Category;
import ca.usherbrooke.ift232.actuRSS.Feed;
import ca.usherbrooke.ift232.actuRSS.News;
import ca.usherbrooke.ift232.actuRSS.controller.command.ActionOkEditSource;
import ca.usherbrooke.ift232.actuRSS.controller.command.ActionRenewEditSource;

public class DialogEditFeed  extends JDialog {

	ArrayList<String> listeCategories = new ArrayList<String>();
	private Feed feed = new Feed();
	private JPanel content;
	private JPanel control;
	private JButton okbutton;
	private JButton cancel;
	private JButton defaultbutton;
	private JPanel namepanel;
	private JPanel urlpanel;
	private JPanel categorypanel;
	private JButton newCategory;
	private JComboBox category;
	private JTextField name;
	private JTextField url;
	private String thename;
	private String theurl;
	private String thecategory;
	private HashMap action;

	public DialogEditFeed(JFrame parent, String title, boolean modal){
		super(parent, title, modal);
		this.setSize(500, 300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		listeCategories.add("Choisir une categorie");
		initDialog();
	}

	public void initDialog() {
		content = new JPanel();

		namepanel = new JPanel();
		namepanel.setBorder(BorderFactory.createTitledBorder("Nom du Flux"));
		name = new JTextField("");
		name.setPreferredSize(new Dimension(300,20));
		name.setMaximumSize(new Dimension(300,20));
		name.setMinimumSize(new Dimension(200,20));
		namepanel.add(name);

		urlpanel = new JPanel();
		urlpanel.setBorder(BorderFactory.createTitledBorder("URL du Flux"));
		url = new JTextField("");
		url.setPreferredSize(new Dimension(300,20));
		url.setMaximumSize(new Dimension(300,20));
		url.setMinimumSize(new Dimension(200,20));
		urlpanel.add(url);

		categorypanel = new JPanel();
		categorypanel.setBorder(BorderFactory.createTitledBorder("Categories"));
		categorypanel.setPreferredSize(new Dimension(300, 60));
		category = new JComboBox();
		category.addItem(listeCategories.get(0));
		newCategory = new JButton("Nouvelle Categorie");
		newCategory.setActionCommand("NewCatEditSource");
		categorypanel.add(category);
		categorypanel.add(newCategory);

		content.add(namepanel,BorderLayout.NORTH);
		content.add(urlpanel,BorderLayout.WEST);
		content.add(categorypanel,BorderLayout.EAST);

		control = new JPanel();

		okbutton = new JButton("OK");
		okbutton.setActionCommand("OkEditSource");

		cancel = new JButton("Annuler");
		cancel.setActionCommand("CancelEditSource");

		defaultbutton = new JButton("Reinitialiser");
		defaultbutton.setActionCommand("RenewEditSource");


		control.add(okbutton);
		control.add(cancel);
		control.add(defaultbutton);


		this.getContentPane().add(content,BorderLayout.CENTER);
		this.getContentPane().add(control,BorderLayout.SOUTH);

	}

	public void showDialog(){
		name.setText(feed.getTitle());
		url.setText(feed.getUrl());
		category.setSelectedItem(feed.getNameCategory());
		this.setVisible(true);
	}

	public boolean Valide(){
		if(name.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Nom manquant", "Erreur", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		else if(url.getText().equals("")){
			JOptionPane.showMessageDialog(null, "URL manquant", "Erreur", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		else if(category.getSelectedIndex() == 0){
			JOptionPane.showMessageDialog(null, "Categorie manquant", "Erreur", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		else 
			return true;
	}

	public void finishedDialog(){
		thename = name.getText();
		theurl = url.getText();
		thecategory = (String) category.getSelectedItem();
		closeDialog();
	}

	public void closeDialog(){
		renewDialog();
		this.dispose();
	}

	public void renewDialog(){
		name.setText(feed.getTitle());
		url.setText(feed.getUrl());
		category.setSelectedIndex(0);
	}

	public String newCategorie(){
		String nom = JOptionPane.showInputDialog(null, "Nouvelle categorie :", "", JOptionPane.QUESTION_MESSAGE);
		while(nom != null && nom.equals("")){
			JOptionPane.showMessageDialog(null, "Nom Categorie invalide", "Erreur", JOptionPane.WARNING_MESSAGE);
			nom = JOptionPane.showInputDialog(null, "Nouvelle categorie :", "", JOptionPane.QUESTION_MESSAGE);
		}
		if(nom != null){
			category.addItem(nom);
			category.setSelectedItem(nom);
		}
		return nom;
	}

	public void listerCategories(List<Category> categories) {
		for(Category c : categories){
			listeCategories.add(c.getName());
		}
		category.removeAllItems();
		for(String s : listeCategories){
			category.addItem(s);
			System.out.println(s);
		}
		listeCategories.clear();
		listeCategories.add("Choisir une categorie");
	}

	public String getName(){
		return thename;
	}

	public String getUrl(){
		return theurl;
	}

	public int getId(){
		return feed.getId();
	}

	public String getCategory(){
		return thecategory;
	} 
	public void setFeed(Feed feed){
		this.feed = feed;
	}

	public List<News> getNews(){
		return this.feed.getListNews();
	}
	public Feed getFeed(){
		return this.feed;
	}

	public HashMap getAction() {
		return action;
	}

	public void setAction(HashMap action) {
		this.action = action;
	}

	public void addListener(ActionListener e)
	{
		okbutton.addActionListener((ActionOkEditSource)action.get("ActionOkEditSource"));
		cancel.addActionListener(e);
		defaultbutton.addActionListener((ActionRenewEditSource)action.get("ActionRenewEditSource"));	
		newCategory.addActionListener(e);
	}
}

