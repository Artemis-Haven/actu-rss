package ca.usherbrooke.ift232.actuRSS.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
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
		setRequestFocusEnabled(false);
		setOpaque(false);
		setMaximumSize(new Dimension(24, 24));
		setIgnoreRepaint(true);
		setBorderPainted(false);
		setAlignmentY(Component.CENTER_ALIGNMENT);
		setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		this.setLayout(new BorderLayout());
		
		mnParametre = new JMenu("");
		mnParametre.setIcon(new ImageIcon(Menu.class.getResource("/img/properties.png")));
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
		
		paraPreferency = new JMenuItem("Préférences");
		mnParametre.add(paraPreferency);
		
		paraSource = new JMenuItem("Gérer les sources");
		mnParametre.add(paraSource);
		
		paraHelp = new JMenuItem("Help");
		mnParametre.add(paraHelp);
		
		paraAbout = new JMenuItem("?");
		mnParametre.add(paraAbout);
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

}
