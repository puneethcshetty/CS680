package edu.umb.cs680.hw02;

public class Student {
	private float tuition;
	private String name;
	private int i20num;
	private String usAddr;
	private int yrsInState;
	private String priorState;	
	private String foreignAddr;
	
	public float getTuition() {
		return tuition;
	}
	public String getName() {
		return name;
	}
	public int getI20num() {
		return i20num;
	}
	public String getUsAddr() {
		return usAddr;
	}
	public int getYrsInState() {
		return yrsInState;
	}
	public String getPriorState() {
		return priorState;
	}
	public String getForeignAddr() {
		return foreignAddr;
	}

	private Student(String name, String usAddr, int yrsInState, String priorState, int i20num, String foreignAddr, StudentStatus status) {
		this.name = name;
		this.i20num = i20num;
		this.usAddr = usAddr;
		this.yrsInState = yrsInState;
		this.priorState = priorState;
		this.foreignAddr = foreignAddr;
		if(status.equals(StudentStatus.INSTATE)) 
			this.tuition = 10000;
		else if(status.equals(StudentStatus.OUTSTATE))
			this.tuition = 15000;
		else
			this.tuition = 20000;
	}
	
	public static Student createInStateStudent(String name, String usAddr, int yrsInState) {
		return new Student(name,usAddr,yrsInState,null,0,null,StudentStatus.INSTATE);
	}
	
	public static Student createOutStateStudent(String name, String usAddr, String priorState) {
		return new Student(name,usAddr,0,priorState,0,null,StudentStatus.OUTSTATE);
	}
	
	public static Student createIntlStudent(String name, String usAddr, int i20num, String foreignAddr) {
		return new Student(name,usAddr,0,null,i20num,foreignAddr,StudentStatus.INTL);
	}
	
	public static void main(String[] args) {
		System.out.println("Student Class Successfully Run!!");
	}
}
