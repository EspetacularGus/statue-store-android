package com.statuestore.yours.ui.login;

import android.support.annotation.Nullable;

public class LoginValidation {

    @Nullable
    private String usernameError;

    @Nullable
    private String passwordError;

    LoginValidation(@Nullable String uError, @Nullable String pError) {
        this.usernameError = uError;
        this.passwordError = pError;
    }

    @Nullable
    public String getUsernameError() {
        return usernameError;
    }

    @Nullable
    public String getPasswordError() {
        return passwordError;
    }

    public boolean isDataValid() {
        return usernameError == null && passwordError == null;
    }
}
