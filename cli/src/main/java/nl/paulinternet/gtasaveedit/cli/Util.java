package nl.paulinternet.gtasaveedit.cli;

import java.io.*;
import java.nio.channels.FileChannel;

public class Util {
    public static String byteToHex(byte b) {
        char[] characters = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        return String.valueOf(characters[(b >> 4) & 15]) + characters[b & 15];
    }

    public static String bytesToHex(byte[] data, boolean spaces) {
        char[] characters = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        StringBuilder builder = new StringBuilder(data.length * (spaces ? 3 : 2));
        for (byte b : data) {
            builder.append(characters[(b >> 4) & 15]);
            builder.append(characters[b & 15]);
            if (spaces)
                builder.append(' ');
        }
        if (spaces)
            builder.deleteCharAt(builder.length() - 1);

        return builder.toString();
    }

    public static byte[] fileGetContents(File filename) throws ErrorMessage {
        RandomAccessFile file = null;
        try {
            // Open file
            file = new RandomAccessFile(filename, "r");

            // Read data
            byte[] data = new byte[(int) file.length()];
            file.readFully(data);
            file.close();

            // Return
            return data;
        } catch (IOException e) {
            throw new ErrorMessage("Failed to read \"" + filename + "\"");
        } finally {
            try {
                file.close();
            } catch (Exception e) {
            }
        }
    }

    public static void filePutContents(String file, byte[] data, int begin, int end) throws ErrorMessage {
        try {
            FileOutputStream out = new FileOutputStream(file);
            out.write(data, begin, end - begin);
            out.close();
        } catch (IOException e) {
            throw new ErrorMessage("Failed to write \"" + file + "\"");
        }
    }

    public static void copyFile(File source, File destination) throws ErrorMessage {
        FileChannel ic = null, oc = null;
        try {
            ic = new FileInputStream(source).getChannel();
            oc = new FileOutputStream(destination).getChannel();
            ic.transferTo(0, ic.size(), oc);
        } catch (IOException e) {
            throw new ErrorMessage("Failed to copy \"" + source + "\" to \"" + destination + "\"");
        } finally {
            try {
                ic.close();
            } catch (Exception e) {
            }
            try {
                oc.close();
            } catch (Exception e) {
            }
        }
    }

    public static void createDirectory(File dir) throws ErrorMessage {
        if (!dir.isDirectory() && !dir.mkdir()) {
            throw new ErrorMessage("Failed to create directory \"" + dir + "\"");
        }
    }

//	public static int searchBlock (byte[] data, int start) {
//		for (int i = start; i < data.length - 4; i++) {
//			int j;
//			for (j = 0; j < 5; j++) {
//				if (data[i + j] != Dir.BLOCK[j])
//					break;
//			}
//			if (j == 5) {
//				return i;
//			}
//		}
//		return -1;
//	}

    public static int getSum(byte[] data) {
        int sum = 0;
        for (byte b : data) {
            sum += ((int) b) & 0xFF;
        }
        return sum;
    }
}