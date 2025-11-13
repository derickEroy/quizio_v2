package com.dak.composers;

import com.dak.views.*;
import com.dak.views.viewModels.FilterByCategorySectionViewModel;
import com.dak.views.viewModels.QuizCategoryPageViewModel;
import com.dak.views.viewModels.QuizGridViewModel;
import com.dak.views.viewModels.SectionHeaderViewModel;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class QuizCategoryPageComposer {
    public static @NotNull QuizCategoryPageView createQuizCategoryPage(String categoryName, @NotNull List<QuizItemView> quizItemViews) {
        SectionHeaderViewModel sectionHeaderViewModel = new SectionHeaderViewModel("Filtering by " + categoryName);
        SectionHeaderView sectionHeaderView = new SectionHeaderView(sectionHeaderViewModel);

        QuizGridViewModel quizGridViewModel = new QuizGridViewModel(quizItemViews.toArray(QuizItemView[]::new));
        QuizGridView quizGridView = new QuizGridView(quizGridViewModel);

        FilterByCategorySectionViewModel filterByCategorySectionViewModel = new FilterByCategorySectionViewModel(sectionHeaderView, quizGridView);
        FilterByCategorySectionView filterByCategorySectionView = new FilterByCategorySectionView(filterByCategorySectionViewModel);
        QuizCategoryPageViewModel quizCategoryPageViewModel = new QuizCategoryPageViewModel(filterByCategorySectionView);

        return new QuizCategoryPageView(quizCategoryPageViewModel);
    }
}
