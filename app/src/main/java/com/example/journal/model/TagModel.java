package com.example.journal.model;

public class TagModel {
    private String tag;
    private boolean selected;

    public TagModel(String tag, boolean selected) {
        this.tag = tag;
        this.selected = selected;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
