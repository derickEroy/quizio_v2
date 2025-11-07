package com.dak.bases;

import com.dak.events.EventPublisher;
import com.dak.events.enums.QuestionEvent;
import com.dak.events.subscribers.QuestionSubscriber;
import com.dak.models.OptionModel;
import com.dak.models.QuestionModel;
import com.dak.views.MultiSelectView;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BaseQuestionController<TView extends BaseQuestionView> extends EventPublisher<QuestionSubscriber, QuestionEvent> {
    private final QuestionModel model;
    private final List<OptionModel> options;
    private final TView view;

    protected String answer;

    public BaseQuestionController(QuestionModel model, List<OptionModel> options, TView view) {
        this.model = model;
        this.options = options;
        this.view = view;
    }

    public QuestionModel getModel() {
        return model;
    }

    public void showAnswerResult(Map<OptionModel, Boolean> resultMap) {
        view.handleAnswerResult(options, resultMap);
    }

    @Override
    protected void notifyHandler(QuestionSubscriber subscriber, QuestionEvent event) {
        if (event == QuestionEvent.INPUT) {
            subscriber.onInput(answer, model);
        } else {
            throw new IllegalArgumentException("Unhandled event case: " + event);
        }
    }

    protected @NotNull DocumentListener createComponentDocumentListener() {
        return new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                handleInput(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                handleInput(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Do nothing
            }

            private void handleInput(@NotNull DocumentEvent e) {
                Document document = e.getDocument();

                try {
                    answer = document.getText(0, document.getLength());
                    notifySubscribers(QuestionEvent.INPUT);
                } catch (BadLocationException ex) {
                    throw new RuntimeException(ex);
                }
            }
        };
    }

    protected ActionListener createComponentActionListener() {
        return e -> {
            Object source = e.getSource();

            if (source instanceof AbstractButton component) {
                if (view instanceof MultiSelectView concreteView) {
                    List<String> answers = new ArrayList<>();

                    JCheckBox optionOne = concreteView.getOptionOne();
                    JCheckBox optionTwo = concreteView.getOptionTwo();
                    JCheckBox optionThree = concreteView.getOptionThree();
                    JCheckBox optionFour = concreteView.getOptionFour();

                    if (optionOne.isSelected()) {
                        answers.add(optionOne.getText());
                    }

                    if (optionTwo.isSelected()) {
                        answers.add(optionTwo.getText());
                    }

                    if (optionThree.isSelected()) {
                        answers.add(optionThree.getText());
                    }

                    if (optionFour.isSelected()) {
                        answers.add(optionFour.getText());
                    }

                    answer = answers.toString();
                } else {
                    answer = component.getText();
                }
            } else {
                throw new IllegalArgumentException("Unexpected event source: " + source);
            }

            notifySubscribers(QuestionEvent.INPUT);
        };
    }
}
