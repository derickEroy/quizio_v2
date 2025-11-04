package com.dak.views;

import com.dak.bases.BaseQuestionView;
import com.dak.views.utils.ColorSet;
import com.dak.views.utils.SizeSet;
import com.dak.views.viewModels.MultipleChoiceViewModel;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class MultipleChoiceView extends BaseQuestionView {
    private final ButtonGroup buttonGroup = new ButtonGroup();

    private final JRadioButton choiceOne;
    private final JRadioButton choiceTwo;
    private final JRadioButton choiceThree;
    private final JRadioButton choiceFour;

    public MultipleChoiceView(@NotNull MultipleChoiceViewModel viewModel) {
        setOpaque(false);
        setLayout(new GridLayout(4, 1, 0, SizeSet.M));

        choiceOne = createChoice(viewModel.choiceOneText());
        choiceTwo = createChoice(viewModel.choiceTwoText());
        choiceThree = createChoice(viewModel.choiceThreeText());
        choiceFour = createChoice(viewModel.choiceFourText());

        add(wrapChoice(choiceOne));
        add(wrapChoice(choiceTwo));
        add(wrapChoice(choiceThree));
        add(wrapChoice(choiceFour));
    }

    public ButtonGroup getButtonGroup() {
        return buttonGroup;
    }

    public JRadioButton getChoiceOne() {
        return choiceOne;
    }

    public JRadioButton getChoiceTwo() {
        return choiceTwo;
    }

    public JRadioButton getChoiceThree() {
        return choiceThree;
    }

    public JRadioButton getChoiceFour() {
        return choiceFour;
    }

    private @NotNull JRadioButton createChoice(String text) {
        JRadioButton option = new JRadioButton(text);
        option.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        option.setOpaque(false);
        option.setFocusPainted(false);
        option.setBorder(BorderFactory.createEmptyBorder(SizeSet._3XS, SizeSet._3XS, SizeSet._3XS, SizeSet._3XS));

        buttonGroup.add(option);

        return option;
    }

    private @NotNull JPanel wrapChoice(JRadioButton option) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panel.add(option, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createLineBorder(ColorSet.getPrimaryBorder()));

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
