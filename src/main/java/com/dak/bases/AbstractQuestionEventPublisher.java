package com.dak.bases;

import com.dak.events.EventPublisher;
import com.dak.events.enums.QuestionInputEvent;
import com.dak.events.subscribers.QuestionInputSubscriber;
import com.dak.models.OptionModel;
import com.dak.models.QuestionModel;
import org.jetbrains.annotations.NotNull;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionListener;
import java.util.List;

public abstract class AbstractQuestionEventPublisher extends EventPublisher<QuestionInputSubscriber, QuestionInputEvent> {
    private final QuestionModel model;
    private final List<OptionModel> optionModels;

    public AbstractQuestionEventPublisher(QuestionModel model, List<OptionModel> optionModels) {
        this.model = model;
        this.optionModels = optionModels;
    }

    public QuestionModel getModel() {
        return model;
    }

    public List<OptionModel> getOptionsModels() {
        return optionModels;
    }

    @Override
    protected void notifyHandler(QuestionInputSubscriber subscriber, QuestionInputEvent event) {
        if (event == QuestionInputEvent.INPUT) {
            subscriber.onInput();
        } else {
            throw new IllegalArgumentException("Unhandled event case: " + event);
        }
    }

    protected @NotNull DocumentListener createComponentDocumentListener() {
        return new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                notifySubscribers(QuestionInputEvent.INPUT);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                notifySubscribers(QuestionInputEvent.INPUT);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Do nothing
            }
        };
    }

    protected ActionListener createComponentActionListener() {
        return e -> notifySubscribers(QuestionInputEvent.INPUT);
    }
}
