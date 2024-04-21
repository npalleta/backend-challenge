package br.com.backend.challenge.commons;

import java.util.regex.Pattern;

public class CharacterChecker {

    public boolean hasOnlyLettersAndSymbols(String stringWithNumber) {
        if (stringWithNumber.isEmpty() && stringWithNumber.isBlank())
            return false;
        return Pattern.compile(
        "^[a-zA-Z\\p{Punct}\\sçÇáàãâéèêíìóòõôúùûüÁÀÃÂÉÈÊÍÌÓÒÕÔÚÙÛÜ]*$"
            ).matcher(stringWithNumber).matches();
        //
    }
}