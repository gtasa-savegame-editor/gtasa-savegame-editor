package nl.paulinternet.libsavegame.variables;

import nl.paulinternet.libsavegame.exceptions.InvalidValueException;

public class VariableTime extends Variable<String> {

    public static final String UNSET = "--:--";

    public VariableTime() {
        Variables.get().timeHour.addOnChangeListener(i -> handleChange(getText()));
        Variables.get().timeMinute.addOnChangeListener(i -> handleChange(getText()));
    }

    @Override
    public String getAllowedCharacters() {
        return "0123456789:";
    }

    @Override
    public String getDefaultText() {
        return "0:00";
    }

    @Override
    public int getMaxLength() {
        return 0;
    }

    @Override
    public String getValue() {
        return getText();
    }

    @Override
    public void setValue(String value) throws InvalidValueException {
        if (!this.value.equals(value)) {
            this.value = getText();
            setText(value);
            handleChange(value);
        }
    }

    @Override
    public String getText() {
        Variable<Integer> hours = Variables.get().timeHour;
        Variable<Integer> minutes = Variables.get().timeMinute;
        if (hours.getValue() == null ||
                minutes.getValue() == null) {
            return UNSET;
        } else {
            int hour = hours.getValue();
            int minute = minutes.getValue();
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

            if (hour < 0 || hour > 23 || minute < 0 || minute > 59)
                throw new InvalidValueException("Value '" + text + "' is not a valid time!");

            Variables.get().timeHour.setValue(hour);
            Variables.get().timeMinute.setValue(minute);
        } catch (NumberFormatException e) {
            throw new InvalidValueException("Error parsing time!", e);
        }
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
