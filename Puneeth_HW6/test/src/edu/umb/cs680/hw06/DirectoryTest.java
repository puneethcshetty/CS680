package edu.umb.cs680.hw06;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.umb.cs680.hw06.Directory;
import edu.umb.cs680.hw06.File;
import edu.umb.cs680.hw06.FileSystem;

class DirectoryTest {
	
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
		Link x = new Link(home, "x", 0, localTime, system);
		Link y = new Link(pictures, "y", 0, localTime, e);
		
		FileSystem.getFileSystem().addRootDir(root);
	}
	
	private String[] dirToStringArray(Directory d) {
		Optional<Directory> optionalDirectory = Optional.ofNullable(d.getParent());
		String[] dirInfo = { Boolean.toString(d.isDirectory()), d.getName(), 
				Integer.toString(d.getSize()), d.getCreationTime().toString(), 
				optionalDirectory.isPresent()?d.getParent().getName():null, 
						Integer.toString(d.getTotalSize()),
						Integer.toString(d.countChildren())};
		return dirInfo;
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
	void checkfetchedDirAndFile() {
		FileSystem fileSystem = FileSystem.getFileSystem();
		assertSame("home", fileSystem.getRootDirs().get(0).findDirByName("home").getName());
		assertSame("root", fileSystem.getRootDirs().get(0).findDirByName("root").getName());
		assertSame("system", fileSystem.getRootDirs().get(0).findDirByName("system").getName());
		assertSame("pictures", fileSystem.getRootDirs().get(0).findDirByName("pictures").getName());
		assertSame("a", fileSystem.getRootDirs().get(0).findFileByName("a").getName());
		assertSame("f", fileSystem.getRootDirs().get(0).findFileByName("f").getName());
	}
	
	@Test
	void checkDirectorySize() {
		FileSystem fileSystem = FileSystem.getFileSystem();
		assertEquals(800, fileSystem.getRootDirs().get(0).findDirByName("root").getTotalSize());
		assertEquals(350, fileSystem.getRootDirs().get(0).findDirByName("home").getTotalSize());
		assertEquals(450, fileSystem.getRootDirs().get(0).findDirByName("system").getTotalSize());
		assertEquals(300, fileSystem.getRootDirs().get(0).findDirByName("pictures").getTotalSize());
	}
	
	@Test
	void checkFilesInDirectory() {
		FileSystem fileSystem = FileSystem.getFileSystem();
		assertSame("d", fileSystem.getRootDirs().get(0).findDirByName("home").getFiles().get(0).getName());
		assertSame("a", fileSystem.getRootDirs().get(0).findDirByName("system").getFiles().get(0).getName());
		assertSame("b", fileSystem.getRootDirs().get(0).findDirByName("system").getFiles().get(1).getName());
		assertSame("c", fileSystem.getRootDirs().get(0).findDirByName("system").getFiles().get(2).getName());
	}
	
	@Test
	void checkSubDirectories() {
		FileSystem fileSystem = FileSystem.getFileSystem();
		assertSame("system", fileSystem.getRootDirs().get(0).findDirByName("root").getSubDirectories().get(0).getName());
		assertSame("home", fileSystem.getRootDirs().get(0).findDirByName("root").getSubDirectories().get(1).getName());
	}
	
	@Test
	void checkChildrenCount() {
		FileSystem fileSystem = FileSystem.getFileSystem();
		assertEquals(2, fileSystem.getRootDirs().get(0).findDirByName("root").countChildren());
		assertEquals(3, fileSystem.getRootDirs().get(0).findDirByName("system").countChildren());
		assertEquals(2, fileSystem.getRootDirs().get(0).findDirByName("home").countChildren());
		assertEquals(2, fileSystem.getRootDirs().get(0).findDirByName("pictures").countChildren());
	}
	
	@Test
	public void verifyDirectoryEqualityRoot() {
		FileSystem fileSystem = FileSystem.getFileSystem();
		String[] expected = { "true", "root", "0", localTime.toString(), null, "800", "2" };
		Directory actual = fileSystem.getRootDirs().get(0).findDirByName("root");
		assertArrayEquals(expected,dirToStringArray(actual));
	}
	
	@Test
	public void verifyDirectoryEqualityHome() { 
		FileSystem fileSystem = FileSystem.getFileSystem();
		String[] expected = { "true", "home", "0", localTime.toString(), "root", "350", "2" };
		Directory actual = fileSystem.getRootDirs().get(0).findDirByName("home");
		assertArrayEquals(expected,dirToStringArray(actual));
	}
	
	@Test
	public void verifyDirectoryEqualitySystem() { 
		FileSystem fileSystem = FileSystem.getFileSystem();
		String[] expected = { "true", "system", "0", localTime.toString(), "root", "450", "3" };
		Directory actual = fileSystem.getRootDirs().get(0).findDirByName("system");
		assertArrayEquals(expected,dirToStringArray(actual));
	}
	
	@Test
	public void verifyDirectoryEqualityPictures() { 
		FileSystem fileSystem = FileSystem.getFileSystem();
		String[] expected = { "true", "pictures", "0", localTime.toString(), "home", "300", "2" };
		Directory actual = fileSystem.getRootDirs().get(0).findDirByName("pictures");
		assertArrayEquals(expected,dirToStringArray(actual));
	}
}
