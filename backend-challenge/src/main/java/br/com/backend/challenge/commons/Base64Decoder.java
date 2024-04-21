package br.com.backend.challenge.commons;

import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import java.util.Base64;

public class Base64Decoder {

    private final String emptyPayload = "{\"Role\":\"\",\"Seed\":\"\",\"Name\":\"\"}";

    public String decodePayload(String encodedData) {
        if (encodedData.isEmpty() && encodedData.isBlank()) {
            throw new ArrayIndexOutOfBoundsException(
                String.format("ERROR:: Index out of bounds: encodedData: %s", 0)
            );
        }

        String payload = new String(Base64.getDecoder().decode(encodedData.split("\\.")[1]));
        return isStringJsonValid(payload) ?
            new String(Base64.getDecoder().decode(encodedData.split("\\.")[1])) :
            emptyPayload;
        //
    }

    private boolean isStringJsonValid(String payload) {
        try {
            if (!payload.isEmpty() && !payload.isBlank())
                JsonParser.parseString(payload);
        } catch (JsonSyntaxException e) {
            return false;
        }
        return true;
    }
}