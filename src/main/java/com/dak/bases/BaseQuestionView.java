package com.dak.bases;

import com.dak.models.OptionModel;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.util.List;
import java.util.Map;

public abstract class BaseQuestionView extends JPanel {
    public boolean isFinish = false;

    abstract public void disableInput();

    public void disableButtonsInput(@NotNull List<AbstractButton> buttons) {
        for (AbstractButton button : buttons) {
            for (var listener : button.getMouseListeners()) {
                button.removeMouseListener(listener);
            }

            button.addMouseListener(new MouseAdapter() {
                @Override public void mousePressed(java.awt.event.MouseEvent e) {
                    e.consume();
                }
            });

            button.setFocusable(false);
        }
    }

    public void handleAnswerResult(List<OptionModel> options, Map<OptionModel, Boolean> resultMap) {
        if (isFinish) {
            return;
        }

        isFinish = true;

        disableInput();
        displayAnswerResult(options, resultMap);
    }

    abstract public void displayAnswerResult(List<OptionModel> options, Map<OptionModel, Boolean> resultMap);
}
