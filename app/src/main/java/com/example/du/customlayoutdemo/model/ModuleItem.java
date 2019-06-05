package com.example.du.customlayoutdemo.model;

import java.util.List;

public class ModuleItem {
    private String layoutCode;
    private String moduleTitle;
    private int width;
    private int height;
    private int marginLeft;
    private int marginTop;
    private int marginRight;
    private int marginBottom;
    private List<ProgramInfo> programInfos;

    public ModuleItem() {}

    public ModuleItem(String layoutCode, String moduleTitle) {
        this.layoutCode = layoutCode;
        this.moduleTitle = moduleTitle;
    }

    public void setLayoutCode(String layoutCode) {
        this.layoutCode = layoutCode;
    }

    public String getLayoutCode() {
        return layoutCode;
    }

    public void setModuleTitle(String moduleTitle) {
        this.moduleTitle = moduleTitle;
    }

    public String getModuleTitle() {
        return moduleTitle;
    }

    public void setProgramInfos(List<ProgramInfo> programInfos) {
        this.programInfos = programInfos;
    }

    public List<ProgramInfo> getProgramInfos() {
        return programInfos;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setMarginLeft(int marginLeft) {
        this.marginLeft = marginLeft;
    }

    public int getMarginLeft() {
        return marginLeft;
    }

    public void setMarginTop(int marginTop) {
        this.marginTop = marginTop;
    }

    public int getMarginTop() {
        return marginTop;
    }

    public void setMarginRight(int marginRight) {
        this.marginRight = marginRight;
    }

    public int getMarginRight() {
        return marginRight;
    }

    public void setMarginBottom(int marginBottom) {
        this.marginBottom = marginBottom;
    }

    public int getMarginBottom() {
        return marginBottom;
    }
}

