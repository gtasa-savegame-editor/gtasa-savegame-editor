package nl.paulinternet.libsavegame.variables;

import nl.paulinternet.libsavegame.Savegame;

public class RoadblockVariable extends Variable<Boolean> {
    public static final int SAN_FIERRO = 0, LAS_VENTURAS = 1;

    private int type;
    private boolean value;

    public RoadblockVariable(int type) {
        this.type = type;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void setValue(Boolean value) {
        if (this.value != value) {
            this.value = value;

            int ipl = Variables.get().currentIplVersion.getValue();
            switch (type) {
                case SAN_FIERRO:
                    Savegame.get().getData().writeBoolean(21, ipl == 1 ? 0x5 : 0x3f, value);
                    break;
                case LAS_VENTURAS:
                    Savegame.get().getData().writeBoolean(21, ipl == 1 ? 0x6 : 0x40, value);
                    break;
            }

            handleChange(value);
        }
    }

    @Override
    public Boolean getValue() {
        Variable<Integer> currentIplVersion = Variables.get().currentIplVersion;
        if (currentIplVersion.getValue() == null) {
            return false;
        } else {
            int ipl = currentIplVersion.getValue();
            boolean value = false;
            switch (type) {
                case SAN_FIERRO:
                    value = Savegame.get().getData().readBoolean(21, ipl == 1 ? 0x5 : 0x3f);
                    break;
                case LAS_VENTURAS:
                    value = Savegame.get().getData().readBoolean(21, ipl == 1 ? 0x6 : 0x40);
                    break;
            }
            return value;
        }
    }
}
