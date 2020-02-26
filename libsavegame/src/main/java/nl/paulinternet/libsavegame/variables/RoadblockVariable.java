package nl.paulinternet.libsavegame.variables;

import nl.paulinternet.libsavegame.Savegame;
import nl.paulinternet.libsavegame.SavegameVars;

public class RoadblockVariable extends Variable<Boolean> {
    public static final int SAN_FIERRO = 0, LAS_VENTURAS = 1;

    private int type;
    private boolean value;

    public RoadblockVariable(int type) {
        this.type = type;
        Savegame.get().addOnGameLoadedHandler(o -> updateValue());
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void setValue(Boolean value) {
        if (this.value != value) {
            this.value = value;

            int ipl = SavegameVars.vars.currentIplVersion.getValue();
            switch (type) {
                case SAN_FIERRO:
                    Savegame.get().getData().writeBoolean(21, ipl == 1 ? 0x5 : 0x3f, value);
                    break;
                case LAS_VENTURAS:
                    Savegame.get().getData().writeBoolean(21, ipl == 1 ? 0x6 : 0x40, value);
                    break;
            }

            if (onChange != null) {
                onChange.handle(value);
            }
        }
    }


    /**
     * @deprecated
     */
    public void updateValue() {
        if (Savegame.get().getData() != null) {
            int ipl = SavegameVars.vars.currentIplVersion.getValue();

            boolean newValue = false;

            switch (type) {
                case SAN_FIERRO:
                    newValue = Savegame.get().getData().readBoolean(21, ipl == 1 ? 0x5 : 0x3f);
                    break;
                case LAS_VENTURAS:
                    newValue = Savegame.get().getData().readBoolean(21, ipl == 1 ? 0x6 : 0x40);
                    break;
            }

            if (newValue != value) {
                value = newValue;
                if (onChange != null) {
                    onChange.handle(value);
                }
            }
        }
    }

}
