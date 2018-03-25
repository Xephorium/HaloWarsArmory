package com.xephorium.armory.ui;

import com.xephorium.armory.ui.resource.color.ArmoryColor;
import com.xephorium.armory.ui.resource.dimension.ArmoryDimension;
import javafx.scene.layout.Pane;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FactionConfigurationPanel extends JPanel {


    /*--- Variables ---*/

    private FactionConfigurationPanelListener listener;
    private JComboBox[] unscComboBoxes = new JComboBox[6];


    /*--- Constructor ---*/

    public FactionConfigurationPanel(FactionConfigurationPanelListener listener) {
        this.listener = listener;

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
        unscPanel.setBackground(Color.WHITE);
        unscPanel.setLayout(new BorderLayout());
        unscPanel.add(createUNSCPlayerPanel(), BorderLayout.NORTH);
        unscPanel.add(createUNSCFactionPanel(), BorderLayout.CENTER);

        JPanel covenantPanel = new JPanel();
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

    private JPanel createUNSCPlayerPanel() {

        String[] colorProfileList = {"Snow & Ice", "Burnt Oak", "Forest Green", "Gunmetal Gray"};
        int nameLabelPadding = 10;
        int playerSeparationPadding = 15;

        JPanel playerListPanel = new JPanel();
        playerListPanel.setLayout(new BoxLayout(playerListPanel, BoxLayout.Y_AXIS));
        playerListPanel.setBackground(Color.WHITE);
        playerListPanel.setBorder(new EmptyBorder(15, 15, 0, 15));

        for (int x = 0; x < 6; x++) {

            JPanel playerPanel = new JPanel();
            playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.X_AXIS));
            playerPanel.setBorder(new EmptyBorder(0, 0, playerSeparationPadding, 0));
            playerPanel.setBackground(Color.WHITE);
            playerPanel.add(new JLabel("Player " + (x + 1)));
            playerPanel.add(new Box.Filler(
                    new Dimension(nameLabelPadding, 0),
                    new Dimension(nameLabelPadding, 0),
                    new Dimension(nameLabelPadding, 0)));

            final int playerNumber = x + 1;
            JComboBox comboBox = new JComboBox(colorProfileList);
            comboBox.setSelectedIndex(0);
            comboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    listener.handleUNSCPlayerProfileUpdated(playerNumber, comboBox.getSelectedItem().toString());
                }
            });
            unscComboBoxes[x] = comboBox;
            playerPanel.add(comboBox);

            playerListPanel.add(playerPanel);
        }

        return playerListPanel;
    }

    private JPanel createUNSCFactionPanel() {

        int iconDimension = 100;

        JPanel factionPanel = new JPanel();
        factionPanel.setBackground(Color.WHITE);

        JPanel resetButtonPanel = new JPanel();
        resetButtonPanel.setBackground(Color.WHITE);
        JButton resetButton = new JButton("Reset");
        resetButtonPanel.add(resetButton, BorderLayout.SOUTH);

        JPanel iconPanel = new JPanel(new GridLayout(1, 1));
        iconPanel.setBackground(ArmoryColor.WINDOW_TEST_COLOR);
        iconPanel.add(new Box.Filler(
                new Dimension(iconDimension,iconDimension),
                new Dimension(iconDimension,iconDimension),
                new Dimension(iconDimension,iconDimension)));

        JPanel saveButtonPanel = new JPanel();
        saveButtonPanel.setBackground(Color.WHITE);
        JButton saveButton = new JButton("Save");
        saveButtonPanel.add(saveButton, BorderLayout.SOUTH);

        factionPanel.add(resetButtonPanel, BorderLayout.WEST);
        factionPanel.add(iconPanel, BorderLayout.CENTER);
        factionPanel.add(saveButtonPanel, BorderLayout.EAST);

        return factionPanel;
    }


    /*--- Listener Interface ---*/

    interface FactionConfigurationPanelListener {

        void handleUNSCPlayerProfileUpdated(int playerNumber, String profileName);

        void handleCovenantPlayerProfileUpdated(int playerNumber, String profileName);
    }
}