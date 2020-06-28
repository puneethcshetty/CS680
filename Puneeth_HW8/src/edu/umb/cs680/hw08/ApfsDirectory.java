package edu.umb.cs680.hw08;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class ApfsDirectory extends ApfsElement{
	
	private LinkedList<ApfsElement> children;
	
	public LinkedList<ApfsElement> getChildren() {
		if(this.children == null) {
			this.children = new LinkedList<ApfsElement>();
		}
		return children;
	}

	public ApfsDirectory(ApfsDirectory parent, String name, int size, LocalDateTime creationTime, String ownerName, LocalDateTime lastModified) {
		super(parent, name, size, creationTime, ownerName, lastModified);
		if(parent != null)
			parent.appendChild(this);
	}

	@Override
	public boolean isDirectory() {
		return true;
	}
	
	public void appendChild(ApfsElement child) {
		if(this.children == null) {
			this.children = new LinkedList<ApfsElement>();
		}
		this.children.add(child);
	}
	
	public int countChildren() {
		return getChildren().size() - getLinks().size();
	}
	
	public LinkedList<ApfsDirectory> getSubDirectories() {
		LinkedList<ApfsDirectory> directoryList = new LinkedList<ApfsDirectory>();
		for(FSElement element : getChildren()) {
			if(element instanceof ApfsDirectory)
				directoryList.add((ApfsDirectory) element);
		}
		return directoryList;	
	}

	public LinkedList<ApfsFile> getFiles() {
		LinkedList<ApfsFile> fileList = new LinkedList<ApfsFile>();
		for(FSElement element : getChildren()) {
			if(element instanceof ApfsFile)
				fileList.add((ApfsFile) element);
		}
		return fileList;
	}
	
	public LinkedList<ApfsLink> getLinks() {
		LinkedList<ApfsLink> linkList = new LinkedList<ApfsLink>();
		for(FSElement element : getChildren()) {
			if(element instanceof ApfsLink)
				linkList.add((ApfsLink) element);
		}
		return linkList;
	}
	
	public int getTotalSize() {
		int totalSize = 0;
		for(ApfsElement element : getChildren()) {
			if(element instanceof ApfsDirectory)
				totalSize += ((ApfsDirectory) element).getTotalSize();
			else
				totalSize += element.getSize();
		}
		return totalSize;
	}
	
	public ApfsDirectory findDirByName(String dirName) {
		ApfsDirectory directory = null;
		if(dirName.equals(getName()))
			directory = this;
		else {
			for(ApfsDirectory dir : getSubDirectories()) {
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
	
	public ApfsFile findFileByName(String fileName) {
		ApfsFile file = null;
		for(ApfsFile f : getFiles()) {
			if(fileName.equals(f.getName()))
				file = f;
		}
		if(file == null) {
			for(ApfsDirectory dir : getSubDirectories()) {
				file = dir.findFileByName(fileName);
				if(file != null)
					break;
			}
		}
		return file;
	}
	
	public ApfsLink findLinkByName(String linkName) {
		ApfsLink link = null;
		for(ApfsLink l : getLinks()) {
			if(linkName.equals(l.getName()))
				link = l;
		}
		if(link == null) {
			for(ApfsDirectory dir : getSubDirectories()) {
				link = dir.findLinkByName(linkName);
				if(link != null)
					break;
			}
		}
		return link;
	}
	
	@Override
	public void accept(ApfsFSVisitor visitor) {
		visitor.visit(this);
		for(ApfsElement e : getChildren()) {
			e.accept(visitor);
		}
	}
	
	public static void main(String[] args) {
		System.out.println("ApfsDirectory Class Successfully Run!!");
	}
}
