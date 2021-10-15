package Concurrence_synchronisation;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;


public class Test extends Thread {
	
	//nombre de producteurs et consommateurs
	int  nbrProd, nbrCons;
	//iP pour les indices du chapeau pour déposer (producteur) et iC indice du chapeau pour prendre (consommateur)
	static volatile int iP = 0, jC=0;
	//maximun de jetons dqns un chapeau
	int tailleChap = 20;
	
	//Chapeau de N jetons
	int[] chap = new int[tailleChap];  
	
	public Test(int nbrProd, int nbrCons ) {

		this.nbrProd = nbrProd;
		this.nbrCons=nbrCons;
	}
	//semaphore utilisée par les consommateurs, elle est à 0 car il ne peuvent pas lire puisqu'il est vide
	Semaphore Nplein = new Semaphore(0);
	//semaphore utilisée par les producteurs, elle est à la taille du chapeau donc N producteurs peut déposer
	Semaphore Nvide = new Semaphore(tailleChap);
	//semaphore pour protéger l'écriture dans le tableau par les producteurs
	Semaphore pro = new Semaphore(1);

	//semaphore pour protéger la lecture dans le tableau par les consommateurs
	Semaphore cons = new Semaphore(1);

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		//on aura 20 producteurs et 10 consomateurs
		Test chap = new Test(20, 10);
		
		//création de consommateurs
		for( int j=0; j<chap.nbrCons; j++) {
			
			new Cons(chap, "Cons"+j).start();
			int t = (int)(500*Math.random());
			//une petite durée entre chaque création
			sleep(t);
			
		}
		
		//création de producteurs
		for( int j=0; j<chap.nbrProd; j++) {
			
			new Prod(chap, "Prod"+j).start();
			int t = (int)(500*Math.random());
			//une petite durée entre chaque création
			sleep(t);
			
		}

	}
	
	public void deposer() throws InterruptedException {
		
		//après chaque écriture dans le chapeau, le nombre se décrémente
		Nvide.acquire();
		//semaphore pro devient 0 pour bloquer les autres producteurs
		pro.acquire();
		
		int t = (int)(500*Math.random());
		
		chap[iP]=t;
		
		System.out.println("j'ai déposé"+chap[iP]+" case : "+iP);
		
		iP = (iP+1)%tailleChap;
		//semaphore pro devient 1 pour donner l'accés à un autre producteur
		pro.release();
		//Nplein s'incrémente depuis le premier jeton posé pour donner l'autorisation au consommateur de consommer
		Nplein.release();
		
		
		
		
	}
	
	public void imprimer() throws InterruptedException {
		
		//Nplein se décrémente 
		Nplein.acquire();
		
		//semaphore cons devient 0 pour qu'un seul consommateur peut prendre le jeton
		cons.acquire();
		
		System.out.println("j'ai récupéré"+chap[jC]+" case : "+jC);
		
		jC = (jC+1)%tailleChap;
		//semaphore cons devient 1 pour donner l'autorisation à un autre consommateur pour prendre le jeton
		cons.release();
		
		//le consommateur fait signe au producteur qu'il y a de place vide
		Nvide.release();
		
	}

}