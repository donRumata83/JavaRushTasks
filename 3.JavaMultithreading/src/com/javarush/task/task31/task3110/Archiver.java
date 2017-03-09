package com.javarush.task.task31.task3110;


import com.javarush.task.task31.task3110.command.ExitCommand;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by Rumata on 09.03.2017.
 */
public class Archiver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите путь к архиву:");
        String archiveFile = scanner.nextLine();
        ZipFileManager zipFileManager = new ZipFileManager(Paths.get(archiveFile));
        System.out.println("введите путь к файлу который надо запаковать:");
        String targetFile = scanner.nextLine();
        try {
            zipFileManager.createZip(Paths.get(targetFile));
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
        try {
            new ExitCommand().execute();
        } catch (Exception e) {
        }

    }

    public static Operation askOperation() throws IOException {
        Operation operation = Operation.EXIT;

            ConsoleHelper.writeMessage("Выберите операцию:");
            ConsoleHelper.writeMessage(Operation.ADD.ordinal() + " добавить файл в архив");
            ConsoleHelper.writeMessage(Operation.CREATE.ordinal() + " упаковать файлы в архив");
            ConsoleHelper.writeMessage(Operation.EXTRACT.ordinal() + " распаковать архив");
            ConsoleHelper.writeMessage(Operation.CONTENT.ordinal() + " просмотреть содержимое архива");
            ConsoleHelper.writeMessage(Operation.EXIT.ordinal() + " выход");
            int answer = ConsoleHelper.readInt();

            for (Operation op : Operation.values()) {
                if (op.ordinal() == answer) operation = op;

            }

        return operation;
    }
}
