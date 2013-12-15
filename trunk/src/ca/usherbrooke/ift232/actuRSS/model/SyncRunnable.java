package ca.usherbrooke.ift232.actuRSS.model;

/**
 * Classe pour définir un thread qui sera utlisé lors de la synchronisation des fluxs Rss. Cela permet d'éviter que l'application "freeze" lors de l'appel
 * à la méthode sync() de la classe Model
 * @author DAVID
 *
 */
public class SyncRunnable implements Runnable {
	
	
	Model model = new Model();
	
	public void run() {
		try {
			model.synchronize();
		} catch (Exception ex) {
			ex.getMessage();
		}
	}
	
	//String args[]
	public static void main() {
		(new Thread(new SyncRunnable())).start();
	}

}
