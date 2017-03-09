package com.javarush.task.task31.task3110;


import com.javarush.task.task31.task3110.exception.PathIsNotFoundException;

import java.io.IOException;

/**
 * Created by Rumata on 09.03.2017.
 */
public class Archiver {

    public static void main(String[] args) {
        Operation operation;
        while (true) {
            try {
                operation = askOperation();
                CommandExecutor.execute(operation);
                if (operation == Operation.EXIT) break;

            } catch (Exception p) {
                if (p instanceof PathIsNotFoundException)
                    ConsoleHelper.writeMessage("Вы не выбрали файл архива или выбрали неверный файл.");
                else ConsoleHelper.writeMessage("Произошла ошибка. Проверьте введенные данные.");
            }
        }
    }

    public static Operation askOperation() throws IOException {

        ConsoleHelper.writeMessage("Выберите операцию:");
        ConsoleHelper.writeMessage(Operation.CREATE.ordinal() + " - упаковать файлы в архив");
        ConsoleHelper.writeMessage(Operation.ADD.ordinal() + " - добавить файл в архив");
        ConsoleHelper.writeMessage(Operation.REMOVE.ordinal() + " - удалить файл из архива");
        ConsoleHelper.writeMessage(Operation.EXTRACT.ordinal() + " - распаковать архив");
        ConsoleHelper.writeMessage(Operation.CONTENT.ordinal() + " - просмотреть содержимое архива");
        ConsoleHelper.writeMessage(Operation.EXIT.ordinal() + " - выход");
        return Operation.values()[ConsoleHelper.readInt()];


    }
}
