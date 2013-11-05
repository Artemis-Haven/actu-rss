package ca.usherbrooke.ift232.actuRSS.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Menu extends JMenuBar{
	
	JMenuBar menuBar;
	JMenu mnParametre;
	JMenuItem paraPreferency;
	JMenuItem paraSource;
	JMenuItem paraAbout;
	JMenuItem paraHelp;
	
	public Menu(){
		
		this.setLayout(new BorderLayout());
		menuBar = new JMenuBar();
		menuBar.setMaximumSize(new Dimension(24, 24));
		
		mnParametre = new JMenu();
		mnParametre.setPreferredSize(new Dimension(50, 30));
		
		mnParametre.setAutoscrolls(true);
		mnParametre.setAlignmentY(Component.TOP_ALIGNMENT);
		mnParametre.setAlignmentX(Component.LEFT_ALIGNMENT);
		mnParametre.setHorizontalTextPosition(SwingConstants.CENTER);
		mnParametre.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		mnParametre.setBorderPainted(true);
		mnParametre.setBorder(new LineBorder(new Color(0, 0, 0), 1, false));
		mnParametre.setHorizontalAlignment(SwingConstants.CENTER);
		mnParametre.setIcon(new ImageIcon("D:\\Projet\\workspace\\ActuRSSvew\\src\\img\\properties.png"));
		
		paraPreferency = new JMenuItem("Préférences");
		mnParametre.add(paraPreferency);
		
		paraSource = new JMenuItem("Gérer les sources");
		mnParametre.add(paraSource);
		
		paraHelp = new JMenuItem("Help");
		mnParametre.add(paraHelp);
		
		paraAbout = new JMenuItem("?");
		mnParametre.add(paraAbout);
		
		menuBar.add(mnParametre);		
		this.add(menuBar);
	}

}
