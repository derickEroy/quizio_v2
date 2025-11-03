package com.dak.views.viewModels;

import com.dak.bases.AbstractQuestionInputPanel;

public record QuestionViewModel(
    String text,
    AbstractQuestionInputPanel questionInputView
) {}
