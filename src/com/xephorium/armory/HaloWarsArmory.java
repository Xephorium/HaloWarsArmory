package com.xephorium.armory;

import com.xephorium.armory.model.Profile;
import com.xephorium.armory.model.ProfileList;
import com.xephorium.armory.repository.GameRepository;
import com.xephorium.armory.repository.ProfileRepository;
import com.xephorium.armory.ui.ArmoryWindow;
import com.xephorium.armory.ui.ArmoryWindowListener;
import com.xephorium.armory.ui.utility.ColorChooser.ColorChooserListener;
import com.xephorium.armory.ui.utility.StringUtility;

import java.awt.*;
import java.util.List;

class HaloWarsArmory implements ArmoryWindowListener {


    /*--- Variables ---*/

    private GameRepository gameRepository;
    private ProfileRepository profileRepository;

    private ProfileList profileList;
    private List<Integer> unscPlayerConfiguration;
    private List<Integer> covenantPlayerConfiguration;

    private ArmoryWindow armoryWindow;


    /*--- Constructor ---*/

    HaloWarsArmory() {
        gameRepository = new GameRepository();
        profileRepository = new ProfileRepository();
        armoryWindow = new ArmoryWindow(this);

        profileList = new ProfileList();
        profileList.addAllNewProfiles(profileRepository.loadCustomPlayerProfileList());
        profileList.addAllNewProfiles(profileRepository.getDefaultUNSCPlayerProfiles());
        profileList.addAllNewProfiles(profileRepository.getDefaultCovenantPlayerProfiles());

        unscPlayerConfiguration = profileRepository.loadCustomUNSCPlayerConfiguration();
        covenantPlayerConfiguration = profileRepository.loadCustomCovenantPlayerConfiguration();

        armoryWindow.displayWindow();
        armoryWindow.updateProfileList(profileList);
        armoryWindow.updateUNSCPlayerConfiguration(unscPlayerConfiguration);
        armoryWindow.updateCovenantPlayerConfiguration(covenantPlayerConfiguration);
        armoryWindow.setValidInstallDirectory(gameRepository.loadInstallationDirectory());
    }


    /*--- Armory Window Overrides ---*/


    // Installation Directory

    @Override
    public void handleBrowseButtonClick() {
        armoryWindow.displayDirectoryChooser();
    }

    @Override
    public void handleDirectorySelection(String directory) {
        if (GameRepository.isValidHaloWarsInstallation(directory)) {
            gameRepository.saveInstallationDirectory(directory);
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
        unscPlayerConfiguration = profileRepository.getDefaultUNSCPlayerConfiguration();
        profileList.addDefaultFactionProfiles(profileRepository.getDefaultUNSCPlayerProfiles());
        armoryWindow.updateProfileList(profileList);
        armoryWindow.updateUNSCPlayerConfiguration(unscPlayerConfiguration);
    }

    @Override
    public void handleCovenantConfigurationReset() {
        covenantPlayerConfiguration = profileRepository.getDefaultCovenantPlayerConfiguration();
        profileList.addDefaultFactionProfiles(profileRepository.getDefaultCovenantPlayerProfiles());
        armoryWindow.updateProfileList(profileList);
        armoryWindow.updateCovenantPlayerConfiguration(covenantPlayerConfiguration);
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
        Profile newProfile = new Profile(profileList.generateNewName());
        profileList.addNewProfileTop(newProfile);
        armoryWindow.updateProfileList(profileList);
        armoryWindow.selectNewProfile(profileList.getProfileByIndex(0));
        profileRepository.saveCustomPlayerProfile(profileList.getProfileByIndex(0));
    }

    @Override
    public void handleDeleteProfileClick(int primaryKey) {
        // TODO - Prompt For Delete?
        profileList.delete(primaryKey);
        armoryWindow.updateProfileList(profileList);

        if (!profileRepository.isDefaultProfilePrimaryKey(primaryKey)) {
            profileRepository.deleteCustomPlayerProfile(primaryKey);
        }
    }

    @Override
    public void handleWorkingProfileSaveClick(Profile profile) {
        Profile newProfile = profile.cloneProfile();
        boolean saveSuccessful = true;

        if (StringUtility.isBlank(newProfile.getName())) {
            armoryWindow.displayProfileMustHaveNameDialog();
            return;
        }

        if (newProfile.equals(profileList.getProfileByPrimaryKey(newProfile.getPrimaryKey()))) {
            armoryWindow.displayNoChangesToSaveDialog();
            return;
        }

        profileList.updateExistingProfile(newProfile);
        armoryWindow.updateProfileList(profileList);

        if (!profileRepository.isDefaultProfilePrimaryKey(profile.getPrimaryKey())) {
            saveSuccessful = profileRepository.saveCustomPlayerProfile(profile);
        }

        if (saveSuccessful) {
            armoryWindow.displayProfileSavedDialog();
        } else {
            // TODO - Show Error Saving Dialog
        }
    }

    @Override
    public void handleWorkingProfileColorClick(Profile workingProfile, Profile.ColorType colorType) {
        armoryWindow.displayColorChooserDialog(workingProfile.getColor(colorType), new ColorChooserListener() {
            @Override
            public void onColorSelection(Color color) {
                workingProfile.setColor(colorType, color);
                armoryWindow.setWorkingProfile(workingProfile);
            }

            @Override
            public void onDialogClose() {
                // Do Nothing
            }
        });
    }
}