package com.dak.views;

import com.dak.views.viewModels.QuizCategoryPageViewModel;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class QuizCategoryPageView extends JPanel {
    public QuizCategoryPageView(@NotNull QuizCategoryPageViewModel viewModel) {
        setOpaque(false);
        setLayout(new BorderLayout());

        add(viewModel.filterByCategorySectionView(), BorderLayout.NORTH);
    }
}
