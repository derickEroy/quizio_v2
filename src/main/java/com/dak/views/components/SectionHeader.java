package com.dak.views.components;

import com.dak.views.utils.SizeSet;

import javax.swing.*;
import java.awt.*;

public class SectionHeader extends JPanel {
    public SectionHeader(String title) {
        setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, SizeSet.M));
        titleLabel.setHorizontalAlignment(JLabel.LEFT);

        add(titleLabel);
    }
}
