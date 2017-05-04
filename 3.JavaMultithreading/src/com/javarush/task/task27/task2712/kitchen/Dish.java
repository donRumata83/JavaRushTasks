package com.javarush.task.task27.task2712.kitchen;

import java.util.Arrays;

/**
 * Created by Rumata on 03.05.2017.
 */
public enum Dish {
    Fish (25),
    Steak (30),
    Soup (15),
    Juice (5),
    Water (3);

    private int duration;

    public int getDuration() {
        return duration;
    }

    Dish(int duration) {
        this.duration = duration;
    }

    public static String allDishesToString() {
       // StringBuilder stringBuilder = new StringBuilder("");
       // Dish[] dishes = Dish.values();
       // stringBuilder.append(dishes[0]);
       // for (int i = 1; i < dishes.length; i++) {
       //     stringBuilder.append(", ").append(dishes[i]);
       // }
       // return stringBuilder.toString();
        return Arrays.toString(Dish.values()).substring(1, Arrays.toString(Dish.values()).length()-1);
    }
}
