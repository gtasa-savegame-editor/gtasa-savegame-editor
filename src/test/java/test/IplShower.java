package test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import nl.paulinternet.gtasaveedit.model.io.ArchiveReader;

public class IplShower
{
	private static final String IMAGE_FILE_V1 = "C:/Program Files/Rockstar Games/GTA San Andreas (v1)/models/gta3.img";
	private static final String IMAGE_FILE_V2 = "C:/Program Files/Rockstar Games/GTA San Andreas/models/gta3.img";

	public static void main (String[] args) throws Exception {
		// Get file lists
		List<String> v1Files = getIplList(IMAGE_FILE_V1);
		List<String> v2Files = getIplList(IMAGE_FILE_V2);
		
		// List to convert to v1
		for (String file : v1Files) {
			System.out.print(v2Files.indexOf(file) + ", ");
		}
		System.out.println();
	
		// List to convert to v2
		System.out.println();
		for (String file : v2Files) {
			System.out.print(v1Files.indexOf(file) + ", ");
		}
	}
	
	private static List<String> getIplList (String archive) throws Exception {
		List<String> files = new ArchiveReader(new File(archive)).getFiles();
		
		List<String> iplFiles = new ArrayList<String>();
		for (String file : files) {
			if (file.endsWith(".ipl")) iplFiles.add(file);
		}
		return iplFiles;
	}
}
