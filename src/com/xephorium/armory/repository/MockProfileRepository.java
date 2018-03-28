package com.xephorium.armory.repository;

import com.xephorium.armory.model.Profile;

import java.awt.*;

public class MockProfileRepository {

    public static Profile[] getProfileList() {
        Profile[] profileList = new Profile[7];
        profileList[0] = createProfile("Snow & Ice", new Color(139, 195, 235));
        profileList[1] = createProfile("Burnt Oak", new Color(136, 97, 24));
        profileList[2] = createProfile("Forest Green", new Color(115, 156, 103));
        profileList[3] = createProfile("Lavender Rain", new Color(165, 114, 215));
        profileList[4] = createProfile("Gunmetal Gray", new Color(90, 90, 90));
        profileList[5] = createProfile("Cherry Red", new Color(228, 74, 63));
        profileList[6] = createProfile("Lava Glow", new Color(218, 155, 26));
        return profileList;
    }

    private static Profile createProfile(String name, Color color) {
        return new Profile(name, color, color, color, color, color);
    }
}
