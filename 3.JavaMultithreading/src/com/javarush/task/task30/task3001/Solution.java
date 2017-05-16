package com.javarush.task.task30.task3001;

/* 
Конвертер систем счислений
*/
public class Solution {
    public static void main(String[] args) {
        Number number = new Number(NumerationSystemType._2, "1101001000000001100001001110110111111100110010101000100111011011011001001011001100011001100000111101111");
        Number result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._3);
        System.out.println(result);
        number = new Number(NumerationSystemType._2, "1101001000000001100001001110110111111100110010101000100111011011011001001011001100011001100000111101111");
        result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._4);
        System.out.println(result);
        number = new Number(NumerationSystemType._2, "1101001000000001100001001110110111111100110010101000100111011011011001001011001100011001100000111101111");
        result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._5);
        System.out.println(result);
        number = new Number(NumerationSystemType._2, "1101001000000001100001001110110111111100110010101000100111011011011001001011001100011001100000111101111");
        result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._6);
        System.out.println(result);
        number = new Number(NumerationSystemType._2, "1101001000000001100001001110110111111100110010101000100111011011011001001011001100011001100000111101111");
        result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._7);
        System.out.println(result);
        number = new Number(NumerationSystemType._2, "1101001000000001100001001110110111111100110010101000100111011011011001001011001100011001100000111101111");
        result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._8);
        System.out.println(result);
        number = new Number(NumerationSystemType._2, "1101001000000001100001001110110111111100110010101000100111011011011001001011001100011001100000111101111");
        result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._9);
        System.out.println(result);
        number = new Number(NumerationSystemType._2, "1101001000000001100001001110110111111100110010101000100111011011011001001011001100011001100000111101111");
        result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._10);
        System.out.println(result);
        number = new Number(NumerationSystemType._2, "1101001000000001100001001110110111111100110010101000100111011011011001001011001100011001100000111101111");
        result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._12);
        System.out.println(result);
        number = new Number(NumerationSystemType._2, "1101001000000001100001001110110111111100110010101000100111011011011001001011001100011001100000111101111");
        result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._16);
        System.out.println(result);
    }

    public static Number convertNumberToOtherNumerationSystem(Number number, NumerationSystem expectedNumerationSystem) {
          int numSistNumber = number.getNumerationSystem().getNumerationSystemIntValue();
          if (numSistNumber == expectedNumerationSystem.getNumerationSystemIntValue()) return number;
          int in10SystemStyle = Integer.parseInt(number.getDigit(), numSistNumber);
          String convert = Integer.toString(in10SystemStyle, expectedNumerationSystem.getNumerationSystemIntValue());
        return new Number(expectedNumerationSystem, convert);
    }
}
