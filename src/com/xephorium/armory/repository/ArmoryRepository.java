package com.xephorium.armory.repository;

import com.xephorium.armory.model.Profile;
import com.xephorium.armory.model.ProfileList;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ArmoryRepository {


    /*--- Constructor ---*/

    public ArmoryRepository() {
        // TODO - Setup File Read/Write
    }


    /*--- Public Methods ---*/

    public List<Integer> loadCustomUNSCPlayerConfiguration() {
        // TODO - Read Changes From File
        List<Integer> customUNSCPlayerConfiguration = new ArrayList<>();
        ProfileList profileList = loadPlayerProfileList();
        for (int x = 0; x < 6; x++) {
            customUNSCPlayerConfiguration.add(profileList.getProfileByIndex(x).getPrimaryKey());
        }
        return customUNSCPlayerConfiguration;
    }

    public List<Integer> loadCustomCovenantPlayerConfiguration() {
        // TODO - Read Changes From File
        List<Integer> customCovenantPlayerConfiguration = new ArrayList<>();
        ProfileList profileList = loadPlayerProfileList();
        for (int x = 0; x < 6; x++) {
            customCovenantPlayerConfiguration.add(profileList.getProfileByIndex(x).getPrimaryKey());
        }
        return customCovenantPlayerConfiguration;
    }

    public List<Integer> loadDefaultUNSCPlayerConfiguration() {
        // TODO - Read Changes From File
        return loadCustomUNSCPlayerConfiguration();
    }

    public List<Integer> loadDefaultCovenantPlayerConfiguration() {
        // TODO - Read Changes From File
        return loadCustomUNSCPlayerConfiguration();
    }

    public ProfileList loadPlayerProfileList() {
        // TODO - Read Changes From File
        // TODO - Note: UNSC/Covenant profiles must be hardcoded with the same
        // TODO - absurd, negative primary keys. This is to prevent custom profile
        // TODO - upon override upon Configuration reset.
        ProfileList profileList = new ProfileList();
        profileList.addNewProfile(createProfile(-50, "Default - Snow & Ice", new Color(37, 118, 181)));
        profileList.addNewProfile(createProfile(-51, "Default - Burnt Oak", new Color(136, 97, 24)));
        profileList.addNewProfile(createProfile(-52, "Default - Forest Green", new Color(115, 156, 103)));
        profileList.addNewProfile(createProfile(-53, "Default - Lavender Rain", new Color(165, 114, 215)));
        profileList.addNewProfile(createProfile(-54, "Default - Gunmetal Gray", new Color(90, 90, 90)));
        profileList.addNewProfile(createProfile(-55, "Default - Cherry Red", new Color(228, 74, 63)));
        profileList.addNewProfile(createProfile(-56, "Default - Lava Glow", new Color(218, 155, 26)));
        return profileList;
    }

    public ProfileList loadDefaultUNSCPlayerProfiles() {
        // TODO - Real Data
        return loadPlayerProfileList();
    }

    public ProfileList loadDefaultCovenantPlayerProfiles() {
        // TODO - Real Data
        return loadPlayerProfileList();
    }


    /*--- Private Methods ---*/

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
