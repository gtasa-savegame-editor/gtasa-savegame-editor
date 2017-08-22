package nl.paulinternet.gtasaveedit.view.pages;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import nl.paulinternet.gtasaveedit.model.Model;
import nl.paulinternet.gtasaveedit.model.TextFieldInterface;
import nl.paulinternet.gtasaveedit.view.connected.ConnectedTextField;
import nl.paulinternet.gtasaveedit.view.MapImage;
import nl.paulinternet.gtasaveedit.view.selectable.SelectableItemComponent;
import nl.paulinternet.gtasaveedit.view.selectable.SelectableItemVariable;
import nl.paulinternet.gtasaveedit.view.selectable.SelectableItems;
import nl.paulinternet.gtasaveedit.view.selectable.SelectableTag;
import nl.paulinternet.gtasaveedit.view.selectable.SelectionSizeLabel;
import nl.paulinternet.gtasaveedit.view.ValueButton;
import nl.paulinternet.gtasaveedit.view.swing.XBox;
import nl.paulinternet.gtasaveedit.view.swing.YBox;

public class CollectablePageTags extends Page
{
	public static class LabelSprayed extends JLabel
	{
		public LabelSprayed (SelectableItems<?> items) {
			items.onDataChange().addHandler(this, "updateText");
		}

		public void updateText () {
			int sprayed = 0;
			
			for (int tag : Model.vars.tags) {
				if (tag >= 0x35) sprayed++;
			}
			
			setText(sprayed + " tags sprayed");
		}
	}

	public CollectablePageTags () {
		super("Tags");
		
		List<SelectableTag> tagList = new ArrayList<SelectableTag>(100);
		addTags(tagList);
		SelectableItems<SelectableTag> tags = new SelectableItems<SelectableTag>(tagList);
		
		// Create xbox
		TextFieldInterface var = new SelectableItemVariable(tags, 0, 0, 255);

		XBox xbox = new XBox();
		xbox.add(new JLabel("Value:"));
		xbox.addSpace(5);
		xbox.add(new ConnectedTextField(var));
		xbox.addSpace(5);
		xbox.add(new JLabel("(0 - 255, sprayed when 229 or higher)"));
		xbox.addSpace(5, 1);
		xbox.add(new ValueButton(var, "255", "Spray selected"));
		xbox.addSpace(5);
		xbox.add(new ValueButton(var, "0", "Unspray selected"));
		
		// Create map
		SelectableItemComponent map = new SelectableItemComponent(MapImage.LOS_SANTOS, tags, SelectableItemComponent.MULTIPLE);
		Model.gameLoaded.addHandler(map, "repaint");
		
		// Create xbox
		XBox xboxBottom = new XBox();
		xboxBottom.add(new LabelSprayed(tags));
		xboxBottom.addSpace(10, 1);
		xboxBottom.add(new SelectionSizeLabel(tags, "tags"));
		
		// Create map ybox
		YBox yboxMap = new YBox();
		yboxMap.add(xbox);
		yboxMap.addSpace(10);
		yboxMap.add(map, 0, 0.0f, 0.0f);
		yboxMap.addSpace(5);
		yboxMap.add(xboxBottom);

		// Create ybox
		YBox ybox = new YBox();
		ybox.add(new JLabel("<html>Click or drag the mouse to select tags.<br />Hold shift or alt to respectively grow or shrink the selection."));
		ybox.addSpace(10);
		ybox.add(yboxMap, 0, 0.0f, 0.0f);
		ybox.setBorder(10);
		setComponent(ybox, true);
	}
	
	private void addTags (List<SelectableTag> tags) {
		int i = 0;
		tags.add(new SelectableTag(i++, 1549.890625f, -1714.523438f));
		tags.add(new SelectableTag(i++, 1448.234375f, -1755.898438f));
		tags.add(new SelectableTag(i++, 1332.132813f, -1722.304688f));
		tags.add(new SelectableTag(i++, 1724.734375f, -1741.5f));
		tags.add(new SelectableTag(i++, 1767.210938f, -1617.539063f));
		tags.add(new SelectableTag(i++, 1799.132813f, -1708.765625f));
		tags.add(new SelectableTag(i++, 1498.632813f, -1207.351563f));
		tags.add(new SelectableTag(i++, 1732.734375f, -963.078125f));
		tags.add(new SelectableTag(i++, 1746.75f, -1359.773438f));
		tags.add(new SelectableTag(i++, 1519.421875f, -1010.945313f));
		tags.add(new SelectableTag(i++, 1687.226563f, -1239.125f));
		tags.add(new SelectableTag(i++, 1783.96875f, -2156.539063f));
		tags.add(new SelectableTag(i++, 1574.710938f, -2691.882813f));
		tags.add(new SelectableTag(i++, 1118.90625f, -2008.242188f));
		tags.add(new SelectableTag(i++, 1850.007813f, -1876.835938f));
		tags.add(new SelectableTag(i++, 1889.242188f, -1982.507813f));
		tags.add(new SelectableTag(i++, 1950.617188f, -2034.398438f));
		tags.add(new SelectableTag(i++, 1936.882813f, -2134.90625f));
		tags.add(new SelectableTag(i++, 1808.34375f, -2092.265625f));
		tags.add(new SelectableTag(i++, 1624.625f, -2296.242188f));
		tags.add(new SelectableTag(i++, 1071.140625f, -1863.789063f));
		tags.add(new SelectableTag(i++, 2065.4375f, -1897.234375f));
		tags.add(new SelectableTag(i++, 2763f, -2012.109375f));
		tags.add(new SelectableTag(i++, 2379.320313f, -2166.21875f));
		tags.add(new SelectableTag(i++, 2134.328125f, -2011.203125f));
		tags.add(new SelectableTag(i++, 2392.359375f, -1914.570313f));
		tags.add(new SelectableTag(i++, 2430.328125f, -1997.90625f));
		tags.add(new SelectableTag(i++, 2587.320313f, -2063.523438f));
		tags.add(new SelectableTag(i++, 2704.195313f, -1966.6875f));
		tags.add(new SelectableTag(i++, 2489.242188f, -1959.070313f));
		tags.add(new SelectableTag(i++, 2273.898438f, -2265.804688f));
		tags.add(new SelectableTag(i++, 2173.59375f, -2165.1875f));
		tags.add(new SelectableTag(i++, 2273.195313f, -2529.117188f));
		tags.add(new SelectableTag(i++, 2704.226563f, -2144.304688f));
		tags.add(new SelectableTag(i++, 2794.53125f, -1906.8125f));
		tags.add(new SelectableTag(i++, 2812.9375f, -1942.070313f));
		tags.add(new SelectableTag(i++, 2874.5f, -1909.382813f));
		tags.add(new SelectableTag(i++, 2046.40625f, -1635.84375f));
		tags.add(new SelectableTag(i++, 2066.429688f, -1652.476563f));
		tags.add(new SelectableTag(i++, 2102.195313f, -1648.757813f));
		tags.add(new SelectableTag(i++, 2162.78125f, -1786.070313f));
		tags.add(new SelectableTag(i++, 2034.398438f, -1801.671875f));
		tags.add(new SelectableTag(i++, 1910.164063f, -1779.664063f));
		tags.add(new SelectableTag(i++, 1837.195313f, -1814.1875f));
		tags.add(new SelectableTag(i++, 1837.664063f, -1640.382813f));
		tags.add(new SelectableTag(i++, 1959.398438f, -1577.757813f));
		tags.add(new SelectableTag(i++, 2074.179688f, -1579.148438f));
		tags.add(new SelectableTag(i++, 2182.234375f, -1467.898438f));
		tags.add(new SelectableTag(i++, 2132.234375f, -1258.09375f));
		tags.add(new SelectableTag(i++, 2233.953125f, -1367.617188f));
		tags.add(new SelectableTag(i++, 2224.765625f, -1193.0625f));
		tags.add(new SelectableTag(i++, 2119.203125f, -1196.617188f));
		tags.add(new SelectableTag(i++, 1974.085938f, -1351.164063f));
		tags.add(new SelectableTag(i++, 2093.757813f, -1413.445313f));
		tags.add(new SelectableTag(i++, 1969.59375f, -1289.695313f));
		tags.add(new SelectableTag(i++, 1966.945313f, -1174.726563f));
		tags.add(new SelectableTag(i++, 1911.867188f, -1064.398438f));
		tags.add(new SelectableTag(i++, 2281.460938f, -1118.960938f));
		tags.add(new SelectableTag(i++, 2239.78125f, -999.75f));
		tags.add(new SelectableTag(i++, 2122.6875f, -1060.898438f));
		tags.add(new SelectableTag(i++, 2062.71875f, -996.4609375f));
		tags.add(new SelectableTag(i++, 2076.726563f, -1071.132813f));
		tags.add(new SelectableTag(i++, 2399.414063f, -1552.03125f));
		tags.add(new SelectableTag(i++, 2353.539063f, -1508.210938f));
		tags.add(new SelectableTag(i++, 2394.101563f, -1468.367188f));
		tags.add(new SelectableTag(i++, 2841.367188f, -1312.960938f));
		tags.add(new SelectableTag(i++, 2820.34375f, -1190.976563f));
		tags.add(new SelectableTag(i++, 2766.085938f, -1197.140625f));
		tags.add(new SelectableTag(i++, 2756.007813f, -1388.125f));
		tags.add(new SelectableTag(i++, 2821.234375f, -1465.09375f));
		tags.add(new SelectableTag(i++, 2767.78125f, -1621.1875f));
		tags.add(new SelectableTag(i++, 2767.757813f, -1819.945313f));
		tags.add(new SelectableTag(i++, 2667.890625f, -1469.132813f));
		tags.add(new SelectableTag(i++, 2612.929688f, -1390.773438f));
		tags.add(new SelectableTag(i++, 2536.21875f, -1352.765625f));
		tags.add(new SelectableTag(i++, 2580.945313f, -1274.09375f));
		tags.add(new SelectableTag(i++, 2603.15625f, -1197.8125f));
		tags.add(new SelectableTag(i++, 2542.953125f, -1363.242188f));
		tags.add(new SelectableTag(i++, 2462.265625f, -1541.414063f));
		tags.add(new SelectableTag(i++, 2522.460938f, -1478.742188f));
		tags.add(new SelectableTag(i++, 2346.515625f, -1350.78125f));
		tags.add(new SelectableTag(i++, 2322.453125f, -1254.414063f));
		tags.add(new SelectableTag(i++, 2273.015625f, -1687.429688f));
		tags.add(new SelectableTag(i++, 2422.90625f, -1682.296875f));
		tags.add(new SelectableTag(i++, 2576.820313f, -1143.273438f));
		tags.add(new SelectableTag(i++, 2621.507813f, -1092.203125f));
		tags.add(new SelectableTag(i++, 2797.921875f, -1097.695313f));
		tags.add(new SelectableTag(i++, 1295.179688f, -1465.21875f));
		tags.add(new SelectableTag(i++, 1271.484375f, -1662.320313f));
		tags.add(new SelectableTag(i++, 810.5703125f, -1797.570313f));
		tags.add(new SelectableTag(i++, 730.4453125f, -1482.007813f));
		tags.add(new SelectableTag(i++, 947.484375f, -1466.71875f));
		tags.add(new SelectableTag(i++, 944.2734375f, -985.8203125f));
		tags.add(new SelectableTag(i++, 1072.90625f, -1012.796875f));
		tags.add(new SelectableTag(i++, 1206.25f, -1162f));
		tags.add(new SelectableTag(i++, 1098.8125f, -1292.546875f));
		tags.add(new SelectableTag(i++, 482.625f, -1761.585938f));
		tags.add(new SelectableTag(i++, 399.0078125f, -2066.882813f));
		tags.add(new SelectableTag(i++, 466.9765625f, -1283.023438f));
		tags.add(new SelectableTag(i++, 583.4609375f, -1502.109375f));
	}
}
