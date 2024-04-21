package br.com.backend.challenge.commons;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@TestMethodOrder(OrderAnnotation.class)
@QuarkusTest
public class DigitCheckerTest {

    private DigitChecker digitChecker;

    @BeforeEach
    void DigitChecker() {
        this.digitChecker = new DigitChecker();
    }

    @Order(1)
    @Test
    void shouldBeAnInstanceOfBoolean() {
        Assertions.assertInstanceOf(Boolean.class, this.digitChecker.isDigit("ABC1"));
    }

    @Order(2)
    @ParameterizedTest
    @ValueSource(strings = {"1234", "4567", "1600", "12345", "67890", "12345678901011"})
    void shouldBeATrueValue(String numbers) {
        Assertions.assertTrue(this.digitChecker.isDigit(numbers));
    }

    @Order(3)
    @ParameterizedTest
    @ValueSource(strings = {"ABCD", "abcd", "XcVb", "POIU", "ÇLKW", "qWeRREDW", "#qwert@!^", "123abc", "ABC123"})
    void shouldReturnAsFalseValue(String numbers) {
        Assertions.assertFalse(this.digitChecker.isDigit(numbers));
    }

    @Order(4)
    @Test
    void shouldBeFalseIfStringEmpty() {
        Assertions.assertFalse(this.digitChecker.isDigit(""));
    }
}