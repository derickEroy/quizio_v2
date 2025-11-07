package com.dak.controllers;

import com.dak.bases.BaseQuestionController;
import com.dak.models.OptionModel;
import com.dak.models.QuestionModel;
import com.dak.views.MultipleChoiceView;

import java.awt.event.ActionListener;
import java.util.List;

public class MultipleChoiceController extends BaseQuestionController<MultipleChoiceView> {
    public MultipleChoiceController(QuestionModel model, List<OptionModel> options, MultipleChoiceView view) {
        super(model, options, view);

        ActionListener actionListener = createComponentActionListener();

        view.getChoiceOne().addActionListener(actionListener);
        view.getChoiceTwo().addActionListener(actionListener);
        view.getChoiceThree().addActionListener(actionListener);
        view.getChoiceFour().addActionListener(actionListener);
    }
}