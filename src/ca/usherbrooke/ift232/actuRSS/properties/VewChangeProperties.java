package ca.usherbrooke.ift232.actuRSS.properties;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;

import ca.usherbrooke.ift232.actuRSS.controller.Controller;

public class VewChangeProperties extends ParamDialog {

	
	private JPanel content;
	private JPanel control;
	private JButton okbutton;
	private JButton cancel;
	private JButton defaultbutton;
	JPanel desplay;
	JRadioButton all,favorite,notRead,read;
	JPanel newsNumber;
	JSpinner spinNumber;
	
	
	public VewChangeProperties(JFrame parent, String title, boolean modal) {
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
		
		content.add(desplay);
		content.add(newsNumber);
		

		control = new JPanel();
		
		okbutton = new JButton("OK");
		okbutton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				finishDialog();
			}
		});
		
		cancel = new JButton("Annuler");
		cancel.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				closeDialog();
			}
		});
		
		defaultbutton = new JButton("Reinitialiser");
		defaultbutton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				renewDialog();
			}
		});

		control.add(okbutton);
		control.add(cancel);
		control.add(defaultbutton);

		this.getContentPane().add(content,BorderLayout.CENTER);
		this.getContentPane().add(control,BorderLayout.SOUTH);

	}
	
	public void finishDialog(){
		String newDisplay;
		String newNumber;
		
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
		Controller.properties.setProperty("News Number", newNumber);
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
	}

	public void renewDialog(){
		spinNumber.setValue(20);
		notRead.setSelected(true);
	}
}
