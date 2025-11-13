package com.dak.composers;

import com.dak.controllers.CategoryItemController;
import com.dak.controllers.QuizItemController;
import com.dak.db.Database;
import com.dak.db.tables.CategoryTable;
import com.dak.db.tables.QuizCategoryTable;
import com.dak.events.mediators.PlayQuizPageControllerMediator;
import com.dak.models.CategoryModel;
import com.dak.models.QuizModel;
import com.dak.views.*;
import com.dak.views.utils.ImageLoader;
import com.dak.views.viewModels.*;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomePageComposer {
    @Contract(" -> new")
    public static @NotNull HomePageView createHomePage() {
        NewReleaseSectionView newReleaseSectionView;

        try {
            newReleaseSectionView = newReleaseSection();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        HomePageViewModel homePageViewModel = new HomePageViewModel(createCategorySection(), newReleaseSectionView);
        return new HomePageView(homePageViewModel);
    }

    @Contract(" -> new")
    public static @NotNull CategorySectionView createCategorySection() {
        SectionHeaderViewModel sectionHeaderViewModel = new SectionHeaderViewModel("Categories");

        List<CategoryItemView> categoryItemViews = CategoryModel
                .findAll()
                .stream()
                .map(model -> {
                    CategoryItemViewModel viewModel = new CategoryItemViewModel(ImageLoader.load(model.getImage()));
                    CategoryItemView view = new CategoryItemView(viewModel);
                    new CategoryItemController(model, view);
                    return view;
                })
                .toList();

        CategorySectionViewModel categorySectionViewModel = new CategorySectionViewModel(categoryItemViews.toArray(CategoryItemView[]::new));
        return new CategorySectionView(sectionHeaderViewModel, categorySectionViewModel);
    }

    public static @NotNull NewReleaseSectionView newReleaseSection() throws SQLException {
        List<QuizModel> quizModels = QuizModel.findAll();
        HashMap<String, List<String>> quizCategoryImagesMap = new HashMap<>();

        try (Connection conn = Database.getConnection()) {
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("CREATE TEMPORARY TABLE temp_quiz_id (quiz_id CHAR(36));");
            }

            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO temp_quiz_id (quiz_id) VALUES (?)")) {
                for (QuizModel quizModel : quizModels) {
                    ps.setString(1, quizModel.getId().toString());
                    ps.addBatch();
                }

                ps.executeBatch();
            }

            String querySql = String.format(
                    """
                    SELECT tqi.quiz_id, c.%s
                    FROM temp_quiz_id tqi
                    LEFT JOIN %s qc ON qc.%s = tqi.quiz_id
                    LEFT JOIN %s c ON c.%s = qc.%s;
                    """,
                    CategoryTable.IMAGE,
                    QuizCategoryTable.TABLE_NAME,
                    QuizCategoryTable.QUIZ_ID,
                    CategoryTable.TABLE_NAME,
                    CategoryTable.ID,
                    QuizCategoryTable.CATEGORY_ID
            );

            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(querySql)) {
                while (rs.next()) {
                    String quizId = rs.getString("quiz_id");
                    String image = rs.getString(CategoryTable.IMAGE);
                    quizCategoryImagesMap.computeIfAbsent(quizId, (_) -> new ArrayList<>()).add(image);
                }
            }
        }

        PlayQuizPageControllerMediator playQuizPageControllerMediator = new PlayQuizPageControllerMediator();
        List<QuizItemView> quizItemViews = new ArrayList<>();

        quizModels.forEach(model -> {
            QuizItemViewModel quizItemViewModel = new QuizItemViewModel(
                    model.getTitle(),
                    model.getCreator(),
                    quizCategoryImagesMap.get(model.getId().toString()).toArray(String[]::new)
            );

            QuizItemView quizItemView = new QuizItemView(quizItemViewModel);
            QuizItemController quizItemController = new QuizItemController(model, quizItemView);

            quizItemController.addSubscriber(playQuizPageControllerMediator);
            quizItemViews.add(quizItemView);
        });

        SectionHeaderViewModel sectionHeaderViewModel = new SectionHeaderViewModel("New Release");
        QuizGridViewModel quizGridViewModel = new QuizGridViewModel(quizItemViews.toArray(QuizItemView[]::new));
        QuizGridView quizGridView = new QuizGridView(quizGridViewModel);
        NewReleaseSectionViewModel newReleaseSectionViewModel = new NewReleaseSectionViewModel(quizGridView);

        return new NewReleaseSectionView(sectionHeaderViewModel, newReleaseSectionViewModel);
    }
}
