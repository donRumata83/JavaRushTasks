package com.javarush.task.task09.task0912;

/* 
Исключение при работе с числами
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        try {

            int num = Integer.parseInt("XYZ");
            System.out.println(num);
        }catch (NumberFormatException e) {
            System.out.println(e.getClass());
        }
        //напишите тут ваш код
    }
}
