package com.xephorium.armory.ui.resource.image;

import javax.swing.*;

public class ArmoryImage {

    private static boolean USE_DETAILED_ICONS = true;

    private static String ICON_UNSC_SIMPLE_PATH = "src\\com\\xephorium\\armory\\ui\\resource\\image\\iconFactionUNSCSimple.png";
    private static String ICON_UNSC_DETAILED_PATH = "src\\com\\xephorium\\armory\\ui\\resource\\image\\iconFactionUNSCDetailed.png";
    private static String ICON_COVENANT_SIMPLE_PATH = "src\\com\\xephorium\\armory\\ui\\resource\\image\\iconFactionCovenantSimple.png";
    private static String ICON_COVENANT_DETAILED_PATH = "src\\com\\xephorium\\armory\\ui\\resource\\image\\iconFactionCovenantDetailed.png";
    private static String ICON_INSTALLATION_FOUND_PATH = "src\\com\\xephorium\\armory\\ui\\resource\\image\\iconInstallationFound.png";
    private static String ICON_INSTALLATION_UNKNOWN_PATH = "src\\com\\xephorium\\armory\\ui\\resource\\image\\iconInstallationUnknown.png";
    private static String ICON_INSTALLATION_INVALID_PATH = "src\\com\\xephorium\\armory\\ui\\resource\\image\\iconInstallationInvalid.png";

    public static ImageIcon ICON_UNSC = new ImageIcon(USE_DETAILED_ICONS ? ICON_UNSC_DETAILED_PATH : ICON_UNSC_SIMPLE_PATH, "UNSC Faction");
    public static ImageIcon ICON_COVENANT = new ImageIcon(USE_DETAILED_ICONS ? ICON_COVENANT_DETAILED_PATH : ICON_COVENANT_SIMPLE_PATH, "Covenant Faction");
    public static ImageIcon ICON_INSTALLATION_FOUND = new ImageIcon(ICON_INSTALLATION_FOUND_PATH, "Installation Found");
    public static ImageIcon ICON_INSTALLATION_UNKNOWN = new ImageIcon(ICON_INSTALLATION_UNKNOWN_PATH, "Installation Unknown");
    public static ImageIcon ICON_INSTALLATION_INVALID = new ImageIcon(ICON_INSTALLATION_INVALID_PATH, "Installation Invalid");
}
