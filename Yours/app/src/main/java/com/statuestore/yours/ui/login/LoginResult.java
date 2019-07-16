package com.statuestore.yours.ui.login;

import android.support.annotation.Nullable;

import com.statuestore.yours.data.model.Usuario;

/**
 * Authentication result : success (user details) or error message.
 */
public class LoginResult<T> {
    @Nullable
    private Usuario success;
    @Nullable
    private String error;

    public LoginResult() {
        if (this instanceof LoginResult.Success) {
            this.success = (Usuario) ((Success) this).getData();
            this.error = null;
        } else if (this instanceof LoginResult.Error) {
            this.error = ((Error) this).getError();
            this.success = null;
        }
    }

    @Nullable
    Usuario getSuccess() {
        return success;
    }

    @Nullable
    String getError() {
        return error;
    }

    // Success sub-class
    public final static class Success<T> extends LoginResult {
        private T data;

        public Success(T data) {
            this.data = data;
        }

        public T getData() {
            return this.data;
        }
    }

    // Error sub-class
    public final static class Error extends LoginResult {
        private Exception error;

        public Error(Exception error) {
            this.error = error;
        }

        public String getError() {
            return ("Exception:" + this.error);
        }
    }
}
