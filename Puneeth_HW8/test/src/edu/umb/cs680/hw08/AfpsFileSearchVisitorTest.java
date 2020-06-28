package edu.umb.cs680.hw08;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class AfpsFileSearchVisitorTest {

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
	
	private String[] fileToStringArray(ApfsFile f) {
		Optional<ApfsDirectory> optionalDirectory = Optional.ofNullable(f.getParent());
		String[] fileInfo = { Boolean.toString(f.isDirectory()), f.getName(), 
				Integer.toString(f.getSize()), f.getCreationTime().toString(), 
				optionalDirectory.isPresent()?f.getParent().getName():null, f.getOwnerName(),f.getLastModified().toString()};
		return fileInfo;
	}
	
	@Test
	public void verifyFileEqualityA() {
		ApfsFileSearchVisitor visitor = new ApfsFileSearchVisitor();
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		String[] expected = { "false", "a", "100", localTime.toString(), "system", "puneeth", localTime.toString() };
		visitor.setFileName("a");
		root.accept(visitor);
		ApfsFile actual = visitor.getFile();
		assertArrayEquals(expected,fileToStringArray(actual));
	}
	
	@Test
	public void verifyFileEqualityB() {
		ApfsFileSearchVisitor visitor = new ApfsFileSearchVisitor();
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		String[] expected = { "false", "b", "150", localTime.toString(), "system", "puneeth", localTime.toString() };
		visitor.setFileName("b");
		root.accept(visitor);
		ApfsFile actual = visitor.getFile();
		assertArrayEquals(expected,fileToStringArray(actual));
	}
	
	@Test
	public void verifyFileEqualityC() {
		ApfsFileSearchVisitor visitor = new ApfsFileSearchVisitor();
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		String[] expected = { "false", "c", "200", localTime.toString(), "system", "puneeth", localTime.toString() };
		visitor.setFileName("c");
		root.accept(visitor);
		ApfsFile actual = visitor.getFile();
		assertArrayEquals(expected,fileToStringArray(actual));
	}
	
	@Test
	public void verifyFileEqualityD() {
		ApfsFileSearchVisitor visitor = new ApfsFileSearchVisitor();
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		String[] expected = { "false", "d", "50", localTime.toString(), "home", "puneeth", localTime.toString() };
		visitor.setFileName("d");
		root.accept(visitor);
		ApfsFile actual = visitor.getFile();
		assertArrayEquals(expected,fileToStringArray(actual));
	}
	
	@Test
	public void verifyFileEqualityE() {
		ApfsFileSearchVisitor visitor = new ApfsFileSearchVisitor();
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		String[] expected = { "false", "e", "100", localTime.toString(), "pictures", "puneeth", localTime.toString() };
		visitor.setFileName("e");
		root.accept(visitor);
		ApfsFile actual = visitor.getFile();
		assertArrayEquals(expected,fileToStringArray(actual));
	}
	
	@Test
	public void verifyFileEqualityF() {
		ApfsFileSearchVisitor visitor = new ApfsFileSearchVisitor();
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		String[] expected = { "false", "f", "200", localTime.toString(), "pictures", "puneeth", localTime.toString() };
		visitor.setFileName("f");
		root.accept(visitor);
		ApfsFile actual = visitor.getFile();
		assertArrayEquals(expected,fileToStringArray(actual));
	}

}
