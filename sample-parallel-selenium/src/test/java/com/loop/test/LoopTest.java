package com.loop.test;

public class LoopTest {
	
	public void perosotan() {
		for (int i=0 ; i < 9 ; i++) {
			for (int j=0 ; j <= i ; j++) {
				System.out.print("* ");
			}
		System.out.println("");
		}	
	}
	
	public void piramid() {
		for (int k=0; k<9 ;k++) {
			for(int l=0 ; l<9 - k ; l++) {
				System.out.print(" ");
			}
			for (int m=0 ; m<=k; m++) {
				System.out.print("* ");
			}
		System.out.println();
		} 	

	}
	
	public void diamond() {
	    int size = 5;
	    
	    for(int n=0 ; n<size ; n++) {
	    	for(int o=0 ; o < size-n ; o++) {
	    		System.out.print(" ");
	    	}
	    	for(int p=0 ; p <= n-1 ; p++) {
	    		System.out.print("* ");
	    	}
	    System.out.println();
	    }
	    
	    for(int q=0 ; q < size ; q++){
	    	for(int g=0 ; g < q ; g++) {
	    		System.out.print(" ");
	    	}
	    	for(int f=0 ; f < size-q ; f++) {
	    		System.out.print("* ");
	    	}
	    System.out.println();	
	    }
	}
	
	public static void main(String[] args) {
		LoopTest test = new LoopTest();
//		test.perosotan();
//		System.out.println();System.out.println();
//		test.piramid();
//		System.out.println();System.out.println();
		test.diamond();
	}

}
