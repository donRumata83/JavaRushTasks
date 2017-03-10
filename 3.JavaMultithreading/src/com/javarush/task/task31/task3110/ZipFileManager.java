package com.javarush.task.task31.task3110;

import com.javarush.task.task31.task3110.exception.PathIsNotFoundException;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.List;
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

    public void createZip(Path source) throws Exception
    {
        //тут не доконца уверен в правильности

            Files.createDirectories(zipFile.getParent());

        try (ZipOutputStream zos = new ZipOutputStream(Files.newOutputStream(zipFile)))
        {
            if(Files.isRegularFile(source)){
                addNewZipEntry(zos,source.getParent(),source.getFileName());
            }
            else if (Files.isDirectory(source)){
                FileManager fileManager = new FileManager(source);
                List<Path> fileName = fileManager.getFileList();
                for(Path path: fileName){
                    addNewZipEntry(zos,source,path);
                }
            }else  throw new PathIsNotFoundException();
            zos.closeEntry();
        }
    }
    private void addNewZipEntry(ZipOutputStream zipOutputStream, Path filePath, Path fileName)throws Exception{
        try(InputStream inputStream =Files.newInputStream(filePath.resolve(fileName)))// тут тоже есть варианты
        {
            ZipEntry entry = new ZipEntry(fileName.toString());
            zipOutputStream.putNextEntry(entry);
            copyData(inputStream,zipOutputStream);
            zipOutputStream.closeEntry();
        }
    }
    private void copyData(InputStream in, OutputStream out) throws Exception {
        byte[] buffer = new byte[1024];
        int length;
        while ((length = in.read(buffer))> 0)
            out.write(buffer, 0, length);
    }
}

