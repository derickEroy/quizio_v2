package com.dak.models;

import java.util.UUID;

public class OptionModel {
    private final UUID id;
    private final UUID questionId;

    private boolean isCorrect;
    private int order;
    private String text;

    public OptionModel(UUID id, UUID questionId, int order, String text, boolean isCorrect) {
        this.id = id;
        this.questionId = questionId;
        this.order = order;
        this.text = text;
        this.isCorrect = isCorrect;
    }

    public UUID getId() {
        return id;
    }

    public UUID getQuestionId() {
        return questionId;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public int getOrder() {
        return order;
    }

    public String getText() {
        return text;
    }
}
