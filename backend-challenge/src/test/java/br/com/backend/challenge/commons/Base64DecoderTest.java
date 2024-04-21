package br.com.backend.challenge.commons;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@TestMethodOrder(OrderAnnotation.class)
@QuarkusTest
public class Base64DecoderTest {

    private Base64Decoder base64Decoder;

    @BeforeEach
    void BeforeEach() {
        this.base64Decoder = new Base64Decoder();
    }

    @Order(1)
    @Test
    void shouldBeAnInstanceOfString() {
        Assertions.assertInstanceOf(String.class, this.base64Decoder.decodePayload("eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg"));
    }

    @Order(2)
    @Test
    void shouldBeAEmptyJsonString() {
        String emptyJsonString = "{\"Role\":\"\",\"Seed\":\"\",\"Name\":\"\"}";
        Assertions.assertEquals(emptyJsonString, this.base64Decoder.decodePayload("eyJhbGciOiJzI1NiJ9.dfsdfsfryJSr2xrIjoiQWRtaW4iLCJTZrkIjoiNzg0MSIsIk5hbrUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05fsdfsIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg"));
    }

    @Order(3)
    @Test
    void shouldNotBeAEmptyData() {
        ArrayIndexOutOfBoundsException thrown = Assertions.assertThrows(
            ArrayIndexOutOfBoundsException.class, () -> this.base64Decoder.decodePayload("")
        );
        Assertions.assertEquals("ERROR:: Index out of bounds: encodedData: 0", thrown.getMessage());
    }

    @Order(4)
    @Test
    void shouldBeAPayloadJsonString() {
        String payloadJsonString = "{\"Role\":\"Admin\",\"Seed\":\"7841\",\"Name\":\"Toninho Araujo\"}";
        Assertions.assertEquals(payloadJsonString, this.base64Decoder.decodePayload("eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg"));
    }
}