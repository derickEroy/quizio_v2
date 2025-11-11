package com.dak.composers;

import com.dak.bases.BaseQuestionController;
import com.dak.bases.BaseQuestionView;
import com.dak.constants.AppConstants;
import com.dak.controllers.*;
import com.dak.enums.QuestionType;
import com.dak.models.OptionModel;
import com.dak.models.QuestionModel;
import com.dak.dtos.QuizNavigationDTO;
import com.dak.states.QuizSessionState;
import com.dak.views.*;
import com.dak.views.viewModels.MultiSelectViewModel;
import com.dak.views.viewModels.MultipleChoiceViewModel;
import com.dak.views.viewModels.PlayQuizPageViewModel;
import com.dak.views.viewModels.QuestionViewModel;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class PlayQuizPageComposer {
    private record QuestionComponents<TView extends BaseQuestionView, TController extends BaseQuestionController<?>>(
            TView view,
            TController controller,
            List<OptionModel> optionModels
    ) {}

    public static @NotNull PlayQuizPageView createPlayQuizPage(String quizId) {
        List<QuestionModel> questionModels = QuestionModel.findManyByQuizId(quizId);

        QuizNavigationDTO quizNavigationDTO = new QuizNavigationDTO(1, questionModels.size());
        QuizNavigationView quizNavigationView = new QuizNavigationView();
        QuizNavigationController quizNavigationController = new QuizNavigationController(quizNavigationView, quizNavigationDTO);

        List<QuestionViewModel> questionViewModels = new ArrayList<>();
        Map<QuestionModel, List<OptionModel>> questionOptionsMap = new HashMap<>();

        QuizSessionState quizSessionState = new QuizSessionState(questionOptionsMap);

        for (QuestionModel questionModel : questionModels) {
            BaseQuestionView questionView;
            BaseQuestionController<?> questionController;

            switch (questionModel.getType()) {
                case QuestionType.FILL_IN_THE_BLANK -> {
                    QuestionComponents<FillInTheBlankView, FillInTheBlankController> components = createFillInTheBlankComponents(questionModel);
                    questionView = components.view();
                    questionController = components.controller();
                    questionOptionsMap.put(questionModel, components.optionModels());
                }
                case QuestionType.MULTIPLE_CHOICE -> {
                    QuestionComponents<MultipleChoiceView, MultipleChoiceController> components = createMultipleChoiceComponents(questionModel);
                    questionView = components.view();
                    questionController = components.controller();
                    questionOptionsMap.put(questionModel, components.optionModels());
                }
                case QuestionType.MULTI_SELECT -> {
                    QuestionComponents<MultiSelectView, MultiSelectController> components = createMultiSelectComponents(questionModel);
                    questionView = components.view();
                    questionController = components.controller();
                    questionOptionsMap.put(questionModel, components.optionModels());
                }
                case QuestionType.TRUE_OR_FALSE -> {
                    QuestionComponents<TrueOrFalseView, TrueOrFalseController> components = createTrueOrFalseComponents(questionModel);
                    questionView = components.view();
                    questionController = components.controller();
                    questionOptionsMap.put(questionModel, components.optionModels());
                }
                default -> throw new IllegalArgumentException("Unhandled question model type: " + questionModel.getType());
            }

            String text = questionModel.getText().replace(AppConstants.QUESTION_BLANK_DELIMITER, "__________");
            QuestionViewModel questionViewModel = new QuestionViewModel(text, questionView);

            questionViewModels.add(questionViewModel);
            quizSessionState.addSubscriber(questionController);
            questionController.addSubscriber(quizSessionState);
        }

        quizNavigationController.addSubscriber(quizSessionState);

        PlayQuizPageViewModel playQuizPageViewModel = new PlayQuizPageViewModel(quizNavigationView, quizNavigationDTO);
        PlayQuizPageView playQuizPageView = new PlayQuizPageView(playQuizPageViewModel, questionViewModels.toArray(QuestionViewModel[]::new));
        PlayQuizPageController playQuizPageController = new PlayQuizPageController(playQuizPageView);

        quizNavigationController.addSubscriber(playQuizPageController);

        return playQuizPageView;
    }

    private static @NotNull QuestionComponents<FillInTheBlankView, FillInTheBlankController> createFillInTheBlankComponents(@NotNull QuestionModel questionModel) {
        OptionModel optionModel = OptionModel.findOneByQuestionId(questionModel.getId());

        FillInTheBlankView view = new FillInTheBlankView();
        FillInTheBlankController controller = new FillInTheBlankController(questionModel, Collections.singletonList(optionModel), view);

        return new QuestionComponents<>(view, controller, Collections.singletonList(optionModel));
    }

    private static @NotNull QuestionComponents<MultipleChoiceView, MultipleChoiceController> createMultipleChoiceComponents(@NotNull QuestionModel questionModel) {
        List<OptionModel> optionModels = OptionModel.findManyByQuestionId(questionModel.getId());

        String[] optionTexts = optionModels
                .stream()
                .map(OptionModel::getText)
                .toArray(String[]::new);

        MultipleChoiceViewModel viewModel = new MultipleChoiceViewModel(optionTexts[0], optionTexts[1], optionTexts[2], optionTexts[3]);
        MultipleChoiceView view = new MultipleChoiceView(viewModel);
        MultipleChoiceController controller = new MultipleChoiceController(questionModel, optionModels, view);

        return new QuestionComponents<>(view, controller, optionModels);
    }

    @Contract("_ -> new")
    private static @NotNull QuestionComponents<MultiSelectView, MultiSelectController> createMultiSelectComponents(@NotNull QuestionModel questionModel) {
        List<OptionModel> optionModels = OptionModel.findManyByQuestionId(questionModel.getId());

        String[] optionTexts = optionModels
                .stream()
                .map(OptionModel::getText)
                .toArray(String[]::new);

        MultiSelectViewModel viewModel = new MultiSelectViewModel(optionTexts[0], optionTexts[1], optionTexts[2], optionTexts[3]);
        MultiSelectView view = new MultiSelectView(viewModel);
        MultiSelectController controller = new MultiSelectController(questionModel, optionModels, view);

        return new QuestionComponents<>(view, controller, optionModels);
    }

    @Contract("_ -> new")
    private static @NotNull QuestionComponents<TrueOrFalseView, TrueOrFalseController> createTrueOrFalseComponents(@NotNull QuestionModel questionModel) {
        OptionModel optionModel = OptionModel.findOneByQuestionId(questionModel.getId());

        TrueOrFalseView view = new TrueOrFalseView();
        TrueOrFalseController controller = new TrueOrFalseController(questionModel, Collections.singletonList(optionModel), view);

        return new QuestionComponents<>(view, controller, Collections.singletonList(optionModel));
    }
}
