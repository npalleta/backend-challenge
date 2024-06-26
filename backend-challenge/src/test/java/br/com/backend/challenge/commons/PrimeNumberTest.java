package br.com.backend.challenge.commons;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(OrderAnnotation.class)
// @QuarkusTest
class PrimeNumberTest {

    private PrimeNumber primeNumber;

    @BeforeEach
    void BeforeEach() {
        this.primeNumber = new PrimeNumber();
    }

    @Order(1)
    @Test
    void shouldBeAnInstanceOfBoolean() {
        Assertions.assertInstanceOf(Boolean.class, this.primeNumber.isPrimeNumber(2));
    }

    @Order(2)
    @Test
    void shouldBeNotAStringParameter() {
        NumberFormatException thrown = Assertions.assertThrows(
            NumberFormatException.class, () -> this.primeNumber.isPrimeNumber(Integer.parseInt("Test"))
        );
        //
        Assertions.assertEquals("For input string: \"Test\"", thrown.getMessage());
    }

    @Order(3)
    @Test
    void shouldBeNotEqualsOne() {
        ArithmeticException thrown = Assertions.assertThrows(
            ArithmeticException.class, () -> this.primeNumber.isPrimeNumber(1)
        );
        Assertions.assertEquals("It's Wrong! This is a negative number, zero or one!", thrown.getMessage());
    }

    @Order(4)
    @Test
    void shouldBeNotEqualsZero() {
        ArithmeticException thrown = Assertions.assertThrows(
            ArithmeticException.class, () -> this.primeNumber.isPrimeNumber(0)
        );
        Assertions.assertEquals("It's Wrong! This is a negative number, zero or one!", thrown.getMessage());
    }

    @Order(5)
    @Test
    void shouldBeNotLessThanOne() {
        ArithmeticException thrown = Assertions.assertThrows(
            ArithmeticException.class, () -> this.primeNumber.isPrimeNumber(-1)
        );
        Assertions.assertEquals("It's Wrong! This is a negative number, zero or one!", thrown.getMessage());
    }

    @Order(6)
    @Test
    void shouldNotBeAPositiveNumber() {
        ArithmeticException thrown = Assertions.assertThrows(
            ArithmeticException.class, () -> this.primeNumber.isPrimeNumber(-2)
        );
        Assertions.assertEquals("It's Wrong! This is a negative number, zero or one!", thrown.getMessage());
    }

    @Order(7)
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 1})
    void shouldNotBeAInvalidNumber(int number) {
        PrimeNumber primeNumber = new PrimeNumber();
        assertThrows(ArithmeticException.class, () -> primeNumber.isPrimeNumber(number));
    }

    @Order(8)
    @ParameterizedTest
    @ValueSource(ints = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97})
    void shouldBeDivisibleByItselfAndByOne(int number) {
        Assertions.assertTrue(this.primeNumber.isPrimeNumber(number));
    }

    @Order(9)
    @ParameterizedTest
    @ValueSource(ints = {4, 6, 8, 10, 20, 25, 26, 30, 33, 34})
    void shouldBeDivisibleByItselfButNotDivisibleByOne(int number) {
        Assertions.assertFalse(this.primeNumber.isPrimeNumber(number));
    }
}