package com.dak.views;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.dak.Main;
import com.dak.views.components.PrimaryButton;
import com.dak.views.utils.*;

public class HeroSection extends JPanel {
    
    public HeroSection() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(Box.createVerticalStrut(SizeSet.S));

        JPanel callToActionPanel = createCallToActionPanel();
        callToActionPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(callToActionPanel);

        JPanel featuresPanel = createFeaturesPanel();
        featuresPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(featuresPanel);
    }

    private JPanel createCallToActionPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(ColorSet.SECONDARY_FOREGROUND);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, SizeSet.XL, 0));
        panel.setOpaque(false);

        JLabel label = new JLabel("Log in or sign up to get the most out of your Quizio learning experience!");
        label.setOpaque(false);

        panel.add(label);
        panel.add(createBigButton("Log in", null, e -> Main.switchPanel(new DummyPage("Login"))));
        panel.add(createBigButton("Sign up", null, e -> Main.switchPanel(new DummyPage("Register"))));

        return panel;
    }

    private JButton createBigButton(String text, ImageIcon icon, ActionListener al) {
        JButton button = new PrimaryButton(text);
        button.setIcon(icon);
        button.setMargin(new Insets(SizeSet._2XS, SizeSet._2XS, SizeSet._2XS, SizeSet._2XS));
        button.setFont(button.getFont().deriveFont(Font.BOLD));
        button.setOpaque(false);
        button.setFocusPainted(false);
        button.addActionListener(al);
        return button;
    }

    private JPanel createFeaturesPanel() {
        JPanel panel = new JPanel();

        panel.setLayout(new FlowLayout(FlowLayout.CENTER, SizeSet._3XL, SizeSet.XL));
        panel.setOpaque(false);

        String features[] = new String[] {
            "Create and share quizzes with your friends",
            "Keep track of performance and progress",
            "Personalize your quiz experience"
        };

        for (String feature : features) {
            JLabel label = new JLabel(feature);
            label.setIcon(ImageSet.getIconFromSVG("check-circle.svg", SizeSet.XL, SizeSet.XL));
            panel.add(label);
        }

        return panel;
    }
}
