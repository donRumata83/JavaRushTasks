package com.javarush.task.task30.task3008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() {

        while (true)
        {
            try
            {
                return reader.readLine();

            }
            catch (IOException e) {writeMessage("Произошла ошибка при попытке ввода текста. Попробуйте еще раз.");}
        }

    }

    public static int readInt() {
        while (true)
        {
            try {
                return Integer.parseInt(readString());
            } catch (NumberFormatException e) {writeMessage("Произошла ошибка при попытке ввода числа. Попробуйте еще раз.");}
        }
    }
}
