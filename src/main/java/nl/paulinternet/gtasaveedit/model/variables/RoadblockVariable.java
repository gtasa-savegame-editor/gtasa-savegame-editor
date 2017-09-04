package nl.paulinternet.gtasaveedit.model.variables;

import nl.paulinternet.gtasaveedit.model.Model;
import nl.paulinternet.gtasaveedit.model.event.Event;
import nl.paulinternet.gtasaveedit.model.event.ReportableEvent;
import nl.paulinternet.gtasaveedit.model.savegame.Savegame;
import nl.paulinternet.gtasaveedit.model.variables.VariableBoolean;

public class RoadblockVariable implements VariableBoolean
{
	public static final int SAN_FIERRO = 0, LAS_VENTURAS = 1;
	
	private int type;
	private boolean value;
	private ReportableEvent onChange;
	
	public RoadblockVariable (int type) {
		this.type = type;
		onChange = new ReportableEvent();
		
		Model.gameLoaded.addHandler(this, "updateValue");
	}

	@Override
	public Boolean getBooleanValue () {
		return value;
	}

	@Override
	public boolean isEnabled () {
		return true;
	}

	@Override
	public Event onChange () {
		return onChange;
	}

	@Override
	public void setBooleanValue (boolean value) {
		if (this.value != value) {
			this.value = value;
			
			int ipl = Model.vars.currentIplVersion.getIntValue();
			switch (type) {
				case SAN_FIERRO: Savegame.getData().writeBoolean(21, ipl == 1 ? 0x5 : 0x3f, value); break;
				case LAS_VENTURAS: Savegame.getData().writeBoolean(21, ipl == 1 ? 0x6 : 0x40, value); break;
			}
			
			onChange.report();
		}
	}


	/**
	 * @deprecated
	 */
	public void updateValue () {

		if (Savegame.getData() != null) {
			int ipl = Model.vars.currentIplVersion.getIntValue();

			boolean newValue = false;
			
			switch (type) {
				case SAN_FIERRO: newValue = Savegame.getData().readBoolean(21, ipl == 1 ? 0x5 : 0x3f); break;
				case LAS_VENTURAS: newValue = Savegame.getData().readBoolean(21, ipl == 1 ? 0x6 : 0x40); break;
			}
			
			if (newValue != value) {
				value = newValue;
				onChange.report();
			}
		}
	}

}
