package ca.usherbrooke.ift232.actuRSS.view.actulist;

import java.awt.EventQueue;
import java.awt.Window;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import ca.usherbrooke.ift232.actuRSS.News;

public class ActuListDemo {

	private JFrame frame;

	/**
	 * Lance l'application
	 * @param args
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

	/**Permet de récupérer une frame
	 * @return frame
	 */
	protected Window getFrame() {
		return frame;
	}

	/**
	 * Crée l'application
	 */
	public ActuListDemo() {
		initialize();
	}

	/**
	 * Initialise le contenu de la frame
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//================== Crï¿½ation des donnï¿½es de dï¿½monstration
		

		List<News> news = new ArrayList<News>();
		
		/*news.add(new News("dfghjkl"));
		news.add(new News("gfhjkl"));
		news.add(new News("yhgfd"));
		*/

		//La flemme de crï¿½er des objets de test maintenant
		
		ActuList myActuList = new ActuList(news);
		
		//================== Ajout d'un ï¿½couteur bidon
		
		myActuList.addActuSelectedListener(new ActuSelectedListener()
		{

			@Override
			public void onActuSelected(ActuSelectedEvent event) 
			{
				
				System.out.println("Source " + event.getSelectedActu() + "sï¿½lectionnï¿½e");
				
			}
			
		});
		
		//================== Ajout du composant ï¿½ la fenï¿½tre
		

		frame.add(myActuList);
		
	}

}
