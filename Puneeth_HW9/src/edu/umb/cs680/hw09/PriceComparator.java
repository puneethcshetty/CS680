package edu.umb.cs680.hw09;

import java.util.Comparator;

public class PriceComparator implements Comparator<Car>{

	@Override
	public int compare(Car arg0, Car arg1) {
		return (int) (arg1.getPrice()-arg0.getPrice());
	}
}
