package com.dak.controllers;

import com.dak.events.subscribers.QuizNavigationSubscriber;
import com.dak.dtos.QuizNavigationDTO;
import com.dak.views.*;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class PlayQuizPageController implements QuizNavigationSubscriber {
    private final PlayQuizPageView view;

    public PlayQuizPageController(PlayQuizPageView view) {
        this.view = view;
    }

    private void showCurrentPage(int currentPage) {
        CardLayout cardLayout = (CardLayout) view.getCardPanel().getLayout();
        cardLayout.show(view.getCardPanel(), String.valueOf(currentPage));
    }

    @Override
    public void onNext(@NotNull QuizNavigationDTO dto) {
        showCurrentPage(dto.currentPage);
    }

    @Override
    public void onPrevious(@NotNull QuizNavigationDTO dto) {
        showCurrentPage(dto.currentPage);
    }
}
