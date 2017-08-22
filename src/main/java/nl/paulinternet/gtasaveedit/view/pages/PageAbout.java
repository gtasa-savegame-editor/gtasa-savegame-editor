package nl.paulinternet.gtasaveedit.view.pages;

import java.awt.Desktop;
import java.net.URI;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import nl.paulinternet.gtasaveedit.view.Window;
import nl.paulinternet.gtasaveedit.view.swing.Alignment;
import nl.paulinternet.gtasaveedit.view.swing.PButton;
import nl.paulinternet.gtasaveedit.view.swing.YBox;

public class PageAbout extends Page
{
	public PageAbout () {
		super("About", true);
		
		PButton websiteButton = new PButton("Go to the website");
		websiteButton.onClick().addHandler(this, "openWebsite", "www.paulinternet.nl/sa");

		JLabel label = new JLabel(
			"<html>" +
			"<font size=+2>GTA SA Savegame Editor</font><br />" +
			"<font size=+1>Version 3.2 (without 3d)</font><br />" +
			"<br />" +
			"This program is created by Paul Breeuwsma.<br />" +
			"The source code is available online.<br />" +
			"Please report any bugs via my website.<br />" +
			"<br />" +
			"Thanks to Tim Smith, OrionSR, Seemann, Pdescobar, hmvartak, Ryosuke, Steve M. and others for researching and programming.<br />" +
			"Thanks to Konoko45 for his 100% complete savegame.<br />" +
			"Thanks to Dennis L for donating and keeping me motivated." +
			"</html>"
		);

		YBox ybox = new YBox();
		ybox.add(label);
		ybox.addSpace(15, 0);
		ybox.add(new Alignment(websiteButton, 0.0f, 0.0f));
		ybox.setBorder(10);
		
		setComponent(ybox, true);
	}

	public void openWebsite (String website) {
		try {
			Desktop.getDesktop().browse(new URI("http://" + website));
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(Window.instance, "Go to " + website, "Website", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
