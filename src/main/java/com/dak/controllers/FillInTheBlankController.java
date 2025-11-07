package com.dak.controllers;

import com.dak.bases.BaseQuestionController;
import com.dak.models.OptionModel;
import com.dak.models.QuestionModel;
import com.dak.views.FillInTheBlankView;

import java.util.List;

public class FillInTheBlankController extends BaseQuestionController<FillInTheBlankView> {
    public FillInTheBlankController(QuestionModel model, List<OptionModel> options, FillInTheBlankView view) {
        super(model, options, view);

        view.getTextField().getDocument().addDocumentListener(createComponentDocumentListener());
    }
}