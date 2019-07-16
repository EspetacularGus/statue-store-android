package com.statuestore.yours.ui.login;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Patterns;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginValidation> loginValidation = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();


    public LoginViewModel() {

    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    LiveData<LoginValidation> getLoginValidation() {
        return loginValidation;
    }

    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginValidation.setValue(new LoginValidation("Username inválido", null));
        } else if (!isPasswordValid(password)) {
            loginValidation.setValue(new LoginValidation(null,
                    "A senha precisa ter mais de 5 caracteres"));
        } else {
            loginValidation.setValue(new LoginValidation(null, null));
        }
    }

    public void setLoginResult(LoginResult loginResult) {
        this.loginResult.setValue(loginResult);
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }


}
