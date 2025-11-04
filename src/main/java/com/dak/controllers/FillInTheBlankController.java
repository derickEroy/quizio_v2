package com.dak.controllers;

import com.dak.bases.BaseQuestionController;
import com.dak.models.QuestionModel;
import com.dak.views.FillInTheBlankView;
import org.jetbrains.annotations.NotNull;

public class FillInTheBlankController extends BaseQuestionController<FillInTheBlankView> {
    public FillInTheBlankController(QuestionModel model, FillInTheBlankView view) {
        super(model, view);

        view.getTextField().getDocument().addDocumentListener(createComponentDocumentListener());
    }
}