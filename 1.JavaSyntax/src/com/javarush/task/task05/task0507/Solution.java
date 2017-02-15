package com.javarush.task.task05.task0507;

/* 
Среднее арифметическое
*/

import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList();
        while (true)
        {
            int temp = scanner.nextInt();
            if (temp != -1) numbers.add(temp);
            else break;
        }
        scanner.close();
        int summ = 0;
        for (int i: numbers)
        {
            summ += i;
        }
        System.out.println((double)summ/numbers.size());
    }
}

