package com.dak.views;

import java.awt.*;

import com.dak.views.components.UnderlineButton;
import com.dak.views.utils.ColorSet;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jetbrains.annotations.NotNull;

import com.dak.views.utils.SizeSet;
import com.dak.views.viewModels.QuizResultPageViewModel;

import javax.swing.BorderFactory;
import javax.swing.Box;

public class QuizResultPageView extends JPanel {

    private final QuizResultPageViewModel viewModel;

    public QuizResultPageView(@NotNull QuizResultPageViewModel viewModel) {
        this.setOpaque(false);
        this.viewModel = viewModel;
        this.setLayout(new GridBagLayout());

        final JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(createHeaderPanel(viewModel.quizName()));
        mainPanel.add(Box.createVerticalStrut(SizeSet.S));
        mainPanel.add(createContentPanel());
        mainPanel.add(Box.createVerticalStrut(SizeSet.S));
        mainPanel.add(createFooterPanel());

        this.add(mainPanel, gbc);
    }

    private @NotNull JPanel createHeaderPanel(String quizName) {
        // Creates the "Quiz Results" title along with the quiz name underneath.
        
        final JPanel panel = new JPanel();
        
        final JLabel title = new JLabel("Quiz Results");
        title.setFont(title.getFont().deriveFont(Font.BOLD, (float) SizeSet.XL));
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        final JLabel subtitle = new JLabel(quizName);
        subtitle.setFont(subtitle.getFont().deriveFont(Font.PLAIN));
        subtitle.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.add(title);
        panel.add(Box.createVerticalStrut(SizeSet.S));
        panel.add(subtitle);

        return panel;
    }
    
    private @NotNull JPanel createContentPanel() {
        // Creates the panel with two boxes: first box displays score, and second box displays percentage.
        
        final JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        
        panel.add(createContentPanelBox("Score", String.valueOf(viewModel.score()), "Out of " + viewModel.total() + " points"));
        panel.add(Box.createHorizontalStrut(SizeSet.S));
        panel.add(createContentPanelBox("Percentage", viewModel.percentage() + "%", " "));
        
        return panel;
    }
    
    private @NotNull JPanel createContentPanelBox(String topText, String mainText, String bottomText) {
        final int BOX_WIDTH = 160;
        final int BOX_HEIGHT = 120;

        final JPanel box = new JPanel();
        box.setBackground(ColorSet.getSecondaryBackground());
        box.setBorder(BorderFactory.createEmptyBorder(SizeSet.XS, SizeSet.XS, SizeSet.XS, SizeSet.XS));
        box.setPreferredSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));
        box.setLayout(new BorderLayout());

        final JLabel topLabel = new JLabel(topText, JLabel.CENTER);
        topLabel.setFont(topLabel.getFont().deriveFont(Font.BOLD));
        box.add(topLabel, BorderLayout.NORTH);

        final JLabel mainLabel = new JLabel(mainText, JLabel.CENTER);
        mainLabel.setFont(mainLabel.getFont().deriveFont(Font.PLAIN, SizeSet._4XL));
        box.add(mainLabel, BorderLayout.CENTER);

        final JLabel bottomLabel = new JLabel(bottomText, JLabel.CENTER);
        bottomLabel.setFont(bottomLabel.getFont().deriveFont(Font.PLAIN));
        box.add(bottomLabel, BorderLayout.SOUTH);

        return box;
    }
    
    private @NotNull JPanel createFooterPanel() {
        // Creates the buttons seen at the bottom.
        
        final JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BorderLayout());

        final JButton button = new UnderlineButton("Back to Home");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        final JButton button2 = new JButton("Review Results");
        button2.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panel.add(button, BorderLayout.WEST);
        panel.add(button2, BorderLayout.EAST);
        
        return panel;
    }
}
