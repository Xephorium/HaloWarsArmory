package com.xephorium.armory.ui;

import com.xephorium.armory.model.Profile;

public interface ArmoryWindowListener {


    // Install Directory Panel

    void handleBrowseButtonClick();

    void handleDirectorySelection(String directory);


    // Faction Configuration Panel

    void handleUNSCPlayerUpdate(int playerNumber, int profilePrimaryKey);

    void handleCovenantPlayerUpdate(int playerNumber, int profilePrimaryKey);

    void handleUNSCConfigurationReset();

    void handleCovenantConfigurationReset();

    void handleUNSCConfigurationSave();

    void handleCovenantConfigurationSave();


    // Profile Configuration Panel

    void handleSelectProfileClick(Profile profile);

    void handleAddProfileClick();

    void handleDeleteProfileClick(int primaryKey);

    void handleWorkingProfileSaveClick(Profile newProfile);

    void handleWorkingProfileColorClick(Profile workingProfile, Profile.ColorType colorType);
}
