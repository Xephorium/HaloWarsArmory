package com.xephorium.armory.repository;

public class SystemRepository {

    public static String getUsername() {
        return System.getProperty("user.name");
    }
}
