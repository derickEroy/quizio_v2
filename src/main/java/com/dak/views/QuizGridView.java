package com.dak.views;

import com.dak.views.utils.SizeSet;
import com.dak.views.viewModels.QuizGridViewModel;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class QuizGridView extends JPanel {
    private final QuizGridViewModel viewModel;

    public QuizGridView(QuizGridViewModel viewModel) {
        this.viewModel = viewModel;

        setOpaque(false);
        setLayout(new BorderLayout());

        add(createGrid(), BorderLayout.CENTER);
    }

    private @NotNull JPanel createGrid() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        for (int i = 0; i < viewModel.quizItemViews().length; i++) {
            int top = (i == 0 || i == 1) ? 0 : SizeSet._5XS;
            int left = (i % 2 == 0) ? 0 : SizeSet._5XS;
            gbc.insets = new Insets(top, left, SizeSet._5XS, SizeSet._5XS);

            gbc.gridx = i % 2;
            gbc.gridy = i / 2;

            panel.add(viewModel.quizItemViews()[i], gbc);
        }

        return panel;
    }
}
