package com.xephorium.armory.ui;

import com.xephorium.armory.model.Profile;
import com.xephorium.armory.ui.resource.color.ArmoryColor;
import com.xephorium.armory.ui.resource.dimension.ArmoryDimension;
import com.xephorium.armory.ui.utility.CustomMouseListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ProfileAttributePanel extends JPanel {


    /*--- Variables ---*/

    private ArmoryWindowListener listener;
    private JTextField profileNameTextField;
    private List<JPanel> profileColorPanels = new ArrayList<>();
    private Profile workingProfile;


    /*--- Constructor ---*/

    public ProfileAttributePanel(ArmoryWindowListener listener) {
        super();
        this.listener = listener;

        initializePanelAttributes();
        initializeProfileNameTextField();

        this.add(profileNameTextField, BorderLayout.PAGE_START);
        this.add(createColorListPanel(), BorderLayout.CENTER);
        this.add(createSaveButton(), BorderLayout.PAGE_END);
    }


    /*--- Public Methods ---*/

    public void setWorkingProfile(Profile profile) {
        workingProfile = profile.cloneProfile();
        profileNameTextField.setText(profile.getName());
        for (int x = 0; x < profileColorPanels.size(); x++) {
            profileColorPanels.get(x).setBackground(profile.getColor(Profile.ColorType.getFromIndex(x)));
        }
    }


    /*--- Private Getter Methods ---*/

    private Profile getWorkingProfile() {
        Profile profile = workingProfile.cloneProfile();
        profile.setName(profileNameTextField.getText());
        return profile;
    }


    /*--- Private Setup Methods ---*/

    private void initializePanelAttributes() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(ArmoryDimension.ATTRIBUTE_BOX_WIDTH, 0));
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
        profileColorPanels.add(new JPanel());
        profileColorPanels.get(colorType.getIndex()).setBorder(BorderFactory.createLineBorder(ArmoryColor.WINDOW_BORDER_COLOR_DARK));
        profileColorPanels.get(colorType.getIndex()).setBackground(ArmoryColor.WINDOW_TEST_COLOR);
        profileColorPanels.get(colorType.getIndex()).setPreferredSize(new Dimension(
                ArmoryDimension.PREVIEW_BOX_DIMENSIONS,
                ArmoryDimension.PREVIEW_BOX_DIMENSIONS));
        colorPreviewPanel.add(profileColorPanels.get(colorType.getIndex()), new GridBagConstraints());

        colorListItem.add(new JLabel(colorType.getDisplayName()), BorderLayout.CENTER);
        colorListItem.add(colorPreviewPanel, BorderLayout.LINE_END);

        return colorListItem;
    }

    private JButton createSaveButton() {
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.handleWorkingProfileSaveClick(getWorkingProfile());
            }
        });
        return saveButton;
    }

    private CustomMouseListener createColorListItemMouseListener(Profile.ColorType colorType) {
        return new CustomMouseListener(new CustomMouseListener.MouseClickListener() {
            @Override
            public void onMouseClick() {
                listener.handleWorkingProfileColorClick(getWorkingProfile(), colorType);
            }
        });
    }
}
