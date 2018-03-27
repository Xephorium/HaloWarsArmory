package com.xephorium.armory.model;

import com.xephorium.armory.ui.resource.color.ArmoryColor;

import java.awt.*;
import java.awt.color.ProfileDataException;

public class ColorProfile {


    /*--- Variables ---*/

    private String name;
    private Color[] colors = new Color[ColorType.values().length];


    /*--- Constructor(s) ---*/

    public ColorProfile() {
        this.name = "New Color ColorProfile";
        for (int x = 0; x < colors.length; x++) {
            this.colors[x] = ArmoryColor.WINDOW_TEST_COLOR;
        }
    }

    public ColorProfile(String name) {
        this.name = name;
        for (int x = 0; x < colors.length; x++) {
            this.colors[x] = ArmoryColor.WINDOW_TEST_COLOR;
        }
    }

    public ColorProfile(String name, Color unitColor, Color corpseColor, Color selectorColor, Color minimapColor, Color hudColor) {
        this.name = name;
        this.setColor(ColorType.UNIT, unitColor);
        this.setColor(ColorType.CORPSE, corpseColor);
        this.setColor(ColorType.SELECTOR, selectorColor);
        this.setColor(ColorType.MINIMAP, minimapColor);
        this.setColor(ColorType.HUD, hudColor);
    }


    /*--- Public Methods ---*/

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor(ColorType colorType) {
        return this.colors[colorType.getIndex()];
    }

    public void setColor(ColorType colorType, Color color) {
        this.colors[colorType.getIndex()] = color;
    }

    public Color[] getColors() {
        return this.colors;
    }

    public void setColors(Color[] colors) {
        this.colors = colors;
    }


    /*--- ColorType Enum ---*/

    public enum ColorType {
        UNIT("Units"),
        CORPSE("Corpses"),
        SELECTOR("Selector"),
        MINIMAP("Minimap"),
        HUD("HUD");

        private String displayName;

        ColorType(String displayName){
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return this.displayName;
        }

        public int getIndex() {
            return this.ordinal();
        }
    }


    /*--- Utility Methods ---*/

    public static String[] getColorProfileNames(ColorProfile[] colorProfileList) {
        String[] colorProfileNameList = new String[colorProfileList.length];
        for (int x = 0; x < colorProfileList.length; x++) {
            colorProfileNameList[x] = colorProfileList[x].getName();
        }
        return colorProfileNameList;
    }

}
