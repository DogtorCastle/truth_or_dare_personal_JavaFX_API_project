package com.example.truthordare;

import javafx.scene.Scene;

public class ThemeHandler {
    private static final String DEFAULT_THEME = TOrDApplication.class.getResource("DefaultTheme.css").toExternalForm();
    private static final String DARK_DEFAULT_THEME = TOrDApplication.class.getResource("DarkDefaultTheme.css").toExternalForm();
    private static final String HOTLINE_MIAMI_THEME = TOrDApplication.class.getResource("HotlineMiamiTheme.css").toExternalForm();
    private static final String PRIDE_THEME = TOrDApplication.class.getResource("PrideTheme.css").toExternalForm();

    protected static Scene sceneCopy;
    protected static String[] themes = {"Original", "Dark", "Hotline Miami", "Pride"};


    public static void init(Scene scene) {
        sceneCopy = scene;
    }
    public static void setTheme(String theme) {
        String cssFileName = switch (theme) {
            case "Original" -> DEFAULT_THEME;
            case "Dark" -> DARK_DEFAULT_THEME;
            case "Hotline Miami" -> HOTLINE_MIAMI_THEME;
            case "Pride" -> PRIDE_THEME;
            default -> DEFAULT_THEME;
        };
        sceneCopy.getStylesheets().clear();
        sceneCopy.getStylesheets().add(cssFileName);
    }
}
