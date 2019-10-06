package nl.paulinternet.gtasaveedit.view.menu;

import nl.paulinternet.gtasaveedit.view.swing.PMenuItem;
import nl.paulinternet.gtasaveedit.view.window.MainWindow;
import nl.paulinternet.libsavegame.Savegame;
import nl.paulinternet.libsavegame.SavegameModel;
import nl.paulinternet.libsavegame.Settings;
import nl.paulinternet.libsavegame.exceptions.ErrorMessageException;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import static nl.paulinternet.libsavegame.Util.MAC;

public class QuickSave extends PMenuItem {
    private int number;

    public QuickSave(int number) {
        this.number = number;

        //noinspection deprecation
        setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0 + number, (MAC ? InputEvent.META_MASK : InputEvent.CTRL_MASK) | InputEvent.SHIFT_MASK));

        SavegameModel.quickLoadUpdate.addHandler(this, "updateText");
        onClick().addHandler(this, "saveFile");

        updateText();
    }

    @SuppressWarnings("WeakerAccess") //used in handler
    public void updateText() {
        String title = SavegameModel.quickLoad.get(number).getValue();
        if (title == null) {
            setText(number + ".");
        } else {
            setText(number + ". " + title);
        }
    }


    @SuppressWarnings("unused") //used in handler
    public void saveFile() {
        try {
            File file = SavegameModel.getSavegameFile(number);
            if (Settings.getWarnOverwriteFile() == Settings.YES && file.exists()) {
                int result = JOptionPane.showConfirmDialog(
                        MainWindow.getInstance(),
                        "Savegame " + number + " already exists.\nDo you want to overwrite it?",
                        "Overwrite file?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

                if (result == JOptionPane.YES_OPTION) Savegame.save(file);
            } else {
                Savegame.save(file);
            }
        } catch (ErrorMessageException e) {
            JOptionPane.showMessageDialog(MainWindow.getInstance(), e.getMessage(), e.getTitle(), JOptionPane.ERROR_MESSAGE);
        }
    }
}
