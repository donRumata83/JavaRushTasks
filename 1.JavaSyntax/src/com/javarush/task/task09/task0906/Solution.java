package com.javarush.task.task09.task0906;

/* 
Логирование стек трейса
*/

public class Solution {
    public static void main(String[] args) {
        log("In main method");
    }

    public static void log(String s) {

        String className = Thread.currentThread().getStackTrace()[2].getClassName();

        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        String space = ": ";
        System.out.println(className + space + methodName + space + s);
    } //напишите тут ваш код
}
