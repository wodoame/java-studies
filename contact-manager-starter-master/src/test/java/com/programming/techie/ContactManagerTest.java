package com.programming.techie;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // JUnit will instantiate the test class only once (therefore we can use @BeforeAll and @AfterAll on non-static methods)
class ContactManagerTest {
    ContactManager contactManager;
    @BeforeAll
    public static void setupAll(){
        System.out.println("Should print before all tests");
    }

    @BeforeEach
    public void setup(){
         contactManager = new ContactManager();
    }

    @AfterEach
    public void tearDown(){
        System.out.println("Should be executed after each test");
    }

    @AfterAll
    public static void tearDownAll(){
        System.out.println("Should be executed at the end of all tests");
    }

    @Test // This annotation is used to indicate that the method is a test method
    @DisplayName("Should create contact successfully") // This annotation is used to provide a custom name for the test method
    public void shouldCreateContact(){
        contactManager.addContact("John", "Doe", "0123456789");
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
    }

    @Test
    @DisplayName("Should throw runtime exception when last name is null")
    public void shouldThrowRuntimeExceptionWhenLastNameIsNull(){
        ContactManager contactManager = new ContactManager();
        assertThrows(RuntimeException.class, () ->{
            contactManager.addContact("John", null, "0123456789");
        });
    }


}