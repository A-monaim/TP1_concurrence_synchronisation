package Concurrence_synchronisation;

public class vPark extends Thread{
	
	//la capacite du parking
	private int capacite;
	
	//variable pour contrôler le nombre des places occupées
	private static volatile int placesOcc = 0;
	
	//constructeur avec la capacite du parking en argument
	public vPark(int p) {
		this.capacite = p;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		vPark parking = new vPark(4);
		
		System.out.println("le parking a "+parking.capacite+" places.");
		
		//création de 100 voitures
		for(int i=0; i < 100; i++){
			
			
			try {
				
				new Voiture(parking, "voiture"+i).start();
				
				long t = (long)(5000*Math.random());
				//de 0 à 5 secondes entre chaque création
				sleep(t);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
			
			
			}
		}
	
	public synchronized void arriver() {
		
		//si le parking est plein la voiture arrivée doit attendre
		while( placesOcc == capacite) {
			
			try {
				
				//attendre qu'une place soit disponible
				wait();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
			
		}
		
		//si le parking n'est pas plein, la voiture entre et le nombre de places occupées s'incremente
		placesOcc++;
		
		System.out.println("Une voiture vient d'entrer, reste "+(capacite-placesOcc)+" places.");
		
		//envoie un signal que le parking n'est pas vide
		notify();
	}
	
	public synchronized void partir() {
		
		//si le parking est vide
		while( placesOcc == 0) {
			
			try {
				
				//attendre que le parking ne reste pas vide
				wait();
				
			} catch (InterruptedException e) {
				
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
			
		}
		
		placesOcc--;
		
		System.out.println((capacite-placesOcc)+" places disponible.");
		
		//envoie un signal qu'une place est disponible
		notify();
	}
	
}