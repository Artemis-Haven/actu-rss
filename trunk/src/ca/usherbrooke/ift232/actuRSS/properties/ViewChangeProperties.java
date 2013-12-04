package ca.usherbrooke.ift232.actuRSS.properties;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ca.usherbrooke.ift232.actuRSS.controller.Controller;

public class ViewChangeProperties extends ParamDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -977093876115135886L;
	private JPanel content;
	private JPanel control;
	private JButton okbutton;
	private JButton cancel;
	private JButton defaultbutton;
	JPanel desplay;
	JRadioButton all,favorite,notRead,read;
	JPanel newsNumber;
	JSpinner spinNumber;
	JPanel fileSearch;
	JTextField path;
	JButton openFile;
	final JFileChooser chooseCSS = new JFileChooser("src/resources");
	
	public ViewChangeProperties(JFrame parent, String title, boolean modal) {
		super(parent, title, modal);
		this.initDialog();
	}

	public void showDialog(){
		super.showDialog();
	}
	public void initDialog() {
		content = new JPanel();
		
		desplay = new JPanel();
		desplay.setBorder(BorderFactory.createTitledBorder("Affichage par defaut"));
		all = new JRadioButton("Tout");
		favorite = new JRadioButton("Favoris");
		notRead = new JRadioButton("Non lu");
		read = new JRadioButton("Lu");
		if(Controller.defaultDisplay.matches("All"))
			all.setSelected(true);
		else if(Controller.defaultDisplay.matches("Favorite"))
			favorite.setSelected(true);
		else if(Controller.defaultDisplay.matches("Not Read"))
			notRead.setSelected(true);
		else
			read.setSelected(true);
		ButtonGroup gp = new ButtonGroup();
		gp.add(all);
		gp.add(favorite);
		gp.add(notRead);
		gp.add(read);
		desplay.add(all);
		desplay.add(favorite);
		desplay.add(notRead);
		desplay.add(read);
		
		newsNumber = new JPanel();
		newsNumber.setBorder(BorderFactory.createTitledBorder("Nombre de news par flux RSS"));
		spinNumber = new JSpinner();
		newsNumber.setPreferredSize(new Dimension(300, 60));
		spinNumber.setPreferredSize(new Dimension(100, 30));
		spinNumber.setValue(Integer.parseInt(Controller.properties.getProperty("News Number")));
		newsNumber.add(spinNumber);
		fileSearch = new JPanel();
		fileSearch.setBorder(BorderFactory.createTitledBorder("Style CSS d'affichage"));
		path = new JTextField(Controller.properties.getProperty("CSS Style"));
		path.setPreferredSize(new Dimension(300,20));
		path.setMaximumSize(new Dimension(300,20));
		path.setMinimumSize(new Dimension(200,20));
		openFile = new JButton("Charger");
		openFile.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				int returnVal =  chooseCSS.showOpenDialog(ViewChangeProperties.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = chooseCSS.getSelectedFile();
		            path.setText(file.getPath());
				}
			}
		});
		fileSearch.add(path);
		fileSearch.add(openFile);
		content.add(desplay);
		content.add(newsNumber);
		content.add(fileSearch);
		

		control = new JPanel();
		
		okbutton = new JButton("OK");
		okbutton.setActionCommand("OkPref");
		
		
		cancel = new JButton("Annuler");
		cancel.setActionCommand("AnnulerPref");
		
		
		defaultbutton = new JButton("Reinitialiser");
		defaultbutton.setActionCommand("ReinitialiserPref");
		
		

		control.add(okbutton);
		control.add(cancel);
		control.add(defaultbutton);

		this.getContentPane().add(content,BorderLayout.CENTER);
		this.getContentPane().add(control,BorderLayout.SOUTH);

	}
	
	public void finishDialog(){
		String newDisplay;
		String newNumber;
		String newPath;
		
		if(all.isSelected())
			newDisplay = "All";
		else if(favorite.isSelected())
			newDisplay = "Favorite";
		else if(notRead.isSelected())
			newDisplay = "Not Read";
		else
			newDisplay = "Read";
		
		Controller.properties.setProperty("Default Display", newDisplay);
		Controller.defaultDisplay  = newDisplay;
		
		newNumber = spinNumber.getValue().toString();
		Controller.properties.setProperty("NewsNumber", newNumber);
		newPath = path.getText();
		System.out.println(newPath);
		Controller.properties.setProperty("CSS Style", newPath);
		Controller.properties.save();
		super.closeDialog();
	}
	
	public void closeDialog(){
		super.closeDialog();
		spinNumber.setValue(Integer.parseInt(Controller.properties.getProperty("News Number")));
		if(Controller.defaultDisplay.matches("All"))
			all.setSelected(true);
		else if(Controller.defaultDisplay.matches("Favorite"))
			favorite.setSelected(true);
		else if(Controller.defaultDisplay.matches("Not Read"))
			notRead.setSelected(true);
		else
			read.setSelected(true);
		path.setText(Controller.properties.getProperty("CSS Style"));
	}

	public void renewDialog(){
		spinNumber.setValue(20);
		notRead.setSelected(true);
		path.setText("src/resources/default.css");
	}
	
	public void addListener(ActionListener e)
	{
		okbutton.addActionListener(e);
		cancel.addActionListener(e);
		defaultbutton.addActionListener(e);
	}
	
	
}
