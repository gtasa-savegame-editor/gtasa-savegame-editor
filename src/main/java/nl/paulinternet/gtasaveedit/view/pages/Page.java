package nl.paulinternet.gtasaveedit.view.pages;

import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;

public abstract class Page
{
	private String title;
	private boolean alwaysVisible;
	private Component component;
	
	protected Page (String title, boolean alwaysVisible) {
		this.title = title;
		this.alwaysVisible = alwaysVisible;
	}
	
	protected Page (String title) {
		this(title, false);
	}

	protected void setComponent (Component component, boolean addScrolling) {
		if (addScrolling) {		
			JScrollPane scrollPane = new JScrollPane(component);
			scrollPane.getVerticalScrollBar().setUnitIncrement(16);
			scrollPane.getHorizontalScrollBar().setUnitIncrement(16);
			scrollPane.setBorder(BorderFactory.createEmptyBorder());
			component = scrollPane;
		}
		
		this.component = component;
	}
	
	public Component getComponent () {
		return component;
	}
	
	public String getTitle () {
		return title;
	}

	/**
	 * Returns whether the page is also visible if no game is loaded
	 */
	public boolean isAlwaysVisible () {
		return alwaysVisible;
	}
}
