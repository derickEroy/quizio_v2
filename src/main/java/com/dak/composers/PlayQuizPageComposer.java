package com.dak.composers;

import com.dak.bases.BaseQuestionController;
import com.dak.bases.BaseQuestionView;
import com.dak.constants.AppConstants;
import com.dak.controllers.*;
import com.dak.enums.QuestionType;
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

import java.util.*;

public class PlayQuizPageComposer {
    public static @NotNull PlayQuizPageView createPlayQuizPage(String quizId) {
        List<QuestionModel> questionModels = QuestionModel.findManyByQuizId(quizId);

        QuizNavigationState quizNavigationState = new QuizNavigationState(1, questionModels.size());

        QuizNavigationView quizNavigationView = new QuizNavigationView();
        QuizNavigationController quizNavigationController = new QuizNavigationController(quizNavigationView, quizNavigationState);

        PlayQuizPageViewModel playQuizPageViewModel = new PlayQuizPageViewModel(quizNavigationView, quizNavigationState);

        List<QuestionViewModel> questionViewModels = new ArrayList<>();
        List<BaseQuestionController<?>> questionControllers = new ArrayList<>();

        Map<QuestionModel, List<OptionModel>> questionOptionsMap = new HashMap<>();

        for (QuestionModel questionModel : questionModels) {
            BaseQuestionView questionView;
            BaseQuestionController<?> questionController;

            switch (questionModel.getType()) {
                case QuestionType.FILL_IN_THE_BLANK -> {
                    OptionModel optionModel = OptionModel.findOneByQuestionId(questionModel.getId());

                    FillInTheBlankView fillInTheBlankView = new FillInTheBlankView();
                    FillInTheBlankController fillInTheBlankController = new FillInTheBlankController(questionModel, Collections.singletonList(optionModel), fillInTheBlankView);

                    questionView = fillInTheBlankView;
                    questionController = fillInTheBlankController;

                    questionOptionsMap.put(questionModel, Collections.singletonList(optionModel));
                }
                case QuestionType.MULTIPLE_CHOICE -> {
                    List<OptionModel> optionModels = OptionModel.findManyByQuestionId(questionModel.getId());

                    String[] optionTexts = optionModels
                            .stream()
                            .map(OptionModel::getText)
                            .toArray(String[]::new);

                    MultipleChoiceViewModel multipleChoiceViewModel = new MultipleChoiceViewModel(optionTexts[0], optionTexts[1], optionTexts[2], optionTexts[3]);
                    MultipleChoiceView multipleChoiceView = new MultipleChoiceView(multipleChoiceViewModel);
                    MultipleChoiceController multipleChoiceController = new MultipleChoiceController(questionModel, optionModels, multipleChoiceView);

                    questionView = multipleChoiceView;
                    questionController = multipleChoiceController;

                    questionOptionsMap.put(questionModel, optionModels);
                }
                case QuestionType.MULTI_SELECT -> {
                    List<OptionModel> optionModels = OptionModel.findManyByQuestionId(questionModel.getId());

                    String[] optionTexts = optionModels
                            .stream()
                            .map(OptionModel::getText)
                            .toArray(String[]::new);

                    MultiSelectViewModel multiSelectViewModel = new MultiSelectViewModel(optionTexts[0], optionTexts[1], optionTexts[2], optionTexts[3]);
                    MultiSelectView multiSelectView = new MultiSelectView(multiSelectViewModel);
                    MultiSelectController multiSelectController = new MultiSelectController(questionModel, optionModels, multiSelectView);

                    questionView = multiSelectView;
                    questionController = multiSelectController;

                    questionOptionsMap.put(questionModel, optionModels);
                }
                case QuestionType.TRUE_OR_FALSE -> {
                    OptionModel optionModel = OptionModel.findOneByQuestionId(questionModel.getId());

                    TrueOrFalseView trueOrFalseView = new TrueOrFalseView();
                    TrueOrFalseController trueOrFalseController = new TrueOrFalseController(questionModel, Collections.singletonList(optionModel), trueOrFalseView);

                    questionView = trueOrFalseView;
                    questionController = trueOrFalseController;

                    questionOptionsMap.put(questionModel, Collections.singletonList(optionModel));
                }
                default -> throw new IllegalArgumentException("Unhandled question model type: " + questionModel.getType());
            }

            // TODO: Change underline length to answer length.
            String text = questionModel.getText().replace(AppConstants.QUESTION_BLANK_DELIMITER, "__________");

            QuestionViewModel questionViewModel = new QuestionViewModel(text, questionView);

            questionViewModels.add(questionViewModel);
            questionControllers.add(questionController);
        }

        PlayQuizPageView playQuizPageView = new PlayQuizPageView(playQuizPageViewModel, questionViewModels.toArray(QuestionViewModel[]::new));
        QuizSessionState quizSessionState = new QuizSessionState(questionOptionsMap);
        PlayQuizPageController playQuizPageController = new PlayQuizPageController(playQuizPageView, quizSessionState, questionControllers);

        quizNavigationController.addSubscriber(playQuizPageController);

        for (BaseQuestionController<?> questionController : questionControllers) {
            questionController.addSubscriber(playQuizPageController);
        }

        return playQuizPageView;
    }
}
