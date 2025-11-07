package com.dak.views;

import com.dak.bases.BaseQuestionView;
import com.dak.models.OptionModel;
import com.dak.views.utils.ColorSet;
import com.dak.views.utils.SizeSet;
import com.dak.views.viewModels.MultiSelectViewModel;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

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

        option.addItemListener(_ -> {
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

    @Override
    public void disableInput() {
        disableButtonsInput(List.of(optionOne, optionTwo, optionThree, optionFour));
    }

    @Override
    public void displayAnswerResult(List<OptionModel> options, Map<OptionModel, Boolean> resultMap) {
        List<JCheckBox> checkboxes = List.of(optionOne, optionTwo, optionThree, optionFour);

        if (resultMap == null) {
            List<OptionModel> correctOptions = options.stream().filter(OptionModel::isCorrect).toList();

            for (OptionModel optionModel : correctOptions) {
                JCheckBox targetCheckbox = checkboxes
                    .stream()
                    .filter(c -> c.getText().equals(optionModel.getText()))
                    .findFirst()
                    .orElse(null);

                assert targetCheckbox != null;
                targetCheckbox.setOpaque(true);
                targetCheckbox.setBackground(ColorSet.getIncorrectOptionBackground());
                ((JPanel) targetCheckbox.getParent()).setBorder(BorderFactory.createLineBorder(ColorSet.getIncorrectOptionAccent()));
            }

            return;
        }

        for (Map.Entry<OptionModel, Boolean> entry : resultMap.entrySet()) {
            OptionModel key = entry.getKey();
            Boolean value = entry.getValue();

            JCheckBox targetCheckbox = checkboxes.stream().filter(c -> c.getText().equals(key.getText())).findFirst().orElse(null);

            Color backgroundColor = value ? ColorSet.getCorrectOptionBackground() : ColorSet.getIncorrectOptionBackground();
            Color borderColor = value ? ColorSet.getCorrectOptionAccent() : ColorSet.getIncorrectOptionAccent();

            assert targetCheckbox != null;
            targetCheckbox.setOpaque(true);
            targetCheckbox.setBackground(backgroundColor);
            ((JPanel) targetCheckbox.getParent()).setBorder(BorderFactory.createLineBorder(borderColor));
        }
    }
}
