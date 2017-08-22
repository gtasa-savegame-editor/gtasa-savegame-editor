package nl.paulinternet.gtasaveedit.view;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import nl.paulinternet.gtasaveedit.model.Model;
import nl.paulinternet.gtasaveedit.model.Settings;
import nl.paulinternet.gtasaveedit.view.swing.Alignment;

public class Window extends JFrame
{
	public static final Window instance = new Window();

	private Window () {
		// Exit on close
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// Set size
		setSize(Settings.getWindowWidth(), Settings.getWindowHeight());
		if (Settings.getWindowMaximized() == Settings.YES) {
			setExtendedState(MAXIMIZED_BOTH);
		}
		else {
			setLocationRelativeTo(null);
		}
		
		// Add
		add(new Alignment(new JLabel("One moment please..."), 0.5f, 0.4f));
		
		// Set the icon
		List<Image> images = new ArrayList<Image>();
		images.add(Images.readImage("icon-16.png"));
		images.add(Images.readImage("icon-32.png"));
		images.add(Images.readImage("icon-48.png"));
		setIconImages(images);

		// Set title
		setTitle("GTA SA Savegame Editor");
	}
	
	public void createContent () {
		// Add tabs
		getContentPane().remove(0);
		add(new TabbedPane());

		// Set menu bar
		setJMenuBar(new MenuBar());
		
		// Observe
		Model.gameClosed.addHandler(this, "onGameClose");
		Model.gameLoaded.addHandler(this, "onTitleChange");
		Model.vars.title.onChange().addHandler(this, "onTitleChange");

		invalidate();
		validate();
	}
	
	public void onGameClose () {
		setTitle("GTA SA Savegame Editor");
	}
	
	public void onTitleChange () {
		setTitle(Model.vars.title.getText() + " - GTA SA Savegame Editor");
	}
}
