package Concurrence_synchronisation;

public class Prod extends Thread{
	
	String nomThread;
	private Test chap;
	
	public Prod(Test t, String nom) {
		
		this.nomThread = nom;
		this.chap = t;
	}
	
	public void run() {
		
		try {
			while (true)
				chap.deposer();
			
				
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}