package nl.paulinternet.libsavegame;

import java.io.IOException;
import java.io.OutputStream;

/**
 * This class represents a byte array.
 * An instance of this class has a reference to the byte array passed in the constructor,
 * changes in the byte array are reflected by this object and vice versa.
 * 
 * @see ByteSequence
 */
public class ByteArray
{
	private byte[] array;
	private int offset;
	private int length;
	
	/**
	 * Constructs a ByteArray representing the specified array
	 * 
	 * @throws NullPointerException if the specified array is null
	 */
	public ByteArray (byte[] array) {
		if (array == null) throw new NullPointerException();
		this.array = array;
		this.offset = 0;
		this.length = array.length;
	}

	/**
	 * Constructs a ByteArray representing a part of an array
	 * 
	 * @throws IndexOutOfBoundsException if (offset < 0 || offset + length > array.length)
	 * @throws NullPointerException if the specified array is null
	 */
	public ByteArray (byte[] array, int offset, int length) {
		if (array == null) throw new NullPointerException();
		if (offset < 0 || offset + length > array.length) throw new IndexOutOfBoundsException();
		this.array = array;
		this.offset = offset;
		this.length = length;
	}

	/**
	 * Returns the length of this array.
	 */
	public int getLength () {
		return length;
	}
	
	/**
	 * Copies a part of this array to a byte array.
	 * 
	 * @param array the byte array receiving the data
	 * @param arrayOffset the offset in the byte array
	 * @param begin the begin index of this array
	 * @param end the end index of this array
	 * 
	 * @throws NullPointerException if the specified array is null
	 * @throws IndexOutOfBoundsException if any index is out of bounds
	 */
	public void copyTo (byte[] array, int arrayOffset, int begin, int end) {
		if (array == null) throw new NullPointerException();
		if (begin < 0 || begin > end || end > length) throw new IndexOutOfBoundsException();
		if (array.length - arrayOffset < end - begin) throw new IndexOutOfBoundsException();
		System.arraycopy(this.array, offset + begin, array, arrayOffset, end - begin);
	}
	
	/**
	 * Overwrites a part of this ByteArray.
	 * 
	 * @param array the byte array containing the data
	 * @param arrayOffset the offset in the byte array
	 * @param begin the begin index of this array
	 * @param end the end index of this array
	 * 
	 * @throws NullPointerException if the specified array is null
	 * @throws IndexOutOfBoundsException if any index is out of bounds
	 */
	public void copyFrom (byte[] array, int arrayOffset, int begin, int end) {
		if (array == null) throw new NullPointerException();
		if (begin < 0 || begin > end || end > length) throw new IndexOutOfBoundsException();
		if (array.length - arrayOffset < end - begin) throw new IndexOutOfBoundsException();
		System.arraycopy(array, arrayOffset, this.array, offset + begin, end - begin);
	}
	
	/**
	 * Gets the value of a byte.
	 * 
	 * @throws IndexOutOfBoundsException if (pos < 0 || pos > length)
	 */
	public byte getByte (int pos) {
		if (pos < 0 || pos > length) throw new IndexOutOfBoundsException();
		return array[offset + pos];
	}
	
	/**
	 * Sets the value of a byte.
	 * 
	 * @throws IndexOutOfBoundsException if (pos < 0 || pos > length)
	 */
	public void setByte (int pos, byte value) {
		if (pos < 0 || pos > length) throw new IndexOutOfBoundsException();
		array[offset + pos] = value;
	}
	
	/**
	 * Returns an instance of ByteArray representing a part of this array.
	 * The data is not copied.
	 */
	public ByteArray getSubArray (int begin, int end) {
		if (begin < 0 || begin > end || end > length) throw new IndexOutOfBoundsException();
		return new ByteArray(array, offset + begin, end - begin);
	}
	
	/**
	 * Writes the contents of this byte array to an OutputStream.
	 * 
	 * @throws IOException when something goes wrong
	 */
	public void writeData (OutputStream out) throws IOException {
		out.write(array, offset, length);
	}
}
