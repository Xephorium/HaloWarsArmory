package com.xephorium.armory.ui;

import com.xephorium.armory.model.Profile;
import com.xephorium.armory.ui.resource.color.ArmoryColor;
import com.xephorium.armory.ui.resource.font.ArmoryFont;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class ProfileBrowsePanel extends JPanel {


    /*--- Variables ---*/

    private ProfileBrowsePanelListener listener;
    private JList profileListPanel;
    private Profile[] profileList;


    /*--- Constructor ---*/

    public ProfileBrowsePanel(ProfileBrowsePanelListener listener) {
        super();
        this.listener = listener;

        initializePanelAttributes();

        this.add(createProfileBrowseHeader(), BorderLayout.PAGE_START);
        this.add(createProfileBrowseScrollPane(), BorderLayout.CENTER);
        this.add(createProfileBrowseButtonPanel(), BorderLayout.PAGE_END);

    }


    /*--- Public Methods ---*/

    public void updateProfileBrowsePanel(Profile[] profileList) {
        Profile[] newProfileList = Profile.cloneProfileList(profileList);
        if (this.profileList == null || this.profileList.length < 1) {
            this.profileList = newProfileList;
            this.profileListPanel.setListData(Profile.getProfileNames(newProfileList));
            this.profileListPanel.setSelectedIndex(0);
            return;
        }

        Profile currentSelectedProfile = this.profileList[profileListPanel.getLeadSelectionIndex()];
        this.profileList = newProfileList;
        this.profileListPanel.setListData(Profile.getProfileNames(newProfileList));
        this.profileListPanel.setSelectedIndex(Profile.getProfileIndexOrFirstIndex(newProfileList, currentSelectedProfile.getPrimaryKey()));
    }

    public void setSelectedProfile(Profile profile) {
        this.profileListPanel.setSelectedIndex(Profile.getProfileIndexOrFirstIndex(profileList, profile.getPrimaryKey()));
    }


    /*--- Private Methods ---*/

    private void initializePanelAttributes() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
    }

    private JLabel createProfileBrowseHeader() {
        JLabel profileBrowseHeader = new JLabel("Color Profiles");
        profileBrowseHeader.setBorder(new EmptyBorder(0,0,5,0));
        profileBrowseHeader.setFont(ArmoryFont.NORMAL_BOLD);
        return profileBrowseHeader;
    }

    private JScrollPane createProfileBrowseScrollPane() {
        initializeProfileBrowseList();
        JScrollPane profileListScroller = new JScrollPane(profileListPanel);
        profileListScroller.setBorder(BorderFactory.createLineBorder(ArmoryColor.WINDOW_BORDER_COLOR_DARK));
        return profileListScroller;
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
                if (!event.getValueIsAdjusting()) {
                    listener.handleProfileSelection(profileList[profileListPanel.getLeadSelectionIndex()]);
                }
            }
        });
    }

    private JPanel createProfileBrowseButtonPanel() {
        JPanel profileBrowseButtonPanel = new JPanel();
        profileBrowseButtonPanel.setLayout(new BorderLayout());
        profileBrowseButtonPanel.setBorder(new EmptyBorder(7, 0, 0, 0));
        profileBrowseButtonPanel.setBackground(Color.WHITE);

        JButton profileDeleteButton = new JButton("Delete");
        profileDeleteButton.setPreferredSize(new Dimension(70, 24));

        JButton profileAddButton = new JButton("Add");
        profileAddButton.setPreferredSize(new Dimension(70, 24));

        JPanel spacerPanel = new JPanel();
        spacerPanel.setBackground(Color.WHITE);

        profileBrowseButtonPanel.add(profileDeleteButton, BorderLayout.LINE_START);
        profileBrowseButtonPanel.add(spacerPanel);
        profileBrowseButtonPanel.add(profileAddButton, BorderLayout.LINE_END);

        return profileBrowseButtonPanel;
    }


    /*--- Interface Listener ---*/

    interface ProfileBrowsePanelListener {

        void handleProfileSelection(Profile profile);
    }

}
