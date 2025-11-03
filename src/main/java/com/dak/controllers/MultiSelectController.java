package com.dak.controllers;

import com.dak.bases.AbstractQuestionEventPublisher;
import com.dak.models.OptionModel;
import com.dak.models.QuestionModel;
import com.dak.views.MultiSelectView;
import org.jetbrains.annotations.NotNull;

import java.awt.event.ActionListener;
import java.util.List;

public class MultiSelectController extends AbstractQuestionEventPublisher {
    private final MultiSelectView view;

    public MultiSelectController(QuestionModel model, List<OptionModel> optionModels, @NotNull MultiSelectView view) {
        super(model, optionModels);
        this.view = view;

        ActionListener actionListener = createComponentActionListener();

        view.getOptionOne().addActionListener(actionListener);
        view.getOptionTwo().addActionListener(actionListener);
        view.getOptionThree().addActionListener(actionListener);
        view.getOptionFour().addActionListener(actionListener);
    }

    public MultiSelectView getView() {
        return view;
    }
}