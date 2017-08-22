package nl.paulinternet.gtasaveedit.view.menu;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import nl.paulinternet.gtasaveedit.model.Event;
import nl.paulinternet.gtasaveedit.model.EventHandler;
import nl.paulinternet.gtasaveedit.model.FileSystem;
import nl.paulinternet.gtasaveedit.model.Model;
import nl.paulinternet.gtasaveedit.model.savegame.Savegame;
import nl.paulinternet.gtasaveedit.view.Main;
import nl.paulinternet.gtasaveedit.view.Window;

public class MenuBar extends JMenuBar
{
	private static class ExitMenuItem extends JMenuItem implements ActionListener
	{
		public ExitMenuItem () {
			super("Exit");
			addActionListener(this);
		}

		@Override
		public void actionPerformed (ActionEvent e) {
			System.exit(0);
		}
	}
	
	private static class CloseMenuItem extends JMenuItem implements ActionListener
	{
		public CloseMenuItem () {
			super("Close");
			setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, Main.MAC ? InputEvent.META_MASK : InputEvent.CTRL_MASK));
			addActionListener(this);
		}

		@Override
		public void actionPerformed (ActionEvent e) {
			Savegame.close();
		}
	}
	
	private static class LoadCompleteSavegame extends JMenuItem implements ActionListener
	{
		public LoadCompleteSavegame () {
			super("100% Complete savegame");
			setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_9, Main.MAC ? InputEvent.META_MASK : InputEvent.CTRL_MASK));
			addActionListener(this);
		}

		@Override
		public void actionPerformed (ActionEvent ev) {
			Savegame.load(MenuBar.class.getResource("/savegame.b"));
		}
	}
	
	public static class OpenDirMenuItem extends JMenuItem implements ActionListener
	{
		public OpenDirMenuItem () {
			super("Open savegame directory");
			setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, Main.MAC ? InputEvent.META_MASK : InputEvent.CTRL_MASK));
			addActionListener(this);
			settingsChanged();
			Model.editSettings.settingsChanged.addHandler(this, "settingsChanged");
		}

		@Override
		public void actionPerformed (ActionEvent ev) {
			try {
				Desktop.getDesktop().open(FileSystem.getSavegameDirectory());
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(Window.instance, "Failed to open the savegame directory.", "Opening directory", JOptionPane.WARNING_MESSAGE);
			}
		}
		
		public void settingsChanged () {
			File savedir = FileSystem.getSavegameDirectory();
			setEnabled(savedir != null && savedir.exists());
		}
	}
	
	private static class ReloadMenuItem extends JMenuItem implements ActionListener
	{
		public ReloadMenuItem () {
			super("Reload savegame titles");
			setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
			addActionListener(this);
		}

		@Override
		public void actionPerformed (ActionEvent e) {
			Model.updateQuickLoad();
		}
	}
	
	public static class RunSaMenuItem extends JMenuItem implements ActionListener
	{
		public RunSaMenuItem () {
			super("Run GTA San Andreas");
			setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0));
			addActionListener(this);
			settingsChanged();
			Model.editSettings.settingsChanged.addHandler(this, "settingsChanged");
		}

		@Override
		public void actionPerformed (ActionEvent e) {
			try {
				if (Main.MAC) {
					Desktop.getDesktop().open(FileSystem.getSanAndreasExecutable());
				}
				else {
					Runtime.getRuntime().exec(new String[] {FileSystem.getSanAndreasExecutable().getPath()}, null, FileSystem.getSanAndreasDirectory());
				}
			}
			catch (Exception ex) {
				JOptionPane.showMessageDialog(Window.instance, "Failed to run GTA San Andreas.", "Run GTA San Andreas", JOptionPane.WARNING_MESSAGE);
			}
		}
		
		public void settingsChanged () {
			setEnabled(FileSystem.getSanAndreasExecutable() != null);
		}
	}
	
	private class Handler implements EventHandler
	{
		@Override
		public void handleEvent (Event e) {
			boolean loaded = e == Model.gameLoaded;
			menuSave.setEnabled(loaded);
			menuItemSave.setEnabled(loaded);
			menuItemClose.setEnabled(loaded);
		}
	}

	private JMenu menuSave;
	private JMenuItem menuItemSave;
	private JMenuItem menuItemClose;
	
	public MenuBar () {
		// File menu
		JMenu menuFile = new JMenu("File");
		menuFile.add(new FileLoad());
		menuFile.add(menuItemSave = new FileSave());
		menuFile.add(menuItemClose = new CloseMenuItem());
		menuFile.add(new FileDelete());
		menuFile.addSeparator();
		menuFile.add(new ReloadMenuItem());
		menuFile.add(new OpenDirMenuItem());
		menuFile.add(new RunSaMenuItem());
		menuFile.addSeparator();
		menuFile.add(new ExitMenuItem());
		
		// Load menu
		JMenu menuLoad = new JMenu("Load");
		for (int i=1; i<=8; i++) {
			menuLoad.add(new QuickLoad(i));
		}
		menuLoad.addSeparator();
		menuLoad.add(new LoadCompleteSavegame());
		
		// Save menu
		menuSave = new JMenu("Save");
		for (int i=1; i<=8; i++) {
			menuSave.add(new QuickSave(i));
		}

		// Delete menu
		JMenu menuDelete = new JMenu("Delete");
		for (int i=1; i<=8; i++) {
			menuDelete.add(new QuickDelete(i));
		}

		// Add menus
		add(menuFile);
		add(menuLoad);
		add(menuSave);
		add(menuDelete);
		
		// Observe
		Handler h = new Handler();
		h.handleEvent(Model.gameClosed);
		Model.gameLoaded.addHandler(h);
		Model.gameClosed.addHandler(h);
	}
}
