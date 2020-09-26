package com.xxx.wordingtech.model.register;

public class RegisterRequestModel {
    String account;
    String avatar = "https://w.wallhaven.cc/full/39/wallhaven-39pw6v.jpg";
    String gender;
    String nikeName;
    String password;
    String role = "User";

    public RegisterRequestModel(String account, String avatar, String gender, String nikeName, String password, String role) {
        this.account = account;
        this.avatar = avatar;
        this.gender = gender;
        this.nikeName = nikeName;
        this.password = password;
        this.role = role;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
