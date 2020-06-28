package edu.umb.cs680.hw14;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.LinkedList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


class YearComparatorTest {

static LinkedList<Car> carList = new LinkedList<Car>();
	
	@BeforeAll
	public static void initialize() {
		Car car1 = new Car("Toyota", "RAV4", 2018, 10000, 8000);
		Car car2 = new Car("BMW", "XYZ", 2000, 40000, 28000);
		Car car3 = new Car("Ford", "ABC", 2010, 30000, 20000);
		Car car4 = new Car("KIA", "UVW", 2012, 25000, 10000);
		Car car5 = new Car("Toyota", "KKK", 2015, 20000, 22000);
		Car car6 = new Car("Toyota", "ABD", 2017, 15000, 8000);
		carList.add(car1);carList.add(car2);carList.add(car3);
		carList.add(car4);carList.add(car5);carList.add(car6);
		Collections.sort(carList,(Car arg0, Car arg1) -> arg0.getYear()-arg1.getYear());
	}
	
	@Test
	void checkCar1AfterSort() {
		Car car1 = new Car("Toyota", "RAV4", 2018, 10000, 8000);
		assertEquals(car1, carList.get(5));
	}
	
	@Test
	void checkCar2AfterSort() {
		Car car2 = new Car("BMW", "XYZ", 2000, 40000, 28000);
		assertEquals(car2, carList.get(0));
	}
	
	@Test
	void checkCar3AfterSort() {
		Car car3 = new Car("Ford", "ABC", 2010, 30000, 20000);
		assertEquals(car3, carList.get(1));
	}
	
	@Test
	void checkCar4AfterSort() {
		Car car4 = new Car("KIA", "UVW", 2012, 25000, 10000);
		assertEquals(car4, carList.get(2));
	}
	
	@Test
	void checkCar5AfterSort() {
		Car car5 = new Car("Toyota", "KKK", 2015, 20000, 22000);
		assertEquals(car5, carList.get(3));
	}
	
	@Test
	void checkCar6AfterSort() {
		Car car6 = new Car("Toyota", "ABD", 2017, 15000, 8000);
		assertEquals(car6, carList.get(4));
	}

}
