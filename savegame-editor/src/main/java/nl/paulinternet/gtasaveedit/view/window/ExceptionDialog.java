package nl.paulinternet.gtasaveedit.view.window;

import nl.paulinternet.gtasaveedit.view.swing.XBox;
import nl.paulinternet.gtasaveedit.view.swing.YBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionDialog extends JDialog {

    private static final Logger log = LoggerFactory.getLogger(ExceptionDialog.class);

    private static class QuitButton extends JButton implements ActionListener {
        public QuitButton() {
            super("Quit application");
            addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    private static class OkButton extends JButton implements ActionListener {
        private JDialog dialog;

        public OkButton(JDialog dialog) {
            super("OK");
            this.dialog = dialog;
            addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            dialog.setVisible(false);
        }
    }

    public ExceptionDialog(Throwable t) {
        super(MainWindow.getInstance(), "Error");

        StringWriter writer = new StringWriter();
        t.printStackTrace(new PrintWriter(writer));
        String stackTrace = writer.toString();

        log.error(stackTrace);

        JTextArea textArea = new JTextArea(stackTrace);
        textArea.setEditable(false);

        XBox xbox = new XBox();
        xbox.addSpace(0, 1);
        xbox.add(new OkButton(this));
        xbox.addSpace(10);
        xbox.add(new QuitButton());
        xbox.addSpace(0, 1);

        YBox ybox = new YBox();
        ybox.add(new JLabel("An error has occured:"));
        ybox.addSpace(10);
        ybox.add(new JScrollPane(textArea), 1);
        ybox.setBorder(10);
        ybox.addSpace(10);
        ybox.add(xbox);

        add(ybox);
        setSize(640, 480);
        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
    }
}
