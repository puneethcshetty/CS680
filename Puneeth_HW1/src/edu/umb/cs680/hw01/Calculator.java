package edu.umb.cs680.hw01;

public class Calculator{
	
	public float multiply(float x,
			float y){
		return x * y;
	}
	
	public float divide(float x,
			float y){
		if(y==0){ throw
			new IllegalArgumentException("division by zero");
		}
		return x/y; 
	}
	
	public static void main(String[] args) {
		Calculator calculator = new Calculator();
		calculator.multiply(10,5);
		calculator.divide(10,5);
		System.out.println("done");
	}
}
