package com.xephorium.armory;

import com.xephorium.armory.model.Profile;
import com.xephorium.armory.model.ProfileList;
import com.xephorium.armory.repository.ArmoryRepository;
import com.xephorium.armory.ui.ArmoryWindow;
import com.xephorium.armory.ui.ArmoryWindowListener;
import com.xephorium.armory.ui.utility.ColorChooser.ColorChooserListener;
import com.xephorium.armory.ui.utility.StringUtility;

import java.awt.*;
import java.util.List;

public class HaloWarsArmory implements ArmoryWindowListener {


    /*--- Variables ---*/

    ArmoryRepository armoryRepository;

    private ProfileList profileList;
    private List<Integer> unscPlayerConfiguration;
    private List<Integer> covenantPlayerConfiguration;

    private ArmoryWindow armoryWindow;


    /*--- Constructor ---*/

    public HaloWarsArmory() {
        armoryRepository = new ArmoryRepository();
        armoryWindow = new ArmoryWindow(this);

        profileList = armoryRepository.loadPlayerProfileList();
        unscPlayerConfiguration = armoryRepository.loadCustomUNSCPlayerConfiguration();
        covenantPlayerConfiguration = armoryRepository.loadCustomUNSCPlayerConfiguration();

        armoryWindow.displayWindow();
        armoryWindow.updateProfileList(profileList);
        armoryWindow.updateUNSCPlayerConfiguration(unscPlayerConfiguration);
        armoryWindow.updateCovenantPlayerConfiguration(covenantPlayerConfiguration);
    }


    /*--- Armory Window Overrides ---*/


    // Installation Directory

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


    // Faction Configuration

    @Override
    public void handleUNSCPlayerUpdate(int playerNumber, int profilePrimaryKey) {
        unscPlayerConfiguration.set(playerNumber, profilePrimaryKey);
    }

    @Override
    public void handleCovenantPlayerUpdate(int playerNumber, int profilePrimaryKey) {
        covenantPlayerConfiguration.set(playerNumber, profilePrimaryKey);
    }

    @Override
    public void handleUNSCConfigurationReset() {
        unscPlayerConfiguration = armoryRepository.loadDefaultUNSCPlayerConfiguration();
        armoryWindow.updateUNSCPlayerConfiguration(unscPlayerConfiguration);
        // TODO - Write Changes to File
    }

    @Override
    public void handleCovenantConfigurationReset() {
        covenantPlayerConfiguration = armoryRepository.loadDefaultCovenantPlayerConfiguration();
        armoryWindow.updateCovenantPlayerConfiguration(covenantPlayerConfiguration);
        // TODO - Write Changes to File
    }

    @Override
    public void handleUNSCConfigurationSave() {
        // TODO - Write Changes to File
    }

    @Override
    public void handleCovenantConfigurationSave() {
        // TODO - Write Changes to File
    }


    // Profile Configuration

    @Override
    public void handleSelectProfileClick(Profile profile) {
        armoryWindow.setSelectedProfile(profile);
    }

    @Override
    public void handleAddProfileClick() {
        Profile newProfile = new Profile(getNewProfileName());
        profileList.addTop(newProfile);
        armoryWindow.updateProfileList(profileList);
        armoryWindow.selectNewProfile(newProfile);
        // TODO - Write Changes to File
    }

    @Override
    public void handleDeleteProfileClick(int primaryKey) {
        // TODO - Prompt For Delete
        profileList.delete(primaryKey);
        armoryWindow.updateProfileList(profileList);
        // TODO - Write Changes to File
    }

    @Override
    public void handleWorkingProfileSaveClick(Profile newProfile) {
        if (StringUtility.isBlank(newProfile.getName())) {
            armoryWindow.displayProfileMustHaveNameDialog();
            return;
        }

        if (newProfile.equals(profileList.getProfileByPrimaryKey(newProfile.getPrimaryKey()))) {
            armoryWindow.displayNoChangesToSaveDialog();
            return;
        }

        profileList.add(newProfile);
        armoryWindow.updateProfileList(profileList);
        armoryWindow.displayProfileSavedDialog();
        // TODO - Write Changes to File
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

    /*--- Private Methods ---*/

    private String getNewProfileName() {
        return "New Profile";
    }
}