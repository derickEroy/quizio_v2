package com.dak.controllers;

import com.dak.bases.BaseQuestionController;
import com.dak.models.OptionModel;
import com.dak.models.QuestionModel;
import com.dak.views.MultiSelectView;

import java.awt.event.ActionListener;
import java.util.List;

public class MultiSelectController extends BaseQuestionController<MultiSelectView> {
    public MultiSelectController(QuestionModel model, List<OptionModel> options, MultiSelectView view) {
        super(model, options, view);

        ActionListener actionListener = createComponentActionListener();

        view.getOptionOne().addActionListener(actionListener);
        view.getOptionTwo().addActionListener(actionListener);
        view.getOptionThree().addActionListener(actionListener);
        view.getOptionFour().addActionListener(actionListener);
    }
}