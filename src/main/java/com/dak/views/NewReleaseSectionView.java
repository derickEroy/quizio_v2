package com.dak.views;

import com.dak.views.viewModels.NewReleaseSectionViewModel;
import com.dak.views.viewModels.SectionHeaderViewModel;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class NewReleaseSectionView extends JPanel {
    private final SectionHeaderViewModel sectionHeaderViewModel;

    public NewReleaseSectionView(SectionHeaderViewModel sectionHeaderViewModel, @NotNull NewReleaseSectionViewModel newReleaseSectionViewModel) {
        this.sectionHeaderViewModel = sectionHeaderViewModel;

        setOpaque(false);
        setLayout(new BorderLayout());

        add(createHeader(), BorderLayout.NORTH);
        add(newReleaseSectionViewModel.quizGridView(), BorderLayout.CENTER);
    }

    private @NotNull SectionHeaderView createHeader() {
        return new SectionHeaderView(sectionHeaderViewModel);
    }
}
