package edu.umb.cs680.hw15;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.LinkedList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.umb.cs680.hw15.APFS;
import edu.umb.cs680.hw15.ApfsDirectory;
import edu.umb.cs680.hw15.ApfsElement;
import edu.umb.cs680.hw15.ApfsFile;
import edu.umb.cs680.hw15.ApfsLink;

class TimeStampComparatorTest {
	
static LocalDateTime localTime = LocalDateTime.of(2019, 5, 17, 0, 0);
	
	@SuppressWarnings("unused")
	@BeforeAll
	public static void initialize() {
		
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.initFileSystem("drive", 2000);
		ApfsDirectory system = new ApfsDirectory(root, "system", 0, localTime, "puneeth", LocalDateTime.of(2019, 5, 20, 0, 0));
		ApfsDirectory home = new ApfsDirectory(root, "home", 0, localTime, "puneeth", LocalDateTime.of(2019, 5, 20, 10, 0));
		ApfsDirectory pictures = new ApfsDirectory(home, "pictures", 0, localTime, "puneeth", LocalDateTime.of(2019, 5, 20, 5, 0));
		ApfsFile a = new ApfsFile(system, "a", 100, localTime, "puneeth", LocalDateTime.of(2019, 5, 21, 10, 0));
		ApfsFile b = new ApfsFile(system, "b", 150, localTime, "puneeth", LocalDateTime.of(2019, 5, 20, 10, 0));
		ApfsFile c = new ApfsFile(system, "c", 200, localTime, "puneeth", LocalDateTime.of(2019, 5, 21, 15, 0));
		ApfsFile d = new ApfsFile(home, "d", 50, localTime, "puneeth", LocalDateTime.of(2019, 5, 20, 20, 0));
		ApfsFile e = new ApfsFile(pictures, "e", 100, localTime, "puneeth", LocalDateTime.of(2019, 5, 21, 23, 0));
		ApfsFile f = new ApfsFile(pictures, "f", 200, localTime, "puneeth", LocalDateTime.of(2019, 5, 21, 0, 20));
		ApfsLink x = new ApfsLink(home, "x", 0, localTime, "puneeth", LocalDateTime.of(2019, 5, 22, 0, 15), system);
		ApfsLink y = new ApfsLink(pictures, "y", 0, localTime, "puneeth", LocalDateTime.of(2019, 5, 22, 0, 20), e);
	}
	
	private String[] elementToStringArray(LinkedList<ApfsElement> fsElements) {
		String[] filesNameList = new String[fsElements.size()];
		int i = 0;
		for(ApfsElement e : fsElements) {
			filesNameList[i] = e.getName();
			i++;
		}
		return filesNameList;
	}

	@Test
	void checkReverseComparatorChildren() {
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		String[] expected = {"system", "home"};
		assertArrayEquals(expected,elementToStringArray(root.getChildren((ApfsElement o1, ApfsElement o2) -> o1.getLastModified().compareTo(o2.getLastModified()))));
	}

	@Test
	void checkReverseComparatorDirc() {
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		String[] expected = {"system", "home"};
		LinkedList<ApfsElement> actual = new LinkedList<ApfsElement>();
		for(ApfsElement e : root.getSubDirectories((ApfsElement o1, ApfsElement o2) -> o1.getLastModified().compareTo(o2.getLastModified()))){
			actual.add(e);
		}
		assertArrayEquals(expected,elementToStringArray(actual));
	}
	
	@Test
	void checkReverseComparatorFiles() {
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		String[] expected = {"b", "a", "c"};
		LinkedList<ApfsElement> actual = new LinkedList<ApfsElement>();
		for(ApfsElement e : root.findDirByName("system").getFiles((ApfsElement o1, ApfsElement o2) -> o1.getLastModified().compareTo(o2.getLastModified()))){
			actual.add(e);
		}
		assertArrayEquals(expected,elementToStringArray(actual));
	}
}
