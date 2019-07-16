package com.statuestore.yours.ui.compra;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;
import com.statuestore.yours.R;
import com.statuestore.yours.data.control.ProdutoController;
import com.statuestore.yours.data.helpers.FileIO;
import com.statuestore.yours.data.model.LoggedInUser;
import com.statuestore.yours.data.model.Produto;
import com.statuestore.yours.ui.MainActivity;

import java.util.ArrayList;
import java.util.regex.Pattern;


public class CustomActivity extends AppCompatActivity {

    Spinner spinnerProd;
    ImageView imageCamiseta;
    ImageView imageEstampa;
    Drawable estampa;
    int camiseta;
    int moletom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        camiseta = R.drawable.camiseta;
        moletom = R.drawable.moletom;
        estampa = getResources().getDrawable(R.drawable.ic_add_black_24dp);

        imageCamiseta = findViewById(R.id.imgCamiseta);
        imageEstampa = findViewById(R.id.imgEstampa);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFabClicked();
            }
        });

        spinnerProd = findViewById(R.id.spinnerProd);
        populateSpinner(spinnerProd);
        spinnerProd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    toolbar.setTitle("Nova Camiseta");
                    overlayImage(camiseta, estampa);
                } else if (position == 1) {
                    toolbar.setTitle("Novo Moletom");
                    overlayImage(moletom, estampa);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Button buttonFile = findViewById(R.id.buttonFile);
        buttonFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M
                        && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1001);
                }

                new MaterialFilePicker()
                        .withActivity(CustomActivity.this)
                        .withRequestCode(1000)
                        .withFilter(Pattern.compile(".*\\.png$"))
                        .withHiddenFiles(true) // Show hidden files and folders
                        .start();
            }
        });

        Button buttonWhite = findViewById(R.id.buttonWhite);
        addButtonListener(buttonWhite, R.drawable.camiseta, R.drawable.moletom);
        Button buttonBlack = findViewById(R.id.buttonBlack);
        addButtonListener(buttonBlack, R.drawable.camiseta_black, R.drawable.moletom_black);
        Button buttonRed = findViewById(R.id.buttonRed);
        addButtonListener(buttonRed, R.drawable.camiseta_red, R.drawable.moletom_red);
        Button buttonBlue = findViewById(R.id.buttonBlue);
        addButtonListener(buttonBlue, R.drawable.camiseta_blue, R.drawable.moletom_blue);
        Button buttonPurple = findViewById(R.id.buttonPurple);
        addButtonListener(buttonPurple, R.drawable.camiseta_purple, R.drawable.moletom_purple);
        Button buttonYellow = findViewById(R.id.buttonYellow);
        addButtonListener(buttonYellow, R.drawable.camiseta_yellow, R.drawable.moletom_yellow);
        Button buttonGreen = findViewById(R.id.buttonGreen);
        addButtonListener(buttonGreen, R.drawable.camiseta_green, R.drawable.moletom_green);

        imageCamiseta.setImageResource(R.drawable.camiseta);
        toolbar.setTitle("Minha Camiseta");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.custom_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.item_voltar) {
            finish();
            goTo(MainActivity.class);
            return true;
        } else if (id == R.id.item_baixar) {
            Toast.makeText(this, "Salvando imagem.", Toast.LENGTH_SHORT).show();

            View view = findViewById(R.id.frameLayout);
            Bitmap bitmapModelo = Bitmap.createBitmap(
                    view.getWidth(),
                    view.getHeight(),
                    Bitmap.Config.ARGB_8888);

            Canvas canvasModelo = new Canvas(bitmapModelo);
            view.draw(canvasModelo);
            FileIO.saveImg("download", bitmapModelo);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void populateSpinner(Spinner spinner) {
        ArrayList<String> produtos = new ArrayList<>();
        produtos.add("Camiseta");
        produtos.add("Moletom");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, produtos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void goTo(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }

    private void addButtonListener(Button button, final int colorCamiseta, final int colorMoletom) {
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                camiseta = colorCamiseta;
                moletom = colorMoletom;
                if (spinnerProd.getSelectedItemPosition() == 0)
                    overlayImage(camiseta, estampa);
                else if (spinnerProd.getSelectedItemPosition() == 1)
                    overlayImage(moletom, estampa);
            }
        });
    }

    private void overlayImage(int modelo, Drawable imagem) {
        Resources r = getResources();
        Drawable[] layers = new Drawable[2];
        layers[0] = r.getDrawable(modelo);
        layers[1] = imagem;

        imageCamiseta.setImageDrawable(layers[0]);
        imageEstampa.setImageDrawable(layers[1]);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == 1001) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this, "Permissão Concedida!", Toast.LENGTH_SHORT)
                        .show();
            else
                Toast.makeText(this, "Permissão Negada", Toast.LENGTH_SHORT)
                        .show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        System.out.println(requestCode);
        System.out.println(resultCode);
        if (requestCode == 1000 && resultCode == RESULT_OK) {
            String filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            estampa = Drawable.createFromPath(filePath);
            overlayImage(camiseta, estampa);
        }
    }

    private void onFabClicked() {
        String nome;
        String caminhoEstampa;
        String caminhoModelo;
        String cor;
        String sexo;
        int idSubgrupo;
        int idCliente;

        EditText textNome = findViewById(R.id.textNome);
        nome = textNome.getText().toString();

        Bitmap bitmapEstampa = ((BitmapDrawable)imageEstampa.getDrawable()).getBitmap();
        caminhoEstampa= FileIO.saveImg(getApplicationContext(), bitmapEstampa,nome+"ESTP");

        View view = findViewById(R.id.frameLayout);
        Bitmap bitmapModelo = Bitmap.createBitmap(
                view.getWidth(),
                view.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvasModelo = new Canvas(bitmapModelo);
        view.draw(canvasModelo);
        caminhoModelo = FileIO.saveImg(getApplicationContext(), bitmapModelo, nome);

        cor = "ffffff";
        sexo = "M";

        if (spinnerProd.getSelectedItemPosition() == 0)
            idSubgrupo = 3;
        else if (spinnerProd.getSelectedItemPosition() == 1)
            idSubgrupo = 4;
        else
            idSubgrupo = 1;

        idCliente = LoggedInUser.getId();

        final Produto produto = new Produto(nome, caminhoEstampa, caminhoModelo, cor,
                sexo, idSubgrupo, idCliente);

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Até aqui tudo certo!");
                Produto result = new ProdutoController().cadastrarProduto(produto);

                if (result != null) {
                    finish();
                    CompraActivity.produto = result;
                    goTo(CompraActivity.class);
                } else {
                    Toast.makeText(CustomActivity.this,
                            "Erro ao cadastrar produto.", Toast.LENGTH_SHORT).show();
                }
            }
        }).start();
    }
}
