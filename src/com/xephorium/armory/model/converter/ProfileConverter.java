package com.xephorium.armory.model.converter;

import com.xephorium.armory.model.Profile;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfileConverter {


    /*--- Variables ---*/

    private static final String LABEL_PREFIX = "        <color ";
    private static final String LABEL_SUFFIX = "/>";
    private static final String LABEL_PRIMARY_KEY = "primaryKey";
    private static final String LABEL_NUMBER = "num";
    private static final String LABEL_NAME = "name";
    private static final String LABEL_UNITS = "objects";
    private static final String LABEL_CORPSES = "corpse";
    private static final String LABEL_SELECTOR = "selection";
    private static final String LABEL_MINIMAP = "minimap";
    private static final String LABEL_PAUSE = "ui";
    private static final String FORMAT_COLOR = "=\\\"[^\"]+\\\"";

    private static final Pattern PATTERN_RED = Pattern.compile("\\\"\\d+ ");
    private static final Pattern PATTERN_GREEN = Pattern.compile(" \\d+ ");
    private static final Pattern PATTERN_BLUE = Pattern.compile("\\d+\\\"");
    private static final Pattern PATTERN_PRIMARY_KEY = Pattern.compile(LABEL_PRIMARY_KEY + FORMAT_COLOR);
    private static final Pattern PATTERN_NAME = Pattern.compile(LABEL_NAME + FORMAT_COLOR);
    private static final Pattern PATTERN_UNITS = Pattern.compile(LABEL_UNITS + FORMAT_COLOR);
    private static final Pattern PATTERN_CORPSES = Pattern.compile(LABEL_CORPSES + FORMAT_COLOR);
    private static final Pattern PATTERN_SELECTOR = Pattern.compile(LABEL_SELECTOR + FORMAT_COLOR);
    private static final Pattern PATTERN_MINIMAP = Pattern.compile(LABEL_MINIMAP + FORMAT_COLOR);
    private static final Pattern PATTERN_PAUSE = Pattern.compile(LABEL_PAUSE + FORMAT_COLOR);

    private static final String ESCAPED_DOUBLE_QUOTE = "&quot;";


    /*--- Public Methods ---*/

    public static String getSaveStringFromProfile(Profile profile, Integer number) {
        if (profile == null)
            return null;

        String playerColorsNumber = "";
        if (number != null) {
            playerColorsNumber = LABEL_NUMBER + "=\"" + number + "\" ";
        }

        return LABEL_PREFIX
                + playerColorsNumber
                + LABEL_UNITS + "=\"" + getSaveColorStringFromColor(profile.getColor(Profile.ColorType.UNIT)) + "\" "
                + LABEL_CORPSES + "=\"" + getSaveColorStringFromColor(profile.getColor(Profile.ColorType.CORPSE)) + "\" "
                + LABEL_SELECTOR + "=\"" + getSaveColorStringFromColor(profile.getColor(Profile.ColorType.SELECTOR)) + "\" "
                + LABEL_MINIMAP + "=\"" + getSaveColorStringFromColor(profile.getColor(Profile.ColorType.MINIMAP_ICON)) + "\" "
                + LABEL_PAUSE + "=\"" + getSaveColorStringFromColor(profile.getColor(Profile.ColorType.PAUSE_MENU)) + "\" "
                + LABEL_NAME + "=\"" + getSaveNameStringFromName(profile.getName()) + "\" "
                + LABEL_PRIMARY_KEY + "=\"" + profile.getPrimaryKey() + "\" "
                + LABEL_SUFFIX;
    }

    public static Profile getProfileFromSaveString(String saveString) {
        if (saveString == null || saveString.isEmpty())
            return null;

        Integer primaryKey = getPrimaryKeyFromSaveString(saveString);
        String name = getNameFromSaveString(saveString);
        Color unitsColor = getUnitsColorFromSaveString(saveString);
        Color corpseColor = getCorpseColorFromSaveString(saveString);
        Color selectorColor = getSelectorColorFromSaveString(saveString);
        Color minimapColor = getMinimapColorFromSaveString(saveString);
        Color pauseColor = getPauseColorFromSaveString(saveString);

        if (primaryKey != null
                && name != null
                && unitsColor != null
                && corpseColor != null
                && selectorColor != null
                && minimapColor != null
                && pauseColor != null) {
            return new Profile(primaryKey, name, unitsColor, corpseColor, selectorColor, minimapColor, pauseColor);
        } else {
            return null;
        }
    }


    /*--- Private Methods ---*/

    private static String getNameFromSaveString(String saveString) {
        Matcher matcher = PATTERN_NAME.matcher(saveString);
        String name = null;

        if (matcher.find()) {
            name = matcher.group(0).substring(LABEL_NAME.length() + 2, matcher.group(0).length() - 1);
            name = name.replaceAll(ESCAPED_DOUBLE_QUOTE, "\"");
        }

        return name;
    }

    private static Integer getPrimaryKeyFromSaveString(String saveString) {
        Matcher matcher = PATTERN_PRIMARY_KEY.matcher(saveString);
        Integer primaryKey = null;

        if (matcher.find()) {
            primaryKey = Integer.parseInt(matcher.group(0).substring(LABEL_PRIMARY_KEY.length() + 2, matcher.group(0).length() - 1));
        }

        return primaryKey;
    }

    private static Color getUnitsColorFromSaveString(String saveString) {
        Matcher matcher = PATTERN_UNITS.matcher(saveString);
        Color unitsColor = null;

        if (matcher.find()) {
            String colorString = matcher.group(0).substring(LABEL_UNITS.length() + 1, matcher.group(0).length());
            unitsColor = getColorFromSaveColorString(colorString);
        }

        return unitsColor;
    }

    private static Color getCorpseColorFromSaveString(String saveString) {
        Matcher matcher = PATTERN_CORPSES.matcher(saveString);
        Color corpsesColor = null;

        if (matcher.find()) {
            String colorString = matcher.group(0).substring(LABEL_CORPSES.length() + 1, matcher.group(0).length());
            corpsesColor = getColorFromSaveColorString(colorString);
        }

        return corpsesColor;
    }

    private static Color getSelectorColorFromSaveString(String saveString) {
        Matcher matcher = PATTERN_SELECTOR.matcher(saveString);
        Color selectorColor = null;

        if (matcher.find()) {
            String colorString = matcher.group(0).substring(LABEL_SELECTOR.length() + 1, matcher.group(0).length());
            selectorColor = getColorFromSaveColorString(colorString);
        }

        return selectorColor;
    }

    private static Color getMinimapColorFromSaveString(String saveString) {
        Matcher matcher = PATTERN_MINIMAP.matcher(saveString);
        Color minimapColor = null;

        if (matcher.find()) {
            String colorString = matcher.group(0).substring(LABEL_MINIMAP.length() + 1, matcher.group(0).length());
            minimapColor = getColorFromSaveColorString(colorString);
        }

        return minimapColor;
    }

    private static Color getPauseColorFromSaveString(String saveString) {
        Matcher matcher = PATTERN_PAUSE.matcher(saveString);
        Color pauseColor = null;

        if (matcher.find()) {
            String colorString = matcher.group(0).substring(LABEL_PAUSE.length() + 1, matcher.group(0).length());
            pauseColor = getColorFromSaveColorString(colorString);
        }

        return pauseColor;
    }

    private static String getSaveNameStringFromName(String name) {
        if (name == null) {
            return null;
        }

        return name.replaceAll("\"", ESCAPED_DOUBLE_QUOTE);
    }

    private static String getSaveColorStringFromColor(Color color) {
        if (color == null)
            return null;

        return color.getRed() + " " + color.getGreen() + " " + color.getBlue();
    }

    private static Color getColorFromSaveColorString(String colorString) {
        if (colorString == null || colorString.isEmpty())
            return null;

        Integer redValue = null;
        Integer greenValue = null;
        Integer blueValue = null;

        Matcher redColorMatcher = PATTERN_RED.matcher(colorString);
        Matcher greenColorMatcher = PATTERN_GREEN.matcher(colorString);
        Matcher blueColorMatcher = PATTERN_BLUE.matcher(colorString);

        if (redColorMatcher.find()) {
            redValue = Integer.parseInt(redColorMatcher.group(0).substring(1, redColorMatcher.group(0).length() - 1));
        }

        if (greenColorMatcher.find()) {
            greenValue = Integer.parseInt(greenColorMatcher.group(0).substring(1, greenColorMatcher.group(0).length() - 1));
        }

        if (blueColorMatcher.find()) {
            blueValue = Integer.parseInt(blueColorMatcher.group(0).substring(0, blueColorMatcher.group(0).length() - 1));
        }

        if (redValue != null && greenValue != null && blueValue != null) {
            return new Color(redValue, greenValue, blueValue);
        } else {
            return null;
        }
    }
}
