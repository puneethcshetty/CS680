package edu.umb.cs680.hw06;

import java.util.LinkedList;

public class FileSystem {

	private static FileSystem instance = null;
	private LinkedList<Directory> rootDirs;
	
	private FileSystem() {};
	
	public LinkedList<Directory> getRootDirs() {
		return this.rootDirs;
	}
	
	public static FileSystem getFileSystem() {
		if(instance==null)
			instance = new FileSystem ();
		return instance;
	}
	
	public void addRootDir(Directory rootDir) {
		rootDirs = new LinkedList<Directory>();
		rootDirs.add(rootDir);
	}
	
	public static void main(String[] args) {
		System.out.println("FileSystem Class Successfully Run!!");
	}
}
