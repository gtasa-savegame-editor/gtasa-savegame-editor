package nl.paulinternet.libsavegame.variables;

import nl.paulinternet.libsavegame.CallbackHandler;
import nl.paulinternet.libsavegame.Savegame;
import nl.paulinternet.libsavegame.TextFieldInterface;
import nl.paulinternet.libsavegame.exceptions.InvalidValueException;

public class VariableTime implements TextFieldInterface {

    @Override
    public String getAllowedCharacters() {
        return "0123456789:";
    }

    @Override
    public String getDefaultText() {
        return "0:00";
    }

    @Override
    public int getMaximumLength() {
        return 0;
    }

    @Override
    public void setOnTextChange(CallbackHandler<String> onChange) {
        // ?
    }

    @Override
    public String getText() {
        Variable<Integer> timeHour = Variables.get().timeHour;
        Variable<Integer> timeMinute = Variables.get().timeMinute;

        if(timeHour.getValue() == null || timeMinute.getValue() == null) {
            return "--:--";
        } else {
            int hour = timeHour.getValue();
            int minute = timeMinute.getValue();
            String minuteString = minute < 10 ? "0" + minute : String.valueOf(minute);
            return hour + ":" + minuteString;
        }
    }

    @Override
    public void setText(String text) throws InvalidValueException {
        try {
            int colon = text.indexOf(":");
            int hour, minute;
            if (colon == -1) {
                int total = Integer.parseInt(text);
                hour = total / 100;
                minute = total % 100;
            } else {
                hour = Integer.parseInt(text.substring(0, colon));
                minute = Integer.parseInt(text.substring(colon + 1));
            }

            if (hour < 0 || hour > 23 || minute < 0 || minute > 59) throw new InvalidValueException();

            Variables.get().timeHour.setValue(hour);
            Variables.get().timeMinute.setValue(minute);
        } catch (NumberFormatException e) {
            throw new InvalidValueException();
        }
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
