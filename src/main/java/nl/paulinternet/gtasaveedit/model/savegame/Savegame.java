 package nl.paulinternet.gtasaveedit.model.savegame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import nl.paulinternet.gtasaveedit.model.Model;
import nl.paulinternet.gtasaveedit.model.exceptions.ErrorMessageException;
import nl.paulinternet.gtasaveedit.model.exceptions.FileFormatException;
import nl.paulinternet.gtasaveedit.model.link.Link;
import nl.paulinternet.gtasaveedit.model.link.SavegameLink;

public class Savegame
{
	public static final int FILESIZE = 202752;
	public static final byte[] BLOCK = new byte[] {66, 76, 79, 67, 75};
	
	private static Link link = new SavegameLink();
	private static SavegameData data;

	public static void load (File filename) throws ErrorMessageException {
		try {
			// Read data
			SavegameData newData = new SavegameData(filename);
			
			// Read settings
			link.load(newData);
			data = newData;
			
			// Report event
			Model.gameLoaded.report();
		}
		catch (FileFormatException e) {
			try {
				if (data != null) link.load(data);
			}
			catch (FileFormatException e2) {
				throw new RuntimeException(e2);
			}
			throw new ErrorMessageException("Loading failed", "The file is not a GTA San Andreas PC savegame.");
		}
		catch (FileNotFoundException e) {
			throw new ErrorMessageException("Loading failed", "The file does not exist.");
		}
		catch (IOException e) {
			throw new ErrorMessageException("Loading failed", "An error occurred while trying to read the file.");
		}
		finally {
			// Update quickload menu
			Model.updateQuickLoad();
		}
	}
	
	public static void load (URL url) {
		data = new SavegameData(url);
		try {
			link.load(data);
		}
		catch (FileFormatException e) {
			throw new RuntimeException(e);
		}
		Model.gameLoaded.report();
	}
	
	public static void save (File file) throws ErrorMessageException {
		// Write settings
		link.save(data);
		
		// Try to save
		try {
			data.save(file);
		}
		catch (FileNotFoundException e) {
			throw new ErrorMessageException("Saving failed", "Failed to create file.");
		}
		catch (IOException e) {
			throw new ErrorMessageException("Saving failed", "An error occurred while writing the file.");
		}
		finally {
			// Update quickload menu
			Model.updateQuickLoad();
		}
	}
	
	public static void close () {
		// Remove data
		data = null;
		
		// Report event
		Model.gameClosed.report();
		
		// Update quickload menu
		Model.updateQuickLoad();
	}
	
	public static SavegameData getData () {
		return data;
	}

	/**
	 * @deprecated
	 */
	public static boolean isLoaded () {
		return data != null;
	}
}
