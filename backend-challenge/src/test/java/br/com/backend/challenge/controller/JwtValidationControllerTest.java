package br.com.backend.challenge.controller;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

@TestMethodOrder(OrderAnnotation.class)
@QuarkusTest
class JwtValidationControllerTest {

    private static Response response;

    private final String URI = "/api/v1/";

    private HashMap<String, String> jwtTokenRequest = new HashMap<>();

    @Order(1)
    @Test
    void shouldBeAHttpStatusCode201AndReturnTrueMsg() {
        this.jwtTokenRequest.put("JwtWebToken", "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg");
        response = given()
            .contentType("application/json")
            .body(this.jwtTokenRequest)
            .when()
            .post("%sjwt-validate".formatted(URI));
        //
        response
            .then()
            .statusCode(201);
        //
        Assertions.assertEquals("verdadeiro", response.asPrettyString());
    }

    @Order(2)
    @Test
    void shouldThrowAnExceptionMessageIfJwtTokenIsEmptyOrBlank() {
        this.jwtTokenRequest.put("JwtWebToken", "");
        response = given()
            .contentType("application/json")
            .body(this.jwtTokenRequest)
            .when()
            .post("%sjwt-validate".formatted(URI));
        //
        response
            .then()
            .statusCode(400);
        //
        Assertions.assertEquals("O JWT Web Token foi definido de forma inválida na requisição.", response.asPrettyString());
    }

    @Order(3)
    @Test
    void shouldThrowAnExceptionMessageWithEmptyData() {
        this.jwtTokenRequest.put("", "");
        response = given()
            .contentType("application/json")
            .body(this.jwtTokenRequest)
            .when()
            .post("%sjwt-validate".formatted(URI));
        //
        response
            .then()
            .statusCode(400);
        //
        Assertions.assertEquals("O JWT Web Token foi definido de forma inválida na requisição.", response.asPrettyString());
    }

    @Order(4)
    @Test
    void shouldHaveAWrongJwtToken() {
        this.jwtTokenRequest.put("JwtWebToken", "eyJhbGciOiJzI1NiJ9.dfsdfsfryJSr2xrIjoiQWRtaW4iLCJTZrkIjoiNzg0MSIsIk5hbrUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05fsdfsIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg");
        response = given()
            .contentType("application/json")
            .body(this.jwtTokenRequest)
            .when()
            .post("%sjwt-validate".formatted(URI));
        //
        response
            .then()
            .statusCode(201);
        //
        Assertions.assertEquals("falso", response.asPrettyString());
    }

    @Order(5)
    @Test
    void shouldHaveAWrongClaimName() {
        this.jwtTokenRequest.put("JwtWebToken", "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiRXh0ZXJuYWwiLCJTZWVkIjoiODgwMzciLCJOYW1lIjoiTTRyaWEgT2xpdmlhIn0.6YD73XWZYQSSMDf6H0i3-kylz1-TY_Yt6h1cV2Ku-Qs");
        response = given()
            .contentType("application/json")
            .body(this.jwtTokenRequest)
            .when()
            .post("%sjwt-validate".formatted(URI));
        //
        response
            .then()
            .statusCode(201);
        //
        Assertions.assertEquals("falso", response.asPrettyString());
    }
    @Order(6)
    @Test
    void shouldHaveMoreThanThreeClaims() {
        this.jwtTokenRequest.put("JwtWebToken", "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiTWVtYmVyIiwiT3JnIjoiQlIiLCJTZWVkIjoiMTQ2MjciLCJOYW1lIjoiVmFsZGlyIEFyYW5oYSJ9.cmrXV_Flm5mfdpfNUVopY_I2zeJUy4EZ4i3Fea98zvY");
        response = given()
            .contentType("application/json")
            .body(this.jwtTokenRequest)
            .when()
            .post("%sjwt-validate".formatted(URI));
        //
        response
            .then()
            .statusCode(201);
        //
        Assertions.assertEquals("falso", response.asPrettyString());
    }
}