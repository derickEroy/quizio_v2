package com.dak.events;

import java.util.ArrayList;
import java.util.List;

public abstract class EventPublisher<TSubscriber extends EventSubscriber, TEvent extends Enum<TEvent>> {
    private final List<TSubscriber> subscribers = new ArrayList<>();

    public List<TSubscriber> getSubscribers() {
        return subscribers;
    }

    public void addSubscriber(TSubscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void removeSubscriber(TSubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    protected void notifySubscribers(TEvent event) {
        for (TSubscriber subscriber : subscribers) {
            notifyHandler(subscriber, event);
        }
    }

    protected abstract void notifyHandler(TSubscriber subscriber, TEvent event);
}