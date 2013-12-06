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
/**
 * Classe manipulant l'interface graphique gérant l'ajout d'un feed
 * 
 * @author bastien
 *
 */
/**
 * @author bastien
 *
 */
public class DialogAddFeed extends JDialog {

	ArrayList<String> listeCategories = new ArrayList<String>();
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
	String thename;
	String theurl;
	String thecategory;
	

	public DialogAddFeed(JFrame parent, String title, boolean modal){
		super(parent, title, modal);
		this.setSize(500, 300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		listeCategories.add("Choisir une categorie");
		initDialog();
	}

	/**
	 * Initialise l'interface graphique
	 * 
	 */
	public void initDialog() {
		content = new JPanel();

		namepanel = new JPanel();
		namepanel.setBorder(BorderFactory.createTitledBorder("Nom du Flux"));
		name = new JTextField();
		name.setPreferredSize(new Dimension(300,20));
		name.setMaximumSize(new Dimension(300,20));
		name.setMinimumSize(new Dimension(200,20));
		namepanel.add(name);
		
		urlpanel = new JPanel();
		urlpanel.setBorder(BorderFactory.createTitledBorder("URL du Flux"));
		url = new JTextField();
		url.setPreferredSize(new Dimension(300,20));
		url.setMaximumSize(new Dimension(300,20));
		url.setMinimumSize(new Dimension(200,20));
		urlpanel.add(url);
		
		categorypanel = new JPanel();
		categorypanel.setBorder(BorderFactory.createTitledBorder("Categories"));
		categorypanel.setPreferredSize(new Dimension(300, 70));
		category = new JComboBox<String>();
		category.addItem(listeCategories.get(0));
		newCategory = new JButton("Nouvelle Categorie");
		newCategory.setActionCommand("NewCatAddSource");
		categorypanel.add(category);
		categorypanel.add(newCategory);
		
		content.add(namepanel,BorderLayout.NORTH);
		content.add(urlpanel,BorderLayout.WEST);
		content.add(categorypanel,BorderLayout.EAST);
		
		control = new JPanel();

		okbutton = new JButton("OK");
		okbutton.setEnabled(false);
		okbutton.setActionCommand("OkAddSource");

		cancel = new JButton("Annuler");
		cancel.setActionCommand("CancelAddSource");

		defaultbutton = new JButton("Reinitialiser");
		defaultbutton.setActionCommand("RenewAddSource");
		

		control.add(okbutton);
		control.add(cancel);
		control.add(defaultbutton);
		
		
		this.getContentPane().add(content,BorderLayout.CENTER);
		this.getContentPane().add(control,BorderLayout.SOUTH);

	}

	
	public void showDialog(){
		this.setVisible(true);
	}
	
	public void verifie(){
		
		boolean canNew = true;
		
		if(name.getText().equals("")){
			canNew = false;
		}
		else if(url.getText().equals("")){
			canNew = false;
		}
		else if(category.getSelectedIndex() == 0){
			canNew = false;
		}
		else
			okbutton.setEnabled(true);
	}
	
	public void finishedDialog(){
			thename = name.getText();
			theurl = url.getText();
			thecategory = (String) category.getSelectedItem();
			closeDialog();
	}

	public void closeDialog(){
		this.renewDialog();
		this.dispose();
	}
	
	public void renewDialog(){
		name.setText("");
		url.setText("");
		category.setSelectedIndex(0);
	}

	public void newCategorie(){
	    String nom = JOptionPane.showInputDialog(null, "Nouvelle categorie :", "", JOptionPane.QUESTION_MESSAGE);
	    if(name.getText().equals("")){
			JOptionPane.showConfirmDialog(null, "Nom manquant", "Erreur", JOptionPane.WARNING_MESSAGE);
			
		}
	    else listeCategories.add(nom);
		
	}
	
	
	/**
	 * Construit la liste des noms des categories
	 * @param categories
	 */
	public void listerCategories(List<Category> categories) {
		for(Category c : categories){
			listeCategories.add(c.getName());
		}
		category.removeAllItems();
		for(String s : listeCategories){
			category.addItem(s);
		}
		listeCategories.clear();
		listeCategories.add("Nouvelle Categorie");
	}
	
	public String getName(){
		return thename;
	}
	
	public String getUrl(){
		return theurl;
	}
	
	public String getCategory(){
		return thecategory;
	}
	
	public void addListener(ActionListener e)
	{
		okbutton.addActionListener(e);
		cancel.addActionListener(e);
		defaultbutton.addActionListener(e);	
		newCategory.addActionListener(e);
	}
}
