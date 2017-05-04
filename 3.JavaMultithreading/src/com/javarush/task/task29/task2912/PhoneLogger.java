package com.javarush.task.task29.task2912;

public class PhoneLogger extends AbstractLogger implements Logger {

    public PhoneLogger(int level) {
        this.level = level;
    }





    @Override
    public void info(String message) {
        System.out.println("Call to director: " + message);
    }
}