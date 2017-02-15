package com.javarush.task.task07.task0706;


import java.util.Scanner;

/* 
Улицы и дома
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int[] massivOfHouses = new int[15];
        for (int i = 0; i < massivOfHouses.length; i++) {
            massivOfHouses[i] = scanner.nextInt();
        }
        scanner.close();
        int odd = 0;
        int even = 0;
        for (int i = 0; i < massivOfHouses.length; i++) {
            if (i%2 == 0) even += massivOfHouses[i];
            else  odd += massivOfHouses[i];
        }
        if (even > odd) System.out.println("В домах с четными номерами проживает больше жителей.");
        else System.out.println("В домах с нечетными номерами проживает больше жителей.");
    }
}
