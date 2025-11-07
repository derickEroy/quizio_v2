package com.dak.controllers;

import com.dak.bases.BaseQuestionController;
import com.dak.models.OptionModel;
import com.dak.models.QuestionModel;
import com.dak.views.TrueOrFalseView;

import java.awt.event.ActionListener;
import java.util.List;

public class TrueOrFalseController extends BaseQuestionController<TrueOrFalseView> {
    public TrueOrFalseController(QuestionModel model, List<OptionModel> options, TrueOrFalseView view) {
        super(model, options, view);

        ActionListener actionListener = createComponentActionListener();

        view.getTrueButton().addActionListener(actionListener);
        view.getFalseButton().addActionListener(actionListener);
    }
}