package nl.paulinternet.gtasaveedit.view.pages;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;

import nl.paulinternet.gtasaveedit.model.savegame.data.Cloth;
import nl.paulinternet.gtasaveedit.model.savegame.data.Clothes;
import nl.paulinternet.gtasaveedit.model.Model;
import nl.paulinternet.gtasaveedit.model.variables.Variable;
import nl.paulinternet.gtasaveedit.view.cloth.ClothButton;
import nl.paulinternet.gtasaveedit.view.cloth.ClothCheckBox;
import nl.paulinternet.gtasaveedit.view.swing.Alignment;
import nl.paulinternet.gtasaveedit.view.swing.Table;

public class ClothPage extends Page
{
	public static final int ALL = 0, FIRST_HALF = 1, SECOND_HALF = 2;
	
	private static final String[] TITLE = new String[] {"Torso", "Haircut", "Legs", "Shoes", "Chains", "Watches", "Shades", "Hats", "Special", "Tattoos"};
	private static final String[] SHOP = new String[] {null, "Binco", "Pro-Laps", "Sub Urban", "ZIP", "Victim", "Didier Sachs"};
	private static final String[] TATTOO = new String[] {"Upper left arm", "Lower left arm", "Upper right arm", "Lower right arm",
		"Back", "Left chest", "Right chest", "Stomach", "Lower back"};
	
	public ClothPage (int type) {
		super(TITLE[type]);
		
		// Table
		Table table = new Table();
		table.setCellAlignment(1.0f, 0.0f);
		
		switch (type) {
			case 1: // Haircut
				addClothes(table, 0, 0, false, 1, 0, FIRST_HALF);
				addClothes(table, 4, 0, false, 1, 0, SECOND_HALF);
				break;
			case 8: // Special
				addClothes(table, 0, 0, false, 8, 0);
				break;
			case 9: // Tattoos
				addClothes(table, 0, 0, true, 0, 0);
				addClothes(table, 4, 0, true, 4, 0);
				addClothes(table, 8, 0, true, 2, 0);
				
				int y = Math.max(Math.max(Clothes.tattoos[0].length, Clothes.tattoos[4].length), Clothes.tattoos[2].length) + 1;
				
				addClothes(table, 0, y, true, 1, 0);
				addClothes(table, 4, y, true, 8, 0);
				addClothes(table, 8, y, true, 3, 0);
				
				y += Math.max(Math.max(Clothes.tattoos[1].length, Clothes.tattoos[8].length), Clothes.tattoos[3].length) + 1;
				
				addClothes(table, 0, y, true, 5, 0);
				addClothes(table, 4, y, true, 7, 0);
				addClothes(table, 8, y, true, 6, 0);
				break;
			default:
				addClothes(table, 0, 0, false, type, 0);
				
				y = Clothes.clothes[type][0].length + 1;

				addClothes(table, 0, y, false, type, 1);
				addClothes(table, 5, y, false, type, 2);
				addClothes(table, 10, y, false, type, 3);
				
				y += Math.max(Math.max(Clothes.clothes[type][1].length, Clothes.clothes[type][2].length), Clothes.clothes[type][3].length) + 1;
				
				addClothes(table, 0, y, false, type, 4);
				addClothes(table, 5, y, false, type, 5);
				addClothes(table, 10, y, false, type, 6);
		}
		
		// Alignment
		Alignment alignment = new Alignment(table, 0.0f, 0.0f);
		alignment.setBorder(10);
		setComponent(alignment, true);
	}

	private void addClothes (Table table, int x, int y, boolean tattoo, int type, int shop, int mode) {
		// Headers
		if (shop != 0 || tattoo) {
			String text = tattoo ? TATTOO[type] : SHOP[shop];
			JLabel label = new JLabel("<html><b>" + text.replace(" ", "&nbsp;") + "</b></html>");
			label.setBorder(BorderFactory.createEmptyBorder(0, 30, 10, 30));
			
			table.setCellExpand(1.0f, 1.0f);
			table.setSpacing(30, 20);
			table.add(label, x, y);
		}
		
		table.setCellExpand(0.0f, 1.0f);
		table.setSpacing(10, 20);
		table.add(new JLabel("Respect"), x + 1, y);
		table.add(new JLabel("Sexy"), x + 2, y);
		table.add(new JLabel("Price"), x + 3, y);
		y++;
		
		// Vars
		Variable<Cloth> var = tattoo ? Model.vars.tattoos.get(type) : Model.vars.clothes.get(type);
		ButtonGroup buttonGroup = tattoo ? PageClothes.tattooButtonGroup[type] : PageClothes.clothButtonGroup[type];
		Cloth[] clothes = tattoo ? Clothes.tattoos[type] : Clothes.clothes[type][shop];
		
		// Begin/end
		int begin = mode == SECOND_HALF ? (clothes.length + 1) / 2 : 0;
		int end = mode == FIRST_HALF ? (clothes.length + 1) / 2 : clothes.length;
		
		// Go
		for (int i=begin; i<end; i++) {
			Cloth cloth = clothes[i];
			
			// Button
			ClothButton button = new ClothButton(cloth, var);
			buttonGroup.add(button);
			
			table.setSpacing(30, 0);
			table.setCellExpand(1.0f, 1.0f);
			table.add(button, x, y);
			table.setSpacing(10, 0);
			table.setCellExpand(0.0f, 1.0f);
			table.add(new JLabel(String.valueOf(cloth.getRespect())), x + 1, y);
			table.add(new JLabel(String.valueOf(cloth.getSexy())), x + 2, y);
			table.add(new JLabel(String.valueOf(cloth.getPrice())), x + 3, y);
			
			if (cloth.getAddress() != 0) {
				table.add(new ClothCheckBox(cloth), x + 4, y);
			}
			
			y++;
		}
	}
	
	private void addClothes (Table table, int x, int y, boolean tattoo, int type, int shop) {
		addClothes(table, x, y, tattoo, type, shop, ALL);
	}
}
