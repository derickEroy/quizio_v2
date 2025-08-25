package com.dak.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.*;

import org.jetbrains.annotations.NotNull;

import com.dak.models.CategoryModel;
import com.dak.views.components.SectionHeader;
import com.dak.views.utils.*;

public class CategorySection extends JPanel {
    private static final int BUTTON_WIDTH = 64;
    private static final int BUTTON_HEIGHT = 64;

    public CategorySection() {
        this.setLayout(new BorderLayout());

        SectionHeader header = new SectionHeader("Categories");
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(null);
        scrollPane.setViewportView(buttonPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        this.add(header, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        
        List<CategoryModel> categories = CategoryModel.findAll();
        for (CategoryModel category : categories) {
            buttonPanel.add(Box.createHorizontalStrut(SizeSet.XS / 2));
            buttonPanel.add(createCategoryButton(category));
            buttonPanel.add(Box.createHorizontalStrut(SizeSet.XS / 2));
        }
    }

    private @NotNull JButton createCategoryButton(CategoryModel category) {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        button.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        button.setOpaque(true);
        button.setBackground(ColorSet.SECONDARY_BACKGROUND);
        button.setIcon(ImageSet.getIconFromSVG(category.getImage() + ".svg", 50, 50));
        button.setToolTipText(category.getName());
        button.setFocusPainted(false);
        return button;
    }
}
