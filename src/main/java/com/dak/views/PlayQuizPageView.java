package com.dak.views;

import com.dak.views.utils.ColorSet;
import com.dak.views.utils.SizeSet;
import com.dak.views.viewModels.PlayQuizPageViewModel;
import com.dak.views.viewModels.QuestionViewModel;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class PlayQuizPageView extends JPanel {
    private final PlayQuizPageViewModel playQuizPageViewModel;
    private final QuestionViewModel[] questionViewModels;

    private final JPanel cardPanel;

    public PlayQuizPageView(@NotNull PlayQuizPageViewModel questionPageViewModel, @NotNull QuestionViewModel @NotNull [] questionViewModels) {
        this.questionViewModels = questionViewModels;
        this.playQuizPageViewModel = questionPageViewModel;

        cardPanel = createCard();

        setOpaque(false);
        setLayout(new GridBagLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(cardPanel, BorderLayout.CENTER);
        mainPanel.add(questionPageViewModel.quizNavigationView(), BorderLayout.SOUTH);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        add(mainPanel, gbc);
    }

    public JPanel getCardPanel() {
        return cardPanel;
    }

    private @NotNull JPanel createCard() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new CardLayout() {
            @Override
            public Dimension preferredLayoutSize(Container parent) {
                Component current = null;

                for (Component comp : parent.getComponents()) {
                    if (comp.isVisible()) {
                        current = comp;
                    }
                }

                if (current != null) {
                    return new Dimension(current.getMaximumSize().width, current.getPreferredSize().height);
                }

                return super.preferredLayoutSize(parent);
            }
        });

        for (int i = 0; i < questionViewModels.length; i++) {
            JLabel pageLabel = createPage();
            pageLabel.setText(i + 1 + "/" + playQuizPageViewModel.quizNavigationState().maxPage);

            panel.add(createQuestion(questionViewModels[i], pageLabel), String.valueOf(i));
        }

        return panel;
    }

    private @NotNull JPanel createQuestion(@NotNull QuestionViewModel questionViewModel, JLabel pageLabel) {
        int minWidth = 300;
        int maxWidth = 700;
        int width;

        JLabel title = createTitle(questionViewModel.text());
        int titleWidth = title.getPreferredSize().width;

        if (titleWidth < minWidth) {
            width = minWidth;
        } else if (titleWidth <= 700) {
            width = title.getPreferredSize().width;
        } else {
            width = maxWidth;
        }

        questionViewModel.questionView().setBorder(BorderFactory.createEmptyBorder(SizeSet.M, 0, SizeSet.M, 0));

        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.add(pageLabel);
        topPanel.add(Box.createVerticalStrut(SizeSet._3XS));
        topPanel.add(title);

        JPanel questionPanel = new JPanel();
        questionPanel.setOpaque(false);
        questionPanel.setLayout(new BorderLayout());
        questionPanel.add(topPanel, BorderLayout.NORTH);
        questionPanel.add(questionViewModel.questionView(), BorderLayout.CENTER);
        questionPanel.setMaximumSize(new Dimension(width, questionPanel.getPreferredSize().height));

        return questionPanel;
    }

    private @NotNull JLabel createPage() {
        JLabel label = new JLabel("1/10", JLabel.CENTER);
        label.setMaximumSize(new Dimension(Integer.MAX_VALUE, label.getPreferredSize().height));

        return label;
    }

    private @NotNull JLabel createTitle(String text) {
        String wrappedText = String.format(
            "<html><body style='width: 100%%; text-align: center'>%s</body></html>",
            text
        );

        JLabel label = new JLabel(wrappedText, JLabel.CENTER);
        label.setForeground(ColorSet.getPrimaryForeground());
        label.setFont(label.getFont().deriveFont(Font.BOLD, (float) SizeSet.M));

        return label;
    }
}
