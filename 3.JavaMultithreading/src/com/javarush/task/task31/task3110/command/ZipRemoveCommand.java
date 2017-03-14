package com.javarush.task.task31.task3110.command;

import com.javarush.task.task31.task3110.ConsoleHelper;
import com.javarush.task.task31.task3110.ZipFileManager;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Rumata on 09.03.2017.
 */
public class ZipRemoveCommand extends ZipCommand {
    @Override
    public void execute() throws Exception {
        ConsoleHelper.writeMessage("Удаление из архива:");
        ZipFileManager zipFileManager = getZipFileManager();
        ConsoleHelper.writeMessage("Введите полное имя файла или директории для удаления:");

        Path fileToDelete = Paths.get(ConsoleHelper.readString());
        zipFileManager.removeFile(fileToDelete);

        ConsoleHelper.writeMessage("Файл удален.");
    }
}
