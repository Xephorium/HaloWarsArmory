package com.xephorium.armory;

import com.xephorium.armory.model.Faction;
import com.xephorium.armory.model.Profile;
import com.xephorium.armory.model.ProfileList;
import com.xephorium.armory.repository.GameRepository;
import com.xephorium.armory.repository.CustomProfileRepository;
import com.xephorium.armory.repository.SystemRepository;
import com.xephorium.armory.ui.ArmoryWindow;
import com.xephorium.armory.ui.ArmoryWindowListener;
import com.xephorium.armory.ui.utility.ColorChooser.ColorChooserListener;
import com.xephorium.armory.ui.utility.StringUtility;
import com.xephorium.armory.ui.utility.VerifyActionDialog;

import java.awt.*;
import java.util.List;

/*
 * Chris Cruzen                            August 2019
 * Halo Wars Armory
 *
 *   HaloWarsArmory is the state-aware presenter layer
 * of Halo Wars Armory. Its role is to handle all user
 * interactions and orchestrate communication between
 * the view and repository layers. This is the command
 * center of the application.
 *
 */


class HaloWarsArmory implements ArmoryWindowListener {


    /*--- Variables ---*/

    private GameRepository gameRepository;
    private CustomProfileRepository customProfileRepository;

    private ProfileList profileList;
    private List<Integer> unscPlayerConfiguration;
    private List<Integer> covenantPlayerConfiguration;

    private ArmoryWindow armoryWindow;


    /*--- Constructor ---*/

    HaloWarsArmory() {
        gameRepository = new GameRepository();
        customProfileRepository = new CustomProfileRepository();
        armoryWindow = new ArmoryWindow(this);

        ProfileList startupProfileList = createStartupProfileList();

        if (gameRepository.isInstallationDirectorySet()) {
            gameRepository.setupPlayerColorsFile();
            profileList = gameRepository.mergeStartupProfileListWithCurrentGameConfiguration(startupProfileList);

            if (profileList.size() > startupProfileList.size()) {
                ProfileList unsavedProfileList = startupProfileList.getDifference(profileList);
                customProfileRepository.saveCustomPlayerProfileList(unsavedProfileList);
            }

            unscPlayerConfiguration = gameRepository.loadSavedFactionConfiguration(Faction.UNSC, profileList);
            covenantPlayerConfiguration = gameRepository.loadSavedFactionConfiguration(Faction.COVENANT, profileList);
        } else {
            profileList = startupProfileList;
            unscPlayerConfiguration = gameRepository.getDefaultUNSCPlayerConfiguration();
            covenantPlayerConfiguration = gameRepository.getDefaultCovenantPlayerConfiguration();
        }

        armoryWindow.displayWindow();
        armoryWindow.updateProfileList(profileList);
        armoryWindow.updateUNSCPlayerConfiguration(unscPlayerConfiguration);
        armoryWindow.updateCovenantPlayerConfiguration(covenantPlayerConfiguration);
        armoryWindow.setValidInstallDirectory(gameRepository.loadInstallationDirectory());

        if (!gameRepository.isInstallationDirectorySet()) {
            armoryWindow.disableConfigurationEdit();
        }
    }


    /*--- Armory Window Overrides ---*/


    // Installation Directory

    @Override
    public void handleBrowseButtonClick() {
        armoryWindow.displayDirectoryChooser();
    }

    @Override
    public void handleDirectorySelection(String directory) {
        Boolean isPreviousDirectoryValid = gameRepository.isInstallationDirectorySet() &&
                GameRepository.isValidHaloWarsInstallation(gameRepository.loadInstallationDirectory());

        if (!GameRepository.isValidHaloWarsInstallation(directory) && !isPreviousDirectoryValid) {
            armoryWindow.setInvalidInstallDirectory();
            armoryWindow.displayGameNotFoundDialog();

        } else if (!GameRepository.isUserLocalDirectoryFound()) {
            armoryWindow.displayUserLocalNotFoundDialog(SystemRepository.getUsername());

        } else if (!GameRepository.isValidHaloWarsInstallation(directory)) {
            gameRepository.setupModManifestFile(directory);
            gameRepository.setupPlayerColorsFile();
            armoryWindow.displayInstallationFallbackDialog();
            armoryWindow.enableConfigurationEdit();

        } else {
            gameRepository.saveInstallationDirectory(directory);
            gameRepository.setupModManifestFile(directory);
            gameRepository.setupPlayerColorsFile();
            armoryWindow.setValidInstallDirectory(directory);
            armoryWindow.displayGameFoundDialog();
            armoryWindow.enableConfigurationEdit();
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
        if (gameRepository.isInstallationDirectorySet()) {
            unscPlayerConfiguration = gameRepository.getDefaultUNSCPlayerConfiguration();
            profileList.addDefaultFactionProfiles(gameRepository.getDefaultUNSCPlayerProfiles());
            armoryWindow.updateProfileList(profileList);
            armoryWindow.updateUNSCPlayerConfiguration(unscPlayerConfiguration);
        } else {
            armoryWindow.displayNoInstallationSetDialog();
        }
    }

    @Override
    public void handleCovenantConfigurationReset() {
        if (gameRepository.isInstallationDirectorySet()) {
            covenantPlayerConfiguration = gameRepository.getDefaultCovenantPlayerConfiguration();
            profileList.addDefaultFactionProfiles(gameRepository.getDefaultCovenantPlayerProfiles());
            armoryWindow.updateProfileList(profileList);
            armoryWindow.updateCovenantPlayerConfiguration(covenantPlayerConfiguration);
        } else {
            armoryWindow.displayNoInstallationSetDialog();
        }
    }

    @Override
    public void handleUNSCConfigurationSave() {
        if (gameRepository.isInstallationDirectorySet()) {
            if (gameRepository.saveFactionProfiles(Faction.UNSC, unscPlayerConfiguration, profileList)) {
                armoryWindow.displayConfigurationSavedDialog();
            } else {
                armoryWindow.displayProblemSavingDialog();
            }
        } else {
            armoryWindow.displayNoInstallationSetDialog();
        }
    }

    @Override
    public void handleCovenantConfigurationSave() {
        if (gameRepository.isInstallationDirectorySet()) {
            if (gameRepository.saveFactionProfiles(Faction.COVENANT, covenantPlayerConfiguration, profileList)) {
                armoryWindow.displayConfigurationSavedDialog();
            } else {
                armoryWindow.displayProblemSavingDialog();
            }
        } else {
            armoryWindow.displayNoInstallationSetDialog();
        }
    }


    // Profile Configuration

    @Override
    public void handleSelectProfileClick(Profile profile) {
        armoryWindow.setSelectedProfile(profile);

        if (gameRepository.isDefaultProfilePrimaryKey(profile.getPrimaryKey())) {
            armoryWindow.disableProfileEdit();
        } else {
            armoryWindow.enableProfileEdit();
        }
    }

    @Override
    public void handleAddProfileClick() {
        if (gameRepository.isInstallationDirectorySet()) {
            Profile newProfile = new Profile(profileList.generateNewName());
            profileList.addNewProfileTop(newProfile);
            armoryWindow.updateProfileList(profileList);
            armoryWindow.selectNewProfile(profileList.getProfileByIndex(0));
            customProfileRepository.saveCustomPlayerProfile(profileList.getProfileByIndex(0));
        } else {
            armoryWindow.displayNoInstallationSetDialog();
        }
    }

    @Override
    public void handleDeleteProfileClick(Profile profile) {
        armoryWindow.displayDeleteProfileDialog(new VerifyActionDialog.VerifyActionListener() {
            @Override
            public void onVerifyActionSelection() {
                profileList.deleteByPrimaryKey(profile.getPrimaryKey());
                armoryWindow.updateProfileList(profileList);

                if (!gameRepository.isDefaultProfilePrimaryKey(profile.getPrimaryKey())) {
                    customProfileRepository.deleteCustomPlayerProfile(profile);
                }
            }

            @Override
            public void onCancelActionSelection() {
            }

            @Override
            public void onDialogClose() {
            }
        }, profileList.getProfileByPrimaryKey(profile.getPrimaryKey()).getName());
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

        if (!gameRepository.isDefaultProfilePrimaryKey(profile.getPrimaryKey())) {
            saveSuccessful = customProfileRepository.saveCustomPlayerProfile(profile);
        }

        if (unscPlayerConfiguration.contains(newProfile.getPrimaryKey())) {
            if (!gameRepository.saveFactionProfiles(Faction.UNSC, unscPlayerConfiguration, profileList)) {
                saveSuccessful = false;
            }
        }

        if (covenantPlayerConfiguration.contains(newProfile.getPrimaryKey())) {
            if (!gameRepository.saveFactionProfiles(Faction.COVENANT, covenantPlayerConfiguration, profileList)) {
                saveSuccessful = false;
            }
        }

        if (saveSuccessful) {
            armoryWindow.displayProfileSavedDialog();
        } else {
            armoryWindow.displayProblemSavingDialog();
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


    /*--- Private Utility Methods ---*/

    private ProfileList createStartupProfileList() {
        ProfileList savedProfileList = new ProfileList();

        if (gameRepository.isInstallationDirectorySet()) {
            savedProfileList.addAllNewProfiles(customProfileRepository.loadCustomPlayerProfileList());
        }

        savedProfileList.addAllNewProfiles(gameRepository.getDefaultUNSCPlayerProfiles());
        savedProfileList.addAllNewProfiles(gameRepository.getDefaultCovenantPlayerProfiles());

        return savedProfileList;
    }
}