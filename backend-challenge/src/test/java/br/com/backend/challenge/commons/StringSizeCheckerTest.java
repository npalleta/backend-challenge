package br.com.backend.challenge.commons;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@TestMethodOrder(OrderAnnotation.class)
@QuarkusTest
public class StringSizeCheckerTest {

    private StringSizeChecker stringSizeChecker;

    @BeforeEach
    void StringSizeChecker() {
        this.stringSizeChecker = new StringSizeChecker();
    }

    @Order(1)
    @Test
    void shouldBeAnInstanceOfBoolean() {
        Assertions.assertInstanceOf(Boolean.class, this.stringSizeChecker.have256Characters("qweasdqweasd"));
    }

    @Order(2)
    @Test
    void shouldNotHaveMoreThan256Chars() {
        String data = "qweasdqweasdqweasdqweasdqweasdqweasdweasdqweasdqweqweasdqweasdqweasdqweasdqweasdqweasdweasdqweasdqwe" +
            "qweasdqweasdqweasdqweasdqweasdqweasdweasdqweasdqweqweasdqweasdqweasdqweasdqweasdqweasdweasdqweasdqwe" +
            "teste123teste123teste123teste123teste123teste123456";
        Assertions.assertFalse(this.stringSizeChecker.have256Characters(data));
    }

    @Order(3)
    @Test
    void shouldBeFalseIfStringEmpty() {
        Assertions.assertFalse(this.stringSizeChecker.have256Characters(""));
    }
}
