package com.xephorium.armory.model;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Profile {


    /*--- Variables ---*/

    public static final int INITIALIZATION_KEY = -1;

    private int primaryKey;
    private String name;
    private List<Color> colorList;


    /*--- Constructor(s) ---*/

    public Profile() {
        this.primaryKey = INITIALIZATION_KEY;
        this.name = "New Color Profile";
        this.colorList = new ArrayList<>();
        for (int x = 0; x < ColorType.values().length; x++) {
            this.colorList.add(Color.WHITE);
        }
    }

    public Profile(String name) {
        this.primaryKey = INITIALIZATION_KEY;
        this.name = name;
        this.colorList = new ArrayList<>();
        for (int x = 0; x < ColorType.values().length; x++) {
            this.colorList.add(Color.WHITE);
        }
    }

    public Profile(String name, Color unitsColor, Color corpseColor, Color selectorColor, Color minimapColor, Color pauseColor) {
        this.primaryKey = INITIALIZATION_KEY;
        this.name = name;
        this.colorList = new ArrayList<>();
        colorList.add(unitsColor);
        colorList.add(corpseColor);
        colorList.add(selectorColor);
        colorList.add(minimapColor);
        colorList.add(pauseColor);
    }

    public Profile(int primaryKey, String name, Color unitsColor, Color corpseColor, Color selectorColor, Color minimapColor, Color pauseColor) {
        this.primaryKey = primaryKey;
        this.name = name;
        this.colorList = new ArrayList<>();
        colorList.add(unitsColor);
        colorList.add(corpseColor);
        colorList.add(selectorColor);
        colorList.add(minimapColor);
        colorList.add(pauseColor);
    }


    /*--- Public Methods ---*/

    public void setPrimaryKey(int primaryKey) {
        this.primaryKey = primaryKey;
    }

    public int getPrimaryKey() {
        return this.primaryKey;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor(ColorType colorType) {
        return colorList.get(colorType.getIndex());
    }

    public void setColor(ColorType colorType, Color color) {
        this.colorList.set(colorType.getIndex(), color);
    }

    public Profile cloneProfile() {
        Profile newProfile = new Profile(this.getName());
        newProfile.setPrimaryKey(this.getPrimaryKey());
        for (int x = 0; x < ColorType.values().length; x++) {
            newProfile.setColor(ColorType.getFromIndex(x), this.getColor(ColorType.getFromIndex(x)));
        }
        return newProfile;
    }

    @Override
    public boolean equals(Object profile) {
        if (!(profile instanceof Profile)) {
            return false;
        }

        boolean sameNameAndId = ((Profile) profile).getPrimaryKey() == this.getPrimaryKey()
                && ((Profile) profile).getName().equals(this.getName());

        boolean sameColors = true;
        for (int x = 0; x < colorList.size(); x++) {
            if (colorList.get(x) != ((Profile) profile).colorList.get(x)) {
                sameColors = false;
            }
        }
        return sameNameAndId && sameColors;
    }


    /*--- ColorType Enum ---*/

    public enum ColorType {
        UNIT("Units"),
        CORPSE("Corpses"),
        SELECTOR("Selector"),
        MINIMAP_ICON("Minimap Icon"),
        PAUSE_MENU("Pause Menu");

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

        public static ColorType getFromIndex(int index) {
            return ColorType.values()[index];
        }
    }
}
