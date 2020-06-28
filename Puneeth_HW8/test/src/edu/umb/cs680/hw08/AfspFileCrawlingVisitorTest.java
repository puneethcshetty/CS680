package edu.umb.cs680.hw08;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class AfspFileCrawlingVisitorTest {

static LocalDateTime localTime = LocalDateTime.of(2019, 5, 17, 0, 0);
	
	@SuppressWarnings("unused")
	@BeforeAll
	public static void initialize() {
		
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.initFileSystem("drive", 2000);
		ApfsDirectory system = new ApfsDirectory(root, "system", 0, localTime, "puneeth", localTime);
		ApfsDirectory home = new ApfsDirectory(root, "home", 0, localTime, "puneeth", localTime);
		ApfsDirectory pictures = new ApfsDirectory(home, "pictures", 0, localTime, "puneeth", localTime);
		ApfsFile a = new ApfsFile(system, "a", 100, localTime, "puneeth", localTime);
		ApfsFile b = new ApfsFile(system, "b", 150, localTime, "puneeth", localTime);
		ApfsFile c = new ApfsFile(system, "c", 200, localTime, "puneeth", localTime);
		ApfsFile d = new ApfsFile(home, "d", 50, localTime, "puneeth", localTime);
		ApfsFile e = new ApfsFile(pictures, "e", 100, localTime, "puneeth", localTime);
		ApfsFile f = new ApfsFile(pictures, "f", 200, localTime, "puneeth", localTime);
		ApfsLink x = new ApfsLink(home, "x", 0, localTime, "puneeth", localTime, system);
		ApfsLink y = new ApfsLink(pictures, "y", 0, localTime, "puneeth", localTime, e);
	}
	
	private String[] filesToStringArray(ArrayList<ApfsFile> files) {
		String[] filesInDir = new String[files.size()];
		int i = 0;
		for(ApfsFile f : files) {
			filesInDir[i] = f.getName();
			i++;
		}
		return filesInDir;
	}
	
	@Test
	void checkFilesUnderRoot() {
		ApfsFileCrawlingVisitor visitor = new ApfsFileCrawlingVisitor();
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		root.accept(visitor);
		String[] expected = {"a","b","c","e","f","d"};
		ArrayList<ApfsFile> actual= visitor.getFiles();
		assertArrayEquals(expected,filesToStringArray(actual));
	}
	
	@Test
	void checkFilesUnderHome() {
		ApfsFileCrawlingVisitor visitor = new ApfsFileCrawlingVisitor();
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		ApfsDirectory home = root.findDirByName("home");
		home.accept(visitor);
		String[] expected = {"e","f","d"};
		ArrayList<ApfsFile> actual= visitor.getFiles();
		assertArrayEquals(expected,filesToStringArray(actual));
	}
	
	@Test
	void checkFilesUnderPictures() {
		ApfsFileCrawlingVisitor visitor = new ApfsFileCrawlingVisitor();
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		ApfsDirectory pictures = root.findDirByName("pictures");
		pictures.accept(visitor);
		String[] expected = {"e","f"};
		ArrayList<ApfsFile> actual= visitor.getFiles();
		assertArrayEquals(expected,filesToStringArray(actual));
	}
	
	@Test
	void checkFilesUnderSystem() {
		ApfsFileCrawlingVisitor visitor = new ApfsFileCrawlingVisitor();
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		ApfsDirectory system = root.findDirByName("system");
		system.accept(visitor);
		String[] expected = {"a","b","c"};
		ArrayList<ApfsFile> actual= visitor.getFiles();
		assertArrayEquals(expected,filesToStringArray(actual));
	}
}
