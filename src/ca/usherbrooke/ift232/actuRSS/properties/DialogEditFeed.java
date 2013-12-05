package ca.usherbrooke.ift232.actuRSS.properties;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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

public class DialogEditFeed  extends JDialog {

	ArrayList<String> listeCategories = new ArrayList<String>();
	Feed feed = null;
	JPanel content;
	JPanel control;
	JButton okbutton;
	JButton cancel;
	JButton defaultbutton;
	JPanel namepanel;
	JPanel urlpanel;
	JPanel categorypanel;
	JButton newCategory;
	JComboBox<String> category;
	JTextField name;
	JTextField url;

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
		name = new JTextField("test");//feed.getTitle());
		name.setPreferredSize(new Dimension(300,20));
		name.setMaximumSize(new Dimension(300,20));
		name.setMinimumSize(new Dimension(200,20));
		namepanel.add(name);
		
		urlpanel = new JPanel();
		urlpanel.setBorder(BorderFactory.createTitledBorder("URL du Flux"));
		url = new JTextField("test");//feed.getUrl());
		url.setPreferredSize(new Dimension(300,20));
		url.setMaximumSize(new Dimension(300,20));
		url.setMinimumSize(new Dimension(200,20));
		urlpanel.add(url);
		
		categorypanel = new JPanel();
		categorypanel.setBorder(BorderFactory.createTitledBorder("Categories"));
		categorypanel.setPreferredSize(new Dimension(300, 60));
		category = new JComboBox<String>();
		for(String s : listeCategories){
			category.addItem(s);
		}
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
		this.setVisible(true);
	}
	
	public void finishedDialog(){
		if(name.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Nom manquant", "Erreur", JOptionPane.WARNING_MESSAGE);	
		}
		else if(url.getText().equals("")){
			JOptionPane.showMessageDialog(null, "URL manquant", "Erreur", JOptionPane.WARNING_MESSAGE);
		}
		else if(category.getSelectedIndex() == 0){
			JOptionPane.showMessageDialog(null, "Categorie manquant", "Erreur", JOptionPane.WARNING_MESSAGE);
		}
		else	
			closeDialog();
	}

	public void closeDialog(){
		this.dispose();
	}
	
	public void renewDialog(){
		name.setText("test");//feed.getTitle());
		url.setText("test");//feed.getUrl());
		category.setSelectedIndex(0);
	}

	public void newCategorie(){
	    String nom = JOptionPane.showInputDialog(null, "Nouvelle categorie :", "", JOptionPane.QUESTION_MESSAGE);
		listeCategories.add(nom);
	}
	
	public void listerCategories(List<Category> categories) {
		for(Category c : categories){
			listeCategories.add(c.getName());
		}
		category.removeAll();
		for(String s : listeCategories){
			category.addItem(s);
		}
	}
	
	public void setFeed(Feed feed){
		this.feed = feed;
	}
	
	public void addListener(ActionListener e)
	{
		okbutton.addActionListener(e);
		cancel.addActionListener(e);
		defaultbutton.addActionListener(e);	
		newCategory.addActionListener(e);
	}
}

