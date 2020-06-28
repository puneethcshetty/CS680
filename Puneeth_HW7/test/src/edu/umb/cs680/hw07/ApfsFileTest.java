package edu.umb.cs680.hw07;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ApfsFileTest {

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
	void checkIfDirectory() {
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		assertTrue(root.findDirByName("root").isDirectory());
		assertTrue(root.findDirByName("home").isDirectory());
		assertTrue(root.findDirByName("system").isDirectory());
		assertTrue(root.findDirByName("pictures").isDirectory());
		assertFalse(root.findFileByName("a").isDirectory());
		assertFalse(root.findFileByName("b").isDirectory());
		assertFalse(root.findFileByName("c").isDirectory());
		assertFalse(root.findFileByName("d").isDirectory());
		assertFalse(root.findFileByName("e").isDirectory());
		assertFalse(root.findFileByName("f").isDirectory());
	}
	
	@Test
	public void verifyFileEqualityA() {
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		String[] expected = { "false", "a", "100", localTime.toString(), "system", "puneeth", localTime.toString() };
		ApfsFile actual = root.findFileByName("a");
		assertArrayEquals(expected,fileToStringArray(actual));
	}
	
	@Test
	public void verifyFileEqualityB() {
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		String[] expected = { "false", "b", "150", localTime.toString(), "system", "puneeth", localTime.toString() };
		ApfsFile actual = root.findFileByName("b");
		assertArrayEquals(expected,fileToStringArray(actual));
	}
	
	@Test
	public void verifyFileEqualityC() {
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		String[] expected = { "false", "c", "200", localTime.toString(), "system", "puneeth", localTime.toString() };
		ApfsFile actual = root.findFileByName("c");
		assertArrayEquals(expected,fileToStringArray(actual));
	}
	
	@Test
	public void verifyFileEqualityD() {
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		String[] expected = { "false", "d", "50", localTime.toString(), "home", "puneeth", localTime.toString() };
		ApfsFile actual = root.findFileByName("d");
		assertArrayEquals(expected,fileToStringArray(actual));
	}
	
	@Test
	public void verifyFileEqualityE() {
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		String[] expected = { "false", "e", "100", localTime.toString(), "pictures", "puneeth", localTime.toString() };
		ApfsFile actual = root.findFileByName("e");
		assertArrayEquals(expected,fileToStringArray(actual));
	}
	
	@Test
	public void verifyFileEqualityF() {
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		String[] expected = { "false", "f", "200", localTime.toString(), "pictures", "puneeth", localTime.toString() };
		ApfsFile actual = root.findFileByName("f");
		assertArrayEquals(expected,fileToStringArray(actual));
	}

}
