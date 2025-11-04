package com.dak.controllers;

import com.dak.bases.BaseQuestionController;
import com.dak.models.QuestionModel;
import com.dak.views.TrueOrFalseView;
import org.jetbrains.annotations.NotNull;

import java.awt.event.ActionListener;

public class TrueOrFalseController extends BaseQuestionController<TrueOrFalseView> {
    public TrueOrFalseController(QuestionModel model, TrueOrFalseView view) {
        super(model, view);

        ActionListener actionListener = createComponentActionListener();

        view.getTrueButton().addActionListener(actionListener);
        view.getFalseButton().addActionListener(actionListener);
    }
}