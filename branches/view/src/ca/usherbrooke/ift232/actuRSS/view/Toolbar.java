package ca.usherbrooke.ift232.actuRSS.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import ca.usherbrooke.ift232.actuRSS.controller.Controller;

/**
 * Actu-RSS
 * Couche : View
 * Date de creation : 2013-11-01
 * Description : 
 *   La Toolbar est la barre avec les boutons qui
 *   figure en haut de notre fenetre principale.
 *   
 * @author Rémi Patrizio, Bastien Meunier, Yann Serée
 * @version 0.1
 */
 
public class Toolbar extends JPanel {

	/**
	 * Variables privee
	 * @param Description d'une variable
	 *
	 */
	private JToggleButton allNewsBtn;
	private JToggleButton unreadNewsBtn;

	private JToggleButton favNewsBtn;
	private JToggleButton readNewsBtn;
	
	private JButton syncBtn;
	private JToggleButton readBtn;
	private JToggleButton favBtn;
	private ButtonGroup btnGroup;
	private Menu menuBtn;
	private JPanel leftPanel;
	private JPanel rightPanel;

	/**
	 * Constructeur
	 * 
	 */
	public Toolbar(){

		this.setLayout(new BorderLayout());
		
		leftPanel = new JPanel();
		
		syncBtn = new JButton(new ImageIcon(getClass().getResource("/img/sync.png")));
		syncBtn.setActionCommand("Sync");
		syncBtn.setBorderPainted(false);
		syncBtn.setFocusPainted(false);
		leftPanel.add(syncBtn);

		buildButtonGroup();
		
		rightPanel = new JPanel();
		rightPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		rightPanel.setOpaque(false);
		rightPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		rightPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		rightPanel.setSize(new Dimension(24, 24));
		
		readBtn = buildToggleButton("/img/read.png");
		readBtn.setActionCommand("Read");
		readBtn.setEnabled(false);
		
		rightPanel.add(readBtn);
		
		favBtn = buildToggleButton("/img/favorite.png");
		favBtn.setActionCommand("FavBtn");
		favBtn.setEnabled(false);
		
		rightPanel.add(favBtn);
		
		menuBtn = new Menu();
		menuBtn.setMaximumSize(new Dimension(24, 24));
		menuBtn.setRequestFocusEnabled(false);
		menuBtn.setOpaque(false);
		menuBtn.setIgnoreRepaint(true);
		menuBtn.setBorderPainted(false);
		menuBtn.setAlignmentY(Component.CENTER_ALIGNMENT);
		menuBtn.setAlignmentX(Component.RIGHT_ALIGNMENT);
		rightPanel.add(menuBtn);

		this.add(leftPanel, BorderLayout.WEST);
		this.add(rightPanel, BorderLayout.EAST);
	}

	private void buildButtonGroup() {
		allNewsBtn = new JToggleButton("Tout");
		unreadNewsBtn = new JToggleButton("Non lus");
		favNewsBtn = new JToggleButton("Favoris");
		readNewsBtn = new JToggleButton("Lus");
		
		if(Controller.afficherBase.equals("Tout"))
			allNewsBtn.setSelected(true);
		else if(Controller.afficherBase.equals("Nonlu"))
			unreadNewsBtn.setSelected(true);
		else if(Controller.afficherBase.equals("Favoris"))
			favNewsBtn.setSelected(true);
		else
			readNewsBtn.setSelected(true);
				
		allNewsBtn.setPreferredSize(new Dimension(100,30));
		allNewsBtn.setMinimumSize(new Dimension(100,30));
		
		unreadNewsBtn.setPreferredSize(new Dimension(100,30));
		unreadNewsBtn.setMinimumSize(new Dimension(100,30));
		
		favNewsBtn.setPreferredSize(new Dimension(100,30));
		favNewsBtn.setMinimumSize(new Dimension(100,30));
		
		readNewsBtn.setPreferredSize(new Dimension(100,30));
		readNewsBtn.setMinimumSize(new Dimension(100,30));
		
		
		btnGroup = new ButtonGroup();
		btnGroup.add(allNewsBtn);
		btnGroup.add(unreadNewsBtn);
		btnGroup.add(favNewsBtn);
		btnGroup.add(readNewsBtn);

		allNewsBtn.setFocusPainted(false);
		unreadNewsBtn.setFocusPainted(false);
		favNewsBtn.setFocusPainted(false);
		readNewsBtn.setFocusPainted(false);	
		
		
		leftPanel.add(allNewsBtn, BorderLayout.WEST);
		leftPanel.add(unreadNewsBtn, BorderLayout.WEST);
		leftPanel.add(favNewsBtn, BorderLayout.WEST);
		leftPanel.add(readNewsBtn, BorderLayout.WEST);
	}

	private JToggleButton buildToggleButton(String path) {
		JToggleButton btn = new JToggleButton(new ImageIcon(getClass().getResource(path)));
		btn.setBorderPainted(false);
		btn.setFocusPainted(false);
		return btn;
	}
	public void addListener(ActionListener e)
	{
		allNewsBtn.addActionListener(e);
		unreadNewsBtn.addActionListener(e);
		favNewsBtn.addActionListener(e);
		readNewsBtn.addActionListener(e);
		
		syncBtn.addActionListener(e);
		
		readBtn.addActionListener(e);
		favBtn.addActionListener(e);			
		
		menuBtn.addListener(e);	
		
	}
	
	public JToggleButton getUnreadNewsBtn() {return unreadNewsBtn;}
	public JToggleButton getFavNewsBtn() {return favNewsBtn;}
	public JButton getSyncBtn() {return syncBtn;}
	public JToggleButton getReadBtn() {return readBtn;}
	public JToggleButton getFavBtn() {return favBtn;}
	public ButtonGroup getBtnGroup() {return btnGroup;}
	public Menu getMenuBtn() {return menuBtn;}
	public JPanel getLeftPanel() {return leftPanel;}
	public JPanel getRightPanel() {return rightPanel;}
}
