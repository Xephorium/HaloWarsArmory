package com.xephorium.armory.ui.utility;

public class StringUtility {

    public static String addLeadingSpace(String string) {

        if (string == null || string.isEmpty()) {
            return "";
        }

        if (!string.isEmpty() && string.substring(1,1).equals(" ")) {
            return string;
        } else {
            return " " + string;
        }
    }

    public static String removeLeadingSpace(String string) {

        if (string == null || string.isEmpty()) {
            return "";
        }

        if (string.substring(1,1).equals(" ")) {
            return string.substring(1, string.length());
        } else {
            return string;
        }
    }
}
