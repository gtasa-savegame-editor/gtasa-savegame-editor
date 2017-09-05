package nl.paulinternet.gtasaveedit.view.menu;

import nl.paulinternet.gtasaveedit.model.Model;
import nl.paulinternet.gtasaveedit.model.Settings;
import nl.paulinternet.gtasaveedit.view.window.MainWindow;
import nl.paulinternet.gtasaveedit.view.swing.PMenuItem;

import javax.swing.*;
import java.io.File;

public class QuickDelete extends PMenuItem {
    private int number;

    public QuickDelete(int number) {
        this.number = number;

        // Observe
        Model.quickLoadUpdate.addHandler(this, "updateText");
        onClick().addHandler(this, "deleteSavegame");

        // Set text
        updateText();
    }

    @SuppressWarnings({"WeakerAccess", "Duplicates"}) //used in handler
    public void updateText() {
        String title = Model.quickLoad.get(number).getValue();
        if (title == null) {
            setEnabled(false);
            setText(number + ".");
        } else {
            setEnabled(true);
            setText(number + ". " + title);
        }
    }

    @SuppressWarnings("unused") //used in handler
    public void deleteSavegame() {
        File file = Model.getSavegameFile(number);
        if (Settings.getWarnDeleteFile() == Settings.YES) {
            int result = JOptionPane.showConfirmDialog(
                    MainWindow.getInstance(),
                    "Are you sure you want to delete savegame " + number + "?",
                    "Delete file?",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (result == JOptionPane.YES_OPTION) {
                file.delete();
                Model.updateQuickLoad();
            }
        } else {
            file.delete();
            Model.updateQuickLoad();
        }
    }
}
