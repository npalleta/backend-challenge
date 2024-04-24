package br.com.backend.challenge.commons;

import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import java.util.Base64;

public class Base64Decoder {

    static final String EMPTY_PAYLOAD = "{\"Role\":\"\",\"Seed\":\"\",\"Name\":\"\"}";

    public String decodePayload(String encodedData) {
        //
        String payload = "";
        //
        if (encodedData.isEmpty() && encodedData.isBlank()) {
            throw new ArrayIndexOutOfBoundsException(
                String.format("ERROR:: Index out of bounds: encodedData: %s", encodedData.length())
            );
        }

        try {
            payload = new String(Base64.getDecoder().decode(encodedData.split("\\.")[1]));
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new ArrayIndexOutOfBoundsException(
                String.format("ERROR:: Index out of bounds: encodedData: %s", payload.length())
            );
        }

        return isStringJsonValid(payload) ?
            new String(Base64.getDecoder().decode(encodedData.split("\\.")[1])) :
            EMPTY_PAYLOAD;
        //
    }

    private boolean isStringJsonValid(String payload) {
        try {
            if (!payload.isEmpty() && !payload.isBlank())
                JsonParser.parseString(payload);
        } catch (JsonSyntaxException ex) {
            return false;
        }
        return true;
    }
}