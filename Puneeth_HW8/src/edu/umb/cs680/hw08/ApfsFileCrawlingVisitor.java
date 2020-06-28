package edu.umb.cs680.hw08;

import java.util.ArrayList;

public class ApfsFileCrawlingVisitor implements ApfsFSVisitor{

	private ArrayList<ApfsFile> files;
	
	public ArrayList<ApfsFile> getFiles() {
		if(null == files) {
			files = new ArrayList<ApfsFile>();
		}
		return files;
	}

	@Override
	public void visit(ApfsLink link) {
		return;
	}

	@Override
	public void visit(ApfsDirectory dir) {
		return;
	}

	@Override
	public void visit(ApfsFile file) {
		if(null == files) {
			files = new ArrayList<ApfsFile>();
		}
		files.add(file);
	}
	
	public static void main(String[] args) {
		System.out.println("ApfsFileCrawlingVisitor Class Successfully Run!!");
	}
}
