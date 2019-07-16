package com.statuestore.yours.ui.register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.statuestore.yours.R;
import com.statuestore.yours.data.control.RegisterController;
import com.statuestore.yours.data.model.LoggedInUser;
import com.statuestore.yours.data.model.Usuario;
import com.statuestore.yours.ui.login.LoginActivity;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class RegisterActivity extends AppCompatActivity {

    Usuario usuario = new Usuario();
    Usuario result = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final LinearLayout layout1 = findViewById(R.id.layout1);
        final LinearLayout layout2 = findViewById(R.id.layout2);

        Spinner spinnerSexo = findViewById(R.id.spinnerSexo);
        populateSpinner(spinnerSexo);

        Button btnContinuar = findViewById(R.id.btnContinuar);
        btnContinuar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (layout1.getVisibility() == View.VISIBLE) {
                    layout1.setVisibility(View.INVISIBLE);
                    layout2.setVisibility(View.VISIBLE);
                } else {
                    novoUsuario();
                }
            }
        });

        ImageView iconBack = findViewById(R.id.iconBack);
        iconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layout1.getVisibility() == View.VISIBLE) {
                    finish();
                    startLogin(null);
                } else {
                    layout1.setVisibility(View.VISIBLE);
                    layout2.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    public void startLogin(View view) {
        finish();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void startConfirmation() {
        Intent intent = new Intent(this, ConfirmationActivity.class);
        startActivity(intent);
    }

    private void populateSpinner(Spinner spinner) {
        ArrayList<String> sexos = new ArrayList<String>();
        sexos.add("Masculino");
        sexos.add("Feminino");
        sexos.add("Outro");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, sexos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        usuario.setSexo("M");
                        break;
                    case 1:
                        usuario.setSexo("F");
                        break;
                    case 2:
                        usuario.setSexo("O");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void novoUsuario() {
        EditText txtEmail = findViewById(R.id.txtEmail);
        EditText txtNome = findViewById(R.id.txtNome);
        EditText txtSobrenome = findViewById(R.id.txtSobrenome);
        EditText txtCpf = findViewById(R.id.txtCPF);
        EditText txtSenha = findViewById(R.id.txtSenha);
        EditText txtDataNascimento = findViewById(R.id.txtDataNasc);

        usuario.setEmail(txtEmail.getText().toString());
        usuario.setNome(txtNome.getText().toString());
        usuario.setSobrenome(txtSobrenome.getText().toString());
        usuario.setCpf(txtCpf.getText().toString()
                .replace(".","")
                .replace("-","")
                .replace("/",""));
        usuario.setSenha(txtSenha.getText().toString());
        usuario.setDataNascimento(txtDataNascimento.getText().toString()
                .replace("/","-")
                .replace(".","-"));

        new Thread(new Runnable() {
            @Override
            public void run() {
                result = new RegisterController().cadastrar(usuario);
                if (result == null)  {
                    showToast("Esse E-mail/CPF já está cadastrado!");
                } else {
                    updateUiWithUser(result);
                }
            }
        }).start();
    }

    private void updateUiWithUser(Usuario model) {
        showToast("Olá " + model.getNome() + "!");
        new LoggedInUser(model.getId(), model.getNome(), model.getEmail());
        finish();
        startConfirmation();
    }

    private void showToast(String errorString) {
        final String error = errorString;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(RegisterActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
