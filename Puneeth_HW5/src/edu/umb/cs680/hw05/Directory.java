package edu.umb.cs680.hw05;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Directory extends FSElement{
	
	private LinkedList<FSElement> children;
	
	public LinkedList<FSElement> getChildren() {
		return children;
	}

	public Directory(Directory parent, String name, int size, LocalDateTime creationTime) {
		super(parent, name, size, creationTime);
		if(parent != null)
			parent.appendChild(this);
	}

	@Override
	public boolean isDirectory() {
		return true;
	}
	
	public void appendChild(FSElement child) {
		if(this.children == null) {
			this.children = new LinkedList<FSElement>();
		}
		this.children.add(child);
	}
	
	public int countChildren() {
		return getChildren().size();
	}
	
	public LinkedList<Directory> getSubDirectories() {
		LinkedList<Directory> directoryList = new LinkedList<Directory>();
		for(FSElement element : getChildren()) {
			if(element instanceof Directory)
				directoryList.add((Directory) element);
		}
		return directoryList;	
	}

	public LinkedList<File> getFiles() {
		LinkedList<File> fileList = new LinkedList<File>();
		for(FSElement element : getChildren()) {
			if(element instanceof File)
				fileList.add((File) element);
		}
		return fileList;
	}
	
	public int getTotalSize() {
		int totalSize = 0;
		for(FSElement element : getChildren()) {
			if(element instanceof Directory)
				totalSize += ((Directory) element).getTotalSize();
			else
				totalSize += element.getSize();
		}
		return totalSize;
	}
	
	public Directory findDirByName(String dirName) {
		Directory directory = null;
		if(dirName.equals(getName()))
			directory = this;
		else {
			for(Directory dir : getSubDirectories()) {
				if(directory == null) {
					directory = dir.findDirByName(dirName);
					if(dirName.equals(dir.getName())) {
						directory = dir;
						break;
					}
				}
			}
		}
		return directory;
	}
	
	public File findFileByName(String fileName) {
		File file = null;
		for(File f : getFiles()) {
			if(fileName.equals(f.getName()))
				file = f;
		}
		if(file == null) {
			for(Directory dir : getSubDirectories()) {
				file = dir.findFileByName(fileName);
				if(file != null)
					break;
			}
		}
		return file;
	}
	
	public static void main(String[] args) {
		System.out.println("Directory Class Successfully Run!!");
	}
}
