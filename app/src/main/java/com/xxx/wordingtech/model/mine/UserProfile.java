package com.xxx.wordingtech.model.mine;

public class UserProfile {
    String account;
    String avatar;
    String background;
    Long birthday;
    Long createTime;
    String gender;
    String nikeName;
    String role;
    String signature;
    int status;
    int uid;
    Long updateTime;

    public UserProfile(String account, String avatar, String background, Long birthday, Long createTime, String gender, String nikeName, String role, String signature, int status, int uid, Long updateTime) {
        this.account = account;
        this.avatar = avatar;
        this.background = background;
        this.birthday = birthday;
        this.createTime = createTime;
        this.gender = gender;
        this.nikeName = nikeName;
        this.role = role;
        this.signature = signature;
        this.status = status;
        this.uid = uid;
        this.updateTime = updateTime;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}