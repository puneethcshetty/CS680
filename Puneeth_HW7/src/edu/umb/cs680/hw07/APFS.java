package edu.umb.cs680.hw07;

import java.time.LocalDateTime;

public class APFS extends FileSystem{

	private static APFS instance = null;
	
	private APFS() {};
	
	public static APFS getAPFSFileSystem() {
		if(instance==null)
			instance = new APFS ();
		return instance;
	}

	@Override
	protected FSElement createDefaultRoot() {
		LocalDateTime localTime = LocalDateTime.of(2019, 5, 17, 0, 0);
		ApfsDirectory root = new ApfsDirectory(null, "root", 0, localTime, "puneeth", localTime);
		return root;
	}
	
	public static void main(String[] args) {
		System.out.println("APFS Class Successfully Run!!");
	}
}
