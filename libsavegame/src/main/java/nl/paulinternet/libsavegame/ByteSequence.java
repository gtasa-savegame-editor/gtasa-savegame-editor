package nl.paulinternet.libsavegame;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class ByteSequence
{
	private List<ByteArray> array;
	private int length;
	
	public ByteSequence () {
		array = new ArrayList<>();
		length = 0;
	}

	public ByteSequence (byte[] data) {
		array = new ArrayList<>();
		array.add(new ByteArray(data));
		length = data.length;
	}
	
	public ByteSequence append (ByteSequence data) {
		array.addAll(data.array);
		length += data.length;
		return this;
	}
	
	public ByteSequence appendSpace (int length) {
		array.add(new ByteArray(new byte[length]));
		this.length += length;
		return this;
	}
	
	public byte[] getBytes (int begin, int end) {
		// Check arguments
		if (begin < 0 || end < begin || end > length) throw new IndexOutOfBoundsException();
		
		// Create a new byte array
		byte[] data = new byte[end - begin];
		
		// Copy the data
		int partBegin = 0;
		
		for (ByteArray part : array) {
			int partEnd = partBegin + part.getLength();
			
			int rangeOffset = Math.max(begin - partBegin, 0);
			int rangeEnd = Math.min(end, partEnd) - partBegin;
			
			if (rangeOffset < rangeEnd) {
				part.copyTo(data, partBegin + rangeOffset - begin, rangeOffset, rangeEnd);
			}

			partBegin = partEnd;
		}
		
		// Return
		return data;
	}
	
	public int getLength () {
		return length;
	}
	
	public ByteSequence getSubSequence (int begin) {
		return getSubSequence(begin, length);
	}

	public ByteSequence getSubSequence (int begin, int end) {
		// Check arguments
		if (begin < 0 || begin > length || end < begin || end > length) throw new IndexOutOfBoundsException();

		// Create a new ByteSequence
		ByteSequence sub = new ByteSequence();
		sub.length = end - begin;

		// Fill the ByteSequence with ByteArrayPart objects
		int partBegin = 0;
		
		for (ByteArray part : array) {
			int partEnd = partBegin + part.getLength();
			
			int rangeOffset = Math.max(begin - partBegin, 0);
			int rangeEnd = Math.min(end, partEnd) - partBegin;
			
			if (rangeOffset < rangeEnd) {
				if (rangeEnd - rangeOffset == part.getLength()) {
					sub.array.add(part);
				}
				else {
					sub.array.add(part.getSubArray(rangeOffset, rangeEnd));
				}
			}

			partBegin = partEnd;
		}

		// Return
		return sub;
	}

	public void overwrite (byte[] data, int offset) {
		// Check arguments
		if (offset < 0 || offset + data.length > length) throw new IndexOutOfBoundsException();

		// Overwrite the data
		int partBegin = 0;
		
		for (ByteArray part : array) {
			int partEnd = partBegin + part.getLength();
			
			int rangeOffset = Math.max(offset - partBegin, 0);
			int rangeEnd = Math.min(offset + data.length, partEnd) - partBegin;
			
			if (rangeOffset < rangeEnd) {
				part.copyFrom(data, partBegin + rangeOffset - offset, rangeOffset, rangeEnd);
			}

			partBegin = partEnd;
		}
	}
	
	public void setByte (int pos, byte data) {
		// Check arguments
		if (pos < 0 || pos >= length) throw new IndexOutOfBoundsException();

		// Copy the data
		int partBegin = 0;
		
		for (ByteArray part : array) {
			int partEnd = partBegin + part.getLength();

			if (pos >= partBegin && pos < partEnd) {
				part.setByte(pos - partBegin, data);
				return;
			}

			partBegin = partEnd;
		}
	}
	
	public void writeContents (OutputStream out) throws IOException {
		for (ByteArray part : array) {
			part.writeData(out);
		}
	}
	
	public void writeContents (File file) throws IOException {
		OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
		try {
			writeContents(out);
		}
		finally {
			try {out.close();} catch (IOException e) {}
		}
	}
}
