package edu.umb.cs680.hw06;

import java.time.LocalDateTime;

public class Link extends FSElement{

	private FSElement target;
	
	public FSElement getTarget() {
		return target;
	}

	public Link(Directory parent, String name, int size, LocalDateTime creationTime, FSElement target) {
		super(parent, name, size, creationTime);
		this.target = target;
		parent.appendChild(this);
	}

	@Override
	public boolean isDirectory() {
		return false;
	}

	public static void main(String[] args) {
		System.out.println("Link Class Successfully Run!!");
	}
}
