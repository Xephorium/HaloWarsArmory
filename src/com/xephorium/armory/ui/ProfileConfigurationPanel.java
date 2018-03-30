package com.xephorium.armory.ui;

import com.xephorium.armory.model.ProfileList;
import com.xephorium.armory.model.Profile;
import com.xephorium.armory.ui.resource.color.ArmoryColor;
import com.xephorium.armory.ui.resource.dimension.ArmoryDimension;
import com.xephorium.armory.ui.resource.font.ArmoryFont;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ProfileConfigurationPanel extends JPanel {


    /*--- Variables ---*/

    private ArmoryWindowListener listener;
    private JPanel profileConfigurationPanel;
    private ProfileBrowsePanel profileBrowsePanel;
    private ProfileAttributePanel profileAttributePanel;
    private EmptyProfileAttributePanel emptyProfileAttributePanel;


    /*--- Constructor ---*/

    public ProfileConfigurationPanel(ArmoryWindowListener listener) {
        super();
        this.listener = listener;

        initializePanelAttributes();
        initializeViewClasses();

        profileConfigurationPanel.add(createProfileConfigurationHeader(), BorderLayout.PAGE_START);
        profileConfigurationPanel.add(profileBrowsePanel, BorderLayout.CENTER);
        profileConfigurationPanel.add(profileAttributePanel, BorderLayout.LINE_END);
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
        profileConfigurationPanel.remove(emptyProfileAttributePanel);
        profileConfigurationPanel.add(profileAttributePanel, BorderLayout.LINE_END);
        profileConfigurationPanel.repaint();
    }

    private void showEmptyProfileAttributePanel() {
        profileConfigurationPanel.remove(profileAttributePanel);
        profileConfigurationPanel.add(emptyProfileAttributePanel, BorderLayout.LINE_END);
        profileConfigurationPanel.repaint();
    }

    private void initializePanelAttributes() {
        this.setLayout(new BorderLayout());

        profileConfigurationPanel = new JPanel(new BorderLayout());
        profileConfigurationPanel.setBackground(Color.WHITE);
        profileConfigurationPanel.setBorder(new CompoundBorder(
                BorderFactory.createLineBorder(ArmoryColor.WINDOW_BORDER_COLOR_LIGHT),
                new EmptyBorder(15, 15, 15, 15)
        ));

        JPanel paddingPanel = new JPanel();
        paddingPanel.setPreferredSize(new Dimension(0, ArmoryDimension.PANEL_PADDING));
        paddingPanel.setBackground(ArmoryColor.WINDOW_BACKGROUND_COLOR);

        this.add(profileConfigurationPanel, BorderLayout.CENTER);
        this.add(paddingPanel, BorderLayout.PAGE_END);
    }

    private void initializeViewClasses() {
        profileBrowsePanel = new ProfileBrowsePanel(listener);
        profileAttributePanel = new ProfileAttributePanel(listener);
        emptyProfileAttributePanel = new EmptyProfileAttributePanel();
    }

    private JLabel createProfileConfigurationHeader() {
        JLabel profileBrowseHeader = new JLabel("Color Profiles");
        profileBrowseHeader.setBorder(new EmptyBorder(0,0,10,0));
        profileBrowseHeader.setFont(ArmoryFont.NORMAL_BOLD);
        return profileBrowseHeader;
    }
}
