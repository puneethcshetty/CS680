package edu.umb.cs680.hw06;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import edu.umb.cs680.hw06.Directory;
import edu.umb.cs680.hw06.FileSystem;

class FileSystemTest {

	@Test
	void checkFileInstance() {
		FileSystem firstInstance = FileSystem.getFileSystem();
		FileSystem secondInstance = FileSystem.getFileSystem();
		assertSame(firstInstance, secondInstance);
	}

	@Test
	void checkRootDir() {
		LocalDateTime localDateTime = LocalDateTime.now();
		Directory root = new Directory(null, "root", 0, localDateTime);
		String[] expected = {null, "root", "0", localDateTime.toString()}; 
		FileSystem.getFileSystem().addRootDir(root);
		Directory actual = FileSystem.getFileSystem().getRootDirs().get(0);
		assertArrayEquals(expected, rootToStringArray(actual));
	}
	
	private String[] rootToStringArray(Directory d){
		String[] dirInfo = {null, d.getName(), Integer.toString(d.getSize()), d.getCreationTime().toString()};
		return dirInfo; 
	}

}
