package nl.paulinternet.gtasaveedit.cli.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Translator {
    private static final String TRANSLATE_FILENAME = "f:/docs/java/editor/research/nametags.txt";
    private static final String TAGS_FILENAME = "f:/docs/java/editor/research/clothtags.txt";

    public static void main(String[] args) throws Exception {
        // Map
        Map<String, String> mapTranslate = new HashMap<String, String>();

        // Load translate file
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(TRANSLATE_FILENAME)));

        while (true) {
            String line = in.readLine();
            if (line == null) break;

            int tab = line.indexOf("\t");
            String tag = line.substring(0, tab);
            String translation = line.substring(tab + 1);
            mapTranslate.put(tag, translation);
        }

        in.close();

        // Translate tag file
        in = new BufferedReader(new InputStreamReader(new FileInputStream(TAGS_FILENAME)));

        while (true) {
            String line = in.readLine();
            if (line == null) break;

            String translated = mapTranslate.get(line);
            if (translated == null) throw new Exception();
            System.out.println(translated);
        }
    }
}
