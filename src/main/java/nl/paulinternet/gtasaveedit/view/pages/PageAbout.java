package nl.paulinternet.gtasaveedit.view.pages;

import nl.paulinternet.gtasaveedit.view.PlayThread;
import nl.paulinternet.gtasaveedit.view.swing.PButton;
import nl.paulinternet.gtasaveedit.view.window.AboutWindow;
import nl.paulinternet.gtasaveedit.view.window.ExceptionDialog;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;

/**
 * @author Lukas Fülling (lukas@k40s.net)
 */
public class PageAbout extends Page {

    private final PButton stopButton;
    private PlayThread playThread;

    public PageAbout() {
        super("About", true);
        AboutWindow aboutWindow = new AboutWindow();
        stopButton = aboutWindow.getStopButton();
        stopButton.onClick().addHandler(this, "stop");
        setComponent(aboutWindow.getYbox(), false);
    }

    @SuppressWarnings({"unused", "WeakerAccess"}) // used as onClick
    public void stop() {
        if (playThread != null) {
            playThread.setStopPlayback(true);
        }
    }


    public void play() {
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
