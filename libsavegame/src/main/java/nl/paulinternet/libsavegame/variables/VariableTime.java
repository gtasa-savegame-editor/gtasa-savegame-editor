package nl.paulinternet.libsavegame.variables;

import nl.paulinternet.libsavegame.SavegameModel;
import nl.paulinternet.libsavegame.TextFieldInterface;
import nl.paulinternet.libsavegame.event.Event;
import nl.paulinternet.libsavegame.event.ReportableEvent;
import nl.paulinternet.libsavegame.exceptions.InvalidValueException;

import static nl.paulinternet.libsavegame.SavegameModel.vars;

public class VariableTime implements TextFieldInterface {
    private ReportableEvent onChange = new ReportableEvent();

    public VariableTime() {
        SavegameModel.gameLoaded.addHandler(onChange);
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
    public int getMaximumLength() {
        return 0;
    }

    @Override
    public String getText() {
        int hour = vars.timeHour.getIntValue();
        int minute = vars.timeMinute.getIntValue();
        String minuteString = minute < 10 ? "0" + minute : String.valueOf(minute);
        return hour + ":" + minuteString;
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

            vars.timeHour.setIntValue(hour);
            vars.timeMinute.setIntValue(minute);
        } catch (NumberFormatException e) {
            throw new InvalidValueException();
        }
    }

    @Override
    public Event onChange() {
        return onChange;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
