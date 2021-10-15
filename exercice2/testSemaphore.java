package Concurrence_synchronisation;

import java.util.concurrent.Semaphore;

public class testSemaphore {
	
	//nombre d'affichage
	int N;
	
	public testSemaphore(int i) {
		
		this.N = i;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		//on a 3 threads donc on aura besoin de 3 semaphores
		Semaphore[] sems = new Semaphore[3];
		
		//pour autoriser au threadA d'écrire en premier
		sems[0] = new Semaphore(1);
		
		//les threads B et C doivent être en attente au début
		sems[1] = new Semaphore(0);
		sems[2] = new Semaphore(0);
		
		//chaque lettre doit s'écrire 5 fois
		testSemaphore test = new testSemaphore(5);
		
		new ThreadA(sems, test.N);
		new ThreadB(sems, test.N);
		new ThreadC(sems, test.N);
	}

}