package com.dak.controllers;

import com.dak.enums.QuestionType;
import com.dak.events.subscribers.QuestionSubscriber;
import com.dak.events.subscribers.QuizNavigationSubscriber;
import com.dak.models.OptionModel;
import com.dak.models.QuestionModel;
import com.dak.states.QuizNavigationState;
import com.dak.states.QuizSessionState;
import com.dak.views.*;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class PlayQuizPageController implements QuizNavigationSubscriber, QuestionSubscriber {
    private final PlayQuizPageView view;
    private final QuizSessionState state;

    public PlayQuizPageController(List<QuestionModel> questionModels, PlayQuizPageView playQuizPageView, QuizSessionState quizSessionState) {
        this.questionModels = questionModels;
        this.playQuizPageView = playQuizPageView;
        this.quizSessionState = quizSessionState;
    }

    public List<QuestionModel> getQuestionModels() {
        return questionModels;
    }

    public PlayQuizPageView getPlayQuizPageView() {
        return playQuizPageView;
    }

    public QuizSessionState getQuizSessionState() {
        return quizSessionState;
    }

    private void showCurrentPage(int currentPage) {
        CardLayout cardLayout = (CardLayout) playQuizPageView.getCardPanel().getLayout();
        cardLayout.show(playQuizPageView.getCardPanel(), String.valueOf(currentPage - 1));
    }

    @Override
    public void onNext(@NotNull QuizNavigationState state) {
        showCurrentPage(state.currentPage);
    }

    @Override
    public void onPrevious(@NotNull QuizNavigationState state) {
        showCurrentPage(state.currentPage);
    }

    @Override
    public void onInput(String answer, @NotNull QuestionModel model) {
        List<OptionModel> optionModels = state.options.get(model);

        switch (model.getType()) {
            case FILL_IN_THE_BLANK -> {
                OptionModel correctOption = optionModels.getFirst();
                state.answers.put(model, Map.of(correctOption, correctOption.getText().equals(answer)));
            }
            case MULTIPLE_CHOICE -> {
                OptionModel correctOption = optionModels.stream().filter(OptionModel::isCorrect).findFirst().orElse(null);

                if (correctOption == null) {
                    throw new IllegalStateException(QuestionType.MULTIPLE_CHOICE + " does not have an answer");
                }

                state.answers.put(model, Map.of(correctOption, correctOption.getText().equals(answer)));
            }
            case MULTI_SELECT -> {
                List<OptionModel> correctOptions = optionModels.stream().filter(OptionModel::isCorrect).toList();

                if (correctOptions.isEmpty()) {
                    throw new IllegalStateException(QuestionType.MULTI_SELECT + " does not have an answer");
                }

                List<String> answers = Arrays.stream(answer.substring(1, answer.length() - 1)
                    .split(", "))
                    .map(String::trim)
                    .toList();

                Map<OptionModel, Boolean> answerMap = optionModels.stream().collect(Collectors.toMap(
                    opt -> opt,
                    opt -> answers.contains(opt.getText()) == opt.isCorrect()
                ));

                state.answers.put(model, answerMap);
            }
            case TRUE_OR_FALSE -> {
                OptionModel correctOption = optionModels.getFirst();
                state.answers.put(model, Map.of(correctOption, correctOption.isCorrect() == Boolean.parseBoolean(answer.toLowerCase())));
            }
            default -> throw new IllegalArgumentException("Unhandled question type: " + model.getType());
        }
    }
}
