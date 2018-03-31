package com.xephorium.armory.ui.resource.image;

import javax.swing.*;

public class ArmoryImage {

    private static boolean USE_DETAILED_ICONS = true;

    private static String ICON_UNSC_SIMPLE_PATH = "src\\com\\xephorium\\armory\\ui\\resource\\image\\iconFactionUNSCSimple.png";
    private static String ICON_UNSC_DETAILED_PATH = "src\\com\\xephorium\\armory\\ui\\resource\\image\\iconFactionUNSCDetailed.png";
    private static String ICON_COVENANT_SIMPLE_PATH = "src\\com\\xephorium\\armory\\ui\\resource\\image\\iconFactionCovenantSimple.png";
    private static String ICON_COVENANT_DETAILED_PATH = "src\\com\\xephorium\\armory\\ui\\resource\\image\\iconFactionCovenantDetailed.png";
    private static String ICON_INSTALLATION_FOUND_STYLIZED_PATH = "src\\com\\xephorium\\armory\\ui\\resource\\image\\iconInstallationFoundStylized.png";
    private static String ICON_INSTALLATION_FOUND_DIALOG_PATH = "src\\com\\xephorium\\armory\\ui\\resource\\image\\iconInstallationFoundDialog.png";
    private static String ICON_INSTALLATION_INVALID_STYLIZED_PATH = "src\\com\\xephorium\\armory\\ui\\resource\\image\\iconInstallationInvalidStylized.png";
    private static String ICON_INSTALLATION_INVALID_DIALOG_PATH = "src\\com\\xephorium\\armory\\ui\\resource\\image\\iconInstallationInvalidDialog.png";
    private static String ICON_INSTALLATION_UNKNOWN_STYLIZED_PATH = "src\\com\\xephorium\\armory\\ui\\resource\\image\\iconInstallationUnknownStylized.png";
    private static String PREVIEW_BACKGROUND_PATH = "src\\com\\xephorium\\armory\\ui\\resource\\image\\previewLayerMap.png";
    private static String PREVIEW_HUD_PATH = "src\\com\\xephorium\\armory\\ui\\resource\\image\\previewLayerHUD.png";

    public static ImageIcon ICON_UNSC = new ImageIcon(USE_DETAILED_ICONS ? ICON_UNSC_DETAILED_PATH : ICON_UNSC_SIMPLE_PATH, "UNSC Faction");
    public static ImageIcon ICON_COVENANT = new ImageIcon(USE_DETAILED_ICONS ? ICON_COVENANT_DETAILED_PATH : ICON_COVENANT_SIMPLE_PATH, "Covenant Faction");
    public static ImageIcon ICON_INSTALLATION_FOUND = new ImageIcon(ICON_INSTALLATION_FOUND_STYLIZED_PATH, "Installation Found");
    public static ImageIcon ICON_INSTALLATION_FOUND_DIALOG = new ImageIcon(ICON_INSTALLATION_FOUND_DIALOG_PATH, "Installation Found");
    public static ImageIcon ICON_INSTALLATION_INVALID = new ImageIcon(ICON_INSTALLATION_INVALID_STYLIZED_PATH, "Installation Invalid");
    public static ImageIcon ICON_INSTALLATION_INVALID_DIALOG = new ImageIcon(ICON_INSTALLATION_INVALID_DIALOG_PATH, "Installation Invalid");
    public static ImageIcon ICON_INSTALLATION_UNKNOWN = new ImageIcon(ICON_INSTALLATION_UNKNOWN_STYLIZED_PATH, "Installation Unknown");
    public static ImageIcon PREVIEW_BACKGROUND = new ImageIcon(PREVIEW_BACKGROUND_PATH, "Preview Background");
    public static ImageIcon PREVIEW_HUD = new ImageIcon(PREVIEW_HUD_PATH, "Preview HUD");
}
