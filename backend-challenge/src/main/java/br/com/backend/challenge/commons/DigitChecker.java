package br.com.backend.challenge.commons;

import java.util.regex.Pattern;

public class DigitChecker {

    public boolean isDigit(String number) {
        if (!number.isEmpty() && !number.isBlank())
            return Pattern.compile("^[0-9]+$").matcher(number).matches();
        else return false;
    }
}