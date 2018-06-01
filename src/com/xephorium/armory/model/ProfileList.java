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

    public Profile getProfileByPrimaryKey(int primaryKey) {
        for (int x = 0; x < profileList.size(); x++) {
            if (primaryKey == profileList.get(x).getPrimaryKey()) {
                return profileList.get(x).cloneProfile();
            }
        }
        return null;
    }

    public Profile getProfileByIndex(int index) {
        return profileList.get(index).cloneProfile();
    }


    public void updateExistingProfile(Profile profile) {
        Profile newProfile = profile.cloneProfile();
        for (int x = 0; x < profileList.size(); x++) {
            if (profileList.get(x).getPrimaryKey() == newProfile.getPrimaryKey()) {
                profileList.set(x, newProfile);
            }
        }
    }

    public void addNewProfile(Profile profile) {
        Profile newProfile = profile.cloneProfile();

        if (newProfile.getPrimaryKey() == Profile.INITIALIZATION_KEY
                || containsPrimaryKey(newProfile.getPrimaryKey())) {
            newProfile.setPrimaryKey(generateNewPrimaryKey());
        }

        profileList.add(newProfile);
    }

    public void addNewProfileTop(Profile profile) {
        Profile newProfile = profile.cloneProfile();

        if (newProfile.getPrimaryKey() == Profile.INITIALIZATION_KEY
                || containsPrimaryKey(newProfile.getPrimaryKey())) {
            newProfile.setPrimaryKey(generateNewPrimaryKey());
        }

        profileList.add(0, newProfile);
    }

    public void addNewProfileAsIs(Profile profile) {
        Profile newProfile = profile.cloneProfile();
        profileList.add(newProfile);
    }

    public void addAllNewProfiles(ProfileList profileList) {
        ProfileList newProfileList = profileList.clone();
        for (int x = 0; x < newProfileList.size(); x++) {
            addNewProfile(newProfileList.getProfileByIndex(x));
        }
    }

    public void addAllNewProfilesAsIs(ProfileList profileList) {
        ProfileList newProfileList = profileList.clone();
        for (int x = 0; x < newProfileList.size(); x++) {
            addNewProfileAsIs(newProfileList.getProfileByIndex(x));
        }
    }

    public void addDefaultFactionProfiles(ProfileList defaultFactionProfiles) {
        ProfileList newDefaultFactionProfiles = defaultFactionProfiles.clone();

        // Append Current Default Profiles to newDefaultFactionProfiles If Not Already There
        for (Profile profile : this.profileList) {
            if (profile.getPrimaryKey() < 0) {
                if (!newDefaultFactionProfiles.containsPrimaryKey(profile.getPrimaryKey())) {
                    newDefaultFactionProfiles.addNewProfileAsIs(profile.cloneProfile());
                }
            }
        }

        // Remove Default Profiles From this
        for (Profile profile : newDefaultFactionProfiles.profileList) {
            if (this.containsPrimaryKey(profile.getPrimaryKey())) {
                this.delete(profile.getPrimaryKey());
            }
        }

        // Sort newDefaultFactionProfiles
        newDefaultFactionProfiles = sortProfileListByPrimaryKey(newDefaultFactionProfiles);

        // Add newDefaultFactionProfiles To this
        for (int x = 0; x < newDefaultFactionProfiles.size(); x++) {
            addNewProfileAsIs(newDefaultFactionProfiles.getProfileByIndex(x).cloneProfile());
        }
    }

    public void delete(int primaryKey) {
        if (containsPrimaryKey(primaryKey)) {
            for (int x = 0; x < profileList.size(); x++) {
                if (primaryKey == profileList.get(x).getPrimaryKey()) {
                    profileList.remove(x);
                }
            }
        }
    }

    public void deleteAll() {
        for (int x = 0; x < profileList.size(); x++) {
            profileList.remove(x);
        }
    }

    @Override
    public ProfileList clone() {
        ProfileList newProfileList = new ProfileList();
        for (int x = 0; x < profileList.size(); x++) {
            // TODO - Change to addNewProfile()
            newProfileList.addNewProfile(profileList.get(x).cloneProfile());
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

    public int getIndexOrFirstIndex(int primaryKey) {
        for (int x = 0; x < profileList.size(); x++) {
            if (primaryKey == profileList.get(x).getPrimaryKey()) {
                return x;
            }
        }
        return 0;
    }

    public String[] getNameList() {
        if (profileList.size() < 1) {
            String[] emptyList = new String[1];
            emptyList[0] = "No Profiles Available";
            return emptyList;
        }

        String[] profileNames = new String[profileList.size()];
        for (int x = 0; x < profileList.size(); x++) {
            profileNames[x] = profileList.get(x).getName();
        }
        return profileNames;
    }

    public int size() {
        return profileList.size();
    }

    public boolean isEmpty() {
        return profileList.isEmpty();
    }

    public String generateNewName() {
        String newNameBase = "New Profile";

        if (!containsName(newNameBase)) {
            return newNameBase;
        } else {
            int x = 1;
            while (containsName(newNameBase + " " + x)) {
                x++;
            }
            return newNameBase + " " + x;
        }

    }

    public boolean containsName(String name) {
        for (int x = 0; x < profileList.size(); x++) {
            if (name.equals(profileList.get(x).getName())) {
                return true;
            }
        }
        return false;
    }

    public boolean containsPrimaryKey(int primaryKey) {
        for (int x = 0; x < profileList.size(); x++) {
            if (primaryKey == profileList.get(x).getPrimaryKey()) {
                return true;
            }
        }
        return false;
    }

    public ProfileList sortProfileListByPrimaryKey(ProfileList profileList) {
        ProfileList sortedProfileList = profileList.clone();
        boolean swapOccurred = true;
        Profile holder;

        while (swapOccurred) {
            swapOccurred = false;
            for(int index = 0; index < sortedProfileList.size() - 1; index++ ) {
                if (sortedProfileList.getProfileByIndex(index).getPrimaryKey() <
                        sortedProfileList.getProfileByIndex(index + 1).getPrimaryKey()) {
                    holder = sortedProfileList.getProfileByIndex(index).cloneProfile();
                    sortedProfileList.setProfileByIndex(index, sortedProfileList.getProfileByIndex(index + 1).cloneProfile());
                    sortedProfileList.setProfileByIndex(index + 1, holder.cloneProfile());
                    swapOccurred = true;
                }
            }
        }

        return sortedProfileList;
    }


    /*--- Private Methods ---*/

    private int generateNewPrimaryKey() {
        int newPrimaryKey = 0;
        while (this.containsPrimaryKey(newPrimaryKey)) {
            newPrimaryKey++;
        }
        return newPrimaryKey;
    }

    private void setProfileByIndex(int index, Profile profile) {
        this.profileList.set(index, profile.cloneProfile());
    }
}
