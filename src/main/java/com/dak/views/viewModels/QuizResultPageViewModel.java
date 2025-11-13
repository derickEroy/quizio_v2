package com.dak.views.viewModels;

public record QuizResultPageViewModel (
    String quizName,
    int score,
    int total,
    int percentage
 ) {}
