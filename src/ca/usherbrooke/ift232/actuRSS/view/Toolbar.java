package ca.usherbrooke.ift232.actuRSS.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import ca.usherbrooke.ift232.actuRSS.controller.Controller;
import ca.usherbrooke.ift232.actuRSS.controller.command.ActionAll;
import ca.usherbrooke.ift232.actuRSS.controller.command.ActionFavButton;
import ca.usherbrooke.ift232.actuRSS.controller.command.ActionFavorite;
import ca.usherbrooke.ift232.actuRSS.controller.command.ActionNotRead;
import ca.usherbrooke.ift232.actuRSS.controller.command.ActionRead;
import ca.usherbrooke.ift232.actuRSS.controller.command.ActionReadButton;
import ca.usherbrooke.ift232.actuRSS.controller.command.ActionSync;




/**
 * Actu-RSS
 * Couche : View
 * Date de creation : 2013-11-01
 * Description : 
 *   La Toolbar est la barre avec les boutons qui
 *   figure en haut de notre fenetre principale.
 *   
 * @author R�mi Patrizio, Bastien Meunier, Yann Ser�e
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
	private HashMap action;

	/**
	 * Constructeur : il ajoute tous les boutons
	 * et les configure
	 * 
	 */
	public Toolbar(HashMap action){

		this.action = action;
		this.setLayout(new BorderLayout());

		leftPanel = new JPanel();

		syncBtn = new JButton(new ImageIcon(getClass().getResource("/resources/sync.png")));
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

		readBtn = buildToggleButton("/resources/read.png");
		readBtn.setActionCommand("Read");
		readBtn.setEnabled(false);

		rightPanel.add(readBtn);


		//TODO voir pourquoi l image ne s affiche plus pourtant l evenement marche
		favBtn = buildToggleButton("/resources/favorite.png");
		//favBtn.setActionCommand("FavBtn");
		favBtn.setEnabled(false);

		rightPanel.add(favBtn);

		menuBtn = new Menu(action);
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

	/**
	 * @return la liste des actions
	 */
	public HashMap getAction() {
		return action;
	}

	public void setAction(HashMap action) {
		this.action = action;
		menuBtn.setAction(action);
	}

	/**
	 * Construit les 4 boutons du choix du filtre :
	 * "Tout", "Non lus", "Favoris" ou "Lus"
	 */
	private void buildButtonGroup() 
	{
		allNewsBtn = new JToggleButton("Tout");
		unreadNewsBtn = new JToggleButton("Non lus");
		favNewsBtn = new JToggleButton("Favoris");
		readNewsBtn = new JToggleButton("Lus");

		if(Controller.properties.getProperty("Default Display").contains("AllFilter"))
			allNewsBtn.setSelected(true);
		else if(Controller.properties.getProperty("Default Display").contains("NotReadFilter"))
			unreadNewsBtn.setSelected(true);
		else if(Controller.properties.getProperty("Default Display").contains("FavoriteFilter"))
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

	class ToutAction extends AbstractAction {
		public ToutAction(String text, ImageIcon icon,
				String desc, Integer mnemonic) {
			super(text, icon);
			putValue(SHORT_DESCRIPTION, desc);
			putValue(MNEMONIC_KEY, mnemonic);
		}
		public void actionPerformed(ActionEvent e) {
			System.out.println("Action for first button/menu item");
		}
	}

	/**
	 * Construit un toggleButton avec l'image dont
	 * le chemin est en parametre
	 * 
	 * @param path le chemin vers l'image du bouton
	 * @return un ToggleButton
	 */
	private JToggleButton buildToggleButton(String path) {
		JToggleButton btn = new JToggleButton(new ImageIcon(getClass().getResource(path)));
		btn.setBorderPainted(false);
		btn.setFocusPainted(false);
		return btn;
	}

	public void addListener()
	{

		//favBtn.setAction((ActionFavButton)action.get("ActionFavButton"));
		//favBtn.addActionListener(e);	
		favBtn.addActionListener((ActionFavButton)action.get("ActionFavButton"));		
		//allNewsBtn.addActionListener(e);
		allNewsBtn.addActionListener((ActionAll)action.get("ActionAll"));
		
		unreadNewsBtn.addActionListener((ActionNotRead)action.get("ActionNotRead"));
		favNewsBtn.addActionListener((ActionFavorite)action.get("ActionFavorite"));
		
		readNewsBtn.addActionListener((ActionRead)action.get("ActionRead"));

		syncBtn.addActionListener((ActionSync)action.get("ActionSync"));
		
		
		
		readBtn.addActionListener((ActionReadButton)action.get("ActionReadButton"));


		menuBtn.addListener();	

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
