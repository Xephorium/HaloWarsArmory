package com.xephorium.armory.ui;

import com.xephorium.armory.ui.ProfileBrowsePanel.ProfileBrowsePanelListener;
import com.xephorium.armory.ui.ProfileAttributePanel.ProfileAttributePanelListener;
import com.xephorium.armory.model.Profile;
import com.xephorium.armory.ui.resource.color.ArmoryColor;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ProfileConfigurationPanel extends JPanel implements
        ProfileAttributePanelListener,
        ProfileBrowsePanelListener {


    /*--- Variables ---*/

    private ProfileConfigurationPanelListener listener;
    private ProfileBrowsePanel profileBrowsePanel;
    private ProfileAttributePanel profileAttributePanel;


    /*--- Constructor ---*/

    public ProfileConfigurationPanel(ProfileConfigurationPanelListener listener) {
        super();
        this.listener = listener;

        initializePanelAttributes();
        initializeViewClasses();

        this.add(profileBrowsePanel, BorderLayout.CENTER);
        this.add(profileAttributePanel, BorderLayout.LINE_END);
    }


    /*--- Public Methods ---*/

    public void updateProfileList(Profile[] profileList) {
        profileBrowsePanel.updateProfileBrowsePanel(profileList);
    }

    public void setSelectedProfile(Profile profile) {
        profileBrowsePanel.setSelectedProfile(profile);
        profileAttributePanel.setWorkingProfile(profile);
    }

    public void setWorkingProfile(Profile profile) {
        profileAttributePanel.setWorkingProfile(profile);
    }

    public void setWorkingProfileName(String name) {
        this.profileAttributePanel.setWorkingProfileName(name);
    }

    public void setWorkingProfileColor(Profile.ColorType colorType, Color color) {
        profileAttributePanel.setWorkingProfileColor(colorType, color);
    }

    public void setWorkingProfileColors(Color[] colors) {
        profileAttributePanel.setWorkingProfileColors(colors);
    }


    /*--- Private Methods ---*/

    private void initializePanelAttributes() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
        this.setBorder(new CompoundBorder(
                BorderFactory.createLineBorder(ArmoryColor.WINDOW_BORDER_COLOR_LIGHT),
                new EmptyBorder(15, 15, 15, 15)
        ));
    }

    private void initializeViewClasses() {
        profileBrowsePanel = new ProfileBrowsePanel(this);
        profileAttributePanel = new ProfileAttributePanel(this);
    }


    /*--- Interface Overrides ---*/

    @Override
    public void handleWorkingProfileSaveClick(Profile newProfile) {
        listener.handleWorkingProfileSaveClick(newProfile);
    }

    @Override
    public void handleProfileSelection(Profile profile) {
        listener.handleProfileSelection(profile);
    }

    /*--- Listener Interface ---*/

    interface ProfileConfigurationPanelListener {

        void handleWorkingProfileSaveClick(Profile newProfile);

        void handleProfileSelection(Profile profile);
    }
}
