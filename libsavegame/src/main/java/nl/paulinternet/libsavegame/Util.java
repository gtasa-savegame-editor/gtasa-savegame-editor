package nl.paulinternet.libsavegame;

public class Util {

    public static int indexOf(byte[] array, byte[] search, int pos) {
        // Check arguments
        if (array == null || search == null) throw new NullPointerException();
        if (pos < 0 || pos > array.length) throw new IndexOutOfBoundsException();

        // Search
        search:
        for (int i = pos; i <= array.length - search.length; i++) {
            for (int j = 0; j < search.length; j++) {
                if (array[i + j] != search[j]) continue search;
            }
            return i;
        }

        // Bytes not found
        return -1;
    }

    public static String getString(byte[] data) {
        int i;
        //noinspection StatementWithEmptyBody
        for (i = 0; i < data.length && data[i] != 0; i++) ;
        return new String(data, 0, i);
    }
}
