package ca.usherbrooke.ift232.actuRSS.view.actulist;

import java.awt.EventQueue;
import java.awt.Window;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import ca.usherbrooke.ift232.actuRSS.common.Actu;

public class ActuListDemo {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ActuListDemo window = new ActuListDemo();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	protected Window getFrame() {
		return frame;
	}

	/**
	 * Create the application.
	 */
	public ActuListDemo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//================== Création des données de démonstration
		

		List<Actu> news = new ArrayList<Actu>();
		
		news.add(new Actu("dfghjkl"));
		news.add(new Actu("gfhjkl"));
		news.add(new Actu("yhgfd"));
		ActuList myActuList = new ActuList(news);
		
		//================== Ajout d'un écouteur bidon
		
		myActuList.addActuSelectedListener(new ActuSelectedListener()
		{

			@Override
			public void onActuSelected(ActuSelectedEvent event) {
				System.out.println("Source " + event.getSelectedSource() + "sélectionnée");
				
			}
			
		});
		
		//================== Ajout du composant à la fenêtre
		

		frame.add(myActuList);
		
	}

}
