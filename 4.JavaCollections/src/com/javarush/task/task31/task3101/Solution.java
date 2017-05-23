package com.javarush.task.task31.task3101;


import java.io.File;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
Проход по дереву файлов
*/
public class Solution {
    public static void main(String[] args) {
        File pathToSearch = new File(args[0]);
        File targetFile = new File(args[1]);
        PriorityQueue<File> queue = new PriorityQueue<>();
        ArrayList<File> fileList = new ArrayList<>();
        queue.add(pathToSearch);
        Path path;
        File tmp;
        while (!queue.isEmpty()) {
            tmp = queue.poll();
            path = tmp.toPath();
            if (!tmp.equals(targetFile)) {
                if (Files.isRegularFile(path)) {
                    if (tmp.length() <= 50) {
                        fileList.add(tmp);
                    } else FileUtils.deleteFile(tmp);
                }

                if (Files.isDirectory(path)) {
                    try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path)) {
                        for (Path file : directoryStream) {
                            queue.add(file.toFile());
                        }
                    } catch (IOException e) {
                    }

                }
            }
        }

        Collections.sort(fileList, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        String targetDirectory = targetFile.getAbsolutePath().substring(0, targetFile.getAbsolutePath().lastIndexOf(File.separator) + 1);
        File newTargetFile = new File(targetDirectory + "allFilesContent.txt");
        FileUtils.renameFile(targetFile, newTargetFile);

        char[] buffer = new char[1000];
        int count = 0;
        try (FileWriter fileWriter = new FileWriter(newTargetFile)) {
            for (File file : fileList) {
                try (FileReader fileReader = new FileReader(file)) {
                    while (fileReader.ready()) {
                        count = fileReader.read(buffer);
                        fileWriter.write(buffer, 0, count);
                    }
                    fileWriter.write("\n");
                } catch (IOException e) {
                }
            }
        } catch (IOException e) {
        }


    }

    public static void deleteFile(File file) {
        if (!file.delete()) System.out.println("Can not delete file with name " + file.getName());
    }
}
