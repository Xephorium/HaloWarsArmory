package com.xephorium.armory.java.ui;

import java.awt.*;

public class ViewUtility {

    public static int getWindowStartX(int windowWidth) {
        return (getScreenWidth()/2) - (windowWidth /2);
    }

    public static int getWindowStartY(int windowHeight) {
        return (getScreenHeight()/2) - (windowHeight /2);
    }

    public static int getScreenHeight() {
        return Toolkit.getDefaultToolkit().getScreenSize().height;
    }

    public static int getScreenWidth() {
        return Toolkit.getDefaultToolkit().getScreenSize().width;
    }
}
