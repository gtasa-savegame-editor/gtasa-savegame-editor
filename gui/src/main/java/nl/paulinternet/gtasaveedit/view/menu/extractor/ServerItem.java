package nl.paulinternet.gtasaveedit.view.menu.extractor;

import nl.paulinternet.gtasaveedit.extractor.ExtractorServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerItem extends JMenuItem implements ActionListener {

    private static final Logger log = LoggerFactory.getLogger(ServerItem.class);

    private boolean active = false;
    private ExtractorServer serverThread = null;
    private ExtractorMenu menu;
    private final StopServerItem stopServerItem;

    ServerItem(ExtractorMenu menu) {
        super("Start Server...");
        this.menu = menu;
        addActionListener(this);
        stopServerItem = new StopServerItem();
        toggleActive(serverThread != null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!active) {
            serverThread = new ExtractorServer(menu);
            serverThread.setUncaughtExceptionHandler((t, ex) -> log.error("Uncaught exception in thread '" + t.getName() + "'", ex));
            serverThread.start();
            toggleActive(true);
        }
    }

    private void toggleActive(boolean active) {
        this.setEnabled(!active);
        this.active = active;
        this.setText((active) ? "Server running..." : "Start server");
        stopServerItem.setEnabled(active);
    }

    public class StopServerItem extends JMenuItem implements ActionListener {

        StopServerItem() {
            super("Stop server");
            addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            serverThread.stopServer();
            toggleActive(false);
        }
    }

    public StopServerItem getStopServerItem() {
        return stopServerItem;
    }
}