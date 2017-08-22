package nl.paulinternet.gtasaveedit.view.pages;

import java.util.List;

import javax.swing.JLabel;
import javax.swing.JSeparator;

import nl.paulinternet.gtasaveedit.model.Model;
import nl.paulinternet.gtasaveedit.model.TextFieldInterface;
import nl.paulinternet.gtasaveedit.model.savegame.variables.VariableFloat;
import nl.paulinternet.gtasaveedit.model.savegame.variables.VariableIntegerImpl;
import nl.paulinternet.gtasaveedit.view.ConnectedRadioButtons;
import nl.paulinternet.gtasaveedit.view.ConnectedTextField;
import nl.paulinternet.gtasaveedit.view.ValueButton;
import nl.paulinternet.gtasaveedit.view.swing.Alignment;
import nl.paulinternet.gtasaveedit.view.swing.Table;
import nl.paulinternet.gtasaveedit.view.swing.XBox;
import nl.paulinternet.gtasaveedit.view.swing.YBox;

public class PageWeapons extends Page
{
	private Table table;
	
	public PageWeapons () {
		super("Weapons");
		
		// Create table		
		table = new Table();
		table.setSpacing(10, 0);
		table.setCellWeight(1.0, 0.0);
		table.setCellExpand(0.0f, 0.0f);
		table.setCellAlignment(0.5f, 0.0f);
		table.add(new JLabel("Weapon"), 1, 0);
		table.add(new JLabel("Ammo"), 2, 0);

		table.setCellExpand(1.0f, 1.0f);
		table.add(new JSeparator(), 0, 1, 4, 1);
		
		// Start slot
		ConnectedRadioButtons startSlot = new ConnectedRadioButtons(Model.vars.weaponStartSlot);
		
		table.setCellWeight(0.0, 0.0);
		table.setCellExpand(0.0f, 1.0f);
		table.setCellAlignment(0.5f, 0.5f);
		table.add(new JLabel("Start with"), 0, 0);
		table.add(startSlot.create(0), 0, 2, 1, 2);
		table.add(startSlot.create(1), 0, 5, 1, 9);
		table.add(startSlot.create(2), 0, 15, 1, 4);
		table.add(startSlot.create(3), 0, 20, 1, 4);
		table.add(startSlot.create(4), 0, 25, 1, 4);
		table.add(startSlot.create(5), 0, 30, 1, 3);
		table.add(startSlot.create(6), 0, 34, 1, 3);
		table.add(startSlot.create(7), 0, 38, 1, 5);
		table.add(startSlot.create(8), 0, 44, 1, 5);
		table.add(startSlot.create(9), 0, 50, 1, 4);
		table.add(startSlot.create(10), 0, 55, 1, 7);
		table.add(startSlot.create(11), 0, 63, 1, 4);
		table.add(startSlot.create(12), 0, 68, 1, 2);

		// Weapons
		table.setCellExpand(1.0f, 1.0f);
		
		List<VariableIntegerImpl> weaponType = Model.vars.weaponType;
		
		ConnectedRadioButtons weapon = new ConnectedRadioButtons(weaponType.get(0));
		table.add(weapon.create(0x00, "Unarmed"), 1, 2);
		table.add(weapon.create(0x01, "Brass Knuckles"), 1, 3);
		table.add(new JSeparator(), 0, 4, 4, 1);
		
		weapon = new ConnectedRadioButtons(weaponType.get(1));
		table.add(weapon.create(0x00, "No"), 1, 5);
		table.add(weapon.create(0x02, "Golf Club"), 1, 6);
		table.add(weapon.create(0x03, "Nightstick"), 1, 7);
		table.add(weapon.create(0x04, "Knife"), 1, 8);
		table.add(weapon.create(0x05, "Baseball Bat"), 1, 9);
		table.add(weapon.create(0x06, "Shovel"), 1, 10);
		table.add(weapon.create(0x07, "Pool Cue"), 1, 11);
		table.add(weapon.create(0x08, "Katana"), 1, 12);
		table.add(weapon.create(0x09, "Chainsaw"), 1, 13);
		table.add(new JSeparator(), 0, 14, 4, 1);
		
		weapon = new ConnectedRadioButtons(weaponType.get(2));
		table.add(weapon.create(0x00, "No"), 1, 15);
		table.add(weapon.create(0x16, "9mm"), 1, 16);
		table.add(weapon.create(0x17, "Silenced 9mm"), 1, 17);
		table.add(weapon.create(0x18, "Desert Eagle"), 1, 18);
		table.add(new JSeparator(), 0, 19, 4, 1);
		
		weapon = new ConnectedRadioButtons(weaponType.get(3));
		table.add(weapon.create(0x00, "No"), 1, 20);
		table.add(weapon.create(0x19, "Shotgun"), 1, 21);
		table.add(weapon.create(0x1a, "Sawnoff Shotgun"), 1, 22);
		table.add(weapon.create(0x1b, "Combat Shotgun"), 1, 23);
		table.add(new JSeparator(), 0, 24, 4, 1);
		
		weapon = new ConnectedRadioButtons(weaponType.get(4));
		table.add(weapon.create(0x00, "No"), 1, 25);
		table.add(weapon.create(0x20, "Tec-9"), 1, 26);
		table.add(weapon.create(0x1c, "Micro SMG"), 1, 27);
		table.add(weapon.create(0x1d, "SMG"), 1, 28);
		table.add(new JSeparator(), 0, 29, 4, 1);
		
		weapon = new ConnectedRadioButtons(weaponType.get(5));
		table.add(weapon.create(0x00, "No"), 1, 30);
		table.add(weapon.create(0x1e, "AK47"), 1, 31);
		table.add(weapon.create(0x1f, "M4"), 1, 32);
		table.add(new JSeparator(), 0, 33, 4, 1);
		
		weapon = new ConnectedRadioButtons(weaponType.get(6));
		table.add(weapon.create(0x00, "No"), 1, 34);
		table.add(weapon.create(0x21, "Rifle"), 1, 35);
		table.add(weapon.create(0x22, "Sniper Rifle"), 1, 36);
		table.add(new JSeparator(), 0, 37, 4, 1);
		
		weapon = new ConnectedRadioButtons(weaponType.get(7));
		table.add(weapon.create(0x00, "No"), 1, 38);
		table.add(weapon.create(0x23, "Rocket Launcher"), 1, 39);
		table.add(weapon.create(0x24, "Heet Seeking Rocket Launcher"), 1, 40);
		table.add(weapon.create(0x25, "Flame Thrower"), 1, 41);
		table.add(weapon.create(0x26, "Vulcan Mini Gun"), 1, 42);
		table.add(new JSeparator(), 0, 43, 4, 1);
		
		weapon = new ConnectedRadioButtons(weaponType.get(8));
		table.add(weapon.create(0x00, "No"), 1, 44);
		table.add(weapon.create(0x10, "Grenade"), 1, 45);
		table.add(weapon.create(0x11, "Tear Gas"), 1, 46);
		table.add(weapon.create(0x12, "Molotov Cocktail"), 1, 47);
		table.add(weapon.create(0x27, "Remote Explosives"), 1, 48);
		table.add(new JSeparator(), 0, 49, 4, 1);
		
		weapon = new ConnectedRadioButtons(weaponType.get(9));
		table.add(weapon.create(0x00, "No"), 1, 50);
		table.add(weapon.create(0x29, "Spraycan"), 1, 51);
		table.add(weapon.create(0x2a, "Fire Extinguisher"), 1, 52);
		table.add(weapon.create(0x2b, "Camera"), 1, 53);
		table.add(new JSeparator(), 0, 54, 4, 1);
		
		weapon = new ConnectedRadioButtons(weaponType.get(10));
		table.add(weapon.create(0x00, "No"), 1, 55);
		table.add(weapon.create(0x0a, "Dildo 1"), 1, 56);
		table.add(weapon.create(0x0b, "Dildo 2"), 1, 57);
		table.add(weapon.create(0x0c, "Vibrator 1"), 1, 58);
		table.add(weapon.create(0x0d, "Vibrator 2"), 1, 59);
		table.add(weapon.create(0x0e, "Flowers"), 1, 60);
		table.add(weapon.create(0x0f, "Cane"), 1, 61);
		table.add(new JSeparator(), 0, 62, 4, 1);
		
		weapon = new ConnectedRadioButtons(weaponType.get(11));
		table.add(weapon.create(0x00, "No"), 1, 63);
		table.add(weapon.create(0x2c, "Night-Vision Goggles"), 1, 64);
		table.add(weapon.create(0x2d, "Thermal Goggles"), 1, 65);
		table.add(weapon.create(0x2e, "Parachute"), 1, 66);
		table.add(new JSeparator(), 0, 67, 4, 1);
		
		weapon = new ConnectedRadioButtons(weaponType.get(12));
		table.add(weapon.create(0x00, "No"), 1, 68);
		table.add(weapon.create(0x28, "Remote Detonator"), 1, 69);
		
		// Ammo
		table.setCellExpand(0.0f, 0.0f);
		table.setCellAlignment(0.5f, 0.5f);
		table.add(createAmmoBox(2), 2, 15, 1, 4);
		table.add(createAmmoBox(3), 2, 20, 1, 4);
		table.add(createAmmoBox(4), 2, 25, 1, 4);
		table.add(createAmmoBox(5), 2, 30, 1, 3);
		table.add(createAmmoBox(6), 2, 34, 1, 3);
		table.add(createAmmoBox(7), 2, 38, 1, 5);
		table.add(createAmmoBox(8), 2, 44, 1, 5);
		table.add(createAmmoBox(9), 2, 50, 1, 4);
		
		// Skills
		table.setCellWeight(0.0, 0.0);
		table.add(new JLabel("Skill"), 3, 0);
		table.add(createSkillBox(Model.vars.weaponPistol), 3, 16);
		table.add(createSkillBox(Model.vars.weaponSilencedPistol), 3, 17);
		table.add(createSkillBox(Model.vars.weaponDesertEagle), 3, 18);
		table.add(createSkillBox(Model.vars.weaponShotgun), 3, 21);
		table.add(createSkillBox(Model.vars.weaponSawnoffShotgun), 3, 22);
		table.add(createSkillBox(Model.vars.weaponCombatShotgun), 3, 23);
		table.add(createSkillBox(Model.vars.weaponMachinePistol), 3, 26, 1, 2);
		table.add(createSkillBox(Model.vars.weaponSmg), 3, 28);
		table.add(createSkillBox(Model.vars.weaponAk47), 3, 31);
		table.add(createSkillBox(Model.vars.weaponM4), 3, 32);

		// Create alignment
		Alignment alignment = new Alignment(table, 0.0f, 0.0f, 1.0f, 0.0f);
		alignment.setBorder(10);
		setComponent(alignment, true);
	}
	
	private YBox createAmmoBox (int slot) {
		TextFieldInterface var = Model.vars.weaponAmmo.get(slot);

		YBox ybox = new YBox();
		ybox.add(new ConnectedTextField(var));
		ybox.addSpace(3);
		ybox.add(new ValueButton(var, "99999"));
		
		return ybox;
	}
	
	private XBox createSkillBox (VariableFloat skill) {
		XBox xbox = new XBox();
		xbox.add(new ConnectedTextField(skill));
		xbox.addSpace(5);
		xbox.add(new ValueButton(skill, "0"));
		xbox.addSpace(5);
		xbox.add(new ValueButton(skill, "1000"));
		
		return xbox;
	}
}
