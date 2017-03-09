package com.javarush.task.task31.task3110;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by Rumata on 09.03.2017.
 */
public class ZipFileManager {
    private Path zipFile;

    public ZipFileManager(Path path) {
        this.zipFile = path;
    }

    public void createZip(Path source) throws Exception {
        try (InputStream inputStream = Files.newInputStream(source);ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(zipFile))){
            ZipEntry zipEntry = new ZipEntry(source.getFileName().toString());
            zipOutputStream.putNextEntry(zipEntry);
            byte[] buff = new byte[1000];
            while (inputStream.available() != -1)
            {
                int count = inputStream.read(buff);
                zipOutputStream.write(buff, 0, count);
            }
            zipOutputStream.flush();
            zipOutputStream.closeEntry();

        }

    }
}
