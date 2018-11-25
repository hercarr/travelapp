package com.qacg.travelapp.models;

import io.realm.RealmObject;

public class Profile extends RealmObject {

    private String urlImageProfile;
    private String nameProfile;

    public String getUrlImageProfile() {
        return urlImageProfile;
    }

    public void setUrlImageProfile(String urlImageProfile) {
        this.urlImageProfile = urlImageProfile;
    }

    public String getNameProfile() {
        return nameProfile;
    }

    public void setNameProfile(String nameProfile) {
        this.nameProfile = nameProfile;
    }
}
