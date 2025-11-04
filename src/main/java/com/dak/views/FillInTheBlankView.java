package com.dak.views;

import com.dak.bases.BaseQuestionView;
import com.dak.views.utils.ColorSet;
import com.dak.views.utils.SizeSet;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

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
        textField.setEditable(true);
        textField.setEnabled(true);

        return textField;
    }
}
