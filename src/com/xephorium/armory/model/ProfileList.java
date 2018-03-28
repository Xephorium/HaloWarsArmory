package com.xephorium.armory.model;

import java.util.ArrayList;
import java.util.List;

public class ProfileList {


    /*--- Variables ---*/

    private List<Profile> profileList;


    /*--- Constructors ---*/

    public ProfileList() {
        profileList = new ArrayList<>();
    }

    public ProfileList(Profile profile) {
        profileList = new ArrayList<>();
        profileList.add(profile);
    }


    /*--- Public Methods ---*/

    public Profile getByPrimaryKey(Profile profile) {

        // TODO - Add Primary Key Enforcement
        for (int x = 0; x < profileList.size(); x++) {
            if (profile.getPrimaryKey() == profileList.get(x).getPrimaryKey()) {
                return profileList.get(x).cloneProfile();
            }
        }
        return null;
    }

    public Profile getByIndex(int index) {
        return profileList.get(index).cloneProfile();
    }

    public void add(Profile profile) {

        // TODO - Add Primary Key Enforcement
        if (this.containsProfile(profile)) {
            updateProfile(profile);
        } else {
            profileList.add(profile);
        }
    }

    @Override
    public ProfileList clone() {
        ProfileList newProfileList = new ProfileList();
        for (int x = 0; x < profileList.size(); x++) {
            newProfileList.add(profileList.get(x).cloneProfile());
        }
        return newProfileList;
    }

    public int getIndexOrFirstIndex(Profile profile) {
        for (int x = 0; x < profileList.size(); x++) {
            if (profile.getPrimaryKey() == profileList.get(x).getPrimaryKey()) {
                return x;
            }
        }
        return 0;
    }

    public String[] getNameList() {
        String[] profileNames = new String[profileList.size()];
        for (int x = 0; x < profileList.size(); x++) {
            profileNames[x] = profileList.get(x).getName();
        }
        return profileNames;
    }

    public int size() {
        return profileList.size();
    }


    /*--- Private Methods ---*/

    private boolean containsProfile(Profile profile) {
        for (int x = 0; x < profileList.size(); x++) {
            if (profile.getPrimaryKey() == profileList.get(x).getPrimaryKey()) {
                return true;
            }
        }
        return false;
    }

    private void updateProfile(Profile profile) {
        for (int x = 0; x < profileList.size(); x++) {
            if (profileList.get(x).getPrimaryKey() == profile.getPrimaryKey()) {
                profileList.set(x, profile);
            }
        }
    }
}
