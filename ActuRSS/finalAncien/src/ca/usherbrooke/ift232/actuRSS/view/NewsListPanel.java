package ca.usherbrooke.ift232.actuRSS.view;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class NewsListPanel extends JList {

	public NewsListPanel() {			
		super(new DefaultListModel<String>());

	}

}
