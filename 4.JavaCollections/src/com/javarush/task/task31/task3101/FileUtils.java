package com.javarush.task.task31.task3101;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class FileUtils {

    public static void deleteFile(File file) {
        if (!file.delete()) System.out.println("Can not delete file with name " + file.getName());
    }

    public static void renameFile(File source, File destination) {
        if (!source.renameTo(destination)) System.out.println("Can not rename file with name " + source.getName());
    }
}
    class MyFileVisitor extends SimpleFileVisitor
{

    public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
        File file = path.toFile();
        if (file.length() <= 50) {Solution.listOfFiles.add(path);}
        else FileUtils.deleteFile(file);

        return FileVisitResult.CONTINUE;
    }
}