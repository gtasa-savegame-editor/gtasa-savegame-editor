package nl.paulinternet.gtasaveedit.view;

import javax.swing.BorderFactory;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import nl.paulinternet.gtasaveedit.model.Model;
import nl.paulinternet.gtasaveedit.view.pages.Page;
import nl.paulinternet.gtasaveedit.view.pages.PageAbout;
import nl.paulinternet.gtasaveedit.view.pages.PageClothes;
import nl.paulinternet.gtasaveedit.view.pages.PageCollectables;
import nl.paulinternet.gtasaveedit.view.pages.PageFix;
import nl.paulinternet.gtasaveedit.view.pages.PageGangWeapons;
import nl.paulinternet.gtasaveedit.view.pages.PageGeneral;
import nl.paulinternet.gtasaveedit.view.pages.PageLocation;
import nl.paulinternet.gtasaveedit.view.pages.PageOptions;
import nl.paulinternet.gtasaveedit.view.pages.PagePeds;
import nl.paulinternet.gtasaveedit.view.pages.PageSchools;
import nl.paulinternet.gtasaveedit.view.pages.PageSkills;
import nl.paulinternet.gtasaveedit.view.pages.PageWeapons;
import nl.paulinternet.gtasaveedit.view.pages.PageZones;

import java.util.Arrays;

public class TabbedPane extends JTabbedPane
{
	private boolean loaded;
	private Page[] pages;

	public TabbedPane () {
		// Create pages
		pages = new Page[] {
			new PageGeneral(),
			new PageSkills(),
			new PageLocation(),
			new PageSchools(),
			new PageWeapons(),
			new PageGangWeapons(),
			new PageZones(),
			new PagePeds(),
			new PageClothes(),
			new PageCollectables(),
			new PageFix(),
			new PageOptions()
			//new PageAbout()
		};
		
		// Add pages
		for (Page page : pages) {
			addTab(page.getTitle(), page.getComponent());
		}

		// Set the border
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		// Observe
		Model.gameLoaded.addHandler(this, "onGameLoaded");
		Model.gameClosed.addHandler(this, "onGameClosed");
		onGameClosed();
	}

	@SuppressWarnings("unused") // used in event
	public void onGameLoaded () {
		if (!loaded) {
			removeAll();
			for (Page page : pages) {
				addTab(page.getTitle(), page.getComponent());
			}
			loaded = true;
			Window.instance.validate();
		}
	}
	
	@SuppressWarnings("WeakerAccess") // used in event
	public void onGameClosed () {
		removeAll();
		for (Page page : pages) {
			if (page.isAlwaysVisible()) {
				addTab(page.getTitle(), page.getComponent());
			}
		}
		loaded = false;
		Window.instance.validate();
	}
	
	public void updateUI () {
		super.updateUI();
		if (!loaded && pages != null) {
			for (Page page : pages) {
				if (!page.isAlwaysVisible()) {
					SwingUtilities.updateComponentTreeUI(page.getComponent());
				}
			}
		}
	}

	void onShowPreferences() {
		Arrays.asList(pages).forEach(p -> {
			if(p instanceof PageOptions) {
				setSelectedComponent(p.getComponent());
			}
		});
	}
}
