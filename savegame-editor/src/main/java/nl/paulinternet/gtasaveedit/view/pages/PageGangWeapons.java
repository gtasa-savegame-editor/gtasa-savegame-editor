package nl.paulinternet.gtasaveedit.view.pages;

import nl.paulinternet.libsavegame.SavegameModel;
import nl.paulinternet.libsavegame.variables.VariableIntegerImpl;
import nl.paulinternet.gtasaveedit.view.connected.ConnectedComboBox;
import nl.paulinternet.gtasaveedit.view.swing.Alignment;
import nl.paulinternet.gtasaveedit.view.swing.Table;

import javax.swing.*;

public class PageGangWeapons extends Page {

    public PageGangWeapons() {
        super("Gang weapons");

        String[] gangs = new String[]{"Ballas", "Grove Street", "Los Santos Vagos", "San Fierro Rifa",
                "Da Nang Boys", "Italian Mafia", "Triads", "Varrio Los Aztecas", "Unused Gang 1", "Unused Gang 2"};

        Table table = new Table();
        table.setSpacing(10, 3);
        table.add(new JLabel("Weapon 1"), 1, 0);
        table.add(new JLabel("Weapon 2"), 2, 0);
        table.add(new JLabel("Weapon 3"), 3, 0);

        for (int i = 0; i < gangs.length; i++) {
            table.add(new JLabel(gangs[i]), 0, i + 1);
            table.add(new WeaponBox(SavegameModel.vars.gangWeapon.get(i * 3)), 1, i + 1);
            table.add(new WeaponBox(SavegameModel.vars.gangWeapon.get(i * 3 + 1)), 2, i + 1);
            table.add(new WeaponBox(SavegameModel.vars.gangWeapon.get(i * 3 + 2)), 3, i + 1);
        }

        Alignment alignment = new Alignment(table, 0.0f, 0.0f);
        alignment.setBorder(10);
        setComponent(alignment, true);
    }

    private static class WeaponBox extends ConnectedComboBox {
        public WeaponBox(VariableIntegerImpl var) {
            super(var);
            addItem(0x00, "No");
            addItem(0x01, "Brass Knuckles");
            addItem(0x02, "Golf Club");
            addItem(0x03, "Nightstick");
            addItem(0x04, "Knife");
            addItem(0x05, "Baseball Bat");
            addItem(0x06, "Shovel");
            addItem(0x07, "Pool Cue");
            addItem(0x08, "Katana");
            addItem(0x09, "Chainsaw");
            addItem(0x16, "9mm");
            addItem(0x17, "Silenced 9mm");
            addItem(0x18, "Desert Eagle");
            addItem(0x19, "Shotgun");
            addItem(0x1A, "Sawnoff Shotgun");
            addItem(0x1B, "Combat Shotgun");
            addItem(0x20, "Tec-9");
            addItem(0x1C, "Micro SMG");
            addItem(0x1D, "SMG");
            addItem(0x1E, "AK47");
            addItem(0x1F, "M4");
            addItem(0x21, "Rifle");
            addItem(0x22, "Sniper Rifle");
            addItem(0x23, "Rocket Launcher");
            addItem(0x24, "Heet Seeking Rocket Launcher");
            addItem(0x25, "Flame Thrower");
            addItem(0x26, "Vulcan Mini Gun");
            addItem(0x10, "Grenade");
            addItem(0x11, "Tear Gas");
            addItem(0x12, "Molotov Cocktail");
            addItem(0x27, "Remote Explosives");
            addItem(0x29, "Spraycan");
            addItem(0x2A, "Fire Extinguisher");
            addItem(0x2B, "Camera");
            addItem(0x0A, "Dildo 1");
            addItem(0x0B, "Dildo 2");
            addItem(0x0C, "Vibrator 1");
            addItem(0x0D, "Vibrator 2");
            addItem(0x0E, "Flowers");
            addItem(0x0F, "Cane");
            addItem(0x2C, "Night-Vision Goggles");
            addItem(0x2D, "Thermal Goggles");
            addItem(0x2E, "Parachute");
            addItem(0x28, "Remote Detonator");
        }
    }
}
