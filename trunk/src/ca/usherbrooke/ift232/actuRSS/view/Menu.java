package ca.usherbrooke.ift232.actuRSS.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;

import ca.usherbrooke.ift232.actuRSS.controller.command.ActionPref;

public class Menu extends JMenuBar{
	
	private JMenuBar menuBar;
	private JMenu mnParametre;
	private JMenuItem paraPreferency;
	private JMenuItem paraSource;
	private JMenuItem paraAbout;
	private JMenuItem paraHelp;
	private HashMap action;
	
	

	public Menu(HashMap action){
		
		this.action = action;
		setRequestFocusEnabled(false);
		setOpaque(false);
		setMaximumSize(new Dimension(24, 24));
		setIgnoreRepaint(true);
		setBorderPainted(false);
		setAlignmentY(Component.CENTER_ALIGNMENT);
		setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		this.setLayout(new BorderLayout());
		
		mnParametre = new JMenu("");
		mnParametre.setIcon(new ImageIcon(Menu.class.getResource("/resources/properties.png")));
		mnParametre.setPreferredSize(new Dimension(50, 30));
		this.add(mnParametre);
		mnParametre.setAutoscrolls(true);
		mnParametre.setAlignmentY(Component.TOP_ALIGNMENT);
		mnParametre.setAlignmentX(Component.LEFT_ALIGNMENT);
		mnParametre.setHorizontalTextPosition(SwingConstants.CENTER);
		mnParametre.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		mnParametre.setBorderPainted(true);
		//mnParametre.setBorder(new LineBorder(new Color(0, 0, 0), 1, false));
		mnParametre.setHorizontalAlignment(SwingConstants.CENTER);
		
		paraPreferency = new JMenuItem("Pr\u00E9f\u00E9rences");
		paraPreferency.setActionCommand("Pref");
		
		mnParametre.add(paraPreferency);

		paraSource = new JMenuItem("G\u00E9rer les sources");
		paraSource.setActionCommand("GererSources");
		mnParametre.add(paraSource);
		
		paraHelp = new JMenuItem("Help");
		
		mnParametre.add(paraHelp);
		
		paraAbout = new JMenuItem("About");
		mnParametre.add(paraAbout);
	}
	
	public HashMap getAction() {
		return action;
	}

	public void setAction(HashMap action) {
		this.action = action;
	}
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	public void addListener(ActionListener e)
	{		
		paraPreferency.addActionListener((ActionPref)action.get("ActionPref"));
		paraSource.addActionListener(e);
		paraAbout.addActionListener(e);		
		paraHelp.addActionListener(e);		

	}
	

}
