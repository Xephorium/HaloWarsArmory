package com.xephorium.armory.repository;

import com.xephorium.armory.model.Profile;
import com.xephorium.armory.model.converter.ProfileConverter;
import com.xephorium.armory.model.ProfileList;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CustomProfileRepository {


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

    public void deletePlayerProfile(int primaryKey) {

        if (CUSTOM_PROFILES_FILE.exists()) {
            deleteProfileFromCustomProfiles(primaryKey);
        }
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
                writer.println(ProfileConverter.getSaveStringFromProfile(profileList.getProfileByIndex(x), null));
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
