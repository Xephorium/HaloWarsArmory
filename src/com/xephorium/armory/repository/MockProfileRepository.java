package com.xephorium.armory.repository;

import com.xephorium.armory.model.Profile;
import com.xephorium.armory.model.ProfileList;

import java.awt.*;

public class MockProfileRepository {

    public static ProfileList getProfileList() {
        ProfileList profileList = new ProfileList();
        profileList.add(createProfile(1, "Snow & Ice", new Color(139, 195, 235)));
        profileList.add(createProfile(2, "Burnt Oak", new Color(136, 97, 24)));
        profileList.add(createProfile(3, "Forest Green", new Color(115, 156, 103)));
        profileList.add(createProfile(4, "Lavender Rain", new Color(165, 114, 215)));
        profileList.add(createProfile(5, "Gunmetal Gray", new Color(90, 90, 90)));
        profileList.add(createProfile(6, "Cherry Red", new Color(228, 74, 63)));
        profileList.add(createProfile(7, "Lava Glow", new Color(218, 155, 26)));
        return profileList;
    }

    private static Profile createProfile(int primaryKey, String name, Color color) {
        return new Profile(
                primaryKey,
                name,
                color,
                color.darker(),
                color.darker().darker(),
                color.darker(),
                color);
    }
}
