package com.example.newdemo.json_models.response;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class UserInfor extends RealmObject {
    @SerializedName("userId")
    private String userId;

    @SerializedName("fullName")
    private String fullName;

    @SerializedName("avatarUrl")
    private String avatar;

    public UserInfor() {
    }

    public UserInfor(String userId, String fullName, String avatar) {
        this.userId = userId;
        this.fullName = fullName;
        this.avatar = avatar;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "UserInfor{" +
                "userId='" + userId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
