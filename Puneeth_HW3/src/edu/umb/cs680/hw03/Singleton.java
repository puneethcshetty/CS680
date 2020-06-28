package edu.umb.cs680.hw03;

public class Singleton {

	private Singleton(){};
	private static Singleton instance = null;
	
	public static Singleton getInstance(){
		if(instance==null)
			instance = new Singleton ();
		return instance;
	}
	
	public static void main(String[] args) {
		System.out.println("Singleton Class Successfully Run!!");
	}

}
