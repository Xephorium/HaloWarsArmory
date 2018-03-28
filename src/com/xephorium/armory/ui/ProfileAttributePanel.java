package com.xephorium.armory.ui;

import com.xephorium.armory.model.Profile;
import com.xephorium.armory.ui.resource.color.ArmoryColor;
import com.xephorium.armory.ui.utility.CustomMouseListener;
import com.xephorium.armory.ui.utility.StringUtility;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileAttributePanel extends JPanel {


    /*--- Variables ---*/

    private JTextField profileNameTextField;
    private JPanel[] profileColorPanels = new JPanel[Profile.ColorType.values().length];
    private Profile workingProfile;


    /*--- Constructor ---*/

    public ProfileAttributePanel() {
        super();

        initializePanelAttributes();
        initializeProfileNameTextField();

        this.add(profileNameTextField, BorderLayout.PAGE_START);
        this.add(createColorListPanel(), BorderLayout.CENTER);
        this.add(createSaveButton(), BorderLayout.PAGE_END);
    }


    /*--- Public Methods ---*/

    public void setWorkingProfile(Profile profile) {
        workingProfile = profile;
        setWorkingProfileName(workingProfile.getName());
        setWorkingProfileColors(workingProfile.getColors());
    }

    public void setWorkingProfileName(String name) {
        workingProfile.setName(name);
        profileNameTextField.setText(name);
    }

    public void setWorkingProfileColor(Profile.ColorType colorType, Color color) {
        workingProfile.setColor(colorType, color);
        profileColorPanels[colorType.getIndex()].setBackground(color);
    }

    public void setWorkingProfileColors(Color[] colors) {
        workingProfile.setColors(colors);
        for (int x = 0; x < profileColorPanels.length; x++) {
            profileColorPanels[x].setBackground(colors[x]);
        }
    }


    /*--- Private Getter Methods ---*/

    private Profile getWorkingProfile() {
        return workingProfile;
    }

    private String getWorkingProfileName() {
        return workingProfile.getName();
    }

    private Color[] getWorkingProfileColors() {
        return workingProfile.getColors();
    }


    /*--- Private Setup Methods ---*/

    private void initializePanelAttributes() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(135, 0));
        this.setBorder(new EmptyBorder(0,15,0,0));
        this.setBackground(Color.WHITE);
    }

    private void initializeProfileNameTextField() {
        profileNameTextField = new JTextField();
        profileNameTextField.setBackground(Color.WHITE);
        profileNameTextField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ArmoryColor.WINDOW_BORDER_COLOR_DARK),
                new EmptyBorder(3,2,3,2)));
        profileNameTextField.setText("Profile Name");
    }

    private JPanel createColorListPanel() {
        JPanel colorListPanel = new JPanel(new GridLayout(Profile.ColorType.values().length, 1));
        colorListPanel.setBorder(new EmptyBorder(5,0,5,0));
        colorListPanel.setBackground(Color.WHITE);
        for (Profile.ColorType colorType : Profile.ColorType.values()) {
            colorListPanel.add(createColorListItem(colorType));
        }
        return colorListPanel;
    }

    private JPanel createColorListItem(Profile.ColorType colorType) {
        JPanel colorListItem = new JPanel(new BorderLayout());
        colorListItem.addMouseListener(createColorListItemMouseListener(colorType));
        colorListItem.setBackground(Color.WHITE);

        JPanel colorPreviewPanel = new JPanel(new GridBagLayout());
        colorPreviewPanel.setBackground(Color.WHITE);
        profileColorPanels[colorType.getIndex()] = new JPanel();
        profileColorPanels[colorType.getIndex()].setBorder(BorderFactory.createLineBorder(ArmoryColor.WINDOW_BORDER_COLOR_DARK));
        profileColorPanels[colorType.getIndex()].setBackground(ArmoryColor.WINDOW_TEST_COLOR);
        profileColorPanels[colorType.getIndex()].setPreferredSize(new Dimension(15, 15));
        colorPreviewPanel.add(profileColorPanels[colorType.getIndex()], new GridBagConstraints());

        colorListItem.add(new JLabel(colorType.getDisplayName()), BorderLayout.CENTER);
        colorListItem.add(colorPreviewPanel, BorderLayout.LINE_END);

        return colorListItem;
    }

    private JButton createSaveButton() {
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO - Handle Save Click
                //listener.handleProfileSaveClick();
            }
        });
        return saveButton;
    }

    private CustomMouseListener createColorListItemMouseListener(Profile.ColorType colorType) {
        return new CustomMouseListener(new CustomMouseListener.MouseClickListener() {
            @Override
            public void onMouseClick() {

                // TODO - Handle Color Click
                //listener.handleWorkingProfileColorClick(colorType, getWorkingProfileColor(colorType));
            }
        });
    }
}
