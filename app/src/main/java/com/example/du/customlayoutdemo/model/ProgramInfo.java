package com.example.du.customlayoutdemo.model;

public class ProgramInfo {
    private String cellCode;
    private int width;
    private int height;
    private int x;
    private int y;
    private String content;

    public ProgramInfo() {}

    public ProgramInfo(String cellCode, String content) {
        this.cellCode = cellCode;
        this.content = content;
    }

    public ProgramInfo(String cellCode, int width, int height, int x, int y, String content) {
        this.cellCode = cellCode;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.content = content;
    }

    public void setCellCode(String cellCode) {
        this.cellCode = cellCode;
    }

    public String getCellCode() {
        return cellCode;
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

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
