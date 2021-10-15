package Concurrence_synchronisation;

import java.util.concurrent.Semaphore;

public class ThreadB extends Thread {
	
	private Semaphore[] sems;
	int nbr;
	public ThreadB(Semaphore[] sems, int i) {
		this.sems = sems;
		this.nbr = i;
		start();
	}
	
	public void run() {
		
		try {
			for(int i=0; i< nbr; i++) {
				
				//ThreadA décrémente du sem[1] donc il devient 0 pour que B ne s'écrit pas jusqu'à que sem[1] soit incrémenter
				sems[1].acquire();
				
				System.out.print("B");
				
				//incémente sem[2] devient 1 donc thread C peut écrire maintenant
				sems[2].release();
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}