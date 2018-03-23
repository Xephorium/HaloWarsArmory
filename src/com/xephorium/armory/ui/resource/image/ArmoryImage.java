package com.xephorium.armory.ui.resource.image;

import javax.swing.*;

public class ArmoryImage {

    private static String ICON_INSTALLATION_FOUND_PATH = "src\\com\\xephorium\\armory\\ui\\resource\\image\\iconInstallationFound.png";
    private static String ICON_INSTALLATION_UNKNOWN_PATH = "src\\com\\xephorium\\armory\\ui\\resource\\image\\iconInstallationUnknown.png";
    private static String ICON_INSTALLATION_INVALID_PATH = "src\\com\\xephorium\\armory\\ui\\resource\\image\\iconInstallationInvalid.png";

    public static ImageIcon ICON_INSTALLATION_FOUND = new ImageIcon(ICON_INSTALLATION_FOUND_PATH, "Installation Found");
    public static ImageIcon ICON_INSTALLATION_UNKNOWN = new ImageIcon(ICON_INSTALLATION_UNKNOWN_PATH, "Installation Unknown");
    public static ImageIcon ICON_INSTALLATION_INVALID = new ImageIcon(ICON_INSTALLATION_INVALID_PATH, "Installation Invalid");
}
