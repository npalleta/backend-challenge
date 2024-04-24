package br.com.backend.challenge.commons;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@TestMethodOrder(OrderAnnotation.class)
@QuarkusTest
class CharacterCheckerTest {

    private CharacterChecker characterChecker;

    @BeforeEach
    void BeforeEach() {
        this.characterChecker = new CharacterChecker();
    }

    @Order(1)
    @Test
    void shouldBeAnInstanceOfBoolean() {
        Assertions.assertInstanceOf(Boolean.class, this.characterChecker.hasOnlyLettersAndSymbols("ABC1"));
    }

    @Order(2)
    @ParameterizedTest
    @ValueSource(strings = {"1234", "4567", "1600", "12345", "67890", "12345678901011", "123abc", "ABC123", "M4ria Olivia"})
    void shouldReturnAsFalseValue(String numbers) {
        Assertions.assertFalse(this.characterChecker.hasOnlyLettersAndSymbols(numbers));
    }

    @Order(3)
    @Test
    void shouldBeFalseIfStringEmpty() {
        Assertions.assertFalse(this.characterChecker.hasOnlyLettersAndSymbols(""));
    }

    @Order(4)
    @ParameterizedTest
    @ValueSource(strings = {"ABCD", "abcd", "XcVb", "POIU", "ÇLKW", "qWeRREDW", "#qwert@!^", "Cláudia", "Antônio"})
    void shouldBeATrueValue(String characters) {
        Assertions.assertTrue(this.characterChecker.hasOnlyLettersAndSymbols(characters));
    }
}