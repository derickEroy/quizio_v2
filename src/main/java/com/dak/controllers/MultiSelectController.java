package com.dak.controllers;

import com.dak.bases.BaseQuestionController;
import com.dak.models.QuestionModel;
import com.dak.views.MultiSelectView;
import org.jetbrains.annotations.NotNull;

import java.awt.event.ActionListener;

public class MultiSelectController extends BaseQuestionController<MultiSelectView> {
    public MultiSelectController(QuestionModel model, MultiSelectView view) {
        super(model, view);

        ActionListener actionListener = createComponentActionListener();

        view.getOptionOne().addActionListener(actionListener);
        view.getOptionTwo().addActionListener(actionListener);
        view.getOptionThree().addActionListener(actionListener);
        view.getOptionFour().addActionListener(actionListener);
    }
}