package edu.umb.cs680.hw07;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ApfsDirectoryTest {

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
	
	private String[] dirToStringArray(ApfsDirectory d) {
		Optional<ApfsDirectory> optionalDirectory = Optional.ofNullable(d.getParent());
		String[] dirInfo = { Boolean.toString(d.isDirectory()), d.getName(), 
				Integer.toString(d.getSize()), d.getCreationTime().toString(), 
				optionalDirectory.isPresent()?d.getParent().getName():null, 
						Integer.toString(d.getTotalSize()),
						Integer.toString(d.countChildren()), d.getOwnerName(),d.getLastModified().toString()};
		return dirInfo;
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
	void checkfetchedDirAndFile() {
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		assertSame("home", root.findDirByName("home").getName());
		assertSame("root", root.findDirByName("root").getName());
		assertSame("system", root.findDirByName("system").getName());
		assertSame("pictures", root.findDirByName("pictures").getName());
		assertSame("a", root.findFileByName("a").getName());
		assertSame("f", root.findFileByName("f").getName());
	}
	
	@Test
	void checkDirectorySize() {
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		assertEquals(800, root.findDirByName("root").getTotalSize());
		assertEquals(350, root.findDirByName("home").getTotalSize());
		assertEquals(450, root.findDirByName("system").getTotalSize());
		assertEquals(300, root.findDirByName("pictures").getTotalSize());
	}
	
	@Test
	void checkFilesInDirectory() {
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		assertSame("d", root.findDirByName("home").getFiles().get(0).getName());
		assertSame("a", root.findDirByName("system").getFiles().get(0).getName());
		assertSame("b", root.findDirByName("system").getFiles().get(1).getName());
		assertSame("c", root.findDirByName("system").getFiles().get(2).getName());
	}
	
	@Test
	void checkSubDirectories() {
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		assertSame("system", root.findDirByName("root").getSubDirectories().get(0).getName());
		assertSame("home", root.findDirByName("root").getSubDirectories().get(1).getName());
	}
	
	@Test
	void checkChildrenCount() {
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		assertEquals(2, root.findDirByName("root").countChildren());
		assertEquals(3, root.findDirByName("system").countChildren());
		assertEquals(2, root.findDirByName("home").countChildren());
		assertEquals(2, root.findDirByName("pictures").countChildren());
	}
	
	@Test
	public void verifyDirectoryEqualityRoot() {
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		String[] expected = { "true", "root", "0", localTime.toString(), null, "800", "2", "puneeth", localTime.toString() };
		ApfsDirectory actual = root.findDirByName("root");
		assertArrayEquals(expected,dirToStringArray(actual));
	}
	
	@Test
	public void verifyDirectoryEqualityHome() { 
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		String[] expected = { "true", "home", "0", localTime.toString(), "root", "350", "2", "puneeth", localTime.toString() };
		ApfsDirectory actual = root.findDirByName("home");
		assertArrayEquals(expected,dirToStringArray(actual));
	}
	
	@Test
	public void verifyDirectoryEqualitySystem() { 
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		String[] expected = { "true", "system", "0", localTime.toString(), "root", "450", "3", "puneeth", localTime.toString() };
		ApfsDirectory actual = root.findDirByName("system");
		assertArrayEquals(expected,dirToStringArray(actual));
	}
	
	@Test
	public void verifyDirectoryEqualityPictures() { 
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		String[] expected = { "true", "pictures", "0", localTime.toString(), "home", "300", "2", "puneeth", localTime.toString() };
		ApfsDirectory actual = root.findDirByName("pictures");
		assertArrayEquals(expected,dirToStringArray(actual));
	}

}
