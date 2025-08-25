package com.dak.views;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class HomePage extends JPanel {
    public HomePage() {
        this.setLayout(new BorderLayout());
        this.add(new HeroSection(), BorderLayout.NORTH);
        this.add(new CategorySection(), BorderLayout.NORTH);
        this.add(new NewReleaseSection(), BorderLayout.CENTER);

        final JPanel nestedPanel = new JPanel();

        this.setLayout(new BorderLayout());
        this.add(new HeroSection(), BorderLayout.NORTH);

        nestedPanel.setLayout(new BorderLayout());
        nestedPanel.add(new CategorySection(), BorderLayout.NORTH);
        nestedPanel.add(new NewReleaseSection());
        this.add(nestedPanel);
    }
}
