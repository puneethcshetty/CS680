package edu.umb.cs680.hw09;

import java.util.Comparator;

public class YearComparator implements Comparator<Car>{

	@Override
	public int compare(Car arg0, Car arg1) {
		return arg0.getYear()-arg1.getYear();
	}
}
