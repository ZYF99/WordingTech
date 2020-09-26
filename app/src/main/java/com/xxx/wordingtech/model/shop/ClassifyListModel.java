package com.xxx.wordingtech.model.shop;

import java.util.List;

public class ClassifyListModel {

    private List<String> classifyList;

    public ClassifyListModel(List<String> classifyList) {
        this.classifyList = classifyList;
    }

    public List<String> getClassifyList() {
        return classifyList;
    }

    public void setClassifyList(List<String> classifyList) {
        this.classifyList = classifyList;
    }
}
