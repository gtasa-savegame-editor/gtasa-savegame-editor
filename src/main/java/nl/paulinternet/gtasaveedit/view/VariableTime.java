package nl.paulinternet.gtasaveedit.view;

import static nl.paulinternet.gtasaveedit.model.Model.vars;
import nl.paulinternet.gtasaveedit.model.Event;
import nl.paulinternet.gtasaveedit.model.exceptions.InvalidValueException;
import nl.paulinternet.gtasaveedit.model.Model;
import nl.paulinternet.gtasaveedit.model.ReportableEvent;
import nl.paulinternet.gtasaveedit.model.TextFieldInterface;

public class VariableTime implements TextFieldInterface
{
	private ReportableEvent onChange = new ReportableEvent();

	public VariableTime () {
		Model.gameLoaded.addHandler(onChange);
	}
	
	@Override
	public String getAllowedCharacters () {
		return "0123456789:";
	}

	@Override
	public String getDefaultText () {
		return "0:00";
	}

	@Override
	public int getMaximumLength () {
		return 0;
	}

	@Override
	public String getText () {
		int hour = vars.timeHour.getIntValue();
		int minute = vars.timeMinute.getIntValue();
		String minuteString = minute < 10 ? "0" + minute : String.valueOf(minute);
		return hour + ":" + minuteString;
	}

	@Override
	public void setText (String text) throws InvalidValueException {
		try {
			int colon = text.indexOf(":");
			int hour, minute;
			if (colon == -1) {
				int total = Integer.parseInt(text);
				hour = total / 100;
				minute = total % 100;
			}
			else {
				hour = Integer.parseInt(text.substring(0, colon));
				minute = Integer.parseInt(text.substring(colon + 1));
			}
			
			if (hour < 0 || hour > 23 || minute < 0 || minute > 59) throw new InvalidValueException();

			vars.timeHour.setIntValue(hour);
			vars.timeMinute.setIntValue(minute);
		}
		catch (NumberFormatException e) {
			throw new InvalidValueException();
		}
	}
	
	@Override
	public Event onChange () {
		return onChange;
	}

	@Override
	public boolean isEnabled () {
		return true;
	}
}
