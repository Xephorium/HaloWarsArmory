package com.xephorium.armory.ui.utility;

public class StringUtility {

    public static String addLeadingSpace(String string) {

        if (string == null || string.isEmpty()) {
            return "";
        }

        return " " + string.trim();
    }

    public static String removeLeadingSpace(String string) {

        if (string == null || string.isEmpty()) {
            return "";
        }

        return string.trim();
    }
}
