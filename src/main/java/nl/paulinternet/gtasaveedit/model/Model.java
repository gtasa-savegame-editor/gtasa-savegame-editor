package nl.paulinternet.gtasaveedit.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Model
{
	public static final ReportableEvent gameLoaded = new ReportableEvent();
	public static final ReportableEvent gameClosed = new ReportableEvent();
	public static final ReportableEvent quickLoadUpdate = new ReportableEvent();
	
	public static final Variables vars = new Variables();
	public static final SettingVariables editSettings = new SettingVariables();
	public static final Variable<ArchiveReader> playerImg = new Variable<ArchiveReader>();
	public static final List<QuickLoad> quickLoad;
	
	private static File saDir;

	static {
		// Quick load
		quickLoad = new ArrayList<QuickLoad>(9);
		quickLoad.add(null);
		for (int i=1; i<=8; i++) {
			quickLoad.add(new QuickLoad(i));
		}
		
		// 
		updatePlayerImg();
	}

	public static File getSavegameFile (int number) {
		return new File(FileSystem.getSavegameDirectory(), "GTASAsf" + number + ".b");
	}
	
	public static void updateQuickLoad () {
		for (int i=1; i<=8; i++) {
			quickLoad.get(i).loadValue();
		}
		Model.quickLoadUpdate.report();
	}
	
	public static void updatePlayerImg () {
		File newSaDir = FileSystem.getSanAndreasDirectory();
		ArchiveReader archive = null;
		
		if (Settings.getShowClothes() == Settings.YES && newSaDir != null) {
			if (newSaDir.equals(saDir)) return;
			
			try {
				if (FileSystem.getPlayerImageFile() != null)
					archive = new ArchiveReader(FileSystem.getPlayerImageFile());
			}
			catch (FileFormatException e) {}
			catch (IOException e) {}
		}

		playerImg.setValue(archive);
		saDir = archive == null ? null : newSaDir;
	}
	
	private Model () {}
}
