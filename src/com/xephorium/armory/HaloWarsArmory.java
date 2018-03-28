package com.xephorium.armory;

import com.xephorium.armory.model.Profile;
import com.xephorium.armory.repository.MockProfileRepository;
import com.xephorium.armory.ui.ArmoryWindow;
import com.xephorium.armory.ui.utility.ColorChooser.ColorChooserListener;

import java.awt.*;

public class HaloWarsArmory implements ArmoryWindow.ArmoryWindowListener {


    /*--- Variables ---*/

    private ArmoryWindow armoryWindow;
    Profile[] profileList;


    /*--- Constructor ---*/

    public HaloWarsArmory() {
        armoryWindow = new ArmoryWindow(this);
        armoryWindow.displayWindow();

        profileList = MockProfileRepository.getProfileList();
        armoryWindow.updateProfileList(profileList);
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

    @Override
    public void handleWorkingProfileSaveClick(Profile newProfile) {
        profileList = Profile.getUpdatedProfileList(profileList, newProfile);
        armoryWindow.updateProfileList(profileList);
        // TODO - Write Changes to File
    }

    @Override
    public void handleProfileSelection(Profile profile) {
        armoryWindow.setSelectedProfile(profile);
    }

    @Override
    public void handleWorkingProfileColorClick(Profile workingProfile, Profile.ColorType colorType) {
        armoryWindow.displayColorChooserDialog(workingProfile.getColor(colorType), new ColorChooserListener() {
            @Override
            public void onColorSelection(Color color) {
                workingProfile.setColor(colorType, color);
                armoryWindow.setWorkingProfile(workingProfile);
                // TODO - Write Changes to File
            }

            @Override
            public void onDialogClose() {
                // Do Nothing
            }
        });
    }
}