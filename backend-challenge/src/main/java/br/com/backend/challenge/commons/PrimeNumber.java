package br.com.backend.challenge.commons;

import java.util.stream.IntStream;

public class PrimeNumber {

    public boolean isPrimeNumber(int number) {
        //
        int newNumber = number;
        //
        boolean checkPrimeNum = isGreaterThanOne(number);
        number = newNumber / 2;
        //
        if (checkPrimeNum || number <= 1)
            checkPrimeNum = IntStream.rangeClosed(2, number).noneMatch(i -> newNumber % i == 0);
        //
        return checkPrimeNum;
    }

    private boolean isGreaterThanOne(int number) {
        if (number > 1) return true;
        else
            throw new ArithmeticException("It's Wrong! This is a negative number, zero or one!");
        //
    }
}