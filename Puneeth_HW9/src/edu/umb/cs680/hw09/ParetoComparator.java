package edu.umb.cs680.hw09;

import java.util.Comparator;

public class ParetoComparator implements Comparator<Car>{

	@Override
	public int compare(Car arg0, Car arg1) {
		if(arg1.getPrice()>=arg0.getPrice() && arg0.getYear()>=arg1.getYear() && arg1.getMileage()>=arg0.getMileage()) {
			if(arg1.getPrice()>arg0.getPrice() || arg0.getYear()>arg1.getYear() || arg1.getMileage()>arg0.getMileage())
				return -1;
			return 0;
		}
		return 0;
	}
}
