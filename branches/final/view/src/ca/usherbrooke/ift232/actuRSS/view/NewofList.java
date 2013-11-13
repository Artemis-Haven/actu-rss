package ca.usherbrooke.ift232.actuRSS.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import ca.usherbrooke.ift232.actuRSS.model.News;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.BoxLayout;

import java.awt.CardLayout;

import javax.swing.JTextArea;

public class NewofList extends JPanel{
	
	private News theNew;
	private ImageIcon icon;
	
	
	public NewofList(News news){
		
		setLayout(new BorderLayout(0, 0));
		setTheNew(news);
		
		JTextPane txtpnTitle = new JTextPane();
		txtpnTitle.setContentType("text/plain");
		txtpnTitle.setText("title");
		add(txtpnTitle, BorderLayout.NORTH);
		
		JTextPane txtpnContents = new JTextPane();
		txtpnContents.setText("contents");
		add(txtpnContents, BorderLayout.CENTER);
		
		
		
		
	}

	public News getTheNew() {
		return theNew;
	}

	public void setTheNew(News theNew) {
		this.theNew = theNew;
	}

}
