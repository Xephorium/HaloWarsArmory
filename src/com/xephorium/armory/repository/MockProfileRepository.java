package com.xephorium.armory.repository;

import com.xephorium.armory.model.Profile;

import java.awt.*;

public class MockProfileRepository {

    public static Profile[] getProfileList() {
        Profile[] profileList = new Profile[7];
        profileList[0] = createProfile(1, "Snow & Ice", new Color(139, 195, 235));
        profileList[1] = createProfile(2, "Burnt Oak", new Color(136, 97, 24));
        profileList[2] = createProfile(3, "Forest Green", new Color(115, 156, 103));
        profileList[3] = createProfile(4, "Lavender Rain", new Color(165, 114, 215));
        profileList[4] = createProfile(5, "Gunmetal Gray", new Color(90, 90, 90));
        profileList[5] = createProfile(6, "Cherry Red", new Color(228, 74, 63));
        profileList[6] = createProfile(7, "Lava Glow", new Color(218, 155, 26));
        return profileList;
    }

    private static Profile createProfile(int primaryKey, String name, Color color) {
        return new Profile(primaryKey, name, color, color, color, color, color);
    }
}
