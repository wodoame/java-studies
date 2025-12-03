package com.programming.techie;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // JUnit will instantiate the test class only once (therefore we can use @BeforeAll and @AfterAll on non-static methods)
class ParametrizingTest {

    @DisplayName("Words that read the same forward and backward are palindromes")
    @ParameterizedTest(name = "{0} should be detected as a palindrome")
    @ValueSource(strings = {"level", "radar", "noon", "racecar"})
    void shouldDetectPalindromes(String candidate) {
        assertTrue(isPalindrome(candidate));
    }

    private boolean isPalindrome(String candidate) {
        String normalized = candidate.replaceAll("[^a-zA-Z]", "").toLowerCase();
        return new StringBuilder(normalized).reverse().toString().equals(normalized);
    }
}

// There are other annotations that can be used for tests like
// DisabledTest, Timeout, NestedTest, Tag etc. Explore them as needed.