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
        writeMessage("Choose the dish from the list below");
        writeMessage(Dish.allDishesToString());
        writeMessage("Print your order");
        List<Dish> order = new ArrayList<>();
        boolean notExit = true;
        String dishToOrder;
        while (notExit) {
            writeMessage("Choose another dish, or \"exit\" to end ordering");
            dishToOrder = readString();
            switch (dishToOrder) {
                case "Fish":
                    order.add(Dish.Fish);
                    break;
                case "Steak":
                    order.add(Dish.Steak);
                    break;
                case "Soup":
                    order.add(Dish.Soup);
                    break;
                case "Juice":
                    order.add(Dish.Juice);
                    break;
                case "Water":
                    order.add(Dish.Water);
                    break;
                case "exit":
                    notExit = false;
                    break;
                default:
                    writeMessage("No such dish in our restaurant");
                    break;
            }
        }


        return order;
    }
}
