package com.dak.views;

import com.dak.models.QuizModel;
import com.dak.views.components.SectionHeader;
import com.dak.views.utils.SizeSet;

import javax.swing.*;
import java.awt.*;

public class NewReleaseSection extends JPanel {
    public NewReleaseSection() {
        setLayout(new BorderLayout());

        SectionHeader header = new SectionHeader("New Release");

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(SizeSet.XS / 2, SizeSet.XS / 2, SizeSet.XS / 2, SizeSet.XS / 2);

        // FIXME: Run this method asynchronously
        java.util.List<QuizModel> quizzes = QuizModel.findAll();

        for (int i = 0; i < quizzes.size(); i++) {
            gbc.gridx = i % 2;
            gbc.gridy = i / 2;
            gbc.weightx = 1.0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            mainPanel.add(new NewReleaseCard(quizzes.get(i)), gbc);
        }

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(mainPanel, BorderLayout.NORTH);

        add(header, BorderLayout.NORTH);
        add(centerPanel);
    }
}
