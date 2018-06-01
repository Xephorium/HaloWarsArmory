package com.xephorium.armory.repository;

import com.xephorium.armory.model.Faction;
import com.xephorium.armory.model.Profile;
import com.xephorium.armory.model.ProfileList;
import com.xephorium.armory.model.converter.ProfileConverter;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class GameRepository {


    /*--- Variables ---*/

    private static final String PLAYER_COLORS_PATH = "overrides\\data\\playercolors.xml";
    private static final String INSTALLATION_FILE_NAME = "InstallationDirectory.txt";
    private static final File INSTALLATION_FILE = new File(getCurrentDirectory() + "\\" + INSTALLATION_FILE_NAME);

    private static final Integer UNSC_PROFILE_START_INDEX = 3;
    private static final Integer UNSC_PROFILE_END_INDEX = 9;
    private static final Integer COVENANT_PROFILE_START_INDEX = 9;
    private static final Integer COVENANT_PROFILE_END_INDEX = 15;


    /*--- Public Methods ---*/

    public ProfileList mergeInitialProfilesWithSavedProfiles(ProfileList profileList) {
        ProfileList initialProfileList = profileList.clone();
        ProfileList mergedProfileList = new ProfileList();

        // Read Current PlayerColors Contents
        List<String> playerColorsContents = readPlayerColorsContents();
        if (playerColorsContents == null) {
            return null;
        }

        // Build PlayerColors ProfileList
        ProfileList playerColorsProfileList = new ProfileList();
        for (int x = UNSC_PROFILE_START_INDEX; x < COVENANT_PROFILE_END_INDEX; x++) {
            Profile currentProfile = ProfileConverter.getProfileFromSaveString(playerColorsContents.get(x));
            playerColorsProfileList.addNewProfileAsIs(currentProfile);
        }

        // Merge initialProfileList and playerColorsProfileList
        for (int x = 0; x < playerColorsProfileList.size(); x++) {
            Profile playerProfile = playerColorsProfileList.getProfileByIndex(x).cloneProfile();
            Boolean playerProfileNotHandled = true;

            for (int y = 0; y < initialProfileList.size(); y++) {
                Profile currentCustomProfile = initialProfileList.getProfileByIndex(y).cloneProfile();

                if (playerProfile.equals(currentCustomProfile)) {
                    mergedProfileList.addNewProfileAsIs(currentCustomProfile);
                    initialProfileList.delete(currentCustomProfile.getPrimaryKey());
                    playerProfileNotHandled = false;
                    break;

                } else if (playerProfile.equalsColors(currentCustomProfile)) {
                    if (!(playerProfile.getName().equals(Profile.INITIALIZATION_NAME)
                            && playerProfile.getPrimaryKey() != Profile.INITIALIZATION_KEY)) {
                        mergedProfileList.addNewProfileAsIs(currentCustomProfile);
                        initialProfileList.delete(currentCustomProfile.getPrimaryKey());

                    } else {
                        mergedProfileList.addNewProfileAsIs(playerProfile);
                    }
                    playerProfileNotHandled = false;
                    break;
                }
            }

            if(playerProfileNotHandled) {
                mergedProfileList.addNewProfile(playerProfile.cloneProfile());
            }
        }
        mergedProfileList.addAllNewProfilesAsIs(initialProfileList.clone());
        mergedProfileList = mergedProfileList.sortProfileListByPrimaryKey(mergedProfileList);

        return mergedProfileList;
    }

    public void saveFactionProfiles(Faction faction,
                                    List<Integer> profileConfiguration,
                                    ProfileList profileList) {

        // Read Current PlayerColors Contents
        List<String> playerColorsContents = readPlayerColorsContents();
        if (playerColorsContents == null) {
            return;
        }

        // Create Faction-Specific Profile List
        ProfileList factionProfiles = new ProfileList();
        for (Integer primaryKey : profileConfiguration) {
            factionProfiles.addNewProfile(profileList.getProfileByPrimaryKey(primaryKey).cloneProfile());
        }

        // Replace Appropriate Lines of PlayerColors
        for (int x = getFactionStartIndex(faction); x < getFactionEndIndex(faction); x++) {
            int profileIndex = x - getFactionStartIndex(faction);
            int colorNumber = x - 3;
            String saveString = ProfileConverter
                    .getSaveStringFromProfile(factionProfiles.getProfileByIndex(profileIndex), colorNumber + 1);
            playerColorsContents.set(x, saveString);
        }

        // Write to PlayerColors
        try {
            PrintWriter writer = new PrintWriter(getGamePlayerColorsFile(), "UTF-8");
            playerColorsContents.forEach(writer::println);
            writer.close();
        } catch (IOException exception) {
            // Do Nothing
        }
    }

    public String loadInstallationDirectory() {
        String installationDirectory = null;

        if (INSTALLATION_FILE.exists()) {
            String directory = readInstallationDirectoryFromSaveFile();
            if (directory != null && isValidHaloWarsInstallation(directory)) {
                installationDirectory = directory;
            }
        } else {
            return null;
        }

        return installationDirectory;
    }

    public boolean saveInstallationDirectory(String directory) {
        if (INSTALLATION_FILE.exists()) {
            if(!writeInstallationDirectoryToSaveFile(directory))
                return false;
        } else {
            if(!createInstallationDirectorySaveFile())
                return false;
            if(!writeInstallationDirectoryToSaveFile(directory))
                return false;
        }

        return true;
    }

    public static boolean isValidHaloWarsInstallation(String directory) {
        File installationDirectory = new File(directory);
        boolean launcherFound = false;
        boolean creviceFound = false;

        for (File file : installationDirectory.listFiles()) {

            if (file.getName().equals("xgameFinal.exe")) {
                launcherFound = true;
            }

            if (file.getName().equals("crevice.era")) {
                creviceFound = true;
            }
        }

        return launcherFound && creviceFound;
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

    private Integer getFactionStartIndex(Faction faction) {
        if (faction == Faction.UNSC) {
            return UNSC_PROFILE_START_INDEX;
        } else {
            return COVENANT_PROFILE_START_INDEX;
        }
    }

    private Integer getFactionEndIndex(Faction faction) {
        if (faction == Faction.UNSC) {
            return UNSC_PROFILE_END_INDEX;
        } else {
            return COVENANT_PROFILE_END_INDEX;
        }
    }

    private List<String> readPlayerColorsContents() {
        File playerColorsFile = getGamePlayerColorsFile();
        if (playerColorsFile == null) {
            // TODO - Create Player Colors File
            return null;
        }

        List<String> playerColorsContents = new ArrayList<>();
        BufferedReader bufferedReader;

        try {
            bufferedReader = new BufferedReader(new FileReader(playerColorsFile));
            String line = bufferedReader.readLine();

            while (line != null) {
                playerColorsContents.add(line);
                line = bufferedReader.readLine();
            }

            bufferedReader.close();
        } catch (IOException exception) {
            return null;
        }

        return playerColorsContents;
    }

    private File getGamePlayerColorsFile() {
        if (!isSavedInstallationDirectoryValid()) {
            return null;
        }

        File gameProfileFile = new File(loadInstallationDirectory() + "\\" + PLAYER_COLORS_PATH);
        if (!gameProfileFile.exists()) {
            return null;
        }

        return gameProfileFile;
    }

    private boolean isSavedInstallationDirectoryValid() {
        String savedDirectory = loadInstallationDirectory();
        return loadInstallationDirectory() != null && isValidHaloWarsInstallation(savedDirectory);
    }

    private static boolean createInstallationDirectorySaveFile() {
        try {
            INSTALLATION_FILE.createNewFile();
        } catch (IOException exception) {
            return false;
        }
        return true;
    }

    private static String readInstallationDirectoryFromSaveFile() {
        String installationDirectory = null;
        BufferedReader bufferedReader;

        try {
            bufferedReader = new BufferedReader(new FileReader(INSTALLATION_FILE));
            String directory = bufferedReader.readLine();

            if (directory != null && directory.trim().length() > 0) {
                installationDirectory = directory;
            }

            bufferedReader.close();
        } catch (IOException exception) {
            // Do Nothing
        }

        return installationDirectory;
    }

    private static boolean writeInstallationDirectoryToSaveFile(String directory) {
        try {
            PrintWriter writer = new PrintWriter(INSTALLATION_FILE, "UTF-8");
            writer.println(directory);
            writer.close();
        } catch (IOException exception) {
            return false;
        }

        return true;
    }

    private static String getCurrentDirectory() {
        return System.getProperty("user.dir");
    }

}
