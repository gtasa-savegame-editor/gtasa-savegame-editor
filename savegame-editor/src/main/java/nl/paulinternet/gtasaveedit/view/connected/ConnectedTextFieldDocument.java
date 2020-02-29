package nl.paulinternet.gtasaveedit.view.connected;

import nl.paulinternet.libsavegame.variables.Variable;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class ConnectedTextFieldDocument extends PlainDocument {
    private int maxLength;
    private char[] chars;

    public ConnectedTextFieldDocument(Variable<?> var) {
        maxLength = var.getMaxLength();
        chars = var.getAllowedCharacters() == null ? null : var.getAllowedCharacters().toCharArray();
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        StringBuilder builder = new StringBuilder();

        if (chars == null) {
            if (maxLength > 0 && getLength() + str.length() > maxLength) {
                builder.append(str, 0, maxLength - getLength());
            } else {
                builder.append(str);
            }
        } else {
            loop:
            for (int i = 0; i < str.length() && (maxLength < 1 || getLength() < maxLength); i++) {
                for (char c : chars) {
                    if (str.charAt(i) == c) {
                        builder.append(c);
                        continue loop;
                    }
                }
            }
        }

        super.insertString(offs, builder.toString(), a);
    }
}
