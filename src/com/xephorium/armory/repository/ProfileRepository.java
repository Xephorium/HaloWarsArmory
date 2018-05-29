package com.xephorium.armory.repository;

import com.xephorium.armory.model.Profile;
import com.xephorium.armory.model.converter.ProfileConverter;
import com.xephorium.armory.model.ProfileList;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProfileRepository {


    /*--- Variables ---*/

    private static final String CUSTOM_PROFILES_FILE_NAME = "CustomProfiles.txt";
    private static final File CUSTOM_PROFILES_FILE = new File(getCurrentDirectory() + "\\" + CUSTOM_PROFILES_FILE_NAME);


    /*--- Public Methods ---*/

    public ProfileList loadCustomPlayerProfileList() {
        ProfileList profileList;

        if (CUSTOM_PROFILES_FILE.exists()) {
            profileList = createNewProfileListFromCustomProfiles();
            writeProfileListToCustomProfiles(profileList);
        } else {
            return new ProfileList();
        }

        return profileList;
    }

    public boolean saveCustomPlayerProfile(Profile profile) {
        if (CUSTOM_PROFILES_FILE.exists()) {
            if(!writeProfileToCustomProfiles(profile))
                return false;
        } else {
            if(!createCustomProfilesSaveFile())
                return false;
            if(!writeProfileToCustomProfiles(profile))
                return false;
        }

        return true;
    }

    public void deleteCustomPlayerProfile(int primaryKey) {

        if (CUSTOM_PROFILES_FILE.exists()) {
            deleteProfileFromCustomProfiles(primaryKey);
        }
    }

    public List<Integer> loadCustomUNSCPlayerConfiguration() {
        // TODO - Read Changes From File
        List<Integer> customUNSCPlayerConfiguration = new ArrayList<>();
        ProfileList profileList = getDefaultUNSCPlayerProfiles();
        for (int x = 0; x < 6; x++) {
            customUNSCPlayerConfiguration.add(profileList.getProfileByIndex(x).getPrimaryKey());
        }
        return customUNSCPlayerConfiguration;
    }

    public List<Integer> loadCustomCovenantPlayerConfiguration() {
        // TODO - Read Changes From File
        List<Integer> customCovenantPlayerConfiguration = new ArrayList<>();
        ProfileList profileList = getDefaultCovenantPlayerProfiles();
        for (int x = 0; x < 6; x++) {
            customCovenantPlayerConfiguration.add(profileList.getProfileByIndex(x).getPrimaryKey());
        }
        return customCovenantPlayerConfiguration;
    }

    public List<Integer> getDefaultUNSCPlayerConfiguration() {
        List<Integer> unscDefaultProfileKeys = new ArrayList<>();
        ProfileList unscDefaultProfileList = getDefaultUNSCPlayerProfiles();
        for (int x = 0; x < unscDefaultProfileList.size(); x++) {
            unscDefaultProfileKeys.add(unscDefaultProfileList.getProfileByIndex(x).getPrimaryKey());
        }
        return unscDefaultProfileKeys;
    }

    public List<Integer> getDefaultCovenantPlayerConfiguration() {
        List<Integer> covenantDefaultProfileKeys = new ArrayList<>();
        ProfileList covenantDefaultProfileList = getDefaultCovenantPlayerProfiles();
        for (int x = 0; x < covenantDefaultProfileList.size(); x++) {
            covenantDefaultProfileKeys.add(covenantDefaultProfileList.getProfileByIndex(x).getPrimaryKey());
        }
        return covenantDefaultProfileKeys;
    }

    /*
      Note: UNSC/Covenant profiles must be hardcoded with consistently
      absurd, negative primary keys. This is to prevent custom profile
      upon override upon Configuration reset.
    */
    public ProfileList getDefaultUNSCPlayerProfiles() {
        ProfileList profileList = new ProfileList();
        profileList.addNewProfile(new Profile(-101, "UNSC - Olive", new Color(145, 163, 85), new Color(35, 55, 12), new Color(69, 110, 24), new Color(69, 110, 24), new Color(69, 110, 24)));
        profileList.addNewProfile(new Profile(-102, "UNSC - Orange", new Color(235, 165, 27), new Color(117, 82, 14), new Color(235, 165, 27), new Color(235, 165, 27), new Color(235, 165, 27)));
        profileList.addNewProfile(new Profile(-103, "UNSC - Navy Blue", new Color(82, 125, 179), new Color(33, 55, 75), new Color(65, 110, 150), new Color(65, 110, 150), new Color(65, 110, 150)));
        profileList.addNewProfile(new Profile(-104, "UNSC - Tomato Red", new Color(215, 99, 68), new Color(100, 30, 0), new Color(200, 60, 0), new Color(200, 60, 0), new Color(200, 60, 0)));
        profileList.addNewProfile(new Profile(-105, "UNSC - Gunmetal Gray", new Color(83, 83, 83), new Color(25, 25, 15), new Color(50, 50, 30), new Color(50, 50, 30), new Color(50, 50, 30)));
        profileList.addNewProfile(new Profile(-106, "UNSC - Desert Brown", new Color(185, 160, 125), new Color(93, 50, 63), new Color(185, 160, 125), new Color(185, 160, 125), new Color(185, 160, 125)));
        return profileList;
    }

    /*
      Note: UNSC/Covenant profiles must be hardcoded with consistently
      absurd, negative primary keys. This is to prevent custom profile
      upon override upon Configuration reset.
    */
    public ProfileList getDefaultCovenantPlayerProfiles() {
        ProfileList profileList = new ProfileList();
        profileList.addNewProfile(new Profile(-107, "Covenant - Violet", new Color(165, 95, 245), new Color(83, 48, 172), new Color(165, 95, 245), new Color(165, 95, 245), new Color(165, 95, 245)));
        profileList.addNewProfile(new Profile(-108, "Covenant - Teal", new Color(0, 175, 200), new Color(0, 83, 100), new Color(0, 175, 200), new Color(0, 175, 200), new Color(0, 175, 200)));
        profileList.addNewProfile(new Profile(-109, "Covenant - Blood Red", new Color(210, 20, 0), new Color(105, 10, 0), new Color(210, 20, 0), new Color(210, 20, 0), new Color(210, 20, 0)));
        profileList.addNewProfile(new Profile(-110, "Covenant - Forest Green", new Color(40, 50, 20), new Color(20, 25, 10), new Color(40, 50, 20), new Color(40, 50, 20), new Color(40, 50, 20)));
        profileList.addNewProfile(new Profile(-111, "Covenant - Yellow", new Color(255, 215, 50), new Color(128, 107, 25), new Color(255, 215, 50), new Color(255, 215, 50), new Color(255, 215, 50)));
        profileList.addNewProfile(new Profile(-112, "Covenant - Beige", new Color(215, 190, 175), new Color(108, 95, 87), new Color(215, 200, 175), new Color(215, 200, 175), new Color(215, 200, 175)));
        return profileList;
    }

    public boolean isDefaultProfilePrimaryKey(int primaryKey) {
        return primaryKey < -100 && primaryKey > -113;
    }


    /*--- Private Methods ---*/

    private static boolean writeProfileToCustomProfiles(Profile profile) {
        ProfileList customProfileList = readCustomProfiles();

        if (customProfileList.containsPrimaryKey(profile.getPrimaryKey())) {
            customProfileList.updateExistingProfile(profile);
        } else {
            customProfileList.addNewProfile(profile);
        }

        return writeProfileListToCustomProfiles(customProfileList);
    }

    private static boolean writeProfileListToCustomProfiles(ProfileList profileList) {
        try {
            PrintWriter writer = new PrintWriter(CUSTOM_PROFILES_FILE, "UTF-8");
            for (int x = 0; x < profileList.size(); x++) {
                writer.println(ProfileConverter.getSaveStringFromProfile(profileList.getProfileByIndex(x)));
            }
            writer.close();
        } catch (IOException exception) {
            return false;
        }

        return true;
    }

    private static boolean deleteProfileFromCustomProfiles(int primaryKey) {
        ProfileList customProfileList = readCustomProfiles();

        if (customProfileList.containsPrimaryKey(primaryKey)) {
            customProfileList.delete(primaryKey);
        }

        return writeProfileListToCustomProfiles(customProfileList);
    }

    private static ProfileList readCustomProfiles() {
        ProfileList customProfileList = new ProfileList();
        BufferedReader bufferedReader;

        try {
            bufferedReader = new BufferedReader(new FileReader(CUSTOM_PROFILES_FILE));
            String line = bufferedReader.readLine();

            while (line != null) {
                Profile profile = ProfileConverter.getProfileFromSaveString(line);
                if (profile != null)
                    customProfileList.addNewProfileAsIs(profile);
                line = bufferedReader.readLine();
            }

            bufferedReader.close();
        } catch (IOException exception) {
            return new ProfileList();
        }

        return customProfileList;
    }

    private static ProfileList createNewProfileListFromCustomProfiles() {
        ProfileList customProfileList = new ProfileList();
        BufferedReader bufferedReader;

        try {
            bufferedReader = new BufferedReader(new FileReader(CUSTOM_PROFILES_FILE));
            String line = bufferedReader.readLine();

            while (line != null) {
                Profile profile = ProfileConverter.getProfileFromSaveString(line);
                if (profile != null)
                    customProfileList.addNewProfile(profile);
                line = bufferedReader.readLine();
            }

            bufferedReader.close();
        } catch (IOException exception) {
            return new ProfileList();
        }

        return customProfileList;
    }

    private static boolean createCustomProfilesSaveFile() {
        try {
            CUSTOM_PROFILES_FILE.createNewFile();
        } catch (IOException exception) {
            return false;
        }
        return true;
    }

    private static String getCurrentDirectory() {
        return System.getProperty("user.dir");
    }
}
