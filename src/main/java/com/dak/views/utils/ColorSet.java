package com.dak.views.utils;

import java.awt.Color;

public class ColorSet {
    private static Color primaryBackground;
    private static Color secondaryBackground;
    private static Color primaryForeground;
    private static Color secondaryForeground;
    private static Color placeholderForeground;
    private static Color primaryBorder;
    private static Color primaryButton;
    private static Color ghostButton;
    private static Color ghostButtonHover;
    private static Color selectedOptionBackground;
    private static Color selectedOptionAccent;
    private static Color incorrectOptionBackground;
    private static Color incorrectOptionAccent;
    private static Color correctOptionBackground;
    private static Color correctOptionAccent;

    static {
        // Set default color theme.
        toLightMode();
    }

    public static void toLightMode() {
        primaryBackground = Color.decode("#FFFFFF");
        secondaryBackground = Color.decode("#F3F3F3");
        primaryForeground = Color.decode("#353535");
        secondaryForeground = Color.decode("#FFFFFF");
        placeholderForeground = Color.decode("#7a7a7a");
        primaryBorder = Color.decode("#B2B2B2");
        primaryButton = Color.decode("#1B98FF");
        ghostButton = Color.decode("#000000");
        ghostButtonHover = Color.decode("#F3F3F3");
        selectedOptionBackground = Color.decode("#CBE8FF");
        selectedOptionAccent = Color.decode("#3692DE");
        incorrectOptionBackground = Color.decode("#FFD8D8");
        incorrectOptionAccent = Color.decode("#DF4D50");
        correctOptionBackground = Color.decode("#B9FFBE");
        correctOptionAccent = Color.decode("#35BD3E");
    }

    public static void toDarkMode() {
        primaryBackground = Color.decode("#252525");
        secondaryBackground = Color.decode("#353535");
        primaryForeground = Color.decode("#FFFFFF");
        secondaryForeground = Color.decode("#FFFFFF");
        placeholderForeground = Color.decode("#7a7a7a");
        primaryBorder = Color.decode("#FFFFFF");
        primaryButton = Color.decode("#1B98FF");
        ghostButton = Color.decode("#000000");
        ghostButtonHover = Color.decode("#F3F3F3");
        selectedOptionBackground = Color.decode("#CBE8FF");
        selectedOptionAccent = Color.decode("#3692DE");
        incorrectOptionBackground = Color.decode("#FFD8D8");
        incorrectOptionAccent = Color.decode("#DF4D50");
        correctOptionBackground = Color.decode("#B9FFBE");
        correctOptionAccent = Color.decode("#35BD3E");
    }

    public static Color getPrimaryBackground() {
        return primaryBackground;
    }

    public static Color getSecondaryBackground() {
        return secondaryBackground;
    }

    public static Color getPrimaryForeground() {
        return primaryForeground;
    }

    public static Color getSecondaryForeground() {
        return secondaryForeground;
    }

    public static Color getPlaceholderForeground() {
        return placeholderForeground;
    }

    public static Color getPrimaryBorder() {
        return primaryBorder;
    }

    public static Color getPrimaryButton() {
        return primaryButton;
    }

    public static Color getGhostButton() {
        return ghostButton;
    }

    public static Color getGhostButtonHover() {
        return ghostButtonHover;
    }

    public static Color getSelectedOptionBackground() {
        return selectedOptionBackground;
    }

    public static Color getSelectedOptionAccent() {
        return selectedOptionAccent;
    }

    public static Color getIncorrectOptionBackground() {
        return incorrectOptionBackground;
    }

    public static Color getIncorrectOptionAccent() {
        return incorrectOptionAccent;
    }

    public static Color getCorrectOptionBackground() {
        return correctOptionBackground;
    }

    public static Color getCorrectOptionAccent() {
        return correctOptionAccent;
    }
}