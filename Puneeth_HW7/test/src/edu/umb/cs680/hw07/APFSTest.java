package edu.umb.cs680.hw07;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class APFSTest {

	static LocalDateTime localTime = LocalDateTime.of(2019, 5, 17, 0, 0);

	@BeforeAll
	public static void initialize() {
		
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsFileSystem.initFileSystem("drive", 2000);
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
	public void checkDefaultRootDir() {
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		String[] expected = { "true", "root", "0", localTime.toString(), null, "0", "0", "puneeth", localTime.toString() };
		ApfsDirectory actual = root.findDirByName("root");
		assertArrayEquals(expected,dirToStringArray(actual));
	}

}
