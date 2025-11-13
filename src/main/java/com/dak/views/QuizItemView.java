package com.dak.views;

import com.dak.views.utils.ColorSet;
import com.dak.views.utils.ImageLoader;
import com.dak.views.utils.SizeSet;
import com.dak.views.viewModels.QuizItemViewModel;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class QuizItemView extends JPanel {
    private final QuizItemViewModel viewModel;

    private final JButton button;

    public QuizItemView(QuizItemViewModel viewModel) {
        this.viewModel = viewModel;

        setLayout(new BorderLayout());
        setBackground(ColorSet.getSecondaryBackground());
        setBorder(BorderFactory.createEmptyBorder(SizeSet.XS, SizeSet.XS, SizeSet.XS, SizeSet.XS));

        JPanel leftPanel = new JPanel();
        leftPanel.setOpaque(false);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.add(createTitle());
        leftPanel.add(createCreator());

        JPanel rightPanel = new JPanel();
        rightPanel.setOpaque(false);
        rightPanel.add(createList());

        button = new JButton("Play");

        rightPanel.add(button);

        add(leftPanel);
        add(rightPanel, BorderLayout.EAST);
    }

    public JButton getButton() {
        return button;
    }

    @Contract(" -> new")
    private @NotNull JLabel createTitle() {
        JLabel label = new JLabel(viewModel.title());
        label.setForeground(ColorSet.getPrimaryForeground());
        label.setFont(label.getFont().deriveFont(Font.BOLD));
        label.setMaximumSize(new Dimension(Integer.MAX_VALUE, SizeSet._4XL));

        return label;
    }

    @Contract(" -> new")
    private @NotNull JLabel createCreator() {
        JLabel label = new JLabel("By " + viewModel.creator());
        label.setForeground(ColorSet.getPrimaryForeground());
        label.setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 0));
        label.setFont(label.getFont().deriveFont(Font.PLAIN, SizeSet.XS));
        label.setMaximumSize(new Dimension(Integer.MAX_VALUE, SizeSet._4XL));

        return label;
    }

    private @NotNull JPanel createList() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, SizeSet._2XS));

        for (int i = 0; i < viewModel.images().length; i++) {
            String image = viewModel.images()[i];
            JLabel imageLabel = new JLabel(ImageLoader.load(image, SizeSet._2XL, SizeSet._2XL));

            panel.add(imageLabel);

            if (i < viewModel.images().length - 1) {
                panel.add(Box.createHorizontalStrut(SizeSet._5XS));
            }
        }

        return panel;
    }
}
