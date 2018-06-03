package com.xephorium.armory.ui;

import com.xephorium.armory.model.Profile;
import com.xephorium.armory.model.Faction;
import com.xephorium.armory.model.ProfileList;
import com.xephorium.armory.ui.resource.color.ArmoryColor;
import com.xephorium.armory.ui.resource.dimension.ArmoryDimension;
import com.xephorium.armory.ui.resource.font.ArmoryFont;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import static com.xephorium.armory.ui.resource.image.ArmoryImage.ICON_COVENANT;
import static com.xephorium.armory.ui.resource.image.ArmoryImage.ICON_UNSC;

public class FactionConfigurationPanel extends JPanel {


    /*--- Variables ---*/

    private enum ButtonType {
        RESET,
        SAVE
    }

    private ArmoryWindowListener listener;

    private List<JComboBox> unscSelectors = new ArrayList<>();
    private List<JComboBox> covenantSelectors = new ArrayList<>();
    private ProfileList profileList = new ProfileList(new Profile("No Profiles Available"));
    private JButton saveButton;
    private JButton resetButton;
    private boolean selectorSetupComplete = false;
    private boolean selectorEmptyState = false;


    /*--- Constructor ---*/

    public FactionConfigurationPanel(ArmoryWindowListener listener) {
        this.listener = listener;

        initializePanelAttributes();
        createTabbedPane();
    }


    /*--- Public Methods ---*/

    public void updateProfileList(ProfileList profileList) {
        selectorSetupComplete = false;
        ProfileList newProfileList = profileList.clone();

        if (newProfileList.isEmpty()) {
            selectorEmptyState = true;
            setEmptyFactionSelectors(unscSelectors);
            setEmptyFactionSelectors(covenantSelectors);
            return;
        }
        updateFactionSelectors(unscSelectors, newProfileList);
        updateFactionSelectors(covenantSelectors, newProfileList);
        this.profileList = newProfileList;

        selectorSetupComplete = true;
        selectorEmptyState = false;
    }

    public void updateUNSCPlayerConfiguration(List<Integer> unscPlayerProfiles) {
        if (unscPlayerProfiles.size() < 1) {
            return;
        }

        for (int x = 0; x < unscPlayerProfiles.size(); x++) {
            unscSelectors.get(x).setSelectedIndex(profileList.getIndexOrFirstIndex(unscPlayerProfiles.get(x)));
        }
    }

    public void updateCovenantPlayerConfiguration(List<Integer> covenantPlayerProfiles) {
        if (covenantPlayerProfiles.size() < 1) {
            return;
        }

        for (int x = 0; x < covenantPlayerProfiles.size(); x++) {
            covenantSelectors.get(x).setSelectedIndex(profileList.getIndexOrFirstIndex(covenantPlayerProfiles.get(x)));
        }
    }

    public void disableConfigurationEdit() {
        for (int x = 0; x < 6; x++) {
            unscSelectors.get(x).setEnabled(false);
            covenantSelectors.get(x).setEnabled(false);
        }
    }

    public void enableConfigurationEdit() {
        for (int x = 0; x < 6; x++) {
            unscSelectors.get(x).setEnabled(true);
            covenantSelectors.get(x).setEnabled(true);
        }
    }


    /*--- Private Update Methods ---*/

    private void updateFactionSelectors(List<JComboBox> factionSelectors, ProfileList newProfileList) {
        for (JComboBox selector : factionSelectors) {
            int oldPrimaryKey = profileList.getProfileByIndex(selector.getSelectedIndex()).getPrimaryKey();
            selector.removeAllItems();
            for (int y = 0; y < newProfileList.size(); y++) {
                selector.addItem(newProfileList.getProfileByIndex(y).getName());
                if (oldPrimaryKey == newProfileList.getProfileByIndex(y).getPrimaryKey()) {
                    selector.setSelectedIndex(y);
                }
            }
        }
    }

    private void setEmptyFactionSelectors(List<JComboBox> factionSelectors) {
        for (JComboBox selector : factionSelectors) {
            selector.removeAllItems();
            selector.addItem("No Profiles Available");
            selector.setSelectedIndex(0);
        }
    }


    /*--- Private Setup Methods ---*/

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
        unscPanel.setBorder(new EmptyBorder(
                ArmoryDimension.FACTION_PLAYER_PADDING_VERTICAL,
                ArmoryDimension.FACTION_PLAYER_PADDING_HORIZONTAL,
                ArmoryDimension.FACTION_PLAYER_PADDING_VERTICAL,
                ArmoryDimension.FACTION_PLAYER_PADDING_HORIZONTAL));
        unscPanel.add(createPlayerListPanel(Faction.UNSC), BorderLayout.NORTH);
        unscPanel.add(createFactionPanel(Faction.UNSC), BorderLayout.CENTER);

        JLabel unscLabel = new JLabel("UNSC");
        unscLabel.setFont(ArmoryFont.NORMAL_BOLD);
        unscLabel.setHorizontalAlignment(JLabel.CENTER);
        unscLabel.setBorder(new EmptyBorder(4, 10, 3, 10));

        tabbedPane.addTab("UNSC", unscPanel);
        tabbedPane.setTabComponentAt(0, unscLabel);

        JPanel covenantPanel = new JPanel();
        covenantPanel.setBackground(Color.WHITE);
        covenantPanel.setLayout(new BorderLayout());
        covenantPanel.setBorder(new EmptyBorder(
                ArmoryDimension.FACTION_PLAYER_PADDING_VERTICAL,
                ArmoryDimension.FACTION_PLAYER_PADDING_HORIZONTAL,
                ArmoryDimension.FACTION_PLAYER_PADDING_VERTICAL,
                ArmoryDimension.FACTION_PLAYER_PADDING_HORIZONTAL));
        covenantPanel.add(createPlayerListPanel(Faction.COVENANT), BorderLayout.NORTH);
        covenantPanel.add(createFactionPanel(Faction.COVENANT), BorderLayout.CENTER);

        JLabel covenantLabel = new JLabel("Covenant");
        covenantLabel.setFont(ArmoryFont.NORMAL_BOLD);
        covenantLabel.setHorizontalAlignment(JLabel.CENTER);
        covenantLabel.setBorder(new EmptyBorder(4, 3, 3, 4));

        tabbedPane.addTab("Covenant", covenantPanel);
        tabbedPane.setTabComponentAt(1, covenantLabel);

        this.add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createPlayerListPanel(Faction faction) {
        int nameLabelPadding = 10;

        JPanel playerListPanel = new JPanel();
        playerListPanel.setLayout(new BoxLayout(playerListPanel, BoxLayout.Y_AXIS));
        playerListPanel.setBackground(Color.WHITE);

        for (int playerNumber = 0; playerNumber < 6; playerNumber++) {

            JPanel playerPanel = new JPanel();
            playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.X_AXIS));
            playerPanel.setBorder(new EmptyBorder(0, 0, ArmoryDimension.FACTION_PLAYER_PADDING_VERTICAL, 0));
            playerPanel.setBackground(Color.WHITE);
            playerPanel.add(new JLabel("Player " + (playerNumber + 1)));
            playerPanel.add(new Box.Filler(
                    new Dimension(nameLabelPadding, 0),
                    new Dimension(nameLabelPadding, 0),
                    new Dimension(nameLabelPadding, 0)));

            if (faction == Faction.UNSC) {
                unscSelectors.add(createFactionComboBox(Faction.UNSC, playerNumber));
                playerPanel.add(unscSelectors.get(playerNumber));
            } else {
                covenantSelectors.add(createFactionComboBox(Faction.COVENANT, playerNumber));
                playerPanel.add(covenantSelectors.get(playerNumber));
            }

            playerListPanel.add(playerPanel);
        }

        return playerListPanel;
    }

    private JComboBox createFactionComboBox(Faction faction, int playerNumber) {
        JComboBox comboBox;
        comboBox = new JComboBox(profileList.getNameList());
        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    if (!selectorSetupComplete || selectorEmptyState) {
                        return;
                    }

                    if (faction == Faction.UNSC) {
                        listener.handleUNSCPlayerUpdate(playerNumber,
                                profileList.getProfileByIndex(comboBox.getSelectedIndex()).getPrimaryKey());
                    } else {
                        listener.handleCovenantPlayerUpdate(playerNumber,
                                profileList.getProfileByIndex(comboBox.getSelectedIndex()).getPrimaryKey());
                    }
                }
            }
        });
        return comboBox;
    }

    private JPanel createFactionPanel(Faction faction) {
        JPanel factionPanel = new JPanel(new BorderLayout());
        factionPanel.setLayout(new BoxLayout(factionPanel, BoxLayout.X_AXIS));
        factionPanel.setBackground(Color.WHITE);

        factionPanel.add(createFactionButtonPanel(ButtonType.RESET, faction), BorderLayout.LINE_START);
        factionPanel.add(createFactionIconPanel(faction), BorderLayout.CENTER);
        factionPanel.add(createFactionButtonPanel(ButtonType.SAVE, faction), BorderLayout.LINE_END);

        return factionPanel;
    }

    private JPanel createFactionButtonPanel(ButtonType buttonType, Faction faction) {
        JPanel mainButtonPanel = new JPanel(new BorderLayout());
        mainButtonPanel.setBackground(Color.WHITE);

        FlowLayout flowLayout = new FlowLayout(FlowLayout.LEADING, 0, 0);
        flowLayout.setAlignment(buttonType == ButtonType.RESET ? FlowLayout.LEFT : FlowLayout.RIGHT);

        JPanel horizontalButtonPanel = new JPanel(flowLayout);
        horizontalButtonPanel.setBackground(Color.WHITE);

        if (buttonType == ButtonType.SAVE) {
            saveButton = new JButton("Save");
            saveButton.addActionListener(getFactionButtonActionListener(buttonType, faction));
        } else {
            resetButton = new JButton("Reset");
            resetButton.addActionListener(getFactionButtonActionListener(buttonType, faction));
        }

        horizontalButtonPanel.add(buttonType == ButtonType.SAVE ? saveButton : resetButton);
        mainButtonPanel.add(horizontalButtonPanel, BorderLayout.SOUTH);

        return mainButtonPanel;
    }

    private ActionListener getFactionButtonActionListener(ButtonType buttonType, Faction faction) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (buttonType == ButtonType.RESET) {
                    if (faction == Faction.UNSC) {
                        listener.handleUNSCConfigurationReset();
                    } else {
                        listener.handleCovenantConfigurationReset();
                    }
                } else {
                    if (!selectorEmptyState) {
                        if (faction == Faction.UNSC) {
                            listener.handleUNSCConfigurationSave();
                        } else {
                            listener.handleCovenantConfigurationSave();
                        }
                    }
                }
            }
        };
    }

    private JPanel createFactionIconPanel(Faction faction) {
        JPanel iconPanel = new JPanel(new GridLayout(1, 1));
        iconPanel.setBackground(Color.WHITE);
        iconPanel.add(new JLabel("", faction == Faction.UNSC ? ICON_UNSC : ICON_COVENANT, JLabel.CENTER));
        return iconPanel;
    }
}