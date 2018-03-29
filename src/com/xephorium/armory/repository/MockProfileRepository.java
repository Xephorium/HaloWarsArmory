package com.xephorium.armory.repository;

import com.xephorium.armory.model.Profile;
import com.xephorium.armory.model.ProfileList;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MockProfileRepository {


    /*--- Public Methods ---*/

    public static ProfileList getProfileList() {
        ProfileList profileList = new ProfileList();
        profileList.add(createProfile("Snow & Ice", new Color(139, 195, 235)));
        profileList.add(createProfile("Burnt Oak", new Color(136, 97, 24)));
        profileList.add(createProfile("Forest Green", new Color(115, 156, 103)));
        profileList.add(createProfile("Lavender Rain", new Color(165, 114, 215)));
        profileList.add(createProfile("Gunmetal Gray", new Color(90, 90, 90)));
        profileList.add(createProfile("Cherry Red", new Color(228, 74, 63)));
        profileList.add(createProfile("Lava Glow", new Color(218, 155, 26)));
        return profileList;
    }

    public static List<Integer> loadCustomPlayerConfiguration() {
        List<Integer> playerProfileList = new ArrayList<>();
        ProfileList profileList = getProfileList();
        for (int x = 0; x < 6; x++) {
            playerProfileList.add(profileList.getByIndex(x).getPrimaryKey());
        }
        return playerProfileList;
    }

    public static List<Integer> loadDefaultUNSCPlayerConfiguration() {
        return loadCustomPlayerConfiguration();
    }

    public static List<Integer> loadDefaultCovenantPlayerConfiguration() {
        return loadCustomPlayerConfiguration();
    }


    /*--- Private Methods ---*/

    private static Profile createProfile(String name, Color color) {
        return new Profile(
                name,
                color,
                color.darker(),
                color.darker().darker(),
                color.darker(),
                color);
    }
}
