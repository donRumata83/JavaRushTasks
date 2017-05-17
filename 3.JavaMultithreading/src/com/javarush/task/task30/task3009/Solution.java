package com.javarush.task.task30.task3009;


import java.util.HashSet;
import java.util.Set;

/* 
Палиндром?
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getRadix("1A"));
        System.out.println(getRadix("112"));        //expected output: [3, 27, 13, 15]
        System.out.println(getRadix("123"));        //expected output: [6]
        System.out.println(getRadix("12641"));
        System.out.println(getRadix("5321"));       //expected output: []
                 //expected output: []
    }

    private static Set<Integer> getRadix(String number) {
        Set<Integer> result = new HashSet<>();
        StringBuilder temp;
        try {
            int numberInDex = Integer.parseInt(number);
            for (int i = 2; i < 37; i++) {
                temp = new StringBuilder(Integer.toString(numberInDex, i));
                if (temp.toString().equals(temp.reverse().toString())) result.add(i);
            }

        } catch (NumberFormatException e) {
        }

        return result;
    }


}