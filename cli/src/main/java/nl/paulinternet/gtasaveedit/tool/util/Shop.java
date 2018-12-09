package nl.paulinternet.gtasaveedit.tool.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Shop
{
	private static class Entry
	{
		private String line;
		private String texture;
		
		public Entry (String line) {
			this.line = line;
			int firstTab = line.indexOf("\t");
			int secondTab = line.indexOf("\t", firstTab + 1);
			
			texture = line.substring(firstTab + 1, secondTab);
		}
		
		public void add (String add) {
			line += add;
		}
		
		public String getTexture () {
			return texture;
		}
		
		public String getLine () {
			return line;
		}
	}
	
	
	private static final String SHOP_FILENAME = "f:/docs/java/editor/research/cloth shops.txt";
	private static final String TABLE_FILENAME = "f:/docs/java/editor/research/table.txt";

	public static void main (String[] args) throws Exception {
		
		Map<String, Entry> entries = new HashMap<String, Entry>();
		
		// Table
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(TABLE_FILENAME)));
		
		while (true) {
			String line = in.readLine();
			if (line == null) break;
			
			Entry entry = new Entry(line);
			entries.put(entry.getTexture(), entry);
		}
		
		// Shop
		String shop = null;
		int shopId = 0;
		int pos = 0;
		
		in = new BufferedReader(new InputStreamReader(new FileInputStream(SHOP_FILENAME)));
		while (true) {
			String line = in.readLine();
			if (line == null) break;
			
			if (line.startsWith("shop ")) {
				shop = line.substring(5);
				shopId++;
				pos = 0;
			}
			
			if (line.startsWith("item ")) {
				String item = line.substring(5);
				
				entries.get(item).add("\t" + shopId + " " + shop + "\t" + (pos++));
			}
		}
		
		// Output
		for (Map.Entry<String, Entry> e : entries.entrySet()) {
			System.out.println(e.getValue().getLine());
		}
		
	}

}
