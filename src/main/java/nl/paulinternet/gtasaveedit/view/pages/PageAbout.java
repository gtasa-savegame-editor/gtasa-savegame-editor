package nl.paulinternet.gtasaveedit.view.pages;

import nl.paulinternet.gtasaveedit.view.PlayThread;
import nl.paulinternet.gtasaveedit.view.swing.PButton;
import nl.paulinternet.gtasaveedit.view.window.AboutWindow;
import nl.paulinternet.gtasaveedit.view.window.ExceptionDialog;
import nl.paulinternet.gtasaveedit.view.window.MainWindow;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.net.URI;

/**
 * @author Lukas Fülling (lukas@k40s.net)
 */
public class PageAbout extends Page {

    private final PButton stopButton;
    private PlayThread playThread;

    public PageAbout() {
        super("About", true);
        AboutWindow aboutWindow = new AboutWindow(false);
        stopButton = aboutWindow.getStopButton();
        stopButton.onClick().addHandler(this, "stop");

        aboutWindow.getWebsiteButton().onClick().addHandler(this, "openWebsite", "www.paulinternet.nl/sa");
        aboutWindow.getRepoButton().onClick().addHandler(this, "openWebsite", "github.com/lfuelling/gtasa-savegame-editor");

        setComponent(aboutWindow.getYbox(), false);
    }

    @SuppressWarnings("unused") // used as onClick
    public void openWebsite(String website) {
        try {
            Desktop.getDesktop().browse(new URI("http://" + website));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(MainWindow.getInstance(), "Go to " + website, "Website", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @SuppressWarnings({"unused", "WeakerAccess"}) // used as onClick
    public void stop() {
        if (playThread != null) {
            playThread.setStopPlayback(true);
        }
    }


    public void play() {
        // TODO: duplicate
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(getClass().getResourceAsStream("/gta-sa-intro.wav")));
            AudioFormat audioFormat = audioInputStream.getFormat();

            DataLine.Info dataLineInfo =
                    new DataLine.Info(
                            SourceDataLine.class,
                            audioFormat);

            SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem.getLine(
                    dataLineInfo);

            playThread = new PlayThread(sourceDataLine, audioFormat, audioInputStream, stopButton);
            playThread.start();
            stopButton.setEnabled(true);
        } catch (Exception e) {
            new ExceptionDialog(e).setVisible(true);
        }
    }

}
