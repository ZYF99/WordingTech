package com.xxx.wordingtech.model.word;

public class Word {
    String english; //英文
    String chinese; //中文
    String symbol; //音标
    String type; //类型 adj,n,adv...
    public Word(String english, String chinese, String symbol, String type) {
        this.english = english;
        this.chinese = chinese;
        this.symbol = symbol;
        this.type = type;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
