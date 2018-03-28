package com.xephorium.armory.ui;

import com.xephorium.armory.model.ColorProfile;
import com.xephorium.armory.ui.resource.color.ArmoryColor;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ProfileConfigurationPanel extends JPanel {


    /*--- Variables ---*/

    private ProfileBrowsePanel profileBrowsePanel;
    private ProfileAttributePanel profileAttributePanel;


    /*--- Constructor ---*/

    public ProfileConfigurationPanel() {
        super();

        initializePanelAttributes();
        initializeViewClasses();

        this.add(profileBrowsePanel, BorderLayout.CENTER);
        this.add(profileAttributePanel, BorderLayout.LINE_END);
    }


    /*--- Public Methods ---*/

    public void setWorkingProfile(ColorProfile colorProfile) {
        profileAttributePanel.setWorkingProfile(colorProfile);
    }

    public void setWorkingProfileName(String name) {
        this.profileAttributePanel.setWorkingProfileName(name);
    }

    public void setWorkingProfileColor(ColorProfile.ColorType colorType, Color color) {
        profileAttributePanel.setWorkingProfileColor(colorType, color);
    }

    public void setWorkingProfileColors(Color[] colors) {
        profileAttributePanel.setWorkingProfileColors(colors);
    }

    public void setColorProfileList(ColorProfile[] colorProfileList) {
        profileAttributePanel.setColorProfileList(colorProfileList);
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
        profileBrowsePanel = new ProfileBrowsePanel();
        profileAttributePanel = new ProfileAttributePanel();
    }
}
