package edu.umb.cs680.hw03;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SingletonTest {

	@Test
	void checkIdenticalInstance() {
		Singleton firstInstance = Singleton.getInstance();
		Singleton secondInstance = Singleton.getInstance();
		assertSame(firstInstance, secondInstance);
	}

}
