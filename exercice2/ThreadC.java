package Concurrence_synchronisation;

import java.util.concurrent.Semaphore;

public class ThreadC extends Thread {
	
	private Semaphore[] sems;
	int nbr;
	public ThreadC(Semaphore[] sems, int i) {
		this.sems = sems;
		this.nbr = i;
		start();
	}
	
	public void run() {
		
		try {
			for(int i=0; i< nbr; i++) {
				
				//ThreadA décrémente du sem[2] donc il devient 0 pour que C ne s'écrit pas jusqu'à que sem[2] soit incrémenter
				sems[2].acquire();
				
				System.out.print("C");
				
				//incémente sem[0] devient 1 donc thread A peut écrire maintenant
				sems[0].release();
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}