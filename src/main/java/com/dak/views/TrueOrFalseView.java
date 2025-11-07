package com.dak.views;

import com.dak.bases.BaseQuestionView;
import com.dak.models.OptionModel;
import com.dak.views.utils.ColorSet;
import com.dak.views.utils.EmptyIcon;
import com.dak.views.utils.SizeSet;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class TrueOrFalseView extends BaseQuestionView {
    private final ButtonGroup buttonGroup = new ButtonGroup();

    private final JRadioButton trueButton;
    private final JRadioButton falseButton;

    public TrueOrFalseView() {
        setOpaque(false);
        setLayout(new BorderLayout());

        trueButton = createButton("True");
        falseButton = createButton("False");

        JPanel mainPanel = new JPanel(new GridLayout(1, 2, SizeSet._3XS, 0));
        mainPanel.setOpaque(false);
        mainPanel.add(wrapButton(trueButton));
        mainPanel.add(wrapButton(falseButton));

        add(mainPanel, BorderLayout.CENTER);
    }

    public JRadioButton getTrueButton() {
        return trueButton;
    }

    public JRadioButton getFalseButton() {
        return falseButton;
    }

    private @NotNull JRadioButton createButton(String text) {
        JRadioButton option = new JRadioButton(text);
        option.setOpaque(false);
        option.setFocusPainted(false);
        option.setIcon(new EmptyIcon());
        option.setSelectedIcon(new EmptyIcon());
        option.setHorizontalAlignment(SwingConstants.CENTER);
        option.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        option.setBorder(BorderFactory.createEmptyBorder(SizeSet._3XS, SizeSet._3XS, SizeSet._3XS, SizeSet._3XS));

        buttonGroup.add(option);
        return option;
    }

    private @NotNull JPanel wrapButton(JRadioButton option) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panel.add(option, BorderLayout.CENTER);

        Border borderWhenDisabled = BorderFactory.createLineBorder(ColorSet.getPrimaryBorder());
        Border borderWhenEnabled = BorderFactory.createLineBorder(ColorSet.getSelectedOptionAccent());

        panel.setBorder(borderWhenDisabled);

        option.addItemListener(_ -> {
            if (option.isSelected()) {
                panel.setOpaque(true);
                panel.setBackground(ColorSet.getSelectedOptionBackground());
                panel.setBorder(borderWhenEnabled);
            } else {
                panel.setOpaque(false);
                panel.setBackground(ColorSet.getSecondaryBackground());
                panel.setBorder(borderWhenDisabled);
            }
        });

        return panel;
    }

    @Override
    public void disableInput() {
        disableButtonsInput(List.of(trueButton, falseButton));
    }

    @Override
    public void displayAnswerResult(List<OptionModel> options, Map<OptionModel, Boolean> resultMap) {
        if (resultMap == null) {
            JRadioButton correctButton = options.getFirst().isCorrect() ? trueButton : falseButton;

            correctButton.setOpaque(true);
            correctButton.setBackground(ColorSet.getIncorrectOptionBackground());
            ((JPanel) correctButton.getParent()).setBorder(BorderFactory.createLineBorder(ColorSet.getIncorrectOptionAccent()));

            return;
        }

        Map.Entry<OptionModel, Boolean> entry = resultMap.entrySet().iterator().next();
        OptionModel key = entry.getKey();
        Boolean value = entry.getValue();

        JRadioButton targetButton = key.isCorrect() ? trueButton : falseButton;
        Color correctBackgroundColor = value ? ColorSet.getCorrectOptionBackground() : ColorSet.getIncorrectOptionBackground();
        Color correctBorderColor = value ? ColorSet.getCorrectOptionAccent() : ColorSet.getIncorrectOptionAccent();

        targetButton.setOpaque(true);
        targetButton.setBackground(correctBackgroundColor);
        ((JPanel) targetButton.getParent()).setBorder(BorderFactory.createLineBorder(correctBorderColor));

        if (!value) {
            JRadioButton correctButton = key.isCorrect() ? falseButton : trueButton;

            correctButton.setOpaque(true);
            correctButton.setBackground(ColorSet.getCorrectOptionBackground());
            ((JPanel) correctButton.getParent()).setBorder(BorderFactory.createLineBorder(ColorSet.getCorrectOptionAccent()));
        }
    }
}