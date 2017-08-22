package nl.paulinternet.gtasaveedit.tool;

import nl.paulinternet.gtasaveedit.model.ByteSequence;
import nl.paulinternet.gtasaveedit.model.SavegameData;

public class ModuleCopy implements Module
{
	public String getName () {
		return "copy";
	}

	public String getDescription () {
		return "Copies/converts a savegame";
	}

	public void execute (String[] args) throws Exception {
		if (args.length == 3) {
			// Check if the arguments are files
			boolean firstFile = args[1].length() == 1 && args[1].charAt(0) >= '1' && args[1].charAt(0) <= '8';
			boolean secondFile = args[2].length() == 1 && args[2].charAt(0) >= '1' && args[2].charAt(0) <= '8';

			// Go
			if (firstFile && secondFile) {
				// Copy the savegame file
				Util.copyFile(Dir.getSavegameFile(args[1]), Dir.getSavegameFile(args[2]));
			}
			else if (firstFile && !secondFile) {
				// Load the file
				ByteSequence[] block = new SavegameData(Dir.getSavegameFile(args[1])).getBlocks();
				
				// Create folder
				Util.createDirectory(Dir.getBlockPath(args[2]));
				
				// Write the data
				for (int i=0; i<block.length; i++) {
					if (block[i].getLength() != 0) {
						block[i].writeContents(Dir.getBlockPath(args[2], i));
					}
				}
			}
			else if (!firstFile && secondFile) {
				// Create a savegame file out of block files
				SavegameData data = new SavegameData();
				
				for (int block : Dir.getBlocks()) {
					data.setBlock(block, new ByteSequence(Util.fileGetContents(Dir.getBlockPath(args[1], block))));
				}
				
				for (int i=0; i<30; i++) {
					if (data.getBlock(i) == null) data.setBlock(i, new ByteSequence());
				}

				data.save(Dir.getSavegameFile(args[2]));
			}
			else if (!firstFile && !secondFile) {
				// Create target directory if necessary
				Util.createDirectory(Dir.getBlockPath(args[2]));

				// Copy block files
				for (int block : Dir.getBlocks()) {
					Util.copyFile(Dir.getBlockPath(args[1], block), Dir.getBlockPath(args[2], block));
				}
			}

			// Output
			System.out.println("Done");
		} else {
			System.out.println("Extracts blocks from a savegame or creates a savegame out of blocks.");
			System.out.println("Use a number from 1 to 8 to indicate a savegame file instead of a dir.");
			System.out.println("Usage: copy <source> <destination>");
		}
	}
}