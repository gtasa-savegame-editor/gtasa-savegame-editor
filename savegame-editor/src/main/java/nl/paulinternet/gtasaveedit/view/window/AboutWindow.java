package nl.paulinternet.gtasaveedit.view.window;

import nl.paulinternet.gtasaveedit.view.PlayThread;
import nl.paulinternet.gtasaveedit.view.swing.PButton;
import nl.paulinternet.gtasaveedit.view.swing.YBox;
import nl.paulinternet.gtasaveedit.view.updater.Updater;
import nl.paulinternet.gtasaveedit.Settings;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.net.URI;

public class AboutWindow extends JFrame {

    private final PButton stopButton, repoButton, websiteButton;
    private final YBox ybox;
    private PlayThread playThread;
    private static AboutWindow INSTANCE = null;

    private AboutWindow() {

        websiteButton = new PButton("Website");
        repoButton = new PButton("View Source / Downloads");
        stopButton = new PButton("Stop Audio");
        stopButton.setEnabled(false);

        websiteButton.onClick().addHandler(AboutWindow.this, "openWebsite", "www.paulinternet.nl/sa");
        repoButton.onClick().addHandler(AboutWindow.this, "openWebsite", "github.com/lfuelling/gtasa-savegame-editor");
        stopButton.onClick().addHandler(AboutWindow.this, "stop");

        PButton updateButton = new PButton("Check For Updates");
        updateButton.onClick().addHandler(e -> Updater.start());

        JLabel label = new JLabel(
                "<html>" +
                        "<font size=+2>GTA:SA Savegame Editor</font><br />" +
                        "<font size=+1>Version " + Updater.CURRENT_TAG + " (without 3d)</font><br />" +
                        "<br />" +
                        "This program was created by Paul Breeuwsma.<br />" +
                        "<br />macOS fixes and extended maintenance by Lukas Fülling.<br />" +
                        "<br />" +
                        "Thanks to Tim Smith, OrionSR, Seemann, Pdescobar, hmvartak, Ryosuke, Steve M. and others for researching and programming.<br />" +
                        "Thanks to Konoko45 for his 100% complete savegame.<br />" +
                        "Thanks to Dennis L for donating and keeping me motivated.<br />" +
                        "<br /><font color=red><b>Please report bugs in this version on GitHub!</b></font><br />" +
                        "The original (pre 3.3) source code is available online.<br />" +
                        "The source code of <b>this</b> version is available under MIT License. It can be accessed by using the 'View Source' button.<br />" +
                        "</html>"
        );
        ybox = new YBox();
        ybox.add(label);
        ybox.addSpace(15, 0);
        ybox.add(repoButton);
        ybox.addSpace(8, 0);
        ybox.add(websiteButton);
        ybox.addSpace(8, 0);
        ybox.add(stopButton);
        ybox.addSpace(8, 0);
        ybox.add(updateButton);
        ybox.setBorder(10);
        getContentPane().add(ybox, BorderLayout.CENTER);


        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setSize(new Dimension(400, 500));
        setLocationRelativeTo(null);
    }

    public YBox getYbox() {
        return ybox;
    }

    @SuppressWarnings("unused") // onClick handler
    public void openWebsite(String website) {
        try {
            Desktop.getDesktop().browse(new URI("https://" + website));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(MainWindow.getInstance(), "Go to " + website, "Website", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void stop() {
        if (playThread != null) {
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

    public PButton getStopButton() {
        return stopButton;
    }

    public PButton getRepoButton() {
        return repoButton;
    }

    public PButton getWebsiteButton() {
        return websiteButton;
    }

    public static AboutWindow get()
    {
        if (INSTANCE == null) {
            INSTANCE = new AboutWindow();
        }
        return INSTANCE;
    }
}
