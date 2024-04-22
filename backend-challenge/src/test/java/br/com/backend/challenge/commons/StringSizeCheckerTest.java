package br.com.backend.challenge.commons;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@TestMethodOrder(OrderAnnotation.class)
@QuarkusTest
public class StringSizeCheckerTest {

    private StringSizeChecker stringSizeChecker;

    @BeforeEach
    void BeforeEach() {
        this.stringSizeChecker = new StringSizeChecker();
    }

    @Order(1)
    @Test
    void shouldBeAnInstanceOfBoolean() {
        Assertions.assertInstanceOf(Boolean.class, this.stringSizeChecker.has256Characters("qweasdqweasd"));
    }

    @Order(2)
    @Test
    void shouldHaveLessThan256Chars() {
        String data = "qweasdqweasdqweasdqweasdqweasdqweasdweasdqweasdqweqweasdqweasdqweasdqweasdqweasdqweasdweasdqweasdqwe" +
            "qweasdqweasdqweasdqweasdqweasdqweasdweasdqweasdqweqweasdqweasdqweasdqweasdqweasdqweasdweasdqweasdqwe" +
            "teste123teste123teste123teste123teste123teste1234512345";
        Assertions.assertTrue(this.stringSizeChecker.has256Characters(data));
    }
    @Order(3)
    @Test
    void shouldHave256Chars() {
        String data = "qweasdqweasdqweasdqweasdqweasdqweasdweasdqweasdqweqweasdqweasdqweasdqweasdqweasdqweasdweasdqweasdqwe" +
            "qweasdqweasdqweasdqweasdqweasdqweasdweasdqweasdqweqweasdqweasdqweasdqweasdqweasdqweasdweasdqweasdqwe" +
            "teste123teste123teste123teste123teste123teste12345123456";
        Assertions.assertTrue(this.stringSizeChecker.has256Characters(data));
    }


    @Order(4)
    @Test
    void shouldNotHaveMoreThan256Chars() {
        String data = "qweasdqweasdqweasdqweasdqweasdqweasdweasdqweasdqweqweasdqweasdqweasdqweasdqweasdqweasdweasdqweasdqwe" +
            "qweasdqweasdqweasdqweasdqweasdqweasdweasdqweasdqweqweasdqweasdqweasdqweasdqweasdqweasdweasdqweasdqwe" +
            "teste123teste123teste123teste123teste123teste123451234567";
        Assertions.assertFalse(this.stringSizeChecker.has256Characters(data));
    }

    @Order(5)
    @Test
    void shouldBeFalseIfStringEmpty() {
        Assertions.assertFalse(this.stringSizeChecker.has256Characters(""));
    }
}