package br.com.backend.challenge.commons;

public class StringSizeChecker {

    public boolean has256Characters(String data) {
        if (!data.isEmpty() && !data.isBlank()) return data.length() <= 256;
        else return false;
    }
}