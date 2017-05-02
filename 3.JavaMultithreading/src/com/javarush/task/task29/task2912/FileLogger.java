package com.javarush.task.task29.task2912;

public class FileLogger extends AbstractLogger implements Logger {


    public FileLogger(int level) {
        this.level = level;
    }





    @Override
    public void info(String message) {
        System.out.println("Logging to file: " + message);
    }
}