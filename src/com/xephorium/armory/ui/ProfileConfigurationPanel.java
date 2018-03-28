package com.xephorium.armory.ui;

import com.xephorium.armory.model.ColorProfile;
import com.xephorium.armory.model.ColorProfile.ColorType;
import com.xephorium.armory.ui.resource.color.ArmoryColor;
import com.xephorium.armory.ui.utility.CustomMouseListener;
import com.xephorium.armory.ui.utility.CustomMouseListener.MouseClickListener;
import com.xephorium.armory.ui.utility.StringUtility;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileConfigurationPanel extends JPanel {


    /*--- Variables ---*/

    private ProfileConfigurationPanelListener listener;

    private ProfileBrowsePanel profileBrowsePanel;

    private JTextField profileNameTextField;
    private JPanel[] profileColorPanels = new JPanel[ColorType.values().length];
    private JList profileListPanel;

    private ColorProfile[] colorProfileList;

    /*--- Constructor ---*/

    public ProfileConfigurationPanel(ProfileConfigurationPanelListener listener) {
        super();
        this.listener = listener;

        initializePanelAttributes();
        initializeViewClasses();

        this.add(profileBrowsePanel, BorderLayout.CENTER);
        this.add(createProfileSettingsPanel(), BorderLayout.LINE_END);
    }


    /*--- Public Methods ---*/

    public String getWorkingProfileName() {
        return StringUtility.removeLeadingSpace(this.profileNameTextField.getText());
    }

    public void setWorkingProfileName(String name) {
        this.profileNameTextField.setText(name);
    }

    public Color getWorkingProfileColor(ColorType colorType) {
        return profileColorPanels[colorType.getIndex()].getBackground();
    }

    public void setWorkingProfileColor(ColorType colorType, Color color) {
        profileColorPanels[colorType.getIndex()].setBackground(color);
    }

    public Color[] getWorkingProfileColors() {
        Color[] colors = new Color[profileColorPanels.length];
        for (int x = 0; x < profileColorPanels.length; x++) {
            colors[x] = profileColorPanels[x].getBackground();
        }
        return colors;
    }

    public void setWorkingProfileColors(Color[] colors) {
        for (int x = 0; x < profileColorPanels.length; x++) {
            profileColorPanels[x].setBackground(colors[x]);
        }
    }

    public ColorProfile getWorkingProfile() {
        ColorProfile workingColorProfile = new ColorProfile();
        workingColorProfile.setName(getWorkingProfileName());
        workingColorProfile.setColors(getWorkingProfileColors());
        return workingColorProfile;
    }

    public void setWorkingProfile(ColorProfile colorProfile) {
        setWorkingProfileName(colorProfile.getName());
        setWorkingProfileColors(colorProfile.getColors());
    }

    public void setColorProfileList(ColorProfile[] colorProfileList) {
        this.colorProfileList = colorProfileList;
        profileBrowsePanel.updateProfileBrowsePanel(colorProfileList);
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
    }

    private JPanel createProfileSettingsPanel() {

        JPanel profileSettingsPanel = new JPanel(new BorderLayout());
        profileSettingsPanel.setPreferredSize(new Dimension(135, 0));
        profileSettingsPanel.setBorder(new EmptyBorder(0,15,0,0));
        profileSettingsPanel.setBackground(Color.WHITE);

        profileNameTextField = new JTextField();
        profileNameTextField.setBackground(Color.WHITE);
        profileNameTextField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ArmoryColor.WINDOW_BORDER_COLOR_DARK),
                new EmptyBorder(3,2,3,2)));
        setWorkingProfileName("ColorProfile Name");

        JPanel profileColorsPanel = new JPanel(new GridLayout(ColorType.values().length, 1));
        profileColorsPanel.setBorder(new EmptyBorder(5,0,5,0));
        profileColorsPanel.setBackground(Color.WHITE);
        for (ColorType colorType :ColorType.values()) {
            profileColorsPanel.add(createColorListItem(colorType));
        }

        JButton profileSaveButton = new JButton("Save");
        profileSaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.handleProfileSaveClick();
            }
        });

        profileSettingsPanel.add(profileNameTextField, BorderLayout.PAGE_START);
        profileSettingsPanel.add(profileColorsPanel, BorderLayout.CENTER);
        profileSettingsPanel.add(profileSaveButton, BorderLayout.PAGE_END);

        return profileSettingsPanel;
    }

    private JPanel createColorListItem(ColorType colorType) {

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

    private CustomMouseListener createColorListItemMouseListener(ColorType colorType) {
        return new CustomMouseListener(new MouseClickListener() {
            @Override
            public void onMouseClick() {
                listener.handleProfileColorClick(colorType, getWorkingProfileColor(colorType));
            }
        });
    }

    private void selectProfileByIndex(int index) {
        setWorkingProfileName(colorProfileList[index].getName());
        setWorkingProfileColors(colorProfileList[index].getColors());
    }


    /*--- Listener Interface ---*/

    interface ProfileConfigurationPanelListener {

        void handleProfileColorClick(ColorType colorType, Color currentColor);

        void handleProfileSaveClick();
    }
}
