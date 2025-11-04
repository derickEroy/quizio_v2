package com.dak.events.subscribers;

import com.dak.events.EventSubscriber;

public interface QuestionSubscriber extends EventSubscriber {
    default void onInput(String answer) {}
}
