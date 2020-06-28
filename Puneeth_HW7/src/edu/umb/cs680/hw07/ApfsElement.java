package edu.umb.cs680.hw07;

import java.time.LocalDateTime;

public abstract class ApfsElement extends FSElement{
	
	private String ownerName;
	private LocalDateTime lastModified;

	public String getOwnerName() {
		return ownerName;
	}

	public LocalDateTime getLastModified() {
		return lastModified;
	}

	public ApfsElement(ApfsDirectory parent, String name, int size, LocalDateTime creationTime, String ownerName, LocalDateTime lastModified) {
		super(parent, name, size, creationTime);
		this.ownerName = ownerName;
		this.lastModified = lastModified;
	}

	public abstract boolean isDirectory();
	
	public static void main(String[] args) {
		System.out.println("ApfsElement Class Successfully Run!!");
	}
}
