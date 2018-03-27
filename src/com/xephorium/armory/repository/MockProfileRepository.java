package com.xephorium.armory.repository;

import com.xephorium.armory.model.Profile;

import java.awt.*;

public class MockProfileRepository {

    public static Profile[] getProfileList() {
        Profile[] profileList = new Profile[7];
        profileList[0] = createColorProfile("Snow & Ice", new Color(139, 195, 235));
        profileList[1] = createColorProfile("Burnt Oak", new Color(136, 97, 24));
        profileList[2] = createColorProfile("Forest Green", new Color(115, 156, 103));
        profileList[3] = createColorProfile("Lavender Rain", new Color(165, 114, 215));
        profileList[4] = createColorProfile("Gunmetal Gray", new Color(90, 90, 90));
        profileList[5] = createColorProfile("Cherry Red", new Color(228, 74, 63));
        profileList[6] = createColorProfile("Lava Glow", new Color(218, 155, 26));
        return profileList;
    }

    private static Profile createColorProfile(String name, Color color) {
        return new Profile(name, color, color, color, color, color);
    }
}
