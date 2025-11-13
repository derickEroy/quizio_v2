package com.dak.controllers;

import com.dak.events.EventPublisher;
import com.dak.events.enums.CategoryItemEvent;
import com.dak.events.subscribers.CategoryItemSubscriber;
import com.dak.models.CategoryModel;
import com.dak.views.CategoryItemView;
import org.jetbrains.annotations.NotNull;

import java.awt.event.ActionListener;

public class CategoryItemController extends EventPublisher<CategoryItemSubscriber, CategoryItemEvent> {
    private final CategoryModel model;
    private final CategoryItemView view;

    public CategoryItemController(CategoryModel model, @NotNull CategoryItemView view) {
        this.model = model;
        this.view = view;

        view.getButton().addActionListener(createButtonActionListener());
    }

    public CategoryModel getModel() {
        return model;
    }

    public CategoryItemView getView() {
        return view;
    }

    private @NotNull ActionListener createButtonActionListener() {
        return (_) -> notifySubscribers(CategoryItemEvent.VIEW);
    }

    @Override
    protected void notifyHandler(CategoryItemSubscriber subscriber, CategoryItemEvent event) {
        if (event == CategoryItemEvent.VIEW) {
            subscriber.onView(model.getName());
        } else {
            throw new IllegalArgumentException("Unhandled event type: " + event);
        }
    }
}