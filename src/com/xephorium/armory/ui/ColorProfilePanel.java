package com.xephorium.armory.ui;

import com.xephorium.armory.model.ColorProfile;
import com.xephorium.armory.model.ColorProfile.ColorType;
import com.xephorium.armory.ui.resource.color.ArmoryColor;
import com.xephorium.armory.ui.resource.dimension.ArmoryDimension;
import com.xephorium.armory.ui.resource.font.ArmoryFont;
import com.xephorium.armory.ui.utility.CustomMouseListener;
import com.xephorium.armory.ui.utility.CustomMouseListener.MouseClickListener;
import com.xephorium.armory.ui.utility.StringUtility;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorProfilePanel extends JPanel {


    /*--- Variables ---*/

    private ColorProfilePanelListener listener;
    private JTextField profileNameTextField;
    private JPanel[] profileColorPanels = new JPanel[ColorType.values().length];
    private JList profileListPanel;

    private ColorProfile[] colorProfileList;

    /*--- Constructor ---*/

    public ColorProfilePanel(ColorProfilePanelListener listener) {
        this.listener = listener;

        initializePanelAttributes();

        this.add(createProfileEditPanel());
        this.add(createProfilePreviewPanel());
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
        updateProfileBrowsePanel();
    }

    public ColorProfile[] getColorProfileList() {
        return this.colorProfileList;
    }


    /*--- Private Methods ---*/

    private void initializePanelAttributes() {
        this.setLayout(new GridLayout(2, 1, 0, ArmoryDimension.PANEL_PADDING));
        this.setBorder(new EmptyBorder(
                ArmoryDimension.PANEL_PADDING/2,
                ArmoryDimension.PANEL_PADDING/2,
                ArmoryDimension.WINDOW_PADDING_VERTICAL,
                ArmoryDimension.WINDOW_PADDING_HORIZONTAL));
        this.setPreferredSize(new Dimension(ArmoryDimension.COLOR_PROFILE_PANEL_WIDTH, 0));
        this.setBackground(ArmoryColor.WINDOW_BACKGROUND_COLOR);
    }

    private JPanel createProfileEditPanel() {

        JPanel profileEditBorderedPanel = new JPanel(new BorderLayout());
        JPanel profileEditPanel = new JPanel(new BorderLayout());

        profileEditBorderedPanel.setBorder(BorderFactory.createLineBorder(ArmoryColor.WINDOW_BORDER_COLOR_LIGHT));
        profileEditPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        profileEditPanel.setBackground(Color.WHITE);

        profileEditPanel.add(createProfileBrowsePanel(), BorderLayout.CENTER);
        profileEditPanel.add(createProfileSettingsPanel(), BorderLayout.LINE_END);
        profileEditBorderedPanel.add(profileEditPanel, BorderLayout.CENTER);

        return profileEditBorderedPanel;
    }

    private JPanel createProfileBrowsePanel() {

        JPanel profileBrowsePanel = new JPanel(new BorderLayout());
        profileBrowsePanel.setBackground(Color.WHITE);

        JLabel profileListHeaderLabel = new JLabel("Color Profiles");
        profileListHeaderLabel.setBorder(new EmptyBorder(0,0,5,0));
        profileListHeaderLabel.setFont(ArmoryFont.NORMAL_BOLD);

        profileListPanel = new JList();
        profileListPanel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        profileListPanel.setLayoutOrientation(JList.VERTICAL);
        profileListPanel.setSelectionBackground(ArmoryColor.PROFILE_LIST_SELECTION_COLOR);
        profileListPanel.setSelectionForeground(Color.BLACK);
        profileListPanel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    selectProfileByIndex(profileListPanel.getLeadSelectionIndex());
                }
            }
        });
        JScrollPane profileListScroller = new JScrollPane(profileListPanel);
        profileListScroller.setBorder(BorderFactory.createLineBorder(ArmoryColor.WINDOW_BORDER_COLOR_DARK));

        JPanel profileBrowseEditPanel = new JPanel();
        profileBrowseEditPanel.setLayout(new BorderLayout());
        profileBrowseEditPanel.setBorder(new EmptyBorder(7, 0, 0, 0));
        profileBrowseEditPanel.setBackground(Color.WHITE);
        JButton profileDeleteButton = new JButton("Delete");
        profileDeleteButton.setPreferredSize(new Dimension(70, 24));
        JButton profileAddButton = new JButton("Add");
        profileAddButton.setPreferredSize(new Dimension(70, 24));
        JPanel middlePanel = new JPanel();
        middlePanel.setBackground(Color.WHITE);
        profileBrowseEditPanel.add(profileDeleteButton, BorderLayout.LINE_START);
        profileBrowseEditPanel.add(middlePanel);
        profileBrowseEditPanel.add(profileAddButton, BorderLayout.LINE_END);

        profileBrowsePanel.add(profileListHeaderLabel, BorderLayout.PAGE_START);
        profileBrowsePanel.add(profileListScroller, BorderLayout.CENTER);
        profileBrowsePanel.add(profileBrowseEditPanel, BorderLayout.PAGE_END);

        return profileBrowsePanel;
    }

    private void updateProfileBrowsePanel() {
        String[] profileNames = new String[colorProfileList.length];
        for (int x = 0; x < colorProfileList.length; x++) {
            profileNames[x] = colorProfileList[x].getName();
        }
        profileListPanel.setListData(profileNames);
        profileListPanel.setSelectedIndex(0);
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

    private JPanel createProfilePreviewPanel() {
        JPanel profilePreviewPanel = new JPanel();
        profilePreviewPanel.setBackground(ArmoryColor.WINDOW_TEST_COLOR);
        profilePreviewPanel.setBorder(BorderFactory.createLineBorder(ArmoryColor.WINDOW_BORDER_COLOR_LIGHT));
        return profilePreviewPanel;
    }

    private void selectProfileByIndex(int index) {
        setWorkingProfileName(colorProfileList[index].getName());
        setWorkingProfileColors(colorProfileList[index].getColors());
    }


    /*--- Listener Interface ---*/

    interface ColorProfilePanelListener {

        void handleProfileColorClick(ColorType colorType, Color currentColor);

        void handleProfileSaveClick();
    }
}
