package edu.umb.cs680.hw05;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class FileTest {
	
	static LocalDateTime localTime = LocalDateTime.now();
	
	@SuppressWarnings("unused")
	@BeforeAll
	public static void initialize() {
		Directory root = new Directory(null, "root", 0, localTime);
		Directory system = new Directory(root, "system", 0, localTime);
		Directory home = new Directory(root, "home", 0, localTime);
		Directory pictures = new Directory(home, "pictures", 0, localTime);
		File a = new File(system, "a", 100, localTime);
		File b = new File(system, "b", 150, localTime);
		File c = new File(system, "c", 200, localTime);
		File d = new File(home, "d", 50, localTime);
		File e = new File(pictures, "e", 100, localTime);
		File f = new File(pictures, "f", 200, localTime);
		
		FileSystem.getFileSystem().addRootDir(root);
	}
	
	private String[] fileToStringArray(File f) {
		Optional<Directory> optionalDirectory = Optional.ofNullable(f.getParent());
		String[] fileInfo = { Boolean.toString(f.isDirectory()), f.getName(), 
				Integer.toString(f.getSize()), f.getCreationTime().toString(), 
				optionalDirectory.isPresent()?f.getParent().getName():null};
		return fileInfo;
	}

	@Test
	void checkIfDirectory() {
		FileSystem fileSystem = FileSystem.getFileSystem();
		assertTrue(fileSystem.getRootDirs().get(0).findDirByName("root").isDirectory());
		assertTrue(fileSystem.getRootDirs().get(0).findDirByName("home").isDirectory());
		assertTrue(fileSystem.getRootDirs().get(0).findDirByName("system").isDirectory());
		assertTrue(fileSystem.getRootDirs().get(0).findDirByName("pictures").isDirectory());
		assertFalse(fileSystem.getRootDirs().get(0).findFileByName("a").isDirectory());
		assertFalse(fileSystem.getRootDirs().get(0).findFileByName("b").isDirectory());
		assertFalse(fileSystem.getRootDirs().get(0).findFileByName("c").isDirectory());
		assertFalse(fileSystem.getRootDirs().get(0).findFileByName("d").isDirectory());
		assertFalse(fileSystem.getRootDirs().get(0).findFileByName("e").isDirectory());
		assertFalse(fileSystem.getRootDirs().get(0).findFileByName("f").isDirectory());
	}
	
	@Test
	public void verifyFileEqualityA() {
		FileSystem fileSystem = FileSystem.getFileSystem();
		String[] expected = { "false", "a", "100", localTime.toString(), "system" };
		File actual = fileSystem.getRootDirs().get(0).findFileByName("a");
		assertArrayEquals(expected,fileToStringArray(actual));
	}
	
	@Test
	public void verifyFileEqualityB() {
		FileSystem fileSystem = FileSystem.getFileSystem();
		String[] expected = { "false", "b", "150", localTime.toString(), "system" };
		File actual = fileSystem.getRootDirs().get(0).findFileByName("b");
		assertArrayEquals(expected,fileToStringArray(actual));
	}
	
	@Test
	public void verifyFileEqualityC() {
		FileSystem fileSystem = FileSystem.getFileSystem();
		String[] expected = { "false", "c", "200", localTime.toString(), "system" };
		File actual = fileSystem.getRootDirs().get(0).findFileByName("c");
		assertArrayEquals(expected,fileToStringArray(actual));
	}
	
	@Test
	public void verifyFileEqualityD() {
		FileSystem fileSystem = FileSystem.getFileSystem();
		String[] expected = { "false", "d", "50", localTime.toString(), "home" };
		File actual = fileSystem.getRootDirs().get(0).findFileByName("d");
		assertArrayEquals(expected,fileToStringArray(actual));
	}
	
	@Test
	public void verifyFileEqualityE() {
		FileSystem fileSystem = FileSystem.getFileSystem();
		String[] expected = { "false", "e", "100", localTime.toString(), "pictures" };
		File actual = fileSystem.getRootDirs().get(0).findFileByName("e");
		assertArrayEquals(expected,fileToStringArray(actual));
	}
	
	@Test
	public void verifyFileEqualityF() {
		FileSystem fileSystem = FileSystem.getFileSystem();
		String[] expected = { "false", "f", "200", localTime.toString(), "pictures" };
		File actual = fileSystem.getRootDirs().get(0).findFileByName("f");
		assertArrayEquals(expected,fileToStringArray(actual));
	}

}
