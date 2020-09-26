package com.xxx.wordingtech.model.shop;

import java.util.List;

public class Shop {
    private long mid;
    private String name;
    private String director;
    private String releaseTime;
    private String introduction;
    private String image;
    private String price;
    private List<String> classifyList;

    public Shop(long mid, String name, String director, String releaseTime, String introduction, String image, String price) {
        this.mid = mid;
        this.name = name;
        this.director = director;
        this.releaseTime = releaseTime;
        this.introduction = introduction;
        this.image = image;
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setMid(long mid) {
        this.mid = mid;
    }
    public long getMid() {
        return mid;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setDirector(String director) {
        this.director = director;
    }
    public String getDirector() {
        return director;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }
    public String getReleaseTime() {
        return releaseTime;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
    public String getIntroduction() {
        return introduction;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
