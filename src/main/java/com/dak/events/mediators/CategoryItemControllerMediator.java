package com.dak.events.mediators;

import com.dak.Main;
import com.dak.composers.QuizCategoryPageComposer;
import com.dak.constants.AppConstants;
import com.dak.events.subscribers.CategoryItemSubscriber;
import com.dak.models.CategoryModel;
import com.dak.models.QuizModel;
import com.dak.views.QuizCategoryPageView;
import com.dak.views.QuizItemView;
import com.dak.views.viewModels.QuizItemViewModel;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class CategoryItemControllerMediator implements CategoryItemSubscriber {
    private final Map<QuizModel, List<CategoryModel>> quizCategoryMap;

    public CategoryItemControllerMediator(Map<QuizModel, List<CategoryModel>> quizCategoryMap) {
        this.quizCategoryMap = quizCategoryMap;
    }

    @Override
    public void onView(String categoryName) {
        List<QuizModel> quizModels = quizCategoryMap
                .entrySet()
                .stream()
                .filter(e -> e.getValue().stream()
                        .anyMatch(c -> c.getName().equals(categoryName)))
                .map(Map.Entry::getKey)
                .toList();

        List<QuizItemViewModel> quizItemViewModels = quizModels
                .stream()
                .map(q -> new QuizItemViewModel(
                        q.getTitle(),
                        q.getCreator(),
                        quizCategoryMap.get(q).stream().map(CategoryModel::getImage).toArray(String[]::new)
                ))
                .toList();

        List<QuizItemView> quizItemViews = quizItemViewModels
                .stream()
                .map(QuizItemView::new)
                .toList();

        QuizCategoryPageView quizCategoryPageView = QuizCategoryPageComposer.createQuizCategoryPage(categoryName, quizItemViews);

        Main.cardPanel.add(quizCategoryPageView, AppConstants.QUIZ_CATEGORY_PAGE);
        ((CardLayout) Main.cardPanel.getLayout()).show(Main.cardPanel, AppConstants.QUIZ_CATEGORY_PAGE);
    }
}
