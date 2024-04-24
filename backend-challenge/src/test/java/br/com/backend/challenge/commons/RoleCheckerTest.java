package br.com.backend.challenge.commons;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@TestMethodOrder(OrderAnnotation.class)
@QuarkusTest
class RoleCheckerTest {

    private RoleChecker roleChecker;

    @BeforeEach
    void BeforeEach() {
        this.roleChecker = new RoleChecker();
    }

    @Order(1)
    @Test
    void shouldBeAnInstanceOfBoolean() {
        Assertions.assertInstanceOf(Boolean.class, this.roleChecker.isValidRole("ABC1"));
    }

    @Order(2)
    @ParameterizedTest
    @ValueSource(strings = {"Test", "User", "Memberber", "QWERT", "TeSt", "123456", "#qwert@!^"})
    void shouldNotBeAValidRole(String roles) {
        Assertions.assertFalse(this.roleChecker.isValidRole(roles));
    }

    @Order(3)
    @ParameterizedTest
    @ValueSource(strings = {"Admin", "Member", "External"})
    void shouldBeAValidRole(String roles) {
        Assertions.assertTrue(this.roleChecker.isValidRole(roles));
    }

    @Order(4)
    @Test
    void shouldBeFalseIfStringEmpty() {
        Assertions.assertFalse(this.roleChecker.isValidRole(""));
    }
}