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

	/**Permet de recuperer une frame
	 * @return frame
	 */
	protected Window getFrame() {
		return frame;
	}

	/**
	 * Cree l'application
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
		
		//================== Cr�ation des donn�es de d�monstration
		

		List<News> news = new ArrayList<News>();
		
		/*news.add(new News("dfghjkl"));
		news.add(new News("gfhjkl"));
		news.add(new News("yhgfd"));
		*/

		//La flemme de cr�er des objets de test maintenant
		
		ActuList myActuList = new ActuList(news);
		
		//================== Ajout d'un �couteur bidon
		
		myActuList.addActuSelectedListener(new ActuSelectedListener()
		{

			@Override
			public void onActuSelected(ActuSelectedEvent event) 
			{
				
				System.out.println("Source " + event.getSelectedActu() + "s�lectionn�e");
				
			}
			
		});
		
		//================== Ajout du composant � la fen�tre
		

		frame.add(myActuList);
		
	}

}
