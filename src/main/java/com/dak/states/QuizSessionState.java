package com.dak.states;

import com.dak.models.OptionModel;
import com.dak.models.QuestionModel;

import java.util.HashMap;
import java.util.Map;

public class QuizSessionState {
    public Map<QuestionModel, OptionModel> answersMap = new HashMap<>();
}
