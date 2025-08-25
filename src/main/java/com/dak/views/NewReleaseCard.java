package com.dak.views;

import com.dak.Main;
import com.dak.models.CategoryModel;
import com.dak.models.QuizCategoryModel;
import com.dak.models.QuizModel;
import com.dak.views.components.PrimaryButton;
import com.dak.views.utils.ColorSet;
import com.dak.views.utils.ImageSet;
import com.dak.views.utils.SizeSet;

import javax.swing.*;
import java.awt.*;

public class NewReleaseCard extends JPanel {
    public NewReleaseCard(QuizModel quiz) {
        final String title = quiz.getTitle();
        final String creator = quiz.getCreator();

        setLayout(new BorderLayout());
        setBackground(ColorSet.SECONDARY_BACKGROUND);
        setBorder(BorderFactory.createEmptyBorder(SizeSet.XS, SizeSet.XS, SizeSet.XS, SizeSet.XS));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD));
        
        JLabel creatorLabel = new JLabel("By " + creator);
        creatorLabel.setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 0));
        creatorLabel.setFont(titleLabel.getFont().deriveFont(Font.PLAIN, SizeSet.XS));
        
        JPanel leftPanel = new JPanel();
        leftPanel.setOpaque(false);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(titleLabel);
        leftPanel.add(creatorLabel);
        leftPanel.add(Box.createVerticalGlue());
        
        JPanel categoriesPanel = new JPanel();
        categoriesPanel.setOpaque(false);
        
        java.util.List<CategoryModel> categories = QuizCategoryModel.findCategoriesFromQuizId(quiz.getId().toString());
        for (CategoryModel category : categories) {
            JLabel icon = new JLabel(ImageSet.getIconFromSVG(category.getImage() + ".svg", SizeSet._3XL, SizeSet._3XL));
            icon.setToolTipText(category.getName());
            categoriesPanel.add(icon);
        }

        JButton button = new PrimaryButton("Take Quiz");
        button.setFont(button.getFont().deriveFont(Font.BOLD));
        button.addActionListener(e -> Main.switchPanel(new DummyPage(title)));
        button.setPreferredSize(new Dimension(button.getPreferredSize().width, SizeSet._3XL));

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        rightPanel.setOpaque(false);
        rightPanel.add(categoriesPanel);
        rightPanel.add(button);

        add(leftPanel);
        add(rightPanel, BorderLayout.EAST);
    }
}
