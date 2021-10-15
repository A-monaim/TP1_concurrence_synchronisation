package Concurrence_synchronisation;

import java.util.concurrent.Semaphore;

public class cParkS extends Thread{
	
	//la capacite du parking
	private int capacite;
	
	//variable pour contrôler le nombre des places occupées
	private static volatile int placesOcc = 0;
	
	//constructeur avec la capacite du parking en argument
	public cParkS(int p) {
		this.capacite = p;
	}

	Semaphore sc = new Semaphore(4);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//initialisation du sémaphore avec le même nombre de place permis au parking
		cParkS parking = new cParkS(4);
		
		
		
		System.out.println("le parking a "+parking.capacite+" places.");
		
		//création de 100 voitures
		for(int i=0; i < 100; i++){
			
			
			try {
				
				new VoitureS(parking, "voiture"+i).start();
				
				long t = (long)(5000*Math.random());
				//de 0 à 5 secondes entre chaque création
				sleep(t);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
			
			
			}
		}
	
	public  void arriver() throws InterruptedException {
		
		//après l'entré d'une voitire, le nombre initialisé au début se décrémente jusqu'à qu'il devient 0 donc aucune voiture ne peut entré
		sc.acquire();
		
		//si le parking n'est pas plein, la voiture entre et le nombre de places occupées s'incremente
		placesOcc++;
		
		System.out.println("Une voiture vient d'entrer, reste "+(capacite-placesOcc)+" places.");

	}
	
	public  void partir() {
		
		//nombre de place se décrémente apr´s la sortie d'une voiture
		placesOcc--;
		
		System.out.println((capacite-placesOcc)+" places disponible.");
		
		//aprés la sortie d'une voiture il s'incrémente et donne la place à une autre si il était à 0
		sc.release();
	}
	
}