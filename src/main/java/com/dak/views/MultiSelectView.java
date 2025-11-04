package com.dak.views;

import com.dak.bases.BaseQuestionView;
import com.dak.views.utils.ColorSet;
import com.dak.views.utils.SizeSet;
import com.dak.views.viewModels.MultiSelectViewModel;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class MultiSelectView extends BaseQuestionView {
    private final JCheckBox optionOne;
    private final JCheckBox optionTwo;
    private final JCheckBox optionThree;
    private final JCheckBox optionFour;

    public MultiSelectView(@NotNull MultiSelectViewModel viewModel) {
        setOpaque(false);
        setLayout(new GridLayout(4, 1, 0, SizeSet.M));

        optionOne = createOption(viewModel.optionOneText());
        optionTwo = createOption(viewModel.optionTwoText());
        optionThree = createOption(viewModel.optionThreeText());
        optionFour = createOption(viewModel.optionFourText());

        add(wrapOption(optionOne));
        add(wrapOption(optionTwo));
        add(wrapOption(optionThree));
        add(wrapOption(optionFour));
    }

    public JCheckBox getOptionOne() {
        return optionOne;
    }

    public JCheckBox getOptionTwo() {
        return optionTwo;
    }

    public JCheckBox getOptionThree() {
        return optionThree;
    }

    public JCheckBox getOptionFour() {
        return optionFour;
    }

    private @NotNull JCheckBox createOption(String text) {
        JCheckBox option = new JCheckBox(text);
        option.setOpaque(false);
        option.setFocusPainted(false);
        option.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        option.setBorder(BorderFactory.createEmptyBorder(SizeSet._3XS, SizeSet._3XS, SizeSet._3XS, SizeSet._3XS));

        return option;
    }

    private @NotNull JPanel wrapOption(JCheckBox option) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createLineBorder(ColorSet.getPrimaryBorder()));
        panel.add(option);

        option.addChangeListener(e -> {
            if (option.isSelected()) {
                panel.setOpaque(true);
                panel.setBackground(ColorSet.getSelectedOptionBackground());
                panel.setBorder(BorderFactory.createLineBorder(ColorSet.getSelectedOptionAccent()));
            } else {
                panel.setOpaque(false);
                panel.setBorder(BorderFactory.createLineBorder(ColorSet.getPrimaryBorder()));
            }
        });

        return panel;
    }
}
