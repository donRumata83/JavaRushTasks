package com.javarush.task.task29.task2902;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

/* 
Рефакторинг в соответствии с Naming and Code Convention 2
*/
public class Solution {
    public static void main(String[] args) throws IOException, InterruptedException {
        Solution solution = new Solution();
        String fileNameToBeOpenedByNotepad = solution.getAbsolutePathToDefaultTxtFile().toString();
        Process notepad = solution.getProcessStartNotepad(fileNameToBeOpenedByNotepad);
        notepad.waitFor();
    }

    public Process getProcessStartNotepad(String fileName) throws IOException {
        String[] cmdArray = new String[]{"notepad.exe", fileName};
        return Runtime.getRuntime().exec(cmdArray);
    }

    public Path getAbsolutePathToDefaultTxtFile() {
        try {URI uri = Solution.class.getResource("file.txt").toURI();
        return  Paths.get(uri);} catch (Exception e) {}
        return null;
    }
}
