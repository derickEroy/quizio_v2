package com.dak.events.subscribers;

import com.dak.events.EventSubscriber;

public interface CategoryItemSubscriber extends EventSubscriber {
    default void onView(String categoryName) {}
}
