package com.xephorium.armory.model.converter;

import com.xephorium.armory.model.Profile;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfileConverter {


    /*--- Variables ---*/

    private static final String PRIVATE_KEY_SAVE_LABEL = "primaryKey";
    private static final String NAME_SAVE_LABEL = "name";
    private static final String UNITS_SAVE_LABEL = "units";
    private static final String CORPSES_SAVE_LABEL = "corpses";
    private static final String SELECTOR_SAVE_LABEL = "selector";
    private static final String MINIMAP_SAVE_LABEL = "minimap";
    private static final String PAUSE_SAVE_LABEL = "pause";
    private static final String BRACE_WRAPPED_DATA = "=\\{[^{]+\\}";

    private static final Pattern RED_COLOR_PATTERN = Pattern.compile("\\{\\d+,");
    private static final Pattern GREEN_COLOR_PATTERN = Pattern.compile(" \\d+,");
    private static final Pattern BLUE_COLOR_PATTERN = Pattern.compile("\\d+\\}");

    private static final Pattern PRIMARY_KEY_PATTERN = Pattern.compile(PRIVATE_KEY_SAVE_LABEL + BRACE_WRAPPED_DATA);
    private static final Pattern NAME_PATTERN = Pattern.compile(NAME_SAVE_LABEL + BRACE_WRAPPED_DATA);
    private static final Pattern UNITS_PATTERN = Pattern.compile(UNITS_SAVE_LABEL + BRACE_WRAPPED_DATA);
    private static final Pattern CORPSES_PATTERN = Pattern.compile(CORPSES_SAVE_LABEL + BRACE_WRAPPED_DATA);
    private static final Pattern SELECTOR_PATTERN = Pattern.compile(SELECTOR_SAVE_LABEL + BRACE_WRAPPED_DATA);
    private static final Pattern MINIMAP_PATTERN = Pattern.compile(MINIMAP_SAVE_LABEL + BRACE_WRAPPED_DATA);
    private static final Pattern PAUSE_PATTERN = Pattern.compile(PAUSE_SAVE_LABEL + BRACE_WRAPPED_DATA);


    /*--- Public Methods ---*/

    public static String getSaveStringFromProfile(Profile profile) {
        if (profile == null)
            return null;

        return PRIVATE_KEY_SAVE_LABEL + "={" + profile.getPrimaryKey() + "} "
                + NAME_SAVE_LABEL + "={" + profile.getName() + "} "
                + UNITS_SAVE_LABEL + "={" + getColorStringFromColor(profile.getColor(Profile.ColorType.UNIT)) + "} "
                + CORPSES_SAVE_LABEL + "={" + getColorStringFromColor(profile.getColor(Profile.ColorType.CORPSE)) + "} "
                + SELECTOR_SAVE_LABEL + "={" + getColorStringFromColor(profile.getColor(Profile.ColorType.SELECTOR)) + "} "
                + MINIMAP_SAVE_LABEL + "={" + getColorStringFromColor(profile.getColor(Profile.ColorType.MINIMAP_ICON)) + "} "
                + PAUSE_SAVE_LABEL + "={" + getColorStringFromColor(profile.getColor(Profile.ColorType.PAUSE_MENU)) + "}";
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
        Matcher matcher = NAME_PATTERN.matcher(saveString);
        String name = null;

        if (matcher.find()) {
            name = matcher.group(0).substring(NAME_SAVE_LABEL.length() + 2, matcher.group(0).length() - 1);
        }

        return name;
    }

    private static Integer getPrimaryKeyFromSaveString(String saveString) {
        Matcher matcher = PRIMARY_KEY_PATTERN.matcher(saveString);
        Integer primaryKey = null;

        if (matcher.find()) {
            primaryKey = Integer.parseInt(matcher.group(0).substring(PRIVATE_KEY_SAVE_LABEL.length() + 2, matcher.group(0).length() - 1));
        }

        return primaryKey;
    }

    private static Color getUnitsColorFromSaveString(String saveString) {
        Matcher matcher = UNITS_PATTERN.matcher(saveString);
        Color unitsColor = null;

        if (matcher.find()) {
            String colorString = matcher.group(0).substring(UNITS_SAVE_LABEL.length() + 1, matcher.group(0).length());
            unitsColor = getColorFromColorString(colorString);
        }

        return unitsColor;
    }

    private static Color getCorpseColorFromSaveString(String saveString) {
        Matcher matcher = CORPSES_PATTERN.matcher(saveString);
        Color corpsesColor = null;

        if (matcher.find()) {
            String colorString = matcher.group(0).substring(CORPSES_SAVE_LABEL.length() + 1, matcher.group(0).length());
            corpsesColor = getColorFromColorString(colorString);
        }

        return corpsesColor;
    }

    private static Color getSelectorColorFromSaveString(String saveString) {
        Matcher matcher = SELECTOR_PATTERN.matcher(saveString);
        Color selectorColor = null;

        if (matcher.find()) {
            String colorString = matcher.group(0).substring(SELECTOR_SAVE_LABEL.length() + 1, matcher.group(0).length());
            selectorColor = getColorFromColorString(colorString);
        }

        return selectorColor;
    }

    private static Color getMinimapColorFromSaveString(String saveString) {
        Matcher matcher = MINIMAP_PATTERN.matcher(saveString);
        Color minimapColor = null;

        if (matcher.find()) {
            String colorString = matcher.group(0).substring(MINIMAP_SAVE_LABEL.length() + 1, matcher.group(0).length());
            minimapColor = getColorFromColorString(colorString);
        }

        return minimapColor;
    }

    private static Color getPauseColorFromSaveString(String saveString) {
        Matcher matcher = PAUSE_PATTERN.matcher(saveString);
        Color pauseColor = null;

        if (matcher.find()) {
            String colorString = matcher.group(0).substring(PAUSE_SAVE_LABEL.length() + 1, matcher.group(0).length());
            pauseColor = getColorFromColorString(colorString);
        }

        return pauseColor;
    }

    private static String getColorStringFromColor(Color color) {
        if (color == null)
            return null;

        return color.getRed() + ", " + color.getGreen() + ", " + color.getBlue();
    }

    private static Color getColorFromColorString(String colorString) {
        if (colorString == null || colorString.isEmpty())
            return null;

        Integer redValue = null;
        Integer greenValue = null;
        Integer blueValue = null;

        Matcher redColorMatcher = RED_COLOR_PATTERN.matcher(colorString);
        Matcher greenColorMatcher = GREEN_COLOR_PATTERN.matcher(colorString);
        Matcher blueColorMatcher = BLUE_COLOR_PATTERN.matcher(colorString);

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
