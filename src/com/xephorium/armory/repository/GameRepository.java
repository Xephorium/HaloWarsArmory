package com.xephorium.armory.repository;

import java.io.*;

public class GameRepository {


    /*--- Variables ---*/

    private static final String INSTALLATION_FILE_NAME = "InstallationDirectory.txt";
    private static final File INSTALLATION_FILE = new File(getCurrentDirectory() + "\\" + INSTALLATION_FILE_NAME);


    /*--- Public Methods ---*/

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
