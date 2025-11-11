package com.dak.states;

import com.dak.enums.QuestionType;
import com.dak.events.EventPublisher;
import com.dak.events.enums.QuizSessionEvent;
import com.dak.events.subscribers.QuestionSubscriber;
import com.dak.events.subscribers.QuizNavigationSubscriber;
import com.dak.events.subscribers.QuizSessionSubscriber;
import com.dak.models.OptionModel;
import com.dak.models.QuestionModel;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.dak.events.enums.QuizSessionEvent.COMPLETE;

public class QuizSessionState extends EventPublisher<QuizSessionSubscriber, QuizSessionEvent> implements QuizNavigationSubscriber, QuestionSubscriber {
    private final Map<QuestionModel, List<OptionModel>> optionsMap;
    private final Map<QuestionModel, Map<OptionModel, Boolean>> answersMap;

    public QuizSessionState(Map<QuestionModel, List<OptionModel>> optionsMap) {
        this.optionsMap = optionsMap;
        this.answersMap = new HashMap<>();
    }

    @Override
    public void onInput(String answer, @NotNull QuestionModel model) {
        List<OptionModel> optionModels = optionsMap.get(model);

        switch (model.getType()) {
            case FILL_IN_THE_BLANK -> {
                OptionModel correctOption = optionModels.getFirst();
                answersMap.put(model, Map.of(correctOption, correctOption.getText().equals(answer)));
            }
            case MULTIPLE_CHOICE -> {
                OptionModel correctOption = optionModels
                        .stream()
                        .filter(OptionModel::isCorrect)
                        .findFirst()
                        .orElse(null);

                if (correctOption == null) {
                    throw new IllegalStateException(QuestionType.MULTIPLE_CHOICE + " does not have an answer");
                }

                answersMap.put(model, Map.of(correctOption, correctOption.getText().equals(answer)));
            }
            case MULTI_SELECT -> {
                List<OptionModel> correctOptions = optionModels
                        .stream()
                        .filter(OptionModel::isCorrect)
                        .toList();

                if (correctOptions.isEmpty()) {
                    throw new IllegalStateException(QuestionType.MULTI_SELECT + " does not have an answer");
                }

                List<String> answers = Arrays
                        .stream(answer.substring(1, answer.length() - 1).split(", "))
                        .map(String::trim)
                        .toList();

                Map<OptionModel, Boolean> answerMap = optionModels.stream().collect(Collectors.toMap(
                        opt -> opt,
                        opt -> answers.contains(opt.getText()) == opt.isCorrect()
                ));

                answersMap.put(model, answerMap);
            }
            case TRUE_OR_FALSE -> {
                OptionModel correctOption = optionModels.getFirst();
                answersMap.put(model, Map.of(correctOption, correctOption.isCorrect() == Boolean.parseBoolean(answer.toLowerCase())));
            }
            default -> throw new IllegalArgumentException("Unhandled question type: " + model.getType());
        }
    }

    @Override
    public void onFinish() {
        notifySubscribers(COMPLETE);
    }

    @Override
    protected void notifyHandler(QuizSessionSubscriber subscriber, QuizSessionEvent event) {
        if (event == COMPLETE) {
            subscriber.onEnd(answersMap);
        } else {
            throw new IllegalArgumentException("Unhandled event case: " + event);
        }
    }
}
