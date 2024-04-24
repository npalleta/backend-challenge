package br.com.backend.challenge.service;

import br.com.backend.challenge.model.JwtPayloadToken;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@TestMethodOrder(OrderAnnotation.class)
@QuarkusTest
class JwtPayloadServiceTest {

    private JwtValidationService jwtValidationService;

    private JwtPayloadToken jwtPayloadToken;

    @BeforeEach
    void BeforeEach() {
        this.jwtPayloadToken = new JwtPayloadToken();
        this.jwtValidationService = new JwtValidationService();
    }

    @Order(1)
    @Test
    void shouldBeAnInstanceOfString() {
        this.jwtPayloadToken.setJwtWebToken("eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg");
        Assertions.assertInstanceOf(String.class, this.jwtValidationService.validateJwtPayload(this.jwtPayloadToken));
    }

    @Order(2)
    @Test
    void shouldBeEqualsTrue() {
        this.jwtPayloadToken.setJwtWebToken("eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg");
        Assertions.assertEquals("verdadeiro", this.jwtValidationService.validateJwtPayload(this.jwtPayloadToken));
    }

    @Order(3)
    @Test
    void shouldHaveAWrongJwtToken() {
        this.jwtPayloadToken.setJwtWebToken("eyJhbGciOiJzI1NiJ9.dfsdfsfryJSr2xrIjoiQWRtaW4iLCJTZrkIjoiNzg0MSIsIk5hbrUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05fsdfsIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg");
        Assertions.assertEquals("falso", this.jwtValidationService.validateJwtPayload(this.jwtPayloadToken));
    }

    @Order(4)
    @Test
    void shouldHaveAWrongClaimName() {
        this.jwtPayloadToken.setJwtWebToken("eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiRXh0ZXJuYWwiLCJTZWVkIjoiODgwMzciLCJOYW1lIjoiTTRyaWEgT2xpdmlhIn0.6YD73XWZYQSSMDf6H0i3-kylz1-TY_Yt6h1cV2Ku-Qs");
        Assertions.assertEquals("falso", this.jwtValidationService.validateJwtPayload(this.jwtPayloadToken));
    }

    @Order(5)
    @Test
    void shouldHaveMoreThanThreeClaims() {
        this.jwtPayloadToken.setJwtWebToken("eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiTWVtYmVyIiwiT3JnIjoiQlIiLCJTZWVkIjoiMTQ2MjciLCJOYW1lIjoiVmFsZGlyIEFyYW5oYSJ9.cmrXV_Flm5mfdpfNUVopY_I2zeJUy4EZ4i3Fea98zvY");
        Assertions.assertEquals("falso", this.jwtValidationService.validateJwtPayload(this.jwtPayloadToken));
    }
}