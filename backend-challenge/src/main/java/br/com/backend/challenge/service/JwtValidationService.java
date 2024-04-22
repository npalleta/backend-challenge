package br.com.backend.challenge.service;

import br.com.backend.challenge.commons.*;
import br.com.backend.challenge.model.JwtPayloadToken;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import jakarta.enterprise.context.ApplicationScoped;

import java.lang.reflect.Type;
import java.util.HashMap;

import static java.lang.System.out;

@ApplicationScoped
public class JwtValidationService {

    public String validateJwtPayload(JwtPayloadToken jwtPayloadToken) {
        String decodedPayloadToken = new Base64Decoder()
            .decodePayload(jwtPayloadToken.getJwtWebToken());
        //
        Type mapType = new TypeToken<HashMap<String, String>>() {}.getType();
        HashMap<String, String> decodedPayloadMap = new Gson().fromJson(decodedPayloadToken, mapType);
        //
        return decodedPayloadMap.size() < 4 &&
            validateRole(decodedPayloadMap.get("Role")) &&
            validateSeed(decodedPayloadMap.get("Seed")) &&
            validateName(decodedPayloadMap.get("Name")) ? "verdadeiro" : "falso";
        //
    }

    private boolean validateName(String name) {
        boolean nameContainsDigit = new CharacterChecker().hasOnlyLettersAndSymbols(name);
        return nameContainsDigit && new StringSizeChecker().has256Characters(name);
    }

    private boolean validateRole(String role) {
        return new RoleChecker().isValidRole(role);
    }

    private boolean validateSeed(String seed) {
        return new PrimeNumber().isPrimeNumber(Integer.parseInt(seed));
    }

    public static void main(String[] args) {
        out.print(new JwtValidationService().validateJwtPayload(new JwtPayloadToken()));
    }
}