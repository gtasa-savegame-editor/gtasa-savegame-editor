package nl.paulinternet.gtasaveedit.view.menu.extractor;

import javax.swing.*;

public class ExtractorMenu extends JMenu {

    public ExtractorMenu() {
        super("Savegame Extractor");
        ServerItem serverItem = new ServerItem(this);
        ServerItem.StopServerItem stopServerItem = serverItem.getStopServerItem();

        add(serverItem);
        add(stopServerItem);
        addSeparator();
    }
}
