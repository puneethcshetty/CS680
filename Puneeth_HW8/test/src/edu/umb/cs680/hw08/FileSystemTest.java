package edu.umb.cs680.hw08;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class FileSystemTest {

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
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		Optional<ApfsDirectory> optionalDirectory = Optional.ofNullable(d.getParent());
		String[] dirInfo = { Boolean.toString(d.isDirectory()), d.getName(), 
				Integer.toString(d.getSize()), d.getCreationTime().toString(), 
				optionalDirectory.isPresent()?d.getParent().getName():null, 
						Integer.toString(d.getTotalSize()),
						Integer.toString(d.countChildren()), ApfsFileSystem.getFileSystemName(), 
						Integer.toString(ApfsFileSystem.getCapacity()), d.getOwnerName(),d.getLastModified().toString() };
		return dirInfo;
	}
	
	@Test
	public void verifyInitialization() {
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		String[] expected = { "true", "root", "0", localTime.toString(), null, "800", "2", "drive", "2000", "puneeth", localTime.toString()};
		ApfsDirectory actual = root.findDirByName("root");
		assertArrayEquals(expected,dirToStringArray(actual));
	}
	
	@Test
	public void verifyOwner() {
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		assertEquals(root.getOwnerName(), "puneeth");
		assertEquals(root.getLastModified(), localTime);
	}

}
