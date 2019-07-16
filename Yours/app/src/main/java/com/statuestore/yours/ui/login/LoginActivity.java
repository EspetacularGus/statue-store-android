package com.statuestore.yours.ui.login;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.statuestore.yours.data.model.LoggedInUser;
import com.statuestore.yours.ui.MainActivity;
import com.statuestore.yours.R;
import com.statuestore.yours.data.control.LoginController;
import com.statuestore.yours.data.model.Usuario;
import com.statuestore.yours.ui.register.RegisterActivity;

import java.util.concurrent.CountDownLatch;

public class LoginActivity extends AppCompatActivity {

    private Usuario result;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final LoginViewModel loginViewModel = new LoginViewModel();

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.btnLogin);
        final LoginController loginController = new LoginController();

        loginViewModel.getLoginValidation().observe(this, new Observer<LoginValidation>() {
            @Override
            public void onChanged(@Nullable LoginValidation loginValidation) {
                if (loginValidation == null) {
                    return;
                }
                loginButton.setEnabled(loginValidation.isDataValid());
                if (loginValidation.getUsernameError() != null) {
                    usernameEditText.setError(loginValidation.getUsernameError());
                }
                if (loginValidation.getPasswordError() != null) {
                    passwordEditText.setError(loginValidation.getPasswordError());
                }
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginController.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        LoginActivity.this.result = loginController.login(
                                usernameEditText.getText().toString(),
                                passwordEditText.getText().toString());

                        if (result == null)
                            showToast("Não foi possível realizar o login!");
                        else
                            updateUiWithUser(result);
                    }
                }).start();
            }
        });
    }

    private void updateUiWithUser(Usuario model) {
        showToast("Olá " + model.getNome() + "!");
        new LoggedInUser(model.getId(), model.getNome(), model.getEmail());
        finish();
        startMain();
    }

    private void showToast(String errorString) {
        final String error = errorString;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void startRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void startMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goToWebsite(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(getString(R.string.password_website)));
        startActivity(intent);
    }
}
