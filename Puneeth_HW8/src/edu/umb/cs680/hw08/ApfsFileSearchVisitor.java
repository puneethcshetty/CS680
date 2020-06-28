package edu.umb.cs680.hw08;

public class ApfsFileSearchVisitor implements ApfsFSVisitor{

	private String fileName;
	private ApfsFile file;
	
	public ApfsFile getFile() {
		return file;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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
		if(fileName.equals(file.getName()))
			this.file = file;
	}

	public static void main(String[] args) {
		System.out.println("ApfsFileSearchVisitor Class Successfully Run!!");
	}
}
