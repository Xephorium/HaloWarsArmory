package com.xephorium.armory.ui;

import com.xephorium.armory.ui.resource.color.ArmoryColor;
import com.xephorium.armory.ui.resource.dimension.ArmoryDimension;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

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
        JPanel profileEditPanel = new JPanel();
        profileEditPanel.setBackground(Color.WHITE);
        profileEditPanel.setBorder(BorderFactory.createLineBorder(ArmoryColor.WINDOW_BORDER_COLOR_LIGHT));
        return profileEditPanel;
    }

    private JPanel createProfilePreviewPanel() {
        JPanel profilePreviewPanel = new JPanel();
        profilePreviewPanel.setBackground(ArmoryColor.WINDOW_TEST_COLOR);
        profilePreviewPanel.setBorder(BorderFactory.createLineBorder(ArmoryColor.WINDOW_BORDER_COLOR_LIGHT));
        return profilePreviewPanel;
    }
}
