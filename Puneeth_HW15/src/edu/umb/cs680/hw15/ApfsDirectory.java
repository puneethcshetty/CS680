package edu.umb.cs680.hw15;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;


public class ApfsDirectory extends ApfsElement{
	
	private LinkedList<ApfsElement> children;
	
	public LinkedList<ApfsElement> getChildren() {
		if(this.children == null) {
			this.children = new LinkedList<ApfsElement>();
		}
		Collections.sort(children, (ApfsElement o1, ApfsElement o2) -> o1.getName().compareTo(o2.getName()));
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
		Collections.sort(directoryList, (ApfsElement o1, ApfsElement o2) -> o1.getName().compareTo(o2.getName()));
		return directoryList;	
	}

	public LinkedList<ApfsFile> getFiles() {
		LinkedList<ApfsFile> fileList = new LinkedList<ApfsFile>();
		for(FSElement element : getChildren()) {
			if(element instanceof ApfsFile)
				fileList.add((ApfsFile) element);
		}
		Collections.sort(fileList, (ApfsElement o1, ApfsElement o2) -> o1.getName().compareTo(o2.getName()));
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
	
	public LinkedList<ApfsElement> getChildren(Comparator<ApfsElement> comparator) {
		Collections.sort(getChildren(), comparator);
		return children;
	}
	
	public LinkedList<ApfsDirectory> getSubDirectories(Comparator<ApfsElement> comparator) {
		LinkedList<ApfsDirectory> directoryList = getSubDirectories();
		Collections.sort(directoryList, comparator);
		return directoryList;
	}
	
	public LinkedList<ApfsFile> getFiles(Comparator<ApfsElement> comparator) {
		LinkedList<ApfsFile> fileList = getFiles();
		Collections.sort(fileList, comparator);
		return fileList;
	}
	
	public static void main(String[] args) {
		System.out.println("ApfsDirectory Class Successfully Run!!");
	}
}
