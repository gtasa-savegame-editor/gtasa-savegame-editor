package nl.paulinternet.libsavegame.data;

import java.awt.*;

public class Zone {
    public static final Color[] gangColor = new Color[]{
            new Color(0xc8, 0x00, 0xc8), // Ballas
            new Color(0x46, 0xc8, 0x00), // Grove
            new Color(0xff, 0xc8, 0x00), // Los Santos Vagos
            new Color(0x78, 0x78, 0xff), // San Fierro Rifa
            new Color(0xc8, 0x78, 0x00), // Da Nang Boys
            new Color(0xc8, 0xc8, 0xc8), // Italian Mafia
            new Color(0xff, 0x46, 0x46), // Triads
            new Color(0x00, 0xe6, 0xe6), // Varrio Los Aztecas
            new Color(135, 93, 0), // Unused Gang 1
            new Color(99, 0, 169), // Unused Gang 2
    };

    public static final int BALLAS = 0, GROVE = 1, VAGOS = 2, SF_RIFA = 3, DA_NANG = 4, ITALIAN = 5;
    public static final int TRIADS = 6, AZTECAS = 7, UNUSED1 = 8, UNUSED2 = 9, DEALER = 0xa, POPCYCLE = 0xf, PED = 0x10;
    public static final int GANG_PRESENT = 0x11, DISABLE_FOOTCOPS = 0x12;

    private int id;
    private int x, y;
    private int width, height;
    private int[] gang;
    private int popcycle;
    private int dealer;
    private int ped;
    private boolean gangPresent, disabledFootcops;
    private Color color;

    public Zone(int id, int x, int y, int width, int height) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        gang = new int[10];
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setValue(int id, int value) {
        switch (id) {
            case POPCYCLE:
                popcycle = value;
                break;
            case PED:
                ped = value;
                break;
            case DEALER:
                dealer = value;
                break;
            case GANG_PRESENT:
                gangPresent = value != 0;
                break;
            case DISABLE_FOOTCOPS:
                disabledFootcops = value != 0;
                break;
            default:
                gang[id] = value;
                color = null;
                break;
        }
    }

    public int getValue(int id) {
        switch (id) {
            case POPCYCLE:
                return popcycle;
            case PED:
                return ped;
            case DEALER:
                return dealer;
            case GANG_PRESENT:
                return gangPresent ? 1 : 0;
            case DISABLE_FOOTCOPS:
                return disabledFootcops ? 1 : 0;
            default:
                return gang[id];
        }
    }

    public Color getColor() {
        if (color == null) {
            int r = 0, g = 0, b = 0;
            int total = 0;

            for (int i = 0; i < 10; i++) {
                r += gang[i] * gangColor[i].getRed();
                g += gang[i] * gangColor[i].getGreen();
                b += gang[i] * gangColor[i].getBlue();
                total += gang[i];
            }

            if (total == 0) {
                color = new Color(0, 0, 0, 0);
            } else {
                r /= total;
                g /= total;
                b /= total;
                int a = Math.min(total, 40) * 3;

                color = new Color(r, g, b, a);
            }
        }
        return color;
    }

    public boolean contains(int x, int y) {
        return x >= this.x && x < this.x + width && y >= this.y && y < this.y + height;
    }
}
