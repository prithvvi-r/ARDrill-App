package com.example.ardrillapp;

public class Drill {
    private String name;
    private String description;
    private String tips;
    private int imageResource;

    public Drill(String name, String description, String tips, int imageResource) {
        this.name = name;
        this.description = description;
        this.tips = tips;
        this.imageResource = imageResource;
    }

    // Getters
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getTips() { return tips; }
    public int getImageResource() { return imageResource; }
}