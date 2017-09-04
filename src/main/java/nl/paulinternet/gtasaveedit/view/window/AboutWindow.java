package nl.paulinternet.gtasaveedit.view.window;

import nl.paulinternet.gtasaveedit.model.Settings;
import nl.paulinternet.gtasaveedit.view.PlayThread;
import nl.paulinternet.gtasaveedit.view.swing.PButton;
import nl.paulinternet.gtasaveedit.view.swing.YBox;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.net.URI;

public class AboutWindow extends JFrame {

    private final PButton stopButton;
    private final YBox ybox;
    private PlayThread playThread;

    public AboutWindow() {

        PButton websiteButton = new PButton("Website");
        websiteButton.onClick().addHandler(this, "openWebsite", "www.paulinternet.nl/sa");

        PButton repoButton = new PButton("View Source / Downloads");
        repoButton.onClick().addHandler(this, "openWebsite", "github.com/lfuelling/gtasa-savegame-editor");

        stopButton = new PButton("Stop Audio");
        stopButton.setEnabled(false);
        stopButton.onClick().addHandler(this, "stop");

        JLabel label = new JLabel(
                "<html>" +
                        "<font size=+2>GTA SA Savegame Editor</font><br />" +
                        "<font size=+1>Version 3.3-rc.1 (without 3d)</font><br />" +
                        "<br />" +
                        "This program was created by Paul Breeuwsma.<br />" +
                        "The original source code is available online.<br />" +
                        "The source code of <b>this</b> version can be accessed using the 'View Source' button. You can also report bugs there!<br />" +
                        "<br />" +
                        "Thanks to Tim Smith, OrionSR, Seemann, Pdescobar, hmvartak, Ryosuke, Steve M. and others for researching and programming.<br />" +
                        "Thanks to Konoko45 for his 100% complete savegame.<br />" +
                        "Thanks to Dennis L for donating and keeping me motivated.<br />" +
                        "<br />macOS tailored version and extended maintenance by Lukas Fülling.<br />" +
                        "<br /><font color=red><b>Please don't spam the original author because of errors in this version!</b></font><br />" +
                        "</html>"
        );
        ybox = new YBox();
        ybox.add(label);
        ybox.addSpace(15, 0);
        ybox.add(repoButton);
        ybox.add(websiteButton);
        ybox.add(stopButton);
        ybox.setBorder(10);
        getContentPane().add(ybox, BorderLayout.CENTER);


        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setSize(new Dimension(400, 475));
        setLocationRelativeTo(null);
    }

    public YBox getYbox() {
        return ybox;
    }

    @SuppressWarnings("unused") // used as onClick
    public void openWebsite(String website) {
        try {
            Desktop.getDesktop().browse(new URI("http://" + website));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(MainWindow.instance, "Go to " + website, "Website", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @SuppressWarnings({"unused", "WeakerAccess"}) // used as onClick
    public void stop() {
        if (playThread!=null) {
            playThread.setStopPlayback(true);
        }
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        if (b && Settings.getSoundOnAboutPage() == Settings.YES) {
            play();
        } else {
            stop();
        }
    }

    private void play() {
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

    public PButton getStopButton() {
        return stopButton;
    }


}
