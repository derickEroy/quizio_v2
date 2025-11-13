package com.dak.controllers;

import com.dak.events.EventPublisher;
import com.dak.events.enums.QuizItemEvent;
import com.dak.events.subscribers.QuizItemSubscriber;
import com.dak.models.QuizModel;
import com.dak.views.QuizItemView;
import org.jetbrains.annotations.NotNull;

import java.awt.event.ActionListener;

public class QuizItemController extends EventPublisher<QuizItemSubscriber, QuizItemEvent> {
    private final QuizModel model;
    private final QuizItemView view;

    public QuizItemController(QuizModel model, @NotNull QuizItemView view) {
        this.model = model;
        this.view = view;

        view.getButton().addActionListener(createPlayButtonActionListener());
    }

    public QuizModel getModel() {
        return model;
    }

    public QuizItemView getView() {
        return view;
    }

    private @NotNull ActionListener createPlayButtonActionListener() {
        return (_) -> notifySubscribers(QuizItemEvent.PLAY);
    }

    @Override
    protected void notifyHandler(QuizItemSubscriber subscriber, @NotNull QuizItemEvent event) {
        if (event == QuizItemEvent.PLAY) {
            subscriber.onPlay(model.getId().toString());
        } else {
            throw new IllegalArgumentException("Unhandled event case: " + event);
        }
    }
}
