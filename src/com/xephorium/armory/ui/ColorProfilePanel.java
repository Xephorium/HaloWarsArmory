package com.xephorium.armory.ui;

import com.xephorium.armory.ui.resource.color.ArmoryColor;
import com.xephorium.armory.ui.resource.dimension.ArmoryDimension;
import com.xephorium.armory.ui.resource.image.ArmoryImage;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.util.concurrent.Flow;

public class ColorProfilePanel extends JPanel {


    /*--- Constructor ---*/

    public ColorProfilePanel() {
        initializePanelAttributes();

        this.add(createProfileEditPanel());
        this.add(createProfilePreviewPanel());
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
        return new JPanel();
    }

    private JPanel createProfileSettingsPanel() {

        JPanel profileSettingsPanel = new JPanel(new BorderLayout());
        profileSettingsPanel.setPreferredSize(new Dimension(120, 0));

        JTextField profileNameTextField = new JTextField();
        profileNameTextField.setText("Sample Profile");

        JPanel profileColorsPanel = new JPanel(new GridLayout(5, 1));
        profileColorsPanel.setBorder(new EmptyBorder(5,0,5,0));
        profileColorsPanel.setBackground(Color.WHITE);
        profileColorsPanel.add(createColorListItem("Units"));
        profileColorsPanel.add(createColorListItem("Corpses"));
        profileColorsPanel.add(createColorListItem("Selector"));
        profileColorsPanel.add(createColorListItem("Minimap"));
        profileColorsPanel.add(createColorListItem("HUD"));

        JButton profileSaveButton = new JButton("Save");

        profileSettingsPanel.add(profileNameTextField, BorderLayout.PAGE_START);
        profileSettingsPanel.add(profileColorsPanel, BorderLayout.CENTER);
        profileSettingsPanel.add(profileSaveButton, BorderLayout.PAGE_END);

        return profileSettingsPanel;
    }

    private JPanel createColorListItem(String colorType) {

        JPanel colorListItem = new JPanel(new BorderLayout());
        colorListItem.setBackground(Color.WHITE);

        JPanel colorPreviewPanel = new JPanel(new GridBagLayout());
        colorPreviewPanel.setBackground(Color.WHITE);
        JPanel colorPreviewBox = new JPanel();
        colorPreviewBox.setBorder(BorderFactory.createLineBorder(ArmoryColor.WINDOW_BORDER_COLOR_DARK));
        colorPreviewBox.setBackground(ArmoryColor.WINDOW_TEST_COLOR);
        colorPreviewBox.setPreferredSize(new Dimension(15, 15));
        colorPreviewPanel.add(colorPreviewBox, new GridBagConstraints());

        colorListItem.add(new JLabel(colorType), BorderLayout.CENTER);
        colorListItem.add(colorPreviewPanel, BorderLayout.LINE_END);

        return colorListItem;
    }

    private JPanel createProfilePreviewPanel() {
        JPanel profilePreviewPanel = new JPanel();
        profilePreviewPanel.setBackground(ArmoryColor.WINDOW_TEST_COLOR);
        profilePreviewPanel.setBorder(BorderFactory.createLineBorder(ArmoryColor.WINDOW_BORDER_COLOR_LIGHT));
        return profilePreviewPanel;
    }
}
