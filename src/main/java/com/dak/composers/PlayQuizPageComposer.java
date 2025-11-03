package com.dak.composers;

import com.dak.bases.AbstractQuestionEventPublisher;
import com.dak.constants.AppConstants;
import com.dak.bases.AbstractQuestionInputPanel;
import com.dak.controllers.*;
import com.dak.models.OptionModel;
import com.dak.models.QuestionModel;
import com.dak.states.QuizNavigationState;
import com.dak.states.QuizSessionState;
import com.dak.views.*;
import com.dak.views.viewModels.MultiSelectViewModel;
import com.dak.views.viewModels.MultipleChoiceViewModel;
import com.dak.views.viewModels.PlayQuizPageViewModel;
import com.dak.views.viewModels.QuestionViewModel;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PlayQuizPageComposer {
    public static @NotNull PlayQuizPageView createPlayQuizPage(String quizId) {
        List<QuestionModel> questionModels = QuestionModel.findManyByQuizId(quizId);

        QuizNavigationState quizNavigationState = new QuizNavigationState(1, questionModels.size());
        QuizNavigationView quizNavigationView = new QuizNavigationView();
        QuizNavigationController quizNavigationController = new QuizNavigationController(quizNavigationView, quizNavigationState);
        PlayQuizPageViewModel playQuizPageViewModel = new PlayQuizPageViewModel(quizNavigationView, quizNavigationState);

        List<QuestionViewModel> questionViewModels = new ArrayList<>();
        List<AbstractQuestionEventPublisher> questionControllers = new ArrayList<>();

        for (QuestionModel questionModel : questionModels) {
            AbstractQuestionInputPanel abstractQuestionInputPanel;
            AbstractQuestionEventPublisher abstractQuestionEventPublisher;

            switch (questionModel.getType()) {
                case QuestionModel.TYPE.FILL_IN_THE_BLANK -> {
                    FillInTheBlankView fillInTheBlankView = new FillInTheBlankView();
                    abstractQuestionInputPanel = fillInTheBlankView;
                    abstractQuestionEventPublisher = new FillInTheBlankController(questionModel, fillInTheBlankView);
                }
                case QuestionModel.TYPE.MULTIPLE_CHOICE -> {
                    List<OptionModel> optionModels = OptionModel.findManyByQuestionId(questionModel.getId());
                    String[] optionTexts = optionModels.stream().map(OptionModel::getText).toArray(String[]::new);

                    MultipleChoiceViewModel multipleChoiceViewModel = new MultipleChoiceViewModel(optionTexts[0], optionTexts[1], optionTexts[2], optionTexts[3]);
                    MultipleChoiceView multipleChoiceView = new MultipleChoiceView(multipleChoiceViewModel);

                    abstractQuestionInputPanel = multipleChoiceView;
                    abstractQuestionEventPublisher = new MultipleChoiceController(questionModel, optionModels, multipleChoiceView);
                }
                case QuestionModel.TYPE.MULTI_SELECT -> {
                    List<OptionModel> optionModels = OptionModel.findManyByQuestionId(questionModel.getId());
                    String[] optionTexts = optionModels.stream().map(OptionModel::getText).toArray(String[]::new);

                    MultiSelectViewModel multiSelectViewModel = new MultiSelectViewModel(optionTexts[0], optionTexts[1], optionTexts[2], optionTexts[3]);
                    MultiSelectView multiSelectView = new MultiSelectView(multiSelectViewModel);

                    abstractQuestionInputPanel = multiSelectView;
                    abstractQuestionEventPublisher = new MultiSelectController(questionModel, optionModels, multiSelectView);
                }
                case QuestionModel.TYPE.TRUE_OR_FALSE -> {
                    TrueOrFalseView trueOrFalseView = new TrueOrFalseView();
                    abstractQuestionInputPanel = trueOrFalseView;
                    abstractQuestionEventPublisher = new TrueOrFalseController(questionModel, trueOrFalseView);
                }
                default -> throw new IllegalArgumentException("Unhandled question model type: " + questionModel.getType());
            }

            String text = questionModel.getText().replace(AppConstants.QUESTION_BLANK_DELIMITER, "__________");
            QuestionViewModel questionViewModel = new QuestionViewModel(text, abstractQuestionInputPanel);
            questionViewModels.add(questionViewModel);
            questionControllers.add(abstractQuestionEventPublisher);
        }

        PlayQuizPageView playQuizPageView = new PlayQuizPageView(
                playQuizPageViewModel,
                questionViewModels.toArray(QuestionViewModel[]::new)
        );

        QuizSessionState quizSessionState = new QuizSessionState();
        PlayQuizPageController playQuizPageController = new PlayQuizPageController(questionModels, playQuizPageView, quizSessionState);

        quizNavigationController.addSubscriber(playQuizPageController);

        for (AbstractQuestionEventPublisher controller : questionControllers) {
            controller.addSubscriber(playQuizPageController);
        }

        return playQuizPageView;
    }
}
