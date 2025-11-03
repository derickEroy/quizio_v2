package com.dak.controllers;

import com.dak.bases.AbstractQuestionEventPublisher;
import com.dak.models.QuestionModel;
import com.dak.views.TrueOrFalseView;
import org.jetbrains.annotations.NotNull;

import java.awt.event.ActionListener;

public class TrueOrFalseController extends AbstractQuestionEventPublisher {
    private final TrueOrFalseView view;

    public TrueOrFalseController(QuestionModel model, @NotNull TrueOrFalseView view) {
        super(model, null);
        this.view = view;

        ActionListener actionListener = createComponentActionListener();

        view.getTrueButton().addActionListener(actionListener);
        view.getFalseButton().addActionListener(actionListener);
    }

    public TrueOrFalseView getView() {
        return view;
    }
}