package com.dak.views.viewModels;

import com.dak.bases.BaseQuestionView;

public record QuestionViewModel(
    String text,
    BaseQuestionView questionView
) {}
