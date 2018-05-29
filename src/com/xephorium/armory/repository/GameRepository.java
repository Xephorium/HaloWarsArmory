package com.xephorium.armory.repository;

import com.xephorium.armory.model.Faction;
import com.xephorium.armory.model.ProfileList;

import java.io.*;
import java.util.*;

public class GameRepository {


    /*--- Variables ---*/

    private static final String GAME_PROFILES_FILE_PATH = "overrides\\data\\playercolors.xml";
    private static final String INSTALLATION_FILE_NAME = "InstallationDirectory.txt";
    private static final File INSTALLATION_FILE = new File(getCurrentDirectory() + "\\" + INSTALLATION_FILE_NAME);


    /*--- Public Methods ---*/

    public void resetGameProfileConfiguration(Faction faction,
                                              List<Integer> profileConfiguration,
                                              ProfileList profileList) {
        File gameProfileConfigurationFile = getGameProfileConfigurationFile();
        if (gameProfileConfigurationFile == null) {
            // TODO - Create Player Colors File
            return;
        }

    }

    public void saveGameProfileConfiguration(Faction faction,
                                             List<Integer> profileConfiguration,
                                             ProfileList profileList) {
        File gameProfileConfigurationFile = getGameProfileConfigurationFile();
        if (gameProfileConfigurationFile == null) {
            // TODO - Create Player Colors File
            return;
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


    /*--- Private Methods ---*/

    private File getGameProfileConfigurationFile() {
        if (!isSavedInstallationDirectoryValid()) {
            return null;
        }

        File gameProfileFile = new File(loadInstallationDirectory() + "\\" + GAME_PROFILES_FILE_PATH);
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
