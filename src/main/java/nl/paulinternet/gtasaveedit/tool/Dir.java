package nl.paulinternet.gtasaveedit.tool;

import java.io.File;

public class Dir
{
	public static final String RESEARCH_PATH = "c:/Cache/gta";

	public static File getSavegameFile (String number) {
		return new File("C:/Users/Paul Breeuwsma/Documents/GTA San Andreas User Files/GTASAsf" + number + ".b");
	}

	public static int[] getBlocks () {
		return new int[] {0, 1, 2, 3, 4, 5, 6, 8, 9, 10, 11, 12, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27};
	}

	public static String getBlockFile (int block) {
		String number = block < 10 ? "0" + block : String.valueOf(block);
		return number + ".block";
	}

	public static File getBlockPath (String path, int block) {
		return new File(getBlockPath(path), getBlockFile(block));
	}

	public static File getBlockPath (String path) {
		return new File(RESEARCH_PATH, path);
	}
}
