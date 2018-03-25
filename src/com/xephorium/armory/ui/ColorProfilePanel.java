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

        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createLineBorder(ArmoryColor.WINDOW_BORDER_COLOR_LIGHT));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(ArmoryColor.WINDOW_TEST_COLOR);
        bottomPanel.setBorder(BorderFactory.createLineBorder(ArmoryColor.WINDOW_BORDER_COLOR_LIGHT));

        this.add(topPanel);
        this.add(bottomPanel);
    }
}
