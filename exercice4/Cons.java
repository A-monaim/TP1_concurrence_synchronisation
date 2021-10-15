package Concurrence_synchronisation;

public class Cons extends Thread{
	
	String nomThread;
	private Test chap;
	public Cons(Test t, String nom) {
		
		this.nomThread = nom;
		this.chap = t;
	}
	
	public void run() {
		
		try {
			
			while (true)
				chap.imprimer();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}