package edu.umb.cs680.hw07;

import java.time.LocalDateTime;

public class ApfsFile extends ApfsElement{

	public ApfsFile(ApfsDirectory parent, String name, int size, LocalDateTime creationTime, String ownerName, LocalDateTime lastModified) {
		super(parent, name, size, creationTime, ownerName, lastModified);
		parent.appendChild(this);
	}

	@Override
	public boolean isDirectory() {
		return false;
	}

	public static void main(String[] args) {
		System.out.println("ApfsFile Class Successfully Run!!");
	}
}
