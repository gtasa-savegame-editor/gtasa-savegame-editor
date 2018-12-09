package nl.paulinternet.gtasaveedit.tool.util;

import java.io.File;
import java.util.List;
import java.util.zip.CRC32;

import nl.paulinternet.gtasaveedit.model.io.ArchiveReader;

public class CrcCalculator
{
	private static final String IMAGE_FILE = "C:/Program Files/Rockstar Games/GTA San Andreas/models/player.img";

	public static void main (String[] args) throws Exception {
		List<String> files = new ArchiveReader(new File(IMAGE_FILE)).getFiles();
		
		CRC32 crc = new CRC32();
		
		for (String file : files) {
			if (file.endsWith(".dff") || file.endsWith(".txd")) {
				crc.reset();
				crc.update(file.substring(0, file.length() - 4).toUpperCase().getBytes());
				System.out.println(file + ": " + (int) ~crc.getValue());
				
			}
		}
	}
}
