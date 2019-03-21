package com.even.recyclerviewdemo.beans;

/**
 * @author by Even on 2019/3/20
 * Emial: emailtopan@163.com
 */
public class StickyBean {
    private String name;
    /**
     * 0标题，1内容
     */
    private int type;

    public StickyBean(String name, int type) {
        this.name = name;
        this.type = type;
    }

    public StickyBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
