package com.xephorium.armory.model;

import com.xephorium.armory.ui.resource.color.ArmoryColor;

import java.awt.*;

public class Profile {


    /*--- Variables ---*/

    private int primaryKey;
    private String name;
    private Color[] colors = new Color[ColorType.values().length];


    /*--- Constructor(s) ---*/

    public Profile(int primaryKey) {
        this.primaryKey = primaryKey;
        this.name = "New Color Profile";
        for (int x = 0; x < colors.length; x++) {
            this.colors[x] = ArmoryColor.WINDOW_TEST_COLOR;
        }
    }

    public Profile(int primaryKey, String name) {
        this.primaryKey = primaryKey;
        this.name = name;
        for (int x = 0; x < colors.length; x++) {
            this.colors[x] = ArmoryColor.WINDOW_TEST_COLOR;
        }
    }

    public Profile(int primaryKey, String name, Color unitColor, Color corpseColor, Color selectorColor, Color minimapColor, Color hudColor) {
        this.primaryKey = primaryKey;
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

    public static String[] getProfileNames(Profile[] profileList) {
        String[] profileNames = new String[profileList.length];
        for (int x = 0; x < profileList.length; x++) {
            profileNames[x] = profileList[x].getName();
        }
        return profileNames;
    }

}
