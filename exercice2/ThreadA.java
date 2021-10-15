package Concurrence_synchronisation;

import java.util.concurrent.Semaphore;

public class ThreadA extends Thread {
	
	
	private Semaphore[] sems;
	
	//nombre d'affichage
	
	int nbr;
	
	public ThreadA(Semaphore[] sems, int i) {
		this.sems = sems;
		this.nbr = i;
		start();
	}
	
	public void run() {
		
		try {
			for(int i=0; i< nbr; i++) {
				
				//ThreadA décrémente du sem[0] donc il devient 0 pour que A ne s'écrit pas jusqu'à que sem[0] soit incrémenter
				sems[0].acquire();
				
				System.out.print("A");
				
				//incémente sem[1] devient 1 donc thread B peut écrire maintenant
				sems[1].release();
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}