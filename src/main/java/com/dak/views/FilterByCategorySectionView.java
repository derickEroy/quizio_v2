package com.dak.views;

import com.dak.views.viewModels.FilterByCategorySectionViewModel;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class FilterByCategorySectionView extends JPanel {
    public FilterByCategorySectionView(@NotNull FilterByCategorySectionViewModel quizByCategoryViewModel) {
        setOpaque(false);
        setLayout(new BorderLayout());

        add(quizByCategoryViewModel.sectionHeaderView(), BorderLayout.NORTH);
        add(quizByCategoryViewModel.quizGridView(), BorderLayout.CENTER);
    }
}
