package com.xxx.wordingtech.model.mine;

public class UpdateUserProfileRequestModel {
    String avatar;
    String background;
    Long birthday;
    String gender;
    String nikeName;
    String signature;

    public UpdateUserProfileRequestModel(String avatar, String background, Long birthday, String gender, String nikeName, String signature) {
        this.avatar = avatar;
        this.background = background;
        this.birthday = birthday;
        this.gender = gender;
        this.nikeName = nikeName;
        this.signature = signature;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
