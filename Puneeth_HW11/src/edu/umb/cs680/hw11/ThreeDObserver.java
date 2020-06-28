package edu.umb.cs680.hw11;

import java.util.Observable;
import java.util.Observer;

public class ThreeDObserver implements Observer{
	
	private String ticker;
	private double quote;
	
	public void update(Observable o, Object o1) {
		
		if(o instanceof StockQuoteObservable)
		{
			ticker = ((StockEvent)o1).getTicker();
			quote = ((StockEvent) o1).getQuote();
			System.out.println("\n ThreeDObserver Notified:\n Ticker: "+ticker+"\n Quote: "+quote+"\n");
		}
		else if(o instanceof DJIAQuoteObservable) {
			quote = ((DJIAEvent) o1).getDija();
			System.out.println("\n ThreeDObserver Notified:\n Quote: "+quote+"\n");
		}
	}
	
	public static void main(String[] args) {
		System.out.println("ThreeDObserver Class Successfully Run!!");
	}
}
