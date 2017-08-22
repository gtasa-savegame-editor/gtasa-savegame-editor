package nl.paulinternet.gtasaveedit.model;

import nl.paulinternet.gtasaveedit.model.exceptions.FileFormatException;
import nl.paulinternet.gtasaveedit.model.savegame.Savegame;
import nl.paulinternet.gtasaveedit.model.variables.Variable;

import java.io.RandomAccessFile;

public class QuickLoad extends Variable<String>
{
	private int number;
	
	public QuickLoad (int number) {
		this.number = number;
		loadValue();
	}
	
	public void loadValue () {
		// Try to read the bytes of the title
		byte[] data;
		RandomAccessFile file = null;
		try {
			// Open file
			file = new RandomAccessFile(Model.getSavegameFile(number), "r");
			if (file.length() != Savegame.FILESIZE) throw new FileFormatException();

			// Read bytes
			file.skipBytes(9);
			data = new byte[100];
			file.readFully(data);
		}
		catch (Exception e) {
			data = null;
		}
		finally {
			try {
				file.close();
			}
			catch (Exception ignored) {}
		}
		
		// Set the value
		if (data == null) {
			setValue(null);
		}
		else {
			int i;
			//noinspection StatementWithEmptyBody
			for (i=0; i<data.length && data[i] != 0; i++);
			String text = new String(data, 0, i).replace(']', '*').replace('(', '[').replace(')', ']');
			setValue(text);
		}
	}
}
