package com.javarush.task.task28.task2810;


import com.javarush.task.task28.task2810.model.Provider;
import com.javarush.task.task28.task2810.model.Strategy;

public class Aggregator {
    public static void main(String[] args) {
        Provider provider = new Provider(new Strategy() {
        });
        Controller controller = new Controller(provider);
        System.out.println(controller);
    }

}
