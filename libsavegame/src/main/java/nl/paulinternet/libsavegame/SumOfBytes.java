package nl.paulinternet.libsavegame;

import java.util.zip.Checksum;

public class SumOfBytes implements Checksum
{
	private int value;
	
	public SumOfBytes () {}

	@Override
	public long getValue () {
		return value;
	}

	@Override
	public void reset () {
		value = 0;
	}

	@Override
	public void update (int b) {
		value += b & 0xff;
	}

	@Override
	public void update (byte[] b, int off, int len) {
		for (int i=off; i<off+len; i++) {
			value += b[i] & 0xff;
		}
	}
}
