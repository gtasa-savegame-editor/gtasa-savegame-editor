package nl.paulinternet.gtasaveedit.view;

import nl.paulinternet.gtasaveedit.view.swing.PButton;
import nl.paulinternet.gtasaveedit.view.window.ExceptionDialog;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.SourceDataLine;

/**
 * Thread used for audio playback in {@link nl.paulinternet.gtasaveedit.view.pages.PageAbout}.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 */

public class PlayThread extends Thread {

    private SourceDataLine sourceDataLine;
    private AudioFormat audioFormat;
    private AudioInputStream audioInputStream;
    private boolean stopPlayback;
    private final PButton stopButton;

    public PlayThread(SourceDataLine sourceDataLine, AudioFormat audioFormat, AudioInputStream audioInputStream, PButton stopButton) {
        this.sourceDataLine = sourceDataLine;
        this.audioFormat = audioFormat;
        this.audioInputStream = audioInputStream;
        this.stopButton = stopButton;
    }

    byte tempBuffer[] = new byte[10000];

    public void run() {
        try {
            sourceDataLine.open(audioFormat);
            sourceDataLine.start();

            int cnt;

            while ((cnt = audioInputStream.read(tempBuffer, 0, tempBuffer.length)) != -1 && !stopPlayback) {
                if (cnt > 0) {
                    sourceDataLine.write(
                            tempBuffer, 0, cnt);
                }
            }

            sourceDataLine.drain();
            sourceDataLine.close();

            stopButton.setEnabled(false);

            stopPlayback = false;
        } catch (Exception e) {
            new ExceptionDialog(e).setVisible(true);
        }
    }

    public void setStopPlayback(boolean stopPlayback) {
        this.stopPlayback = stopPlayback;
    }
}

