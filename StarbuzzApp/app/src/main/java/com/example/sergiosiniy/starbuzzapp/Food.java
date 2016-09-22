package com.example.sergiosiniy.starbuzzapp;

/**
 * Created by helpdeskss on 22.09.2016.
 */

public class Food {

    private String name;
    private String description;
    private int imageResourceId;

    public static final Food[] dishes = {
            new Food("Burger", "Fast food which will make you die :D", R.drawable.burger),
            new Food("Salad", "Healthy food. Good for you at all.", R.drawable.salad),
            new Food("Corndog", "Fast food, like a burger.", R.drawable.corndog)
    };

    private Food(String name, String descr, int resId) {
        this.name = name;
        this.description = descr;
        this.imageResourceId = resId;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String toString() {
        return this.name;
    }
}
