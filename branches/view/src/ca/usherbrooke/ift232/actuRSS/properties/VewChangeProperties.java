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

public class VewChangeProperties extends ParamDialog {

	
	ProgramProperties properties = ProgramProperties.getInstance();
	private JPanel content;
	private JPanel controle;
	private JButton okbutton;
	private JButton cancel;
	private JButton defaultbutton;
	JPanel afficherBase;
	JRadioButton tous,favoris,nonlu,lu;
	JPanel newsNumber;
	JSpinner spinNumber;
	
	
	public VewChangeProperties(JFrame parent, String title, boolean modal) {
		super(parent, title, modal);
		this.initDialog();
	}

	public void initDialog() {
		content = new JPanel();
		
		afficherBase = new JPanel();
		afficherBase.setBorder(BorderFactory.createTitledBorder("Affichage par defaut"));
		tous = new JRadioButton("Tous");
		favoris = new JRadioButton("Favoris");
		nonlu = new JRadioButton("Non lu");
		lu = new JRadioButton("Lu");
		String afficher = properties.getProperty("AfficherBase");
		if(afficher.matches("Tous"))
			tous.setSelected(true);
		else if(afficher.matches("Favoris"))
			favoris.setSelected(true);
		else if(afficher.matches("Non Lu"))
			nonlu.setSelected(true);
		else
			lu.setSelected(true);
		ButtonGroup gp = new ButtonGroup();
		gp.add(tous);
		gp.add(favoris);
		gp.add(nonlu);
		gp.add(lu);
		afficherBase.add(tous);
		afficherBase.add(favoris);
		afficherBase.add(nonlu);
		afficherBase.add(lu);
		
		newsNumber = new JPanel();
		newsNumber.setBorder(BorderFactory.createTitledBorder("Nombre de news par flux RSS"));
		spinNumber = new JSpinner();
		newsNumber.setPreferredSize(new Dimension(300, 50));
		spinNumber.setPreferredSize(new Dimension(100, 20));
		String initValue = properties.getProperty("NewsNumber");
		spinNumber.setValue(Integer.parseInt(initValue));
		newsNumber.add(spinNumber);
		
		content.add(afficherBase);
		content.add(newsNumber);
		

		controle = new JPanel();
		
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

		controle.add(okbutton);
		controle.add(cancel);
		controle.add(defaultbutton);

		this.getContentPane().add(content,BorderLayout.CENTER);
		this.getContentPane().add(controle,BorderLayout.SOUTH);

	}
	
	public void finishDialog(){
		String newAfficher;
		String newNumber;
		
		if(tous.isSelected())
			newAfficher = "Tous";
		else if(favoris.isSelected())
			newAfficher = "Favoris";
		else if(nonlu.isSelected())
			newAfficher = "Non lu";
		else
			newAfficher = "Lu";
		
		properties.setProperty("AfficherBase", newAfficher);
		
		newNumber = spinNumber.getValue().toString();
		properties.setProperty("NewsNumber", newNumber);
		properties.save();
		super.closeDialog();
	}
	
	public void closeDialog(){
		super.closeDialog();
	}

	public void renewDialog(){
		spinNumber.setValue(20);
		tous.setSelected(true);
	}
}
