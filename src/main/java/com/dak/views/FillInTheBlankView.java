package com.dak.views;

import com.dak.bases.BaseQuestionView;
import com.dak.models.OptionModel;
import com.dak.views.utils.ColorSet;
import com.dak.views.utils.SizeSet;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.List;
import java.util.Map;

public class FillInTheBlankView extends BaseQuestionView {
    private final JTextField textField;

    private final String placeholderText = "Type your answer...";

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
        JTextField textField = new JTextField(placeholderText);
        textField.setBackground(ColorSet.getSecondaryBackground());
        textField.setBorder(BorderFactory.createEmptyBorder(SizeSet._3XS, SizeSet._3XS, SizeSet._3XS, SizeSet._3XS));
        textField.setForeground(ColorSet.getPlaceholderForeground());

        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholderText)) {
                    textField.setText("");
                    textField.setForeground(ColorSet.getPrimaryForeground());
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholderText);
                    textField.setForeground(ColorSet.getPlaceholderForeground());
                }
            }
        });

        return textField;
    }

    private void displayTextFieldResult(boolean correct) {
        textField.setBackground(correct
                ? ColorSet.getCorrectOptionBackground()
                : ColorSet.getIncorrectOptionBackground());

        textField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(correct
                ? ColorSet.getCorrectOptionAccent()
                : ColorSet.getIncorrectOptionAccent()), textField.getBorder()));
    }

    @Override
    public void disableInput() {
        textField.setEditable(false);
        textField.setFocusable(false);
    }

    @Override
    public void displayAnswerResult(List<OptionModel> options, Map<OptionModel, Boolean> resultMap) {
        if (resultMap == null) {
            displayTextFieldResult(false);
            return;
        }

        displayTextFieldResult(resultMap.entrySet().iterator().next().getValue());
    }
}
