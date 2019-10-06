package nl.paulinternet.libsavegame.variables;

import nl.paulinternet.libsavegame.SavegameModel;
import nl.paulinternet.libsavegame.event.Event;
import nl.paulinternet.libsavegame.event.ReportableEvent;
import nl.paulinternet.libsavegame.Savegame;

public class RoadblockVariable implements VariableBoolean {
    public static final int SAN_FIERRO = 0, LAS_VENTURAS = 1;

    private int type;
    private boolean value;
    private ReportableEvent onChange;

    public RoadblockVariable(int type) {
        this.type = type;
        onChange = new ReportableEvent();

        SavegameModel.gameLoaded.addHandler(this, "updateValue");
    }

    @Override
    public Boolean getBooleanValue() {
        return value;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Event onChange() {
        return onChange;
    }

    @Override
    public void setBooleanValue(boolean value) {
        if (this.value != value) {
            this.value = value;

            int ipl = SavegameModel.vars.currentIplVersion.getIntValue();
            switch (type) {
                case SAN_FIERRO:
                    Savegame.getData().writeBoolean(21, ipl == 1 ? 0x5 : 0x3f, value);
                    break;
                case LAS_VENTURAS:
                    Savegame.getData().writeBoolean(21, ipl == 1 ? 0x6 : 0x40, value);
                    break;
            }

            onChange.report();
        }
    }


    /**
     * @deprecated
     */
    public void updateValue() {

        if (Savegame.getData() != null) {
            int ipl = SavegameModel.vars.currentIplVersion.getIntValue();

            boolean newValue = false;

            switch (type) {
                case SAN_FIERRO:
                    newValue = Savegame.getData().readBoolean(21, ipl == 1 ? 0x5 : 0x3f);
                    break;
                case LAS_VENTURAS:
                    newValue = Savegame.getData().readBoolean(21, ipl == 1 ? 0x6 : 0x40);
                    break;
            }

            if (newValue != value) {
                value = newValue;
                onChange.report();
            }
        }
    }

}
