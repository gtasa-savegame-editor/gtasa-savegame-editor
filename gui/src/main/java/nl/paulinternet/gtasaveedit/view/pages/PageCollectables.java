package nl.paulinternet.gtasaveedit.view.pages;

import nl.paulinternet.gtasaveedit.view.pages.collectables.*;

import javax.swing.*;

public class PageCollectables extends Page {
    public PageCollectables() {
        super("Collectables");

        Page[] pages = new Page[]{
                new CollectablePageTags(),
                new CollectablePageJumps(),
                new CollectablePageOysters(),
                new CollectablePageSnapshots(),
                new CollectablePageHorseshoes()
        };

        JTabbedPane pane = new JTabbedPane();
        for (Page page : pages) {
            pane.addTab(page.getTitle(), page.getComponent());
        }
        pane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setComponent(pane, false);
    }
}
