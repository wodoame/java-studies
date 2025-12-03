package com.programming.techie;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import static org.junit.jupiter.api.Assertions.*;

// This demonstrates how to execute tests conditionally based on the operating system
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // JUnit will instantiate the test class only once (therefore we can use @BeforeAll and @AfterAll on non-static methods)
class ConditionalExecutionsTest {
    ContactManager contactManager;

    @BeforeEach
    public void setup(){
         contactManager = new ContactManager();
    }

    @Test // This annotation is used to indicate that the method is a test method
    @DisplayName("Should create contact on Linux") // This annotation is used to provide a custom name for the test method
    @EnabledOnOs(value = OS.LINUX, disabledReason = "Enabled only on Linux OS")
    public void shouldCreateContactOnlyOnLinux(){
        contactManager.addContact("John", "Doe", "0123456789");
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
    }

    @Test
    @DisplayName("Should not say hello on Linux OS")
    @DisabledOnOs(value = OS.LINUX, disabledReason = "Disabled on Linux OS")
    public void shouldNotSayHelloOnLinux(){
        System.out.println("Hello, World!");
    }
}