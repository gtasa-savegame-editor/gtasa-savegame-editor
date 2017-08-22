package nl.paulinternet.gtasaveedit.model;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * @deprecated
 */
public class TextureLoader
{
	public static BufferedImage getTexture (String name) throws IOException {
		ArchiveReader archive = Model.playerImg.getValue();
		if (archive == null) return null;
		
		// Get texture
		RWFile rw = new RWFile(archive.getFile(name + ".txd"));
		RWFile struct = rw.getPart(0x16).getPart(0x15).getPart(0x1);
		LittleEndianRandomAccessFile file = struct.getFile();
		
		// Read header
		file.seek(struct.getOffset() + 80);
		short width = Short.reverseBytes(file.readShort());
		short height = Short.reverseBytes(file.readShort());
		file.skipBytes(3);
		boolean hasAlpha = file.readBoolean();

		// Read image
		BufferedImage image = new BufferedImage(width, height, hasAlpha ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB);

		byte[] data = new byte[width*height*4];
		file.readFully(data);
		
		DataInputStream in = new DataInputStream(new ByteArrayInputStream(data));
		
		for (int y=height-1; y>=0; y--) {
			for (int x=0; x<width; x++) {
				image.setRGB(x, y, Integer.reverseBytes(in.readInt()));
			}
		}

		// Return
		return image;
	}
}
