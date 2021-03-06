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

class ReverseAlphabeticalComparatorTest {

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
		assertArrayEquals(expected,elementToStringArray(root.getChildren((ApfsElement o1, ApfsElement o2) -> o2.getName().compareTo(o1.getName()))));
	}

	@Test
	void checkReverseComparatorDirc() {
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		String[] expected = {"system", "home"};
		LinkedList<ApfsElement> actual = new LinkedList<ApfsElement>();
		for(ApfsElement e : root.getSubDirectories((ApfsElement o1, ApfsElement o2) -> o2.getName().compareTo(o1.getName()))){
			actual.add(e);
		}
		assertArrayEquals(expected,elementToStringArray(actual));
	}
	
	@Test
	void checkReverseComparatorFiles() {
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		String[] expected = {"c", "b", "a"};
		LinkedList<ApfsElement> actual = new LinkedList<ApfsElement>();
		for(ApfsElement e : root.findDirByName("system").getFiles((ApfsElement o1, ApfsElement o2) -> o2.getName().compareTo(o1.getName()))){
			actual.add(e);
		}
		assertArrayEquals(expected,elementToStringArray(actual));
	}
}
