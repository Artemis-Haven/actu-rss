package ca.usherbrooke.ift232.actuRSS.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ca.usherbrooke.ift232.actuRSS.model.Model;
import ca.usherbrooke.ift232.actuRSS.view.View;

public class Controller implements ActionListener{
	
	private Model model;
	private View view;	
	
	
	public Controller(Model model, View view) {		
		this.model = model;
		this.view = view;
		
		//TODO view.addListener((ActionListener)this);
		model.addObserver(view);
		model.notifyObserver();
	}





	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		String action = arg0.getActionCommand();
		Object source = arg0.getSource();
		
	}

}
