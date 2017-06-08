package com.v1ncent.io.gank.find.pojo;

/**
 * Created by v1ncent on 2017/5/23.
 */

public class CategoryVO {
    private int icon;
    private int bg;
    private String name;

    public CategoryVO(int icon, int bg, String name) {
        this.icon = icon;
        this.bg = bg;
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getBg() {
        return bg;
    }

    public void setBg(int bg) {
        this.bg = bg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
