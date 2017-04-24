package com.javarush.task.task28.task2811;

/* 
ReentrantReadWriteLock
*/

import java.util.LinkedHashMap;

public class Solution {
    public static void main(String[] args) {
        ReadWriteMap<Integer, String> linkedSafeMap = new ReadWriteMap<>(new LinkedHashMap<>());
    }
}
