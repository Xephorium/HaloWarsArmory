package com.xephorium.armory.repository;

import com.xephorium.armory.model.ColorProfile;

import java.awt.*;

public class MockColorProfileRepository {

    public static ColorProfile[] getProfileList() {
        ColorProfile[] colorProfileList = new ColorProfile[7];
        colorProfileList[0] = createColorProfile("Snow & Ice", new Color(139, 195, 235));
        colorProfileList[1] = createColorProfile("Burnt Oak", new Color(136, 97, 24));
        colorProfileList[2] = createColorProfile("Forest Green", new Color(115, 156, 103));
        colorProfileList[3] = createColorProfile("Lavender Rain", new Color(165, 114, 215));
        colorProfileList[4] = createColorProfile("Gunmetal Gray", new Color(90, 90, 90));
        colorProfileList[5] = createColorProfile("Cherry Red", new Color(228, 74, 63));
        colorProfileList[6] = createColorProfile("Lava Glow", new Color(218, 155, 26));
        return colorProfileList;
    }

    private static ColorProfile createColorProfile(String name, Color color) {
        return new ColorProfile(name, color, color, color, color, color);
    }
}
