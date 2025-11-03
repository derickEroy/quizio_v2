package com.dak.controllers;

import com.dak.bases.AbstractQuestionEventPublisher;
import com.dak.models.OptionModel;
import com.dak.models.QuestionModel;
import com.dak.views.MultipleChoiceView;
import org.jetbrains.annotations.NotNull;

import java.awt.event.ActionListener;
import java.util.List;

public class MultipleChoiceController extends AbstractQuestionEventPublisher {
    private final MultipleChoiceView view;

    public MultipleChoiceController(QuestionModel model, List<OptionModel> optionModels, @NotNull MultipleChoiceView view) {
        super(model, optionModels);
        this.view = view;

        ActionListener actionListener = createComponentActionListener();

        view.getChoiceOne().addActionListener(actionListener);
        view.getChoiceTwo().addActionListener(actionListener);
        view.getChoiceThree().addActionListener(actionListener);
        view.getChoiceFour().addActionListener(actionListener);
    }

    public MultipleChoiceView getView() {
        return view;
    }
}