package ca.usherbrooke.ift232.actuRSS.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

import ca.usherbrooke.ift232.actuRSS.model.Model;
import ca.usherbrooke.ift232.actuRSS.view.View;

public class Controller implements ActionListener{
	
	private Model model;
	private View view;	
	
	
	public Controller(Model model, View view) {		
		this.model = model;
		this.view = view;		
		
		model.addObserver(view);
		model.notifyObserver();
		view.addListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {		
		
		String action = arg0.getActionCommand();
		Object source = arg0.getSource();		
		
		if (action.equals("Tout")) {			
			System.out.println("tout");		
		}
		if (action.equals("Non lus")) {			
			System.out.println("Non lus");		
		}
		if (action.equals("Favoris")) {			
			System.out.println("Favoris");		
		}
	
		if (action.equals("Sync")) {			
			System.out.println("Sync");	
			//model.synchronize();
		}
		if (action.equals("Read")) {			
			System.out.println("Read");		
		}
		if (action.equals("FavBtn")) {			
			System.out.println("FavBtn");		
		}		
		
		if (action.equals("Pref")) {			
			System.out.println("Pref");		
		}
		if (action.equals("GererSources")) {			
			System.out.println("GererSources");		
		}
		if (action.equals("Help")) {			
			System.out.println("Help");		
		}
		if (action.equals("About")) {			
			System.out.println("About");
			JDialog Dev = new JDialog();
			JOptionPane.showMessageDialog(Dev,"Developpés par plusieurs moustachus et quelques Zboubs", "Actu-RSS",new Integer(JOptionPane.INFORMATION_MESSAGE).intValue());
		}

		
	}
	

}
