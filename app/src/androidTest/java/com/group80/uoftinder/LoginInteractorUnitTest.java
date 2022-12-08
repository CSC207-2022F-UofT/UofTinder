package com.group80.uoftinder;

import static org.junit.Assert.assertEquals;

import com.group80.uoftinder.use_case_layer.login.LoginInteractor;

import org.junit.Test;

/**
 * A LoginInteractorUnitTest class that tests the functionality of LoginInteractor class
 */
public class LoginInteractorUnitTest {

    /**
     * Test that LoginInteractor.checkEmail and LoginInteractor.checkPassword is able to detect that
     * both the email and password fields are empty
     */
    @Test
    public void noEmailNoPasswordTest() {
        String email = "";
        String password = "";

        boolean actualEmail = LoginInteractor.checkEmail(email);
        boolean actualPassword = LoginInteractor.checkPassword(password);

        boolean expectedEmail = true;
        boolean expectedPassword = true;

        assertEquals(expectedEmail, actualEmail);
        assertEquals(expectedPassword, actualPassword);
    }

    /**
     * Test that LoginInteractor.checkEmail and LoginInteractor.checkPassword is able to detect that
     * the email field is not empty but the password field is
     */
    @Test
    public void emailNoPasswordTest() {
        String email = "test@mail.utoronto.ca";
        String password = "";

        boolean actualEmail = LoginInteractor.checkEmail(email);
        boolean actualPassword = LoginInteractor.checkPassword(password);

        boolean expectedEmail = false;
        boolean expectedPassword = true;

        assertEquals(expectedEmail, actualEmail);
        assertEquals(expectedPassword, actualPassword);
    }

    /**
     * Test that LoginInteractor.checkEmail and LoginInteractor.checkPassword is able to detect that
     * the email field is empty but the password field is not
     */
    @Test
    public void noEmailPasswordTest() {
        String email = "";
        String password = "12345678";

        boolean actualEmail = LoginInteractor.checkEmail(email);
        boolean actualPassword = LoginInteractor.checkPassword(password);

        boolean expectedEmail = true;
        boolean expectedPassword = false;

        assertEquals(expectedEmail, actualEmail);
        assertEquals(expectedPassword, actualPassword);
    }

    /**
     * Test that LoginInteractor.checkEmail and LoginInteractor.checkPassword is able to detect that
     * both the email and password fields are not empty
     */
    @Test
    public void emailPasswordTest() {
        String email = "test@mail.utoronto.ca";
        String password = "12345678";

        boolean actualEmail = LoginInteractor.checkEmail(email);
        boolean actualPassword = LoginInteractor.checkPassword(password);

        boolean expectedEmail = false;
        boolean expectedPassword = false;

        assertEquals(expectedEmail, actualEmail);
        assertEquals(expectedPassword, actualPassword);
    }
}
