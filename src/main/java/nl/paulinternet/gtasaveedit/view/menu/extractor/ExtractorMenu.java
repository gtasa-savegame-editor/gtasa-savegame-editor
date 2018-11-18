package nl.paulinternet.gtasaveedit.view.menu.extractor;

import javax.swing.*;

public class ExtractorMenu extends JMenu {

    public static final int SERVER_ITEM_IDX = 0;
    public static final int STOP_ITEM_IDX = 1;
    public static final int EXTRACTED_SAVEGAMES_START_IDX = 2;

    public ExtractorMenu() {
        super("Savegame Extractor");
        add(new ServerItem(this), SERVER_ITEM_IDX);
    }
}
