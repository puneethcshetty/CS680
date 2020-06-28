package edu.umb.cs680.hw08;

public class ApfsCountingVisitor implements ApfsFSVisitor{

	private int dirNum = 0;
	private int fileNum = 0;
	private int linkNum = 0;
	
	public int getDirNum() {
		return dirNum;
	}

	public int getFileNum() {
		return fileNum;
	}

	public int getLinkNum() {
		return linkNum;
	}

	@Override
	public void visit(ApfsLink link) {
		linkNum++;
	}

	@Override
	public void visit(ApfsDirectory dir) {
		dirNum++;
	}

	@Override
	public void visit(ApfsFile file) {
		fileNum++;
	}

	public static void main(String[] args) {
		System.out.println("ApfsCountingVisitor Class Successfully Run!!");
	}
}
