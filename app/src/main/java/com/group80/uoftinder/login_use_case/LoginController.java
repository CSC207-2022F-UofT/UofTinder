/**
 * Class that calls loginUser method of LoginInteractor through LoginInput interface to
 * login the User
 */

package com.group80.uoftinder.login_use_case;

import android.widget.EditText;

public class LoginController {
    final LoginInput loginInput;

    public LoginController(LoginInput loginGateway) {
        this.loginInput = loginGateway;
    }

    /**
     * Attempts to log in user using email and password
     * @param loginEmail email input
     * @param loginPassword password input
     */
    public void loginUser(EditText loginEmail, EditText loginPassword) {
        loginInput.loginUser(loginEmail, loginPassword);
    }
}
