package com.javarush.task.task31.task3101;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
Проход по дереву файлов
*/
public class Solution {
    public static List<Path> listOfFiles;


    public static void main(String[] args) {
        try {
            Files.walkFileTree(Paths.get(args[0]), new MyFileVisitor());
        } catch (IOException e) {e.printStackTrace();}
        Collections.sort(listOfFiles, new Comparator<Path>() {
            @Override
            public int compare(Path o1, Path o2) {
                return o1.getFileName().compareTo(o2.getFileName());
            }
        });
        Path pathToFile = Paths.get(args[1]);

        try {
        Files.createFile(pathToFile);
        FileUtils.renameFile();
        } catch (IOException e) {}
    }

    public static void deleteFile(File file) {
        if (!file.delete()) System.out.println("Can not delete file with name " + file.getName());
    }
}
