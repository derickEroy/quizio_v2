package com.dak.events.subscribers;

import com.dak.events.EventSubscriber;

public interface QuestionInputSubscriber extends EventSubscriber {
    default void onInput() {}
}
