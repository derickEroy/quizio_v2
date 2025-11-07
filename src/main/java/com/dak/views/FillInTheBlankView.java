package com.dak.views;

import com.dak.bases.BaseQuestionView;
import com.dak.models.OptionModel;
import com.dak.views.utils.ColorSet;
import com.dak.views.utils.SizeSet;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class FillInTheBlankView extends BaseQuestionView {
    private final JTextField textField;

    public FillInTheBlankView() {
        setOpaque(false);
        setLayout(new GridLayout(1, 1));

        textField = createTextField();

        add(textField);
    }

    public JTextField getTextField() {
        return textField;
    }

    private @NotNull JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setBackground(ColorSet.getSecondaryBackground());
        textField.setBorder(BorderFactory.createEmptyBorder(SizeSet._3XS, SizeSet._3XS, SizeSet._3XS, SizeSet._3XS));

        return textField;
    }

    @Override
    public void disableInput() {
        textField.setEditable(false);
        textField.setFocusable(false);
    }

    @Override
    public void displayAnswerResult(List<OptionModel> options, Map<OptionModel, Boolean> resultMap) {
        if (resultMap == null) {
            textField.setBackground(ColorSet.getIncorrectOptionBackground());
            textField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(ColorSet.getIncorrectOptionAccent()), textField.getBorder()));
            return;
        }

        Map.Entry<OptionModel, Boolean> entry = resultMap.entrySet().iterator().next();
        Boolean value = entry.getValue();

        Color backgroundColor = value ? ColorSet.getCorrectOptionBackground() : ColorSet.getIncorrectOptionBackground();
        Color borderColor = value ? ColorSet.getCorrectOptionAccent() : ColorSet.getIncorrectOptionAccent();

        textField.setBackground(backgroundColor);
        textField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(borderColor), textField.getBorder()));
    }
}
