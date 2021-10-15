package Concurrence_synchronisation;

import java.util.concurrent.Semaphore;

public class VoitureS extends Thread {
	
	//nom de la voiture
	private String nomV;
		
	private cParkS parking;
	
	//temps de stationnement d'une voiture
	private long temps;
	
	public VoitureS (cParkS parking, String nom){
		
		this.parking = parking;
		
		this.nomV = nom;
		
		}
	Semaphore sc = new Semaphore(4);
	public void run(){
		
		
		
		try {
			
			System.out.println(this.nomV + " demande d'entrer.");
			parking.arriver();
			
			//temps de stationnement de la voiture entrée au parking
			temps = (long)(20000*Math.random());
			
			System.out.println("je suis "+this.nomV + " je vais me garer "+temps+"s.");
			//temps de stationnement de la voiture entrée au parking entre 0 à 20 secondes		
			sleep(temps);
			
			parking.partir();
			System.out.println(this.nomV + " a quitté.");
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		
	}
	
}