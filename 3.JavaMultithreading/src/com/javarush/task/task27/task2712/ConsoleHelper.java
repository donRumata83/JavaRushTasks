package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rumata on 03.05.2017.
 */
public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return reader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        List<Dish> dishes = new ArrayList<>();
        ConsoleHelper.writeMessage("Выберите блюда. Для завершения наберите 'exit'.");
        ConsoleHelper.writeMessage(Dish.allDishesToString());
        while (true) {
            String dishToOrder = readString();
            if (dishToOrder.equalsIgnoreCase("exit")) {
                break;
            }
//            try {
//                dishes.add(Dish.valueOf(dishToOrder)); //?
//            }
//            catch (IllegalArgumentException e) {
//                ConsoleHelper.writeMessage(dishToOrder + " is not detected");
//            }
            if(dishToOrder.isEmpty()){
                writeMessage("Блюдо не выбрано");
                continue;
            }
            boolean found = false;
            for(Dish d : Dish.values())
                if(d.name().equalsIgnoreCase(dishToOrder)) {
                    dishes.add(d);
                    found = true;
                }
            if(!found){
                writeMessage("Нет такого блюда");
            }
        }
        return dishes;
    }
}
