package com.javarush.task.task31.task3110;

/**
 * Created by Rumata on 10.03.2017.
 */
public class FileProperties{
    private String name;
    private long size;
    private long compressedSize;
    private int compressionMethod;
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public long getSize()
    {
        return size;
    }
    public void setSize(long size)
    {
        this.size = size;
    }
    public long getCompressedSize()
    {
        return compressedSize;
    }
    public void setCompressedSize(long compressedSize)
    {
        this.compressedSize = compressedSize;
    }
    public int getCompressionMethod()
    {
        return compressionMethod;
    }
    public void setCompressionMethod(int compressionMethod)
    {
        this.compressionMethod = compressionMethod;
    }
    public FileProperties(String name, long size, long compressedSize, int compressionMethod){
        this.name=name;
        this.size=size;
        this.compressedSize=compressedSize;
        this.compressionMethod=compressionMethod;
    }
    public long getCompressionRatio(){
        return 100 - ((compressedSize * 100) / size);
    }
    @Override
    public String toString(){
        if (size > 0){
            return String.format("%s\t %d Kb(%d Kb) сжатие: %d%%", name, Math.round(size/1024), Math.round(compressedSize/1024), getCompressionRatio());
        }else{
            return name;
        }
    }}