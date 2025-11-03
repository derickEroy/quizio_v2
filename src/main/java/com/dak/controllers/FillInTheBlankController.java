package com.dak.controllers;

import com.dak.bases.AbstractQuestionEventPublisher;
import com.dak.models.QuestionModel;
import com.dak.views.FillInTheBlankView;
import org.jetbrains.annotations.NotNull;

public class FillInTheBlankController extends AbstractQuestionEventPublisher {
    private final FillInTheBlankView view;

    public FillInTheBlankController(QuestionModel model, @NotNull FillInTheBlankView view) {
        super(model, null);
        this.view = view;

        view.getTextField().getDocument().addDocumentListener(createComponentDocumentListener());
    }

    public FillInTheBlankView getView() {
        return view;
    }
}