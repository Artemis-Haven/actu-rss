package ca.usherbrooke.ift232.actuRSS.model;

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
