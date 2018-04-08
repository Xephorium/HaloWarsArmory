package com.xephorium.armory.repository;

import com.xephorium.armory.model.Profile;
import com.xephorium.armory.model.ProfileList;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GameRepository {


    /*--- Variables ---*/


    /*--- Constructor ---*/

    public GameRepository() {
        // TODO - Setup File Read/Write
    }


    /*--- Public Methods ---*/

    public boolean isValidHaloWarsInstallation(String directory) {
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

}
