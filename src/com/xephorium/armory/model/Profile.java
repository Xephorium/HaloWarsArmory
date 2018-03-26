package com.xephorium.armory.model;

import com.xephorium.armory.ui.resource.color.ArmoryColor;

import java.awt.*;

public class Profile {


    /*--- Variables ---*/

    private String name;
    private Color[] colors = new Color[ColorType.values().length];


    /*--- Constructor(s) ---*/

    public Profile() {
        this.name = "New Color Profile";
        for (int x = 0; x < colors.length; x++) {
            this.colors[x] = ArmoryColor.WINDOW_TEST_COLOR;
        }
    }

    public Profile(String name) {
        this.name = name;
        for (int x = 0; x < colors.length; x++) {
            this.colors[x] = ArmoryColor.WINDOW_TEST_COLOR;
        }
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


    /*--- ColorType Enum ---*/

    public enum ColorType {
        UNITS("Units"),
        CORPSES("Corpses"),
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
}
