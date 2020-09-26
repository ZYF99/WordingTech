package com.xxx.wordingtech.model.shop;

public class FilmComment {
    private long mid;
    private String content;

    public FilmComment(long mid, String content) {
        this.mid = mid;
        this.content = content;
    }

    public long getMid() {
        return mid;
    }

    public void setMid(long mid) {
        this.mid = mid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
