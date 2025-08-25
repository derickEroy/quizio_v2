package com.dak.views;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.dak.Main;
import com.dak.views.components.SectionHeader;
import com.dak.views.utils.SizeSet;

public class DummyPage extends JPanel{
    public DummyPage(String message) {
        this.setLayout(new FlowLayout());

        this.add(new SectionHeader(message, SizeSet._2XL));

        JButton button = new JButton("Back to home");
        button.addActionListener(e -> {
            Main.switchPanel(new HomePage());
        });

        this.add(button);
    }
}
