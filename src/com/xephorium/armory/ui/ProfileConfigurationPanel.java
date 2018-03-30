package com.xephorium.armory.ui;

import com.xephorium.armory.model.ProfileList;
import com.xephorium.armory.model.Profile;
import com.xephorium.armory.ui.resource.color.ArmoryColor;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ProfileConfigurationPanel extends JPanel {


    /*--- Variables ---*/

    private ArmoryWindowListener listener;
    private ProfileBrowsePanel profileBrowsePanel;
    private ProfileAttributePanel profileAttributePanel;
    private EmptyProfileAttributePanel emptyProfileAttributePanel;


    /*--- Constructor ---*/

    public ProfileConfigurationPanel(ArmoryWindowListener listener) {
        super();
        this.listener = listener;

        initializePanelAttributes();
        initializeViewClasses();

        this.add(profileBrowsePanel, BorderLayout.CENTER);
        this.add(profileAttributePanel, BorderLayout.LINE_END);
    }


    /*--- Public Methods ---*/

    public void updateProfileList(ProfileList profileList) {
        if (profileList.isEmpty()) {
            showEmptyProfileAttributePanel();
        } else {
            showPopulatedProfileAttributePanel();
        }

        profileBrowsePanel.updateProfileBrowsePanel(profileList);
    }

    public void setSelectedProfile(Profile profile) {
        profileBrowsePanel.setSelectedProfile(profile);
        profileAttributePanel.setWorkingProfile(profile);
    }

    public void selectNewProfile(Profile profile) {
        profileBrowsePanel.selectNewProfile(profile);
        profileAttributePanel.setWorkingProfile(profile);
    }

    public void setWorkingProfile(Profile profile) {
        profileAttributePanel.setWorkingProfile(profile);
    }


    /*--- Private Methods ---*/

    private void showPopulatedProfileAttributePanel() {
        this.remove(emptyProfileAttributePanel);
        this.add(profileAttributePanel, BorderLayout.LINE_END);
        this.repaint();
    }

    private void showEmptyProfileAttributePanel() {
        this.remove(profileAttributePanel);
        this.add(emptyProfileAttributePanel, BorderLayout.LINE_END);
        this.repaint();
    }

    private void initializePanelAttributes() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
        this.setBorder(new CompoundBorder(
                BorderFactory.createLineBorder(ArmoryColor.WINDOW_BORDER_COLOR_LIGHT),
                new EmptyBorder(15, 15, 15, 15)
        ));
    }

    private void initializeViewClasses() {
        profileBrowsePanel = new ProfileBrowsePanel(listener);
        profileAttributePanel = new ProfileAttributePanel(listener);
        emptyProfileAttributePanel = new EmptyProfileAttributePanel();
    }
}
