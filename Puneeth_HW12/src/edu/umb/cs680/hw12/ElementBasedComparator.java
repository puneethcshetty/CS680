package edu.umb.cs680.hw12;

import java.util.Comparator;

public class ElementBasedComparator implements Comparator<ApfsElement>{

	@Override
	public int compare(ApfsElement o1, ApfsElement o2) {
		if(o1 instanceof ApfsDirectory)
			return -1;
		if(o1 instanceof ApfsFile)
			return 0;
		return 1;
	}

}
