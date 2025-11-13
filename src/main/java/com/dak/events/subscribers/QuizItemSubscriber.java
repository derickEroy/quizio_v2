package com.dak.events.subscribers;

import com.dak.events.EventSubscriber;

public interface QuizItemSubscriber extends EventSubscriber {
    default void onPlay(String quizId) {}
}
