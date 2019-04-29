package com.example.materialdesigntest;

public class Fruit {
    private String name; // 水果的名字
    private int imageId; // 水果对应图片的资源ID

    public Fruit(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {

        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
