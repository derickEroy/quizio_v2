package com.dak.controllers;

import com.dak.bases.BaseQuestionController;
import com.dak.models.QuestionModel;
import com.dak.views.MultipleChoiceView;
import org.jetbrains.annotations.NotNull;

import java.awt.event.ActionListener;

public class MultipleChoiceController extends BaseQuestionController<MultipleChoiceView> {
    public MultipleChoiceController(QuestionModel model, MultipleChoiceView view) {
        super(model, view);

        ActionListener actionListener = createComponentActionListener();

        view.getChoiceOne().addActionListener(actionListener);
        view.getChoiceTwo().addActionListener(actionListener);
        view.getChoiceThree().addActionListener(actionListener);
        view.getChoiceFour().addActionListener(actionListener);
    }
}