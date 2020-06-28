package edu.umb.cs680.hw06;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class LinkTest {
	
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
	
	private String[] fsElementToStringArray(FSElement f) {
		Optional<Directory> optionalDirectory = Optional.ofNullable(f.getParent());
		String[] fsElementInfo = { Boolean.toString(f.isDirectory()), f.getName(), 
				Integer.toString(f.getSize()), f.getCreationTime().toString(), 
				optionalDirectory.isPresent()?f.getParent().getName():null};
		return fsElementInfo;
	}
	
	@Test
	public void verifyTargetEqualityHome() {
		FileSystem fileSystem = FileSystem.getFileSystem();
		String[] expected = { "true", "system", "0", localTime.toString(), "root" };
		Directory actual = (Directory) fileSystem.getRootDirs().get(0).findLinkByName("x").getTarget();
		assertArrayEquals(expected,fsElementToStringArray(actual));
	}
	
	@Test
	public void verifyTargetEqualityE() {
		FileSystem fileSystem = FileSystem.getFileSystem();
		String[] expected = { "false", "e", "100", localTime.toString(), "pictures" };
		File actual = (File) fileSystem.getRootDirs().get(0).findLinkByName("y").getTarget();
		assertArrayEquals(expected,fsElementToStringArray(actual));
	}

}
