package edu.umb.cs680.hw02;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StudentTest {

	Student inStateStudent = Student.createInStateStudent("ABC", "XYZ", 20);;
	Student outStateStudent = Student.createOutStateStudent("ABC", "XYZ", "PQR");
	Student intlStudent = Student.createIntlStudent("ABC", "XYZ", 12334567, "PQR");
	
	@Test
	void testGetTuition() {
		assertEquals(10000, inStateStudent.getTuition());
		assertEquals(15000, outStateStudent.getTuition());
		assertEquals(20000, intlStudent.getTuition());
	}

	@Test
	void testGetName() {
		assertEquals("ABC", inStateStudent.getName());
		assertEquals("ABC", outStateStudent.getName());
		assertEquals("ABC", intlStudent.getName());
	}

	@Test
	void testGetI20num() {
		assertEquals(0, inStateStudent.getI20num());
		assertEquals(0, outStateStudent.getI20num());
		assertEquals(12334567, intlStudent.getI20num());
		
	}

	@Test
	void testGetUsAddr() {
		assertEquals("XYZ", inStateStudent.getUsAddr());
		assertEquals("XYZ", outStateStudent.getUsAddr());
		assertEquals("XYZ", intlStudent.getUsAddr());
	}

	@Test
	void testGetYrsInState() {
		assertEquals(20, inStateStudent.getYrsInState());
		assertEquals(0, outStateStudent.getYrsInState());
		assertEquals(0, intlStudent.getYrsInState());
	}

	@Test
	void testGetPriorState() {
		assertNull(inStateStudent.getPriorState());
		assertEquals("PQR", outStateStudent.getPriorState());
		assertNull(intlStudent.getPriorState());
	}

	@Test
	void testGetForeignAddr() {
		assertNull(inStateStudent.getForeignAddr());
		assertNull(outStateStudent.getForeignAddr());
		assertEquals("PQR", intlStudent.getForeignAddr());
	}

	@Test
	void testCreateInStateStudent() {
		Student student= Student.createInStateStudent("ABC", "XYZ", 20);
		float actual = student.getTuition(); 
		float expected = 10000;
		assertEquals(expected, actual);
	}

	@Test
	void testCreateOutStateStudent() {
		Student student = Student.createOutStateStudent("ABC", "XYZ", "PQR");
		float actual = student.getTuition(); 
		float expected = 15000;
		assertEquals(expected, actual);
	}

	@Test
	void testCreateIntlStudent() {
		Student student = Student.createIntlStudent("ABC", "XYZ", 12334567, "PQR");
		float actual = student.getTuition(); 
		float expected = 20000;
		assertEquals(expected, actual);
	}

}
