package edu.umb.cs680.hw09;

import java.util.Comparator;

public class MileageComparator implements Comparator<Car>{

	@Override
	public int compare(Car arg0, Car arg1) {
		return arg1.getMileage()-arg0.getMileage();
	}
}
