package com.xephorium.armory.ui;

import com.xephorium.armory.ui.resource.color.ArmoryColor;
import com.xephorium.armory.ui.resource.dimension.ArmoryDimension;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FactionConfigurationPanel extends JPanel {


    /*--- Constructor ---*/

    public FactionConfigurationPanel() {
        initializePanelAttributes();
        createTabbedPane();
    }


    /*--- Private Methods ---*/

    private void initializePanelAttributes() {
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(
                ArmoryDimension.PANEL_PADDING/2 - 3,
                ArmoryDimension.WINDOW_PADDING_HORIZONTAL,
                ArmoryDimension.WINDOW_PADDING_VERTICAL,
                ArmoryDimension.PANEL_PADDING/2 - 2));
        this.setBackground(ArmoryColor.WINDOW_BACKGROUND_COLOR);
    }

    private void createTabbedPane() {

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel unscPanel = new JPanel();
        JPanel covenantPanel = new JPanel();
        unscPanel.setBackground(Color.WHITE);
        covenantPanel.setBackground(Color.WHITE);

        JLabel unscLabel = new JLabel("UNSC");
        JLabel covenantLabel = new JLabel("Covenant");
        unscLabel.setBorder(new EmptyBorder(4, 10, 3, 10));
        covenantLabel.setBorder(new EmptyBorder(4, 3, 3, 3));

        tabbedPane.addTab("UNSC", unscPanel);
        tabbedPane.addTab("Covenant", covenantPanel);
        tabbedPane.setTabComponentAt(0, unscLabel);
        tabbedPane.setTabComponentAt(1, covenantLabel);

        this.add(tabbedPane, BorderLayout.CENTER);
    }
}