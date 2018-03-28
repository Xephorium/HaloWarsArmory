package com.xephorium.armory;

import com.xephorium.armory.model.ColorProfile;
import com.xephorium.armory.repository.MockColorProfileRepository;
import com.xephorium.armory.ui.ArmoryWindow;

public class HaloWarsArmory implements ArmoryWindow.ArmoryWindowListener {


    /*--- Variables ---*/

    private ArmoryWindow armoryWindow;


    /*--- Constructor ---*/

    public HaloWarsArmory() {
        armoryWindow = new ArmoryWindow(this);
        armoryWindow.displayWindow();

        ColorProfile[] mockColorProfileList = MockColorProfileRepository.getProfileList();
        armoryWindow.updateColorProfileList(mockColorProfileList);
    }


    /*--- Armory Window Overrides ---*/

    @Override
    public void handleBrowseButtonClick() {
        armoryWindow.displayDirectoryChooser();
    }

    @Override
    public void handleDirectorySelection(String directory) {
        if (true /* Valid Halo Wars Installation */) {
            armoryWindow.setValidInstallDirectory(directory);
            armoryWindow.displayGameFoundDialog();
        } else {
            armoryWindow.setInvalidInstallDirectory();
            armoryWindow.displayGameNotFoundDialog();
        }
    }
}