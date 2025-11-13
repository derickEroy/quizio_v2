package com.dak;

import java.awt.*;

import javax.swing.*;

import com.dak.views.QuizResultPageView;
import com.dak.views.utils.ColorSet;
import com.dak.views.utils.SizeSet;
import com.dak.views.viewModels.QuizResultPageViewModel;

public class Main {
    public static final JFrame frame = new JFrame();
    public static final JPanel cardPanel = new JPanel(new CardLayout());

    public static void main(String[] args) {
        JPanel contentPane = (JPanel) frame.getContentPane();
        contentPane.setBackground(ColorSet.getPrimaryBackground());
        contentPane.setBorder(BorderFactory.createEmptyBorder(SizeSet.XS, SizeSet.XS, SizeSet.XS, SizeSet.XS));

        cardPanel.setOpaque(false);
    //    cardPanel.add(HomePageComposer.createHomePage(), AppConstants.HOME_PAGE);
        cardPanel.add(createTestView(), "test");

        contentPane.add(cardPanel);

        frame.setTitle("Quizio");
        frame.setSize(new Dimension(1200, 800));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static JPanel createTestView() {
        QuizResultPageViewModel quizResultPageViewModel = new QuizResultPageViewModel("Name of quiz taken here", 17, 20, 85);

        return new QuizResultPageView(quizResultPageViewModel);
    }
}
