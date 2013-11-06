package ca.usherbrooke.ift232.actuRSS.view;

import java.awt.BorderLayout;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

/**
 * Actu-RSS
 * Couche : View
 * Date de creation : 2013-11-01
 * Description : 
 *   La Toolbar est la barre avec les boutons qui
 *   figure en haut de notre fenetre principale.
 *   
 * @author Prenom Nom
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
	private JToggleButton syncBtn;
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
		
		syncBtn = buildToggleButton("/img/sync.png");
		leftPanel.add(syncBtn);

		buildButtonGroup();
		
		rightPanel = new JPanel();
		
		readBtn = buildToggleButton("/img/read.png");
		rightPanel.add(readBtn);
		
		favBtn = buildToggleButton("/img/favorite.png");
		rightPanel.add(favBtn);
		
		menuBtn = new Menu();
		rightPanel.add(menuBtn);

		this.add(leftPanel, BorderLayout.WEST);
		this.add(rightPanel, BorderLayout.EAST);
	}

	private void buildButtonGroup() {
		allNewsBtn = new JToggleButton("Tout", true);
		unreadNewsBtn = new JToggleButton("Non lus");
		favNewsBtn = new JToggleButton("Favoris");
		
		btnGroup = new ButtonGroup();
		btnGroup.add(allNewsBtn);
		btnGroup.add(unreadNewsBtn);
		btnGroup.add(favNewsBtn);
		leftPanel.add(allNewsBtn, BorderLayout.WEST);
		leftPanel.add(unreadNewsBtn, BorderLayout.WEST);
		leftPanel.add(favNewsBtn, BorderLayout.WEST);
	}

	private JToggleButton buildToggleButton(String path) {
		JToggleButton btn = new JToggleButton(new ImageIcon(getClass().getResource(path)));
		return btn;
	}
}
