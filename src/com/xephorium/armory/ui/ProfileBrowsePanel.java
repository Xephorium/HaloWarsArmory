package com.xephorium.armory.ui;

import com.xephorium.armory.model.Profile;
import com.xephorium.armory.model.ProfileList;
import com.xephorium.armory.ui.resource.color.ArmoryColor;
import com.xephorium.armory.ui.resource.font.ArmoryFont;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileBrowsePanel extends JPanel {


    /*--- Variables ---*/

    private ArmoryWindowListener listener;
    private JScrollPane profileListScrollPane;
    private JList profileListPanel;
    private JButton profileDeleteButton;
    private ProfileList profileList;
    private boolean browsePanelSetupComplete = false;
    private boolean browsePanelEmptyState = false;


    /*--- Constructor ---*/

    public ProfileBrowsePanel(ArmoryWindowListener listener) {
        super();
        this.listener = listener;

        initializePanelAttributes();

        this.add(createProfileBrowseScrollPane(), BorderLayout.CENTER);
        this.add(createProfileBrowseButtonPanel(), BorderLayout.PAGE_END);

    }


    /*--- Public Methods ---*/

    public void updateProfileBrowsePanel(ProfileList profileList) {
        ProfileList newProfileList = profileList.clone();

        // Empty Profile List
        if (newProfileList.isEmpty()) {
            browsePanelEmptyState = true;
            this.profileListPanel.setEnabled(false);
            this.profileList = null;
            this.profileListPanel.removeAll();
            this.profileListPanel.clearSelection();
            this.profileListPanel.setListData(getEmptyStateData());
            return;
        }

        // Initial Profile List
        if (this.profileList == null) {
            browsePanelSetupComplete = false;
            browsePanelEmptyState = false;
            this.profileListPanel.setEnabled(true);
            this.profileList = newProfileList;
            this.profileListPanel.removeAll();
            this.profileListPanel.clearSelection();
            this.profileListPanel.setListData(newProfileList.getNameList());
            browsePanelSetupComplete = true;
            this.profileListPanel.setSelectedIndex(0);
            return;
        }

        // Populated Profile List
        browsePanelSetupComplete = false;
        browsePanelEmptyState = false;
        this.profileListPanel.setEnabled(true);
        Profile currentSelectedProfile = this.profileList.getProfileByIndex(profileListPanel.getLeadSelectionIndex());
        this.profileList = newProfileList;
        this.profileListPanel.setListData(newProfileList.getNameList());
        browsePanelSetupComplete = true;
        this.profileListPanel.setSelectedIndex(newProfileList.getIndexOrFirstIndex(currentSelectedProfile));
    }

    public void setSelectedProfile(Profile profile) {
        this.profileListPanel.setSelectedIndex(profileList.getIndexOrFirstIndex(profile));
    }

    public void selectNewProfile(Profile profile) {
        this.profileListPanel.setSelectedIndex(profileList.getIndexOrFirstIndex(profile));
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                profileListScrollPane.getVerticalScrollBar().setValue(0);
            }
        });
    }

    public void enableProfileEdit() {
        profileDeleteButton.setEnabled(true);
    }

    public void disableProfileEdit() {
        profileDeleteButton.setEnabled(false);
    }


    /*--- Private Methods ---*/

    private void initializePanelAttributes() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
    }

    private JScrollPane createProfileBrowseScrollPane() {
        initializeProfileBrowseList();
        profileListScrollPane = new JScrollPane(profileListPanel);
        profileListScrollPane.setBorder(BorderFactory.createLineBorder(ArmoryColor.WINDOW_BORDER_COLOR_DARK));
        return profileListScrollPane;
    }

    private void initializeProfileBrowseList() {
        profileListPanel = new JList();
        profileListPanel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        profileListPanel.setLayoutOrientation(JList.VERTICAL);
        profileListPanel.setSelectionBackground(ArmoryColor.PROFILE_LIST_SELECTION_COLOR);
        profileListPanel.setSelectionForeground(Color.BLACK);
        profileListPanel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && !browsePanelEmptyState && browsePanelSetupComplete) {
                    listener.handleSelectProfileClick(profileList.getProfileByIndex(profileListPanel.getLeadSelectionIndex()));
                }
            }
        });
    }

    private JPanel createProfileBrowseButtonPanel() {
        JPanel profileBrowseButtonPanel = new JPanel();
        profileBrowseButtonPanel.setLayout(new BorderLayout());
        profileBrowseButtonPanel.setBorder(new EmptyBorder(7, 0, 0, 0));
        profileBrowseButtonPanel.setBackground(Color.WHITE);

        profileDeleteButton = new JButton("Delete");
        profileDeleteButton.setPreferredSize(new Dimension(70, 24));
        profileDeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!browsePanelEmptyState) {
                    listener.handleDeleteProfileClick(profileList.getProfileByIndex(profileListPanel.getLeadSelectionIndex()).getPrimaryKey());
                }
            }
        });

        JButton profileAddButton = new JButton("Add");
        profileAddButton.setPreferredSize(new Dimension(70, 24));
        profileAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.handleAddProfileClick();
            }
        });

        JPanel spacerPanel = new JPanel();
        spacerPanel.setBackground(Color.WHITE);

        profileBrowseButtonPanel.add(profileDeleteButton, BorderLayout.LINE_START);
        profileBrowseButtonPanel.add(spacerPanel);
        profileBrowseButtonPanel.add(profileAddButton, BorderLayout.LINE_END);

        return profileBrowseButtonPanel;
    }

    private String[] getEmptyStateData() {
        String[] emptyStateData = new String[1];
        emptyStateData[0] = "No Profiles Available";
        return emptyStateData;
    }

}
