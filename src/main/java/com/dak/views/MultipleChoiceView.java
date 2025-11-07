package com.dak.views;

import com.dak.bases.BaseQuestionView;
import com.dak.models.OptionModel;
import com.dak.views.utils.ColorSet;
import com.dak.views.utils.SizeSet;
import com.dak.views.viewModels.MultipleChoiceViewModel;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

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

    private @NotNull JPanel wrapChoice(JRadioButton choice) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panel.add(choice, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createLineBorder(ColorSet.getPrimaryBorder()));

        choice.addItemListener(_ -> {
            if (choice.isSelected()) {
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

    @Override
    public void disableInput() {
        disableButtonsInput(List.of(choiceOne, choiceTwo, choiceThree, choiceFour));
    }

    @Override
    public void displayAnswerResult(List<OptionModel> options, Map<OptionModel, Boolean> resultMap) {
        List<JRadioButton> radioButtons =  Stream.of(choiceOne, choiceTwo, choiceThree, choiceFour).toList();

        if (resultMap == null) {
            JRadioButton correctButton = radioButtons
                .stream()
                .filter(r -> r.getText().equals(options.getFirst().getText()))
                .findFirst()
                .orElse(null);

            assert correctButton != null;
            correctButton.setOpaque(true);
            correctButton.setBackground(ColorSet.getIncorrectOptionBackground());
            ((JPanel) correctButton.getParent()).setBorder(BorderFactory.createLineBorder(ColorSet.getIncorrectOptionAccent()));

            return;
        }

        Map.Entry<OptionModel, Boolean> entry = resultMap.entrySet().iterator().next();
        OptionModel key = entry.getKey();
        Boolean value = entry.getValue();

        JRadioButton targetButton = radioButtons
            .stream()
            .filter(JRadioButton::isSelected)
            .findFirst()
            .orElse(null);

        if (value) {
            assert targetButton != null;
            targetButton.setOpaque(true);
            targetButton.setBackground(ColorSet.getCorrectOptionBackground());
            ((JPanel) targetButton.getParent()).setBorder(BorderFactory.createLineBorder(ColorSet.getCorrectOptionAccent()));
        } else {
            JRadioButton correctButton = radioButtons
                .stream()
                .filter(c -> c.getText().equals(key.getText()))
                .findFirst()
                .orElse(null);

            assert correctButton != null;
            correctButton.setOpaque(true);
            correctButton.setBackground(ColorSet.getCorrectOptionBackground());
            ((JPanel) correctButton.getParent()).setBorder(BorderFactory.createLineBorder(ColorSet.getCorrectOptionAccent()));

            assert targetButton != null;
            targetButton.setOpaque(true);
            targetButton.setBackground(ColorSet.getIncorrectOptionBackground());
            ((JPanel) targetButton.getParent()).setBorder(BorderFactory.createLineBorder(ColorSet.getIncorrectOptionAccent()));
        }
    }
}
