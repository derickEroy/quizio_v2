package com.dak.composers;

import com.dak.controllers.CategoryItemController;
import com.dak.controllers.QuizItemController;
import com.dak.events.mediators.CategoryItemControllerMediator;
import com.dak.events.mediators.PlayQuizPageControllerMediator;
import com.dak.models.CategoryModel;
import com.dak.models.QuizCategoryModel;
import com.dak.models.QuizModel;
import com.dak.views.*;
import com.dak.views.utils.ImageLoader;
import com.dak.views.viewModels.*;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class HomePageComposer {
    private static final List<QuizModel> quizModels = QuizModel.findAll();
    private static final Map<QuizModel, List<CategoryModel>> quizCategoryMap = new HashMap<>();

    @Contract(" -> new")
    public static @NotNull HomePageView createHomePage() {
        loadQuizCategory();

        HomePageViewModel homePageViewModel = new HomePageViewModel(createCategorySection(), createNewReleaseSection());

        return new HomePageView(homePageViewModel);
    }

    @Contract(" -> new")
    private static @NotNull CategorySectionView createCategorySection() {
        CategoryItemControllerMediator mediator = new CategoryItemControllerMediator(quizCategoryMap);

        List<CategoryItemView> views = CategoryModel.findAll().stream()
                .map(model -> {
                    CategoryItemViewModel categoryItemViewModel = new CategoryItemViewModel(ImageLoader.load(model.getImage()));
                    CategoryItemView categoryItemView = new CategoryItemView(categoryItemViewModel);
                    CategoryItemController categoryItemController = new CategoryItemController(model, categoryItemView);
                    categoryItemController.addSubscriber(mediator);

                    return categoryItemView;
                })
                .toList();

        SectionHeaderViewModel sectionHeaderViewModel = new SectionHeaderViewModel("Categories");
        CategorySectionViewModel categorySectionViewModel = new CategorySectionViewModel(views.toArray(CategoryItemView[]::new));

        return new CategorySectionView(sectionHeaderViewModel, categorySectionViewModel);
    }

    private static @NotNull NewReleaseSectionView createNewReleaseSection() {
        PlayQuizPageControllerMediator mediator = new PlayQuizPageControllerMediator();
        List<QuizItemView> quizItemViews = new ArrayList<>();

        quizModels.forEach(model -> {
            List<CategoryModel> categoryModels = quizCategoryMap.getOrDefault(model, List.of());

            QuizItemViewModel quizItemViewModel = new QuizItemViewModel(
                    model.getTitle(),
                    model.getCreator(),
                    categoryModels.stream().map(CategoryModel::getImage).toArray(String[]::new)
            );

            QuizItemView quizItemView = new QuizItemView(quizItemViewModel);
            QuizItemController quizItemController = new QuizItemController(model, quizItemView);
            quizItemController.addSubscriber(mediator);

            quizItemViews.add(quizItemView);
        });

        SectionHeaderViewModel sectionHeaderViewModel = new SectionHeaderViewModel("New Release");
        QuizGridView quizGridView = new QuizGridView(new QuizGridViewModel(quizItemViews.toArray(QuizItemView[]::new)));
        NewReleaseSectionViewModel newReleaseSectionViewModel = new NewReleaseSectionViewModel(quizGridView);

        return new NewReleaseSectionView(sectionHeaderViewModel, newReleaseSectionViewModel);
    }

    private static void loadQuizCategory() {
        Map<String, CategoryModel> categoryById = CategoryModel
                .findAll()
                .stream()
                .collect(Collectors.toMap(
                        c -> c.getId().toString(),
                        c -> c)
                );

        for (QuizModel quizModel : quizModels) {
            List<QuizCategoryModel> quizCategoryModels = QuizCategoryModel.findManyByQuizId(quizModel.getId().toString());
            List<CategoryModel> categoryModels = quizCategoryModels.stream()
                    .map(qc -> Objects.requireNonNull(
                            categoryById.get(qc.getCategoryId().toString()),
                            () -> "Category not found for QuizCategoryModel with categoryId: " + qc.getCategoryId()
                    ))
                    .toList();

            quizCategoryMap.put(quizModel, categoryModels);
        }
    }
}
