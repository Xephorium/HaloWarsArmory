package com.xephorium.armory;

import com.xephorium.armory.model.Faction;
import com.xephorium.armory.model.Profile;
import com.xephorium.armory.model.ProfileList;
import com.xephorium.armory.repository.GameRepository;
import com.xephorium.armory.repository.CustomProfileRepository;
import com.xephorium.armory.ui.ArmoryWindow;
import com.xephorium.armory.ui.ArmoryWindowListener;
import com.xephorium.armory.ui.utility.ColorChooser.ColorChooserListener;
import com.xephorium.armory.ui.utility.StringUtility;
import com.xephorium.armory.ui.utility.VerifyActionDialog;

import java.awt.*;
import java.util.List;

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

        profileList = new ProfileList();
        profileList.addAllNewProfiles(customProfileRepository.loadCustomPlayerProfileList());
        profileList.addAllNewProfiles(gameRepository.getDefaultUNSCPlayerProfiles());
        profileList.addAllNewProfiles(gameRepository.getDefaultCovenantPlayerProfiles());
        profileList = gameRepository.mergeInitialProfilesWithSavedProfiles(profileList);

        unscPlayerConfiguration = gameRepository.loadSavedFactionConfiguration(Faction.UNSC, profileList);
        covenantPlayerConfiguration = gameRepository.loadSavedFactionConfiguration(Faction.COVENANT, profileList);

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
        unscPlayerConfiguration = gameRepository.getDefaultUNSCPlayerConfiguration();
        profileList.addDefaultFactionProfiles(gameRepository.getDefaultUNSCPlayerProfiles());
        armoryWindow.updateProfileList(profileList);
        armoryWindow.updateUNSCPlayerConfiguration(unscPlayerConfiguration);
    }

    @Override
    public void handleCovenantConfigurationReset() {
        covenantPlayerConfiguration = gameRepository.getDefaultCovenantPlayerConfiguration();
        profileList.addDefaultFactionProfiles(gameRepository.getDefaultCovenantPlayerProfiles());
        armoryWindow.updateProfileList(profileList);
        armoryWindow.updateCovenantPlayerConfiguration(covenantPlayerConfiguration);
    }

    @Override
    public void handleUNSCConfigurationSave() {
        if(gameRepository.saveFactionProfiles(Faction.UNSC, unscPlayerConfiguration, profileList)) {
            armoryWindow.displayConfigurationSavedDialog();
        } else {
            armoryWindow.displayProblemSavingDialog();
        }
    }

    @Override
    public void handleCovenantConfigurationSave() {
        if (gameRepository.saveFactionProfiles(Faction.COVENANT, covenantPlayerConfiguration, profileList)) {
            armoryWindow.displayConfigurationSavedDialog();
        } else {
            armoryWindow.displayProblemSavingDialog();
        }
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
        customProfileRepository.saveCustomPlayerProfile(profileList.getProfileByIndex(0));
    }

    @Override
    public void handleDeleteProfileClick(int primaryKey) {
        armoryWindow.displayDeleteProfileDialog(new VerifyActionDialog.VerifyActionListener() {
            @Override
            public void onVerifyActionSelection() {
                profileList.delete(primaryKey);
                armoryWindow.updateProfileList(profileList);

                if (!gameRepository.isDefaultProfilePrimaryKey(primaryKey)) {
                    customProfileRepository.deletePlayerProfile(primaryKey);
                }
            }

            @Override
            public void onCancelActionSelection() {}

            @Override
            public void onDialogClose() {}
        }, profileList.getProfileByPrimaryKey(primaryKey).getName());
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
}