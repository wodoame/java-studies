package com.programming.techie;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // JUnit will instantiate the test class only once (therefore we can use @BeforeAll and @AfterAll on non-static methods)
class RepeatTest {
    @RepeatedTest(value = 5, name="Repeated test {currentRepetition} of {totalRepetitions} times") // suitable for tests which have to be executed multiple times due to some randomness
    @DisplayName("Repeat this test multiple times") // This annotation is used to provide a custom name for the test method
    public void shouldRunMultipleTimes(){
        System.out.println("This has been ran");
    }

}