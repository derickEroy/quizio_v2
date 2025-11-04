package com.dak.controllers;

import com.dak.events.subscribers.QuestionSubscriber;
import com.dak.events.subscribers.QuizNavigationSubscriber;
import com.dak.models.OptionModel;
import com.dak.models.QuestionModel;
import com.dak.states.QuizNavigationState;
import com.dak.states.QuizSessionState;
import com.dak.views.*;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.List;

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
    public void onInput(String answer) {
        System.out.println(answer);
    }
}
